package com.github.xiaoy.droolrule.api;

import com.github.xiaoy.droolrule.init.InitRuleLoader;
import com.github.xiaoy.droolrule.param.FixedSectionParam;
import com.github.xiaoy.droolrule.service.RuleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 构建模板，保存到数据库
     *
     * @throws IOException
     */
    @PostMapping("/temp/{ruleGenName}")
    public long templateConverRule(@PathVariable("ruleGenName") String ruleGenName) throws Exception {
        log.info("生成器的名称：{}", ruleGenName);
        long sceneId = ruleInfoService.templateConverRule(ruleGenName);
        log.info("规则 sceneId：{}", sceneId);
        return sceneId;
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
     * 重新加载给定场景下的规则
     *
     * @param sceneId 场景ID
     */
    @GetMapping("reload/{sceneId}")
    public String reload(@PathVariable("sceneId") Long sceneId) {
        log.info("reload scene:" + sceneId);
        initRuleLoader.reload(sceneId);
        return "success";
    }

    /**
     * 触发给定场景规则
     *
     * @param sceneId 场景ID
     */
    @GetMapping("fire/{sceneId}/{id}")
    public Object fire(@PathVariable("sceneId") long sceneId,
                       @PathVariable("id") long id) {
        log.info("fire scene sceneId:{},id:{}", sceneId, id);

        // id 查询出数据，组装成 对应的对象
        Object p1;
        if (id == 1) {
            p1 = new FixedSectionParam("2", 5, "1", "1",
                    new BigDecimal(1000000));
        } else {
            p1 = new FixedSectionParam("1", new BigDecimal(1000000000),
                    "1", "2", new BigDecimal(50000));
        }
        ruleInfoService.fire(sceneId, p1);
        return p1;
    }
}
