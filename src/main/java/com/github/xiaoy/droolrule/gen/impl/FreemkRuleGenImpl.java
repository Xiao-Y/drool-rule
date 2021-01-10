package com.github.xiaoy.droolrule.gen.impl;

import com.github.xiaoy.droolrule.gen.RuleGen;
import com.github.xiaoy.droolrule.gen.param.FreemkTemp;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FreemkRuleGenImpl implements RuleGen {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public List<Map<String, Object>> prepareData(Object obj) {
        return null;
    }

    public List<FreemkTemp> prepareData() {
        FreemkTemp temp = new FreemkTemp();
        temp.setJumpPoint("1");
        temp.setHouseType("1");
        temp.setHouseTypeName("住宅");
        temp.setCommissionType("1");
        temp.setSalesAmount(new BigDecimal(50000000));
        temp.setCommission(new BigDecimal(100));
        temp.setFixedAmount(new BigDecimal(3000));

        FreemkTemp temp2 = new FreemkTemp();
        temp2.setJumpPoint("2");
        temp2.setHouseType("2");
        temp2.setNumMin(2);
        temp2.setNumMax(13);
        temp2.setHouseTypeName("小于150w 商铺");
        temp2.setCommissionType("2");
        temp2.setSalesAmount(new BigDecimal(1500000000));
        temp2.setCommission(new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP));
        temp2.setFixedAmount(new BigDecimal(5000));

//        Map<String, Object> data = new HashMap<>();
//
//        data.put("houseType", "1");
//        data.put("houseTypeName", "住宅");
//        data.put("commissionType", "1");
//        data.put("salesAmount", new BigDecimal(50000000));
//        data.put("commission", new BigDecimal(100));
//        data.put("fixedAmount", new BigDecimal(3000));
//
//        Map<String, Object> data2 = new HashMap<>();
//
//        data2.put("houseType", "2");
//        data2.put("houseTypeName", "小于150w 商铺");
//        data.put("commissionType", "2");
//        data2.put("salesAmount", new BigDecimal(1500000000));
//        data2.put("commission", new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP));
//        data2.put("fixedAmount", new BigDecimal(5000));
//
//        Map<String, Object> data3 = new HashMap<>();
//
//        data3.put("houseType", "3");
//        data3.put("houseTypeName", "150-300w 商铺");
//        data.put("commissionType", "1");
//        data3.put("salesAmount", new BigDecimal(300000000));
//        data3.put("commission", new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP));
//        data3.put("fixedAmount", new BigDecimal(8000));
//
//
//        Map<String, Object> data4 = new HashMap<>();
//
//        data4.put("houseType", "4");
//        data4.put("houseTypeName", "300w 以上商铺");
//        data.put("commissionType", "1");
//        data4.put("salesAmount", new BigDecimal(400000000));
//        data4.put("commission", new BigDecimal(200));
//        data4.put("fixedAmount", new BigDecimal(18000));
//        return Arrays.asList(data, data2, data3, data4);
        return Arrays.asList(temp, temp2);
    }

    @Override
    public String getTemplateFileName() {
        return "fixed.ftl";
    }

    @Override
    public String applyRuleTemplate(Object obj) throws Exception {
        List<FreemkTemp> data = prepareData();
        Map<String, Object> maps = new HashMap<>();
        maps.put("root", data);
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(getTemplateFileName());
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, maps);
    }
}
