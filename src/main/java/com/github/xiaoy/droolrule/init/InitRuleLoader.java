package com.github.xiaoy.droolrule.init;

import com.github.xiaoy.droolrule.vo.RuleInfoVo;
import com.github.xiaoy.droolrule.service.DroolsRuleService;
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
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 规则加载器
 *
 * @author liuyongtao
 * @Date 2021/1/10 16:37
 **/
@Slf4j
@Component
public class InitRuleLoader implements ApplicationRunner {

    /**
     * key:kcontainerName,value:KieContainer，每个分组对应一个KieContainer
     */
    private final ConcurrentMap<String, KieContainer> kieContainerMap = new ConcurrentHashMap<>();

    @Autowired
    private DroolsRuleService droolsRuleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("************** start--- 加载规则数据*************");
        reloadAll();
        log.info("************** end --- 加载规则数据*************");
    }

    /**
     * 构造kcontainerName
     *
     * @param groupId 分组ID
     * @return kcontainerName
     */
    private String buildKcontainerName(Long groupId) {
        return "kcontainer_" + groupId;
    }

    /**
     * 构造kbaseName
     *
     * @param groupId 分组ID
     * @return kbaseName
     */
    private String buildKbaseName(Long groupId) {
        return "kbase_" + groupId;
    }

    /**
     * 构造ksessionName
     *
     * @param groupId 分组ID
     * @return ksessionName
     */
    private String buildKsessionName(Long groupId) {
        return "ksession_" + groupId;
    }

    /**
     * 获取 KieContainer
     *
     * @param groupId 分组ID
     * @return KieContainer
     */
    public KieContainer getKieContainerByGroupId(Long groupId) {
        return kieContainerMap.get(buildKcontainerName(groupId));
    }

    /**
     * 删除 KieContainer
     *
     * @param groupId 分组ID
     * @return KieContainer
     */
    public KieContainer delKieContainerByGroupId(Long groupId) {
        return kieContainerMap.remove(buildKcontainerName(groupId));
    }

    /**
     * 重新加载所有规则
     */
    public void reloadAll() {
        Map<Long, List<RuleInfoVo>> groupId2RuleInfoListMap = droolsRuleService.getRuleInfoListMap();
        for (Map.Entry<Long, List<RuleInfoVo>> entry : groupId2RuleInfoListMap.entrySet()) {
            long groupId = entry.getKey();
            reload(groupId, entry.getValue());
        }
        log.info("reload all success");
    }

    /**
     * 重新加载给定分组下的规则
     *
     * @param groupId 分组ID
     */
    public boolean reload(String tempCode, Long groupId) {
        List<RuleInfoVo> ruleInfoVos = droolsRuleService.getRuleInfoListByGroupId(tempCode, groupId);
        boolean reload = reload(groupId, ruleInfoVos);
        log.info("reload success:{}", reload);
        return reload;
    }

    /**
     * 重新加载给定分组给定规则列表，对应一个kmodule
     *
     * @param groupId   分组ID
     * @param ruleInfoVos 规则列表
     */
    private boolean reload(Long groupId, List<RuleInfoVo> ruleInfoVos) {
        if (CollectionUtils.isEmpty(ruleInfoVos)) {
            log.info("规则组为空，不添加");
            return false;
        }
        KieServices kieServices = KieServices.get();
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        KieBaseModel kieBaseModel = kieModuleModel.newKieBaseModel(buildKbaseName(groupId));
        kieBaseModel.setDefault(true);
        kieBaseModel.addPackage(MessageFormat.format("rules.scene_{0}", String.valueOf(groupId)));
        kieBaseModel.newKieSessionModel(buildKsessionName(groupId));

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for (RuleInfoVo ruleInfoVo : ruleInfoVos) {
            log.info("正在加载规则 id:{},groupId:{}", ruleInfoVo.getId(), ruleInfoVo.getGroupId());
            String fullPath = MessageFormat.format("src/main/resources/rules/scene_{0}/rule_{1}.drl",
                    String.valueOf(groupId), String.valueOf(ruleInfoVo.getId()));
            kieFileSystem.write(fullPath, ruleInfoVo.getRuleContent());
        }
        kieFileSystem.writeKModuleXML(kieModuleModel.toXML());

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            log.info("rule error:{}", results.getMessages());
            throw new IllegalStateException("rule error");
        }

        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        kieContainerMap.put(buildKcontainerName(groupId), kieContainer);
        return true;
    }
}