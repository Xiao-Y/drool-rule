package com.github.xiaoy.droolrule.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("re_rule_info")
public class RuleInfo {

    @TableId("id")
    private Long id;
    /**
     * 场景id，一个场景对应多个规则，一个场景对应一个业务场景，一个场景对应一个kmodule
     */
    private Long sceneId;
    /**
     * 规则生成器的名称
     *
     **/
    private String ruleGenName;
    /**
     * 规则内容，既drl文件内容
     */
    private String ruleContent;
    private Boolean isDelete;
    private Boolean validInd;
    private Date createTime;
    private Date updateTime;
}