package com.github.xiaoy.droolrule.service;

import com.github.xiaoy.droolrule.vo.RuleInfoVo;

import java.util.List;

/**
 * 查询参数接口
 *
 * @author liuyongtao
 * @since 2021-1-12 19:39
 */
public interface RuleInfoService<T> {

    /**
     * 查询出所有的规则
     *
     * @return
     * @author liuyongtao
     */
    List<RuleInfoVo> findRuleList();

    /**
     * 通过 groupId 查询出一组规则
     *
     * @param groupId
     * @return
     * @author liuyongtao
     */
    List<RuleInfoVo> findRuleListByGroupId(Long groupId);

    /**
     * 查询生成规则的参数，在对应的生成器中转换数据类型
     *
     * @param bizId 业务主键
     * @return
     * @author liuyongtao
     */
    List<T> findGenerateRuleParam(Long bizId);

    /**
     * 更新规则
     *
     * @param info
     */
    void updateRule(RuleInfoVo info);
}
