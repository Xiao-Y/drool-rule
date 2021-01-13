package com.github.billow.droolrule.test2;

import com.alibaba.fastjson.JSON;
import com.github.xiaoy.droolrule.param.DeveloperSettlementParam;

import java.math.BigDecimal;

/**
 * @author liuyongtao
 * @since 2021-1-13 9:18
 */
public class Tests {

    public static void main(String[] args) {
        DeveloperSettlementParam param = new DeveloperSettlementParam("2", "1", "2", 15,
                new BigDecimal(1000000), "2021-01-12");
        System.out.println(JSON.toJSONString(param));
    }
}
