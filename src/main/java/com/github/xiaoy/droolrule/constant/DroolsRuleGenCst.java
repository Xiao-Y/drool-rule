package com.github.xiaoy.droolrule.constant;

import com.github.xiaoy.droolrule.gen.impl.DeveloperSettlementRuleGenImpl;

public class DroolsRuleGenCst {

    protected final static String DEVELOPER_SETTLEMENT_RULE_GEN = toLowerCaseFirstOne(DeveloperSettlementRuleGenImpl.class.getSimpleName());

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
