package com.github.xiaoy.droolrule.gen.impl;

import com.github.xiaoy.droolrule.gen.RuleGen;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BaseRuleGen extends RuleGen {

    @Override
    public List<Map<String, Object>> prepareData(Object obj) {
        throw new RuntimeException("无法设置参数");
    }

    @Override
    public String getTemplateFileName() {
        throw new RuntimeException("无法设置模板名称");
    }
}
