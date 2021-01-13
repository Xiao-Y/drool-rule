package com.github.xiaoy.droolrule.constant;


import com.github.xiaoy.droolrule.gen.impl.DeveloperSettlementRuleGenImpl;
import com.github.xiaoy.droolrule.service.impl.DeveloperSettlementRuleServiceImpl;

/**
 * 规则生成器
 *
 * @author liuyongtao
 */
public class DroolsRuleGenCst {
    /**
     * 开发商模板生成器
     */
    protected final static String DEVELOPER_SETTLEMENT_RULE_GEN = toLowerCaseFirstOne(DeveloperSettlementRuleGenImpl.class.getSimpleName());
    /**
     * 开发商规则服务
     */
    protected final static String DEVELOPER_SETTLEMENT_RULE_SERVICE = toLowerCaseFirstOne(DeveloperSettlementRuleServiceImpl.class.getSimpleName());

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
