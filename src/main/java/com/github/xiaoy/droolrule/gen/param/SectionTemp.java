package com.github.xiaoy.droolrule.gen.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 固定 + 点数
 *
 * @author billow
 * @Date 2021/1/9 15:26
 * @return: null
 **/
@Data
public class SectionTemp {

    // 成交套数-最小值
    private Integer numMin = 0;
    // 成交套数-最大值
    private Integer numMax = Integer.MAX_VALUE;

    // 产品类型：1-住宅，2-小于150w 商铺，3-150-300w 商铺，4-300w 以上商铺
    private String houseType;
    private String houseTypeName;

    // 如果是固定，放入金额; 如果是点数，放入小数
    private BigDecimal commission = BigDecimal.ZERO;

    // 固定提成金额
    private BigDecimal fixedAmount = BigDecimal.ZERO;

}
