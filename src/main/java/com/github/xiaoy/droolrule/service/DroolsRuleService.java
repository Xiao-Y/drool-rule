package com.github.xiaoy.droolrule.service;

import com.github.xiaoy.droolrule.vo.RuleInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 规则服务接口
 *
 * @author liuyongtao
 * @since 2021-1-8 16:18
 */
public interface DroolsRuleService {

    /**
     * 模板转换规则语句
     *
     * @param tempCode: 模板生成类型
     * @param bizId     业务id,用于查询规则参数
     * @author liuyongtao
     * @Date 2021/1/8 21:30
     * @return: long
     **/
    RuleInfoVo genRule(String tempCode, Long bizId) throws Exception;

    /**
     * 获取给定分组下的规则信息列表
     *
     * @param groupId 分组ID
     * @return 规则列表
     */
    List<RuleInfoVo> getRuleInfoListByGroupId(String tempCode, Long groupId);

    /**
     * 获取分组与规则信息列表的Map
     *
     * @return 分组规则信息列表Map，Map(groupId : List < RuleInfo >)
     */
    Map<Long, List<RuleInfoVo>> getRuleInfoListMap();

    /**
     * 触发给定分组规则
     *
     * @param groupId: 分组ID
     * @param param:   规参数对象
     * @author liuyongtao
     * @Date 2021/1/10 18:04
     * @return: void
     **/
    Object fire(String tempCode, Long groupId, Map<String, Object> param);

    /**
     * 删除给定分组规则
     *
     * @param groupId: 分组ID
     * @author liuyongtao
     * @Date 2021/1/10 18:04
     * @return: void
     **/
    void delRule(Long groupId);
}
