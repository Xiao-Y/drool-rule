package com.github.xiaoy.droolrule.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 规则参数配置
 *
 * @author liuyongtao
 * @since 2021-1-13 14:58
 */
@Data
@TableName("re_rule_config_detail")
public class RuleConfigInfoDetail {

    @TableId("id")
    private Long id;

    private Long ruleConfigId;
    /**
     * 跳点条件，1-固定，2-范围
     */
    private String jumpPoint;
    /**
     * 提成类型，1-固定，2-比例
     */
    private String commissionType;
    /**
     * 物业类型：1-住宅，2-小于150w 商铺，3-150-300w 商铺，4-300w 以上商铺
     */
    private String houseType;
    /**
     * 考核开始时间
     */
    private Date startDate;
    /**
     * 考核结束时间
     */
    private Date endDate;
    /**
     * 销售金额
     */
    private BigDecimal salesAmount;
    /**
     * 成交套数-最小值
     */
    private Integer numMin;
    /**
     * 成交套数-最大值
     */
    private Integer numMax;
    /**
     * 提成固定金额
     */
    private BigDecimal fixedAmount;

    /**
     * 如果是固定，放入金额; 如果是点数，放入小数
     */
    private BigDecimal commission;

    private Boolean isDelete;
    private Boolean validInd;
    private Date createTime;
    private Date updateTime;
}
