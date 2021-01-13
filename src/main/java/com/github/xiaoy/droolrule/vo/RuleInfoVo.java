package com.github.xiaoy.droolrule.vo;

import lombok.Data;

/**
 * 规则信息
 *
 * @author liuyongtao
 */
@Data
public class RuleInfoVo {

    private Long id;
    /**
     * 分组id，一个分组对应多个规则，一个分组对应一个业务分组，一个分组对应一个kmodule
     */
    private Long groupId;
    /**
     * 规则内容，既drl文件内容
     */
    private String ruleContent;
}