package com.github.xiaoy.droolrule.init;

import com.github.xiaoy.droolrule.entity.RuleInfo;
import com.github.xiaoy.droolrule.service.RuleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 规则加载器
 *
 * @author billow
 * @Date 2021/1/10 16:37
 **/
@Slf4j
@Component
public class InitRuleLoader implements ApplicationRunner {

    /**
     * key:kcontainerName,value:KieContainer，每个场景对应一个KieContainer
     */
    private final ConcurrentMap<String, KieContainer> kieContainerMap = new ConcurrentHashMap<>();

    @Autowired
    private RuleInfoService ruleInfoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("************** start--- 加载规则数据*************");
        reloadAll();
        log.info("************** end --- 加载规则数据*************");
    }

    /**
     * 构造kcontainerName
     *
     * @param sceneId 场景ID
     * @return kcontainerName
     */
    private String buildKcontainerName(long sceneId) {
        return "kcontainer_" + sceneId;
    }

    /**
     * 构造kbaseName
     *
     * @param sceneId 场景ID
     * @return kbaseName
     */
    private String buildKbaseName(long sceneId) {
        return "kbase_" + sceneId;
    }

    /**
     * 构造ksessionName
     *
     * @param sceneId 场景ID
     * @return ksessionName
     */
    private String buildKsessionName(long sceneId) {
        return "ksession_" + sceneId;
    }

    public KieContainer getKieContainerBySceneId(long sceneId) {
        return kieContainerMap.get(buildKcontainerName(sceneId));
    }

    /**
     * 重新加载所有规则
     */
    public void reloadAll() {
        Map<Long, List<RuleInfo>> sceneId2RuleInfoListMap = ruleInfoService.getRuleInfoListMap();
        for (Map.Entry<Long, List<RuleInfo>> entry : sceneId2RuleInfoListMap.entrySet()) {
            long sceneId = entry.getKey();
            reload(sceneId, entry.getValue());
        }
        log.info("reload all success");
    }

    /**
     * 重新加载给定场景下的规则
     *
     * @param sceneId 场景ID
     */
    public void reload(Long sceneId) {
        List<RuleInfo> ruleInfos = ruleInfoService.getRuleInfoListBySceneId(sceneId);
        reload(sceneId, ruleInfos);
        log.info("reload success");
    }

    /**
     * 重新加载给定场景给定规则列表，对应一个kmodule
     *
     * @param sceneId   场景ID
     * @param ruleInfos 规则列表
     */
    private void reload(long sceneId, List<RuleInfo> ruleInfos) {
        KieServices kieServices = KieServices.get();
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        KieBaseModel kieBaseModel = kieModuleModel.newKieBaseModel(buildKbaseName(sceneId));
        kieBaseModel.setDefault(true);
        kieBaseModel.addPackage(MessageFormat.format("rules.scene_{0}", String.valueOf(sceneId)));
        kieBaseModel.newKieSessionModel(buildKsessionName(sceneId));

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for (RuleInfo ruleInfo : ruleInfos) {
            log.info("正在加载规则 id:{},sceneId:{}", ruleInfo.getId(), ruleInfo.getSceneId());
            String fullPath = MessageFormat.format("src/main/resources/rules/scene_{0}/rule_{1}.drl",
                    String.valueOf(sceneId), String.valueOf(ruleInfo.getId()));
            kieFileSystem.write(fullPath, ruleInfo.getRuleContent());
        }
        kieFileSystem.writeKModuleXML(kieModuleModel.toXML());

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            log.info("rule error:{}", results.getMessages());
            throw new IllegalStateException("rule error");
        }

        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        kieContainerMap.put(buildKcontainerName(sceneId), kieContainer);
    }
}