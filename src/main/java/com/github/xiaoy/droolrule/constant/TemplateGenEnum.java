package com.github.xiaoy.droolrule.constant;


import com.github.xiaoy.droolrule.param.DeveloperSettlementParam;

import static com.github.xiaoy.droolrule.constant.DroolsRuleGenCst.DEVELOPER_SETTLEMENT_RULE_GEN;
import static com.github.xiaoy.droolrule.constant.DroolsRuleGenCst.DEVELOPER_SETTLEMENT_RULE_SERVICE;

/**
 * 规则相关参数配置
 *
 * @author liuyongtao
 * @Date 2021/1/10 20:09
 **/
public enum TemplateGenEnum {
    /**
     * 开发商结算相关
     */
    DeveloperSettlement("1", DEVELOPER_SETTLEMENT_RULE_GEN, DEVELOPER_SETTLEMENT_RULE_SERVICE, DeveloperSettlementParam.class);

    /**
     * 模板类型
     */
    private String tempCode;
    /**
     * 模板生成器
     */
    private String ruleGen;
    /**
     * 规则查询服务
     */
    private String ruleInfoService;
    /**
     * 规则触发类型
     */
    private Class<?> paramClass;

    /**
     * 规则相关参数配置
     *
     * @param tempCode        模板类型
     * @param ruleGen         模板生成器
     * @param ruleInfoService 规则查询服务
     * @param paramClass      规则触发类型
     */
    TemplateGenEnum(String tempCode, String ruleGen, String ruleInfoService, Class<?> paramClass) {
        this.tempCode = tempCode;
        this.ruleGen = ruleGen;
        this.ruleInfoService = ruleInfoService;
        this.paramClass = paramClass;
    }

    public static String getRuleGenByTempCode(String tempCode) {
        TemplateGenEnum[] values = TemplateGenEnum.values();
        for (TemplateGenEnum m : values) {
            if (m.tempCode.equals(tempCode)) {
                return m.ruleGen;
            }
        }
        throw new RuntimeException("没有找到对应的规则生成器,tempCode:" + tempCode);
    }

    public static Class<?> getParamClassByTempCode(String tempCode) {
        TemplateGenEnum[] values = TemplateGenEnum.values();
        for (TemplateGenEnum m : values) {
            if (m.tempCode.equals(tempCode)) {
                return m.paramClass;
            }
        }
        throw new RuntimeException("没有找到对应的规则参数匹配器,tempCode:" + tempCode);
    }

    public static String getRuleInfoServiceByTempCode(String tempCode) {
        TemplateGenEnum[] values = TemplateGenEnum.values();
        for (TemplateGenEnum m : values) {
            if (m.tempCode.equals(tempCode)) {
                return m.ruleInfoService;
            }
        }
        throw new RuntimeException("没有找到对应的规则查询服务,tempCode:" + tempCode);
    }
}
