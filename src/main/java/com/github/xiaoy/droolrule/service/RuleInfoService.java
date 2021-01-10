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
     * @param ruleGenName: 生成器名称。具体类型参见 {@link DroolsRuleGenCst}
     * @author billow
     * @Date 2021/1/8 21:30
     * @return: long
     **/
    long templateConverRule(String ruleGenName) throws Exception;

    /**
     * 获取给定场景下的规则信息列表
     *
     * @param sceneId 场景ID
     * @return 规则列表
     */
    List<RuleInfo> getRuleInfoListBySceneId(Long sceneId);

    /**
     * 获取场景与规则信息列表的Map
     *
     * @return 场景规则信息列表Map，Map(sceneId : List < RuleInfo >)
     */
    Map<Long, List<RuleInfo>> getRuleInfoListMap();

    /**
     * 触发给定场景规则
     *
     * @param sceneId: 场景ID
     * @param obj:     规参数对象
     * @author billow
     * @Date 2021/1/10 18:04
     * @return: void
     **/
    void fire(long sceneId, Object obj);
}
