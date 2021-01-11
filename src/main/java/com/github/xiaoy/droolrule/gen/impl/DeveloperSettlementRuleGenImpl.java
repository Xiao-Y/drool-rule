package com.github.xiaoy.droolrule.gen.impl;

import com.github.xiaoy.droolrule.gen.RuleGen;
import com.github.xiaoy.droolrule.gen.param.DeveloperSettlementTemp;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Component
public class DeveloperSettlementRuleGenImpl extends RuleGen<DeveloperSettlementTemp> {

    @Override
    public List<DeveloperSettlementTemp> prepareData(Object obj) {
        // 固定-固定-start
        DeveloperSettlementTemp temp2 = new DeveloperSettlementTemp();
        temp2.setJumpPoint("1");
        temp2.setHouseType("1");
        temp2.setHouseTypeName("住宅");
        temp2.setCommissionType("1");
        temp2.setSalesAmount(new BigDecimal(500));
        temp2.setCommission(new BigDecimal(100));
        temp2.setFixedAmount(new BigDecimal(30));

        DeveloperSettlementTemp temp3 = new DeveloperSettlementTemp();
        temp3.setJumpPoint("1");
        temp3.setHouseType("2");
        temp3.setHouseTypeName("小于150w商铺");
        temp3.setCommissionType("1");
        temp3.setSalesAmount(new BigDecimal(1000));
        temp3.setCommission(new BigDecimal(1000));
        temp3.setFixedAmount(new BigDecimal(50));

        DeveloperSettlementTemp temp4 = new DeveloperSettlementTemp();
        temp4.setJumpPoint("1");
        temp4.setHouseType("3");
        temp4.setHouseTypeName("150-300w商铺");
        temp4.setCommissionType("1");
        temp4.setSalesAmount(new BigDecimal(2000));
        temp4.setCommission(new BigDecimal(1500));
        temp4.setFixedAmount(new BigDecimal(100));

        DeveloperSettlementTemp temp5 = new DeveloperSettlementTemp();
        temp5.setJumpPoint("1");
        temp5.setHouseType("4");
        temp5.setHouseTypeName("300w以上商铺");
        temp5.setCommissionType("1");
        temp5.setSalesAmount(new BigDecimal(2500));
        temp5.setCommission(new BigDecimal(2000));
        temp5.setFixedAmount(new BigDecimal(150));
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

        DeveloperSettlementTemp temp7 = new DeveloperSettlementTemp();
        temp7.setJumpPoint("1");
        temp7.setHouseType("2");
        temp7.setHouseTypeName("小于150w商铺");
        temp7.setCommissionType("2");
        temp7.setSalesAmount(new BigDecimal(1000));
        temp7.setCommission(new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP));
        temp7.setFixedAmount(new BigDecimal(50));

        DeveloperSettlementTemp temp8 = new DeveloperSettlementTemp();
        temp8.setJumpPoint("1");
        temp8.setHouseType("3");
        temp8.setHouseTypeName("150-300w商铺");
        temp8.setCommissionType("2");
        temp8.setSalesAmount(new BigDecimal(2000));
        temp8.setCommission(new BigDecimal(0.08).setScale(2, RoundingMode.HALF_UP));
        temp8.setFixedAmount(new BigDecimal(100));

        DeveloperSettlementTemp temp9 = new DeveloperSettlementTemp();
        temp9.setJumpPoint("1");
        temp9.setHouseType("4");
        temp9.setHouseTypeName("300w以上商铺");
        temp9.setCommissionType("1");
        temp9.setSalesAmount(new BigDecimal(2500));
        temp9.setCommission(new BigDecimal(0.1).setScale(2, RoundingMode.HALF_UP));
        temp9.setFixedAmount(new BigDecimal(150));
        // 固定-点数-end

        // 范围-固定-start
        DeveloperSettlementTemp temp21 = new DeveloperSettlementTemp();
        temp21.setJumpPoint("2");
        temp21.setHouseType("1");
        temp21.setHouseTypeName("住宅");
        temp21.setNumMax(10);
        temp21.setCommissionType("1");
        temp21.setSalesAmount(new BigDecimal(500));
        temp21.setCommission(new BigDecimal(100));
        temp21.setFixedAmount(new BigDecimal(30));

        DeveloperSettlementTemp temp31 = new DeveloperSettlementTemp();
        temp31.setJumpPoint("2");
        temp31.setHouseType("2");
        temp31.setHouseTypeName("小于150w商铺");
        temp31.setNumMin(11);
        temp31.setNumMax(20);
        temp31.setCommissionType("1");
        temp31.setSalesAmount(new BigDecimal(1000));
        temp31.setCommission(new BigDecimal(1000));
        temp31.setFixedAmount(new BigDecimal(50));

        DeveloperSettlementTemp temp41 = new DeveloperSettlementTemp();
        temp41.setJumpPoint("2");
        temp41.setHouseType("3");
        temp41.setHouseTypeName("150-300w商铺");
        temp41.setNumMin(21);
        temp41.setNumMax(30);
        temp41.setCommissionType("1");
        temp41.setSalesAmount(new BigDecimal(2000));
        temp41.setCommission(new BigDecimal(1500));
        temp41.setFixedAmount(new BigDecimal(100));

        DeveloperSettlementTemp temp51 = new DeveloperSettlementTemp();
        temp51.setJumpPoint("2");
        temp51.setHouseType("4");
        temp51.setHouseTypeName("300w以上商铺");
        temp51.setNumMin(21);
        temp51.setCommissionType("1");
        temp51.setSalesAmount(new BigDecimal(2500));
        temp51.setCommission(new BigDecimal(2000));
        temp51.setFixedAmount(new BigDecimal(150));
        // 范围-固定-end

        // 范围-点数-start
        DeveloperSettlementTemp temp61 = new DeveloperSettlementTemp();
        temp61.setJumpPoint("2");
        temp61.setHouseType("1");
        temp61.setHouseTypeName("住宅");
        temp61.setNumMax(10);
        temp61.setCommissionType("2");
        temp61.setSalesAmount(new BigDecimal(500));
        temp61.setCommission(new BigDecimal(0.02).setScale(2, RoundingMode.HALF_UP));
        temp61.setFixedAmount(new BigDecimal(30));

        DeveloperSettlementTemp temp71 = new DeveloperSettlementTemp();
        temp71.setJumpPoint("2");
        temp71.setHouseType("2");
        temp71.setHouseTypeName("小于150w商铺");
        temp71.setNumMin(11);
        temp71.setNumMax(20);
        temp71.setCommissionType("2");
        temp71.setSalesAmount(new BigDecimal(1000));
        temp71.setCommission(new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP));
        temp71.setFixedAmount(new BigDecimal(50));

        DeveloperSettlementTemp temp81 = new DeveloperSettlementTemp();
        temp81.setJumpPoint("2");
        temp81.setHouseType("3");
        temp81.setHouseTypeName("150-300w商铺");
        temp81.setNumMin(21);
        temp81.setNumMax(30);
        temp81.setCommissionType("2");
        temp81.setSalesAmount(new BigDecimal(2000));
        temp81.setCommission(new BigDecimal(0.08).setScale(2, RoundingMode.HALF_UP));
        temp81.setFixedAmount(new BigDecimal(100));

        DeveloperSettlementTemp temp91 = new DeveloperSettlementTemp();
        temp91.setJumpPoint("2");
        temp91.setHouseType("4");
        temp91.setHouseTypeName("300w以上商铺");
        temp91.setNumMin(30);
        temp91.setCommissionType("1");
        temp91.setSalesAmount(new BigDecimal(2500));
        temp91.setCommission(new BigDecimal(0.1).setScale(2, RoundingMode.HALF_UP));
        temp91.setFixedAmount(new BigDecimal(150));
        // 范围-点数-end
        return Arrays.asList(temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9,
                temp21, temp31, temp41, temp51, temp61, temp71, temp81, temp91);
    }

    @Override
    public String getTemplateFileName() {
        return "DeveloperSettlement.ftl";
    }
}
