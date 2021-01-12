package com.github.xiaoy.droolrule.gen.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 模板参数对应 DeveloperSettlement.ftl
 *
 * @author billow
 * @Date 2021/1/9 15:26
 * @return: null
 **/
@Data
public class DeveloperSettlementTemp {

    /**
     * 考核开始时间
     */
    private String startDate;
    /**
     * 考核结束时间
     */
    private String endDate;
    /**
     * 跳点条件，1-固定，2-范围
     */
    private String jumpPoint;
    /**
     * 物业类型：1-住宅，2-小于150w 商铺，3-150-300w 商铺，4-300w 以上商铺
     */
    private String houseType;
    /**
     * 产品类型名称
     */
    private String houseTypeName;
    /**
     * 销售金额
     */
    private BigDecimal salesAmount = BigDecimal.ZERO;
    /**
     * 成交套数-最小值
     */
    private Integer numMin = 0;
    /**
     * 成交套数-最大值
     */
    private Integer numMax = Integer.MAX_VALUE;
    /**
     * 提成类型，1-固定，2-比例
     */
    private String commissionType;
    /**
     * 如果是固定，放入金额; 如果是点数，放入小数
     */
    private BigDecimal commission = BigDecimal.ZERO;
    /**
     * 提成固定金额
     */
    private BigDecimal fixedAmount = BigDecimal.ZERO;

}
