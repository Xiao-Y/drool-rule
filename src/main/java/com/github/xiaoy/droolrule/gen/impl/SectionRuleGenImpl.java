package com.github.xiaoy.droolrule.gen.impl;

import com.github.xiaoy.droolrule.gen.RuleGen;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Component
public class SectionRuleGenImpl implements RuleGen {


    @Override
    public List<Map<String, Object>> prepareData() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("houseType", "1");
        data1.put("houseTypeName", "住宅");
        data1.put("numMin", 1);
        data1.put("numMax", 10);
        data1.put("commission", new BigDecimal(0.03).setScale(2, RoundingMode.HALF_UP));
        data1.put("fixedAmount", new BigDecimal(3000));

        Map<String, Object> data11 = new HashMap<>();
        data11.put("houseType", "1");
        data11.put("houseTypeName", "住宅");
        data11.put("numMin", 11);
        data11.put("numMax", 20);
        data11.put("commission", new BigDecimal(0.035).setScale(2, RoundingMode.HALF_UP));
        data11.put("fixedAmount", new BigDecimal(5000));

        Map<String, Object> data12 = new HashMap<>();
        data12.put("houseType", "1");
        data12.put("houseTypeName", "住宅");
        data12.put("numMin", 21);
        data12.put("numMax", Integer.MAX_VALUE);
        data12.put("commission", new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP));
        data12.put("fixedAmount", new BigDecimal(8000));

        Map<String, Object> data21 = new HashMap<>();
        data21.put("houseType", "2");
        data21.put("houseTypeName", "小于150w 商铺");
        data21.put("numMin", 1);
        data21.put("numMax", 3);
        data21.put("commission", new BigDecimal(0.07).setScale(2, RoundingMode.HALF_UP));
        data21.put("fixedAmount", new BigDecimal(15000));

        Map<String, Object> data22 = new HashMap<>();
        data22.put("houseType", "2");
        data22.put("houseTypeName", "小于150w 商铺");
        data22.put("numMin", 4);
        data22.put("numMax", Integer.MAX_VALUE);
        data22.put("commission", new BigDecimal(0.09).setScale(2, RoundingMode.HALF_UP));
        data22.put("fixedAmount", new BigDecimal(25000));

        Map<String, Object> data31 = new HashMap<>();
        data31.put("houseType", "4");
        data31.put("houseTypeName", "150w 以上商铺");
        data31.put("numMin", 11);
        data31.put("numMax", Integer.MAX_VALUE);
        data31.put("commission", new BigDecimal(0.16).setScale(2, RoundingMode.HALF_UP));
        data31.put("fixedAmount", new BigDecimal(28000));

        Map<String, Object> data32 = new HashMap<>();
        data32.put("houseType", "4");
        data32.put("houseTypeName", "150w 以上商铺");
        data32.put("numMin", 1);
        data32.put("numMax", 20);
        data32.put("commission", new BigDecimal(0.19).setScale(2, RoundingMode.HALF_UP));
        data32.put("fixedAmount", new BigDecimal(48000));

        return Arrays.asList(data1, data11, data12,data21,data22, data32, data31);


    }

    @Override
    public String getTemplateFileName() {
        return "droolRule/templates/section.drt";
    }
}
