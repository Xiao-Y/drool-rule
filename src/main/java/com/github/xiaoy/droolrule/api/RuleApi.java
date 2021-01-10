package com.github.xiaoy.droolrule.api;

import com.github.xiaoy.droolrule.init.InitRuleLoader;
import com.github.xiaoy.droolrule.param.FixedSectionParam;
import com.github.xiaoy.droolrule.service.RuleInfoService;
import com.github.xiaoy.droolrule.utils.SnowFlakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 规则配置api
 *
 * @author liuyongtao
 * @since 2021-1-8 16:03
 */
@Slf4j
@RestController
public class RuleApi {

    @Autowired
    private RuleInfoService ruleInfoService;

    @Autowired
    private InitRuleLoader initRuleLoader;

    @PostMapping("/insertRule/{jumpPoint}")
    public long insertRule(@PathVariable("jumpPoint") String jumpPoint,
                           @RequestParam("groupId") long groupId) throws Exception {
        log.info("跳点类型：{}", jumpPoint);
        long id = ruleInfoService.insertRule(jumpPoint, groupId);
        log.info("规则 id：{},groupId：{}", id, groupId);
        return groupId;
    }

    /**
     * 构建模板，保存到数据库
     *
     * @throws IOException
     */
    @PostMapping("/temp/{ruleGenName}")
    public long templateConverRule(@PathVariable("ruleGenName") String ruleGenName) throws Exception {
        log.info("生成器的名称：{}", ruleGenName);
        long groupId = SnowFlakeUtil.getFlowIdInstance().nextId();
        long id = ruleInfoService.templateConverRule(ruleGenName, groupId);
        log.info("规则 id：{},groupId：{}", id, groupId);
        return groupId;
    }

    /**
     * 重新加载所有规则
     */
    @GetMapping("reload")
    public String reload() {
        log.info("reload all");
        initRuleLoader.reloadAll();
        return "success";
    }

    /**
     * 重新加载给定分组下的规则
     *
     * @param groupId 分组ID
     */
    @GetMapping("reload/{groupId}")
    public String reload(@PathVariable("groupId") Long groupId) {
        log.info("reload scene:" + groupId);
        initRuleLoader.reload(groupId);
        return "success";
    }

    /**
     * 触发给定分组规则
     *
     * @param groupId 分组ID
     */
    @GetMapping("fire/{groupId}/{id}")
    public Object fire(@PathVariable("groupId") long groupId,
                       @PathVariable("id") long id) {
        log.info("fire scene groupId:{},id:{}", groupId, id);

        // id 查询出数据，组装成 对应的对象
        Object p1;
        if (id == 1) {
            p1 = new FixedSectionParam("1", "2", 5,
                    new BigDecimal(1000000));
        } else {
            FixedSectionParam p = new FixedSectionParam("1", "2", new BigDecimal(10000000000L),
                    new BigDecimal(50000));
            p.setSalesNum(15);
            p1 = p;
        }
        ruleInfoService.fire(groupId, p1);
        return p1;
    }
}
