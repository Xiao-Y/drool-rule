package com.github.xiaoy.droolrule.api;

import com.github.xiaoy.droolrule.init.InitRuleLoader;
import com.github.xiaoy.droolrule.param.DeveloperSettlementParam;
import com.github.xiaoy.droolrule.service.RuleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insertRule/{tempCode}")
    public long insertRule(@PathVariable("tempCode") String tempCode,
                           @RequestParam("groupId") long groupId) throws Exception {
        log.info("模板生成类型：{},规则分组：{}", tempCode, groupId);
        long id = ruleInfoService.insertRule(tempCode, groupId);
        log.info("规则 id：{},groupId：{}", id, groupId);
        return groupId;
    }

    @PostMapping("/delRule/{groupId}")
    public String delRule(@PathVariable("groupId") long groupId) throws Exception {
        log.info("规则分组：{}", groupId);
        ruleInfoService.delRule(groupId);
        return "success";
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
        if (id == 11) {
            p1 = new DeveloperSettlementParam("1", "1", "2",
                    new BigDecimal(10000000000L), new BigDecimal(50000));
        } else if (id == 12) {
            p1 = new DeveloperSettlementParam("1", "2", "2",
                    new BigDecimal(10000000000L), new BigDecimal(50000));
        } else if (id == 21) {
            p1 = new DeveloperSettlementParam("2", "1", "2", 15,
                    new BigDecimal(1000000));
        } else {
            p1 = new DeveloperSettlementParam("2", "2", "2", 15,
                    new BigDecimal(1000000));
        }
        ruleInfoService.fire(groupId, p1);
        return p1;
    }
}
