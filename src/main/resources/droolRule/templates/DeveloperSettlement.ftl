package DeveloperSettlementParam;

import com.github.xiaoy.droolrule.param.DeveloperSettlementParam;
import java.math.BigDecimal;
import java.math.RoundingMode;

global org.slf4j.Logger logger

<#list root as rule>
rule "${(rule.jumpPoint == "1") ? string("固定","范围")}-${(rule.commissionType == "1") ? string("固定","点数")}-${rule.houseTypeName}-${rule.commission}"
    when
        <#if rule.jumpPoint == "1">
        $p : DeveloperSettlementParam(jumpPoint == "${rule.jumpPoint}"
            && commissionType == "${rule.commissionType}"
            && houseType == "${rule.houseType}"
            && salesAmount >= ${rule.salesAmount?c});
        </#if>
        <#if rule.jumpPoint == "2">
         $p : DeveloperSettlementParam(jumpPoint == "${rule.jumpPoint}"
            && commissionType == "${rule.commissionType}"
            && houseType == "${rule.houseType}"
            && ${rule.numMin?c} <= salesNum
            && salesNum <= ${rule.numMax?c});
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
        logger.info("${(rule.jumpPoint == "1") ? string("固定","范围")}-${(rule.commissionType == "1") ? string("固定","点数")}-${rule.houseTypeName}-${rule.commission}：{}",$p);
end

</#list>