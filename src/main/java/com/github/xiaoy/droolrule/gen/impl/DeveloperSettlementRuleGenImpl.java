package com.github.xiaoy.droolrule.gen.impl;

import com.github.xiaoy.droolrule.exception.DroolsRuleException;
import com.github.xiaoy.droolrule.gen.RuleGen;
import com.github.xiaoy.droolrule.gen.param.DeveloperSettlementTemp;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 开发商规则生成器
 *
 * @author liuyongtao
 */
@Component
public class DeveloperSettlementRuleGenImpl extends RuleGen<DeveloperSettlementTemp> {

    @Override
    protected void checkTempData(List<DeveloperSettlementTemp> data) {
        if (CollectionUtils.isEmpty(data)) {
            throw DroolsRuleException.error("规则模板数据不能为空");
        }

        for (DeveloperSettlementTemp temp : data) {
            log.info("校验的数据：{}", temp);
            Assert.notNull(temp.getJumpPoint(), "JumpPoint 不能为空");
            Assert.notNull(temp.getHouseType(), "HouseType 不能为空");
            Assert.notNull(temp.getHouseTypeName(), "HouseTypeName 不能为空");
            Assert.notNull(temp.getCommissionType(), "CommissionType 不能为空");
            Assert.notNull(temp.getStartDate(), "StartDate 不能为空");
            Assert.notNull(temp.getEndDate(), "EndDate 不能为空");
        }
    }

    @Override
    public String getTemplateFileName() {
        return "DeveloperSettlement.ftl";
    }
}
