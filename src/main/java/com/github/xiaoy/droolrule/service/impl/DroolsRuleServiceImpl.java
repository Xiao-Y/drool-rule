package com.github.xiaoy.droolrule.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.xiaoy.droolrule.constant.TemplateGenEnum;
import com.github.xiaoy.droolrule.gen.RuleGen;
import com.github.xiaoy.droolrule.init.InitRuleLoader;
import com.github.xiaoy.droolrule.service.DroolsRuleService;
import com.github.xiaoy.droolrule.service.RuleInfoService;
import com.github.xiaoy.droolrule.utils.KieSessionHelper;
import com.github.xiaoy.droolrule.vo.RuleInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规则api 服务
 *
 * @author liuyongtao
 * @since 2021-1-8 16:18
 */
@Slf4j
@Service
public class DroolsRuleServiceImpl implements DroolsRuleService {

    @Autowired
    private KieSessionHelper kieSessionHelper;

    @Autowired
    private Map<String, RuleGen> ruleGenMap;

    @Autowired
    private Map<String, RuleInfoService> ruleInfoServiceMap;

    @Autowired
    private InitRuleLoader initRuleLoader;

    @Override
    public RuleInfoVo genRule(String tempCode, Long bizId) throws Exception {
        log.info("fire tempCode：{},bizId：{}", tempCode, bizId);
        // 查询生成规则的参数
        String ruleInfoServiceName = TemplateGenEnum.getRuleInfoServiceByTempCode(tempCode);
        RuleInfoService ruleInfoService = ruleInfoServiceMap.get(ruleInfoServiceName);
        List ruleParam = ruleInfoService.findGenerateRuleParam(bizId);
        // 模板转换规则
        String ruleGenName = TemplateGenEnum.getRuleGenByTempCode(tempCode);
        RuleGen ruleGen = ruleGenMap.get(ruleGenName);
        String drlString = ruleGen.generateRule(ruleParam);
        // 生成新的分组的规则
        RuleInfoVo info = new RuleInfoVo();
        info.setId(bizId);
        info.setRuleContent(drlString);
        ruleInfoService.updateRule(info);
        return info;
    }

    @Override
    public List<RuleInfoVo> getRuleInfoListByGroupId(String tempCode, Long groupId) {
        log.info("fire tempCode：{},groupId：{}", tempCode, groupId);
        String ruleInfoServiceName = TemplateGenEnum.getRuleInfoServiceByTempCode(tempCode);
        RuleInfoService ruleInfoService = ruleInfoServiceMap.get(ruleInfoServiceName);
        return ruleInfoService.findRuleListByGroupId(groupId);
    }

    @Override
    public Map<Long, List<RuleInfoVo>> getRuleInfoListMap() {
        List<RuleInfoVo> allRuleInfoVos = new ArrayList<>();
        // 查询出规则
        for (Map.Entry<String, RuleInfoService> entry : ruleInfoServiceMap.entrySet()) {
            List<RuleInfoVo> ruleList = entry.getValue().findRuleList();
            if (CollectionUtils.isEmpty(ruleList)) {
                continue;
            }
            allRuleInfoVos.addAll(ruleList);
        }
        // 重新构建
        Map<Long, List<RuleInfoVo>> groupId2RuleInfoListMap = new HashMap<>();
        for (RuleInfoVo ruleInfoVo : allRuleInfoVos) {
            List<RuleInfoVo> ruleInfoVos = groupId2RuleInfoListMap.computeIfAbsent(ruleInfoVo.getGroupId(), k -> new ArrayList<>());
            ruleInfoVos.add(ruleInfoVo);
        }
        return groupId2RuleInfoListMap;
    }

    @Override
    public Object fire(String tempCode, Long groupId, Map<String, Object> param) {
        log.info("fire tempCode：{},groupId：{},param：{}", tempCode, groupId, param);
        Class<?> paramClass = TemplateGenEnum.getParamClassByTempCode(tempCode);
        Object obj = JSON.parseObject(JSON.toJSONString(param), paramClass);
        KieSession kieSession = kieSessionHelper.getKieSessionByGroupId(tempCode, groupId);
        kieSession.insert(obj);
        int size = kieSession.fireAllRules();
        log.info("匹配到 {} 个规则", size);
        kieSession.dispose();
        return obj;
    }

    @Override
    public void delRule(Long groupId) {
        initRuleLoader.delKieContainerByGroupId(groupId);
    }
}
