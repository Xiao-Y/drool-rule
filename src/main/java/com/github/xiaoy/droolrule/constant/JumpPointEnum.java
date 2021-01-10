package com.github.xiaoy.droolrule.constant;

/**
 * 跳点类型
 *
 * @author billow
 * @Date 2021/1/10 20:09
 **/
public enum JumpPointEnum {
    Fix("1", DroolsRuleGenCst.FIXED_RULE_GEN),
    Section("2", DroolsRuleGenCst.SECTION_RULE_GEN);

    // 跳点类型
    private String jumpPoint;
    // 模板生成器
    private String ruleGen;

    JumpPointEnum(String jumpPoint, String ruleGen) {
        this.jumpPoint = jumpPoint;
        this.ruleGen = ruleGen;
    }

    public static String getRuleGenByJumpPoint(String jumpPoint) {
        JumpPointEnum[] values = JumpPointEnum.values();
        for (JumpPointEnum m : values) {
            if (m.jumpPoint.equals(jumpPoint)) {
                return m.ruleGen;
            }
        }
        return jumpPoint;
    }
}
