package com.github.xiaoy.droolrule.gen.impl;

import com.github.xiaoy.droolrule.exception.DroolsRuleException;
import com.github.xiaoy.droolrule.gen.RuleGen;
import com.github.xiaoy.droolrule.gen.param.DeveloperSettlementTemp;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DeveloperSettlementRuleGenImpl extends RuleGen<DeveloperSettlementTemp> {

    @Override
    public List<DeveloperSettlementTemp> prepareData(Object obj) {
        // 以下数据从数据库中查询拼装
        // 固定-固定-start
        DeveloperSettlementTemp temp2 = new DeveloperSettlementTemp();
        temp2.setJumpPoint("1");
        temp2.setHouseType("1");
        temp2.setHouseTypeName("住宅");
        temp2.setCommissionType("1");
        temp2.setSalesAmount(new BigDecimal(500));
        temp2.setCommission(new BigDecimal(100));
        temp2.setFixedAmount(new BigDecimal(30));
        temp2.setStartDate("2021-01-11");
        temp2.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp3 = new DeveloperSettlementTemp();
        temp3.setJumpPoint("1");
        temp3.setHouseType("2");
        temp3.setHouseTypeName("小于150w商铺");
        temp3.setCommissionType("1");
        temp3.setSalesAmount(new BigDecimal(1000));
        temp3.setCommission(new BigDecimal(1000));
        temp3.setFixedAmount(new BigDecimal(50));
        temp3.setStartDate("2021-01-11");
        temp3.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp4 = new DeveloperSettlementTemp();
        temp4.setJumpPoint("1");
        temp4.setHouseType("3");
        temp4.setHouseTypeName("150-300w商铺");
        temp4.setCommissionType("1");
        temp4.setSalesAmount(new BigDecimal(2000));
        temp4.setCommission(new BigDecimal(1500));
        temp4.setFixedAmount(new BigDecimal(100));
        temp4.setStartDate("2021-01-11");
        temp4.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp5 = new DeveloperSettlementTemp();
        temp5.setJumpPoint("1");
        temp5.setHouseType("4");
        temp5.setHouseTypeName("300w以上商铺");
        temp5.setCommissionType("1");
        temp5.setSalesAmount(new BigDecimal(2500));
        temp5.setCommission(new BigDecimal(2000));
        temp5.setFixedAmount(new BigDecimal(150));
        temp5.setStartDate("2021-01-11");
        temp5.setEndDate("2021-01-18");
        // 固定-固定-end


        // 固定-点数-start
        DeveloperSettlementTemp temp6 = new DeveloperSettlementTemp();
        temp6.setJumpPoint("1");
        temp6.setHouseType("1");
        temp6.setHouseTypeName("住宅");
        temp6.setCommissionType("2");
        temp6.setSalesAmount(new BigDecimal(500));
        temp6.setCommission(new BigDecimal(0.02).setScale(2, RoundingMode.HALF_UP));
        temp6.setFixedAmount(new BigDecimal(30));
        temp6.setStartDate("2021-01-11");
        temp6.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp7 = new DeveloperSettlementTemp();
        temp7.setJumpPoint("1");
        temp7.setHouseType("2");
        temp7.setHouseTypeName("小于150w商铺");
        temp7.setCommissionType("2");
        temp7.setSalesAmount(new BigDecimal(1000));
        temp7.setCommission(new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP));
        temp7.setFixedAmount(new BigDecimal(50));
        temp7.setStartDate("2021-01-11");
        temp7.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp8 = new DeveloperSettlementTemp();
        temp8.setJumpPoint("1");
        temp8.setHouseType("3");
        temp8.setHouseTypeName("150-300w商铺");
        temp8.setCommissionType("2");
        temp8.setSalesAmount(new BigDecimal(2000));
        temp8.setCommission(new BigDecimal(0.08).setScale(2, RoundingMode.HALF_UP));
        temp8.setFixedAmount(new BigDecimal(100));
        temp8.setStartDate("2021-01-11");
        temp8.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp9 = new DeveloperSettlementTemp();
        temp9.setJumpPoint("1");
        temp9.setHouseType("4");
        temp9.setHouseTypeName("300w以上商铺");
        temp9.setCommissionType("1");
        temp9.setSalesAmount(new BigDecimal(2500));
        temp9.setCommission(new BigDecimal(0.1).setScale(2, RoundingMode.HALF_UP));
        temp9.setFixedAmount(new BigDecimal(150));
        temp9.setStartDate("2021-01-11");
        temp9.setEndDate("2021-01-18");
        // 固定-点数-end

        // 范围-固定-start
        DeveloperSettlementTemp temp21 = new DeveloperSettlementTemp();
        temp21.setJumpPoint("2");
        temp21.setHouseType("1");
        temp21.setHouseTypeName("住宅");
        temp21.setNumMax(10);
        temp21.setCommissionType("1");
        temp21.setCommission(new BigDecimal(100));
        temp21.setFixedAmount(new BigDecimal(30));
        temp21.setStartDate("2021-01-11");
        temp21.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp31 = new DeveloperSettlementTemp();
        temp31.setJumpPoint("2");
        temp31.setHouseType("2");
        temp31.setHouseTypeName("小于150w商铺");
        temp31.setNumMin(11);
        temp31.setNumMax(20);
        temp31.setCommissionType("1");
        temp31.setCommission(new BigDecimal(1000));
        temp31.setFixedAmount(new BigDecimal(50));
        temp31.setStartDate("2021-01-11");
        temp31.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp41 = new DeveloperSettlementTemp();
        temp41.setJumpPoint("2");
        temp41.setHouseType("3");
        temp41.setHouseTypeName("150-300w商铺");
        temp41.setNumMin(21);
        temp41.setNumMax(30);
        temp41.setCommissionType("1");
        temp41.setCommission(new BigDecimal(1500));
        temp41.setFixedAmount(new BigDecimal(100));
        temp41.setStartDate("2021-01-11");
        temp41.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp51 = new DeveloperSettlementTemp();
        temp51.setJumpPoint("2");
        temp51.setHouseType("4");
        temp51.setHouseTypeName("300w以上商铺");
        temp51.setNumMin(21);
        temp51.setCommissionType("1");
        temp51.setCommission(new BigDecimal(2000));
        temp51.setFixedAmount(new BigDecimal(150));
        temp51.setStartDate("2021-01-11");
        temp51.setEndDate("2021-01-18");
        // 范围-固定-end

        // 范围-点数-start
        DeveloperSettlementTemp temp61 = new DeveloperSettlementTemp();
        temp61.setJumpPoint("2");
        temp61.setHouseType("1");
        temp61.setHouseTypeName("住宅");
        temp61.setNumMax(10);
        temp61.setCommissionType("2");
        temp61.setCommission(new BigDecimal(0.02).setScale(2, RoundingMode.HALF_UP));
        temp61.setFixedAmount(new BigDecimal(30));
        temp61.setStartDate("2021-01-11");
        temp61.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp71 = new DeveloperSettlementTemp();
        temp71.setJumpPoint("2");
        temp71.setHouseType("2");
        temp71.setHouseTypeName("小于150w商铺");
        temp71.setNumMin(11);
        temp71.setNumMax(20);
        temp71.setCommissionType("2");
        temp71.setCommission(new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP));
        temp71.setFixedAmount(new BigDecimal(50));
        temp71.setStartDate("2021-01-11");
        temp71.setEndDate("2021-01-18");

        DeveloperSettlementTemp temp81 = new DeveloperSettlementTemp();
        temp81.setJumpPoint("2");
        temp81.setHouseType("3");
        temp81.setHouseTypeName("150-300w商铺");
        temp81.setNumMin(21);
        temp81.setNumMax(30);
        temp81.setCommissionType("2");
        temp81.setCommission(new BigDecimal(0.08).setScale(2, RoundingMode.HALF_UP));
        temp81.setFixedAmount(new BigDecimal(100));
        temp81.setStartDate("2021-01-11");
        temp81.setEndDate("2021-01-18");
        DeveloperSettlementTemp temp91 = new DeveloperSettlementTemp();
        temp91.setJumpPoint("2");
        temp91.setHouseType("4");
        temp91.setHouseTypeName("300w以上商铺");
        temp91.setNumMin(30);
        temp91.setCommissionType("1");
        temp91.setCommission(new BigDecimal(0.1).setScale(2, RoundingMode.HALF_UP));
        temp91.setFixedAmount(new BigDecimal(150));
        temp91.setStartDate("2021-01-11");
        temp91.setEndDate("2021-01-18");
        // 范围-点数-end
        return Arrays.asList(temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9,
                temp21, temp31, temp41, temp51, temp61, temp71, temp81, temp91);
    }

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
