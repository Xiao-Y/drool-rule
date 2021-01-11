package com.github.xiaoy.droolrule.constant;

/**
 * 跳点类型
 *
 * @author billow
 * @Date 2021/1/10 20:09
 **/
public enum TemplateGenEnum {
    DeveloperSettlement("1", DroolsRuleGenCst.DEVELOPER_SETTLEMENT_RULE_GEN);

    // 跳点类型
    private String tempCode;
    // 模板生成器
    private String ruleGen;

    TemplateGenEnum(String tempCode, String ruleGen) {
        this.tempCode = tempCode;
        this.ruleGen = ruleGen;
    }

    public static String getRuleGenByTempCode(String tempCode) {
        TemplateGenEnum[] values = TemplateGenEnum.values();
        for (TemplateGenEnum m : values) {
            if (m.tempCode.equals(tempCode)) {
                return m.ruleGen;
            }
        }
        return tempCode;
    }
}
