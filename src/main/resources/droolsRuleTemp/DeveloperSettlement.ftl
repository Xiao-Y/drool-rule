package DeveloperSettlementParam;

import com.github.xiaoy.droolrule.param.DeveloperSettlementParam;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

global org.slf4j.Logger logger

// 处理时间的function
function LocalDate checkDate(String et){
    return LocalDate.parse(et, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}
<#list root as rule>

rule "${(rule.jumpPoint == "1") ? string("固定","范围")}-${(rule.commissionType == "1") ? string("固定","点数")}-${rule.houseTypeName}-${rule.commission}"
    when
        $p : DeveloperSettlementParam(
            jumpPoint == "${rule.jumpPoint}"
            && commissionType == "${rule.commissionType}"
            && houseType == "${rule.houseType}"
            && checkDate(assessmentDate).compareTo(checkDate("${rule.startDate}")) >= 0
            && checkDate(assessmentDate).compareTo(checkDate("${rule.endDate}")) <= 0
        <#if rule.jumpPoint == "1">
            && salesAmount >= ${rule.salesAmount?c}
        </#if>
        <#if rule.jumpPoint == "2">
            && ${rule.numMin?c} <= salesNum
            && salesNum <= ${rule.numMax?c}
        </#if>
        );
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