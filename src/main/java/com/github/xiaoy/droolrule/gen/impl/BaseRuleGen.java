package com.github.xiaoy.droolrule.gen.impl;

import com.github.xiaoy.droolrule.gen.RuleGen;
import org.springframework.stereotype.Component;

/**
 * 基类生成器
 *
 * @author liuyongtao
 */
@Component
public class BaseRuleGen extends RuleGen {

    @Override
    public String getTemplateFileName() {
        throw new RuntimeException("无法设置模板名称");
    }
}
