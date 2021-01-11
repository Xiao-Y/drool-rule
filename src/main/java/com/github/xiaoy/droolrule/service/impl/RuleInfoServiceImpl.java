package com.github.xiaoy.droolrule.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.xiaoy.droolrule.constant.TemplateGenEnum;
import com.github.xiaoy.droolrule.entity.RuleInfo;
import com.github.xiaoy.droolrule.gen.RuleGen;
import com.github.xiaoy.droolrule.mapper.RuleInfoMapper;
import com.github.xiaoy.droolrule.service.RuleInfoService;
import com.github.xiaoy.droolrule.utils.KieSessionHelper;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author liuyongtao
 * @since 2021-1-8 16:18
 */
@Slf4j
@Service
public class RuleInfoServiceImpl extends ServiceImpl<RuleInfoMapper, RuleInfo> implements RuleInfoService {

    @Autowired
    private KieSessionHelper kieSessionHelper;

    @Autowired
    private Map<String, RuleGen> ruleGenMap;

    @Override
    public long insertRule(String tempCode, long groupId) throws Exception {
        String ruleGenName = TemplateGenEnum.getRuleGenByTempCode(tempCode);
        RuleGen ruleGen = ruleGenMap.get(ruleGenName);
        if (ruleGen == null) {
            throw new RuntimeException("没有找到对应的模板生成器，tempCode：" + tempCode);
        }
        // 模板转换规则
        String drlString = ruleGen.generateRule(null);

        // 设置当前生成器的其它数据为无效
//        LambdaQueryWrapper<RuleInfo> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(RuleInfo::getRuleGenName, ruleGenName)
//                .eq(RuleInfo::getValidInd, true);
//        RuleInfo info = new RuleInfo();
//        info.setValidInd(false);
//        this.update(info, wrapper);

        // 保存新的到数据库
        RuleInfo ruleInfo = new RuleInfo();
        ruleInfo.setGroupId(groupId);
        ruleInfo.setRuleGenName(ruleGenName);
        ruleInfo.setRuleContent(drlString);
        ruleInfo.setValidInd(true);
        ruleInfo.setIsDelete(false);
        ruleInfo.setCreateTime(new Date());
        ruleInfo.setUpdateTime(new Date());
        this.save(ruleInfo);
        return ruleInfo.getId();
    }

    @Override
    public List<RuleInfo> getRuleInfoListByGroupId(Long groupId) {
        LambdaQueryWrapper<RuleInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RuleInfo::getGroupId, groupId);
        return this.list(wrapper);
    }

    @Override
    public Map<Long, List<RuleInfo>> getRuleInfoListMap() {
        Map<Long, List<RuleInfo>> groupId2RuleInfoListMap = new HashMap<>();
        List<RuleInfo> allRuleInfos = this.list();
        for (RuleInfo ruleInfo : allRuleInfos) {
            List<RuleInfo> ruleInfos = groupId2RuleInfoListMap.computeIfAbsent(ruleInfo.getGroupId(), k -> new ArrayList<>());
            ruleInfos.add(ruleInfo);
        }
        return groupId2RuleInfoListMap;
    }

    @Override
    public void fire(long groupId, Object obj) {
        log.info("匹配参数：{}", JSON.toJSONString(obj));
        KieSession kieSession = kieSessionHelper.getKieSessionByGroupId(groupId);
        kieSession.insert(obj);
        int size = kieSession.fireAllRules();
        log.info("匹配到 {} 个规则", size);
        kieSession.dispose();
    }
}
