package com.github.xiaoy.droolrule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.xiaoy.droolrule.constant.DroolsRuleGenCst;
import com.github.xiaoy.droolrule.entity.RuleInfo;

import java.util.List;
import java.util.Map;

/**
 * @author liuyongtao
 * @since 2021-1-8 16:18
 */
public interface RuleInfoService extends IService<RuleInfo> {

    /**
     * 模板转换规则语句
     *
     * @param tempCode: 模板生成类型
     * @param groupId:  分组id,不同规则可以分到一个组
     * @author billow
     * @Date 2021/1/8 21:30
     * @return: long
     **/
    long insertRule(String tempCode, long groupId) throws Exception;

    /**
     * 获取给定分组下的规则信息列表
     *
     * @param groupId 分组ID
     * @return 规则列表
     */
    List<RuleInfo> getRuleInfoListByGroupId(Long groupId);

    /**
     * 获取分组与规则信息列表的Map
     *
     * @return 分组规则信息列表Map，Map(groupId : List < RuleInfo >)
     */
    Map<Long, List<RuleInfo>> getRuleInfoListMap();

    /**
     * 触发给定分组规则
     *
     * @param groupId: 分组ID
     * @param obj:     规参数对象
     * @author billow
     * @Date 2021/1/10 18:04
     * @return: void
     **/
    void fire(long groupId, Object obj);

    /**
     * 删除给定分组规则
     *
     * @param groupId: 分组ID
     * @author billow
     * @Date 2021/1/10 18:04
     * @return: void
     **/
    void delRule(long groupId);
}
