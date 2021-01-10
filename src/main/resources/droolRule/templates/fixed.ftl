package com.github.xiaoy.droolrule.fixed;
import com.github.xiaoy.droolrule.param.FixedSectionParam;
import java.math.BigDecimal;
import java.math.RoundingMode;

global org.slf4j.Logger logger

<#list root as rule>
rule "${(rule.commissionType == "1") ? string("固定","范围")}-${rule.houseTypeName}-${rule.commission}"
    when
        <#if rule.jumpPoint == "1">
        $p : FixedSectionParam(houseType == "${rule.houseType}"
            && salesAmount >= ${rule.salesAmount?c});
        </#if>
        <#if rule.jumpPoint == "2">
         $p : FixedSectionParam(houseType == "${rule.houseType}"
            && ${rule.numMin} <= salesNum
            && salesNum <= ${rule.numMax});
        </#if>
    then
        BigDecimal sumAmount = BigDecimal.ZERO;
        <#if rule.commissionType == "2">
        sumAmount = $p.getAmount().multiply(new BigDecimal(${rule.commission})).add(new BigDecimal(${rule.fixedAmount?c}));
        </#if>
        <#if rule.commissionType == "1">
        sumAmount = new BigDecimal(${rule.commission?c}).add(new BigDecimal(${rule.fixedAmount?c}));
        </#if>
        $p.getResult().add(sumAmount.setScale(2, RoundingMode.HALF_UP));
        logger.info("固定-${rule.houseTypeName}-${rule.commission}：{}",$p);
end
</#list>