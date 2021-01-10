package com.github.xiaoy.droolrule.gen.impl;

import com.github.xiaoy.droolrule.gen.RuleGen;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FixedRuleGenImpl implements RuleGen {


    @Override
    public List<Map<String, Object>> prepareData() {
        Map<String, Object> data = new HashMap<>();

        data.put("houseType", "1");
        data.put("houseTypeName", "住宅");
        data.put("salesAmount", new BigDecimal(50000000));
        data.put("commission", new BigDecimal(0.03).setScale(2, RoundingMode.HALF_UP));
        data.put("fixedAmount", new BigDecimal(3000));

        Map<String, Object> data2 = new HashMap<>();

        data2.put("houseType", "2");
        data2.put("houseTypeName", "小于150w 商铺");
        data2.put("salesAmount", new BigDecimal(1500000000));
        data2.put("commission", new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP));
        data2.put("fixedAmount", new BigDecimal(5000));

        Map<String, Object> data3 = new HashMap<>();

        data3.put("houseType", "3");
        data3.put("houseTypeName", "150-300w 商铺");
        data3.put("salesAmount", new BigDecimal(300000000));
        data3.put("commission", new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP));
        data3.put("fixedAmount", new BigDecimal(8000));


        Map<String, Object> data4 = new HashMap<>();

        data4.put("houseType", "4");
        data4.put("houseTypeName", "300w 以上商铺");
        data4.put("salesAmount", new BigDecimal(400000000));
        data4.put("commission", new BigDecimal(0.06).setScale(2, RoundingMode.HALF_UP));
        data4.put("fixedAmount", new BigDecimal(18000));
        return Arrays.asList(data, data2, data3, data4);
    }

    @Override
    public String getTemplateFileName() {
        return "droolRule/templates/fixed.drt";
    }
}
