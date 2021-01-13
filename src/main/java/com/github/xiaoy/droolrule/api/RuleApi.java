package com.github.xiaoy.droolrule.api;

import com.github.xiaoy.droolrule.vo.RuleInfoVo;
import com.github.xiaoy.droolrule.init.InitRuleLoader;
import com.github.xiaoy.droolrule.service.DroolsRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 规则配置api
 *
 * @author liuyongtao
 * @since 2021-1-8 16：03
 */
@Slf4j
@RequestMapping("/api/ruleApi")
@RestController
public class RuleApi {

    @Autowired
    private DroolsRuleService droolsRuleService;

    @Autowired
    private InitRuleLoader initRuleLoader;

    /**
     * 生成规则，返回规则组id
     *
     * @param tempCode 参见 com.evergrande.cloud.deal.drools.constant.TemplateGenEnum
     * @param bizId    业务id,用于查询规则参数
     * @return
     * @throws Exception
     */
    @PostMapping("/genRule/{tempCode}/{bizId}")
    public RuleInfoVo genRule(@PathVariable("tempCode") String tempCode,
                              @PathVariable("bizId") Long bizId) throws Exception {
        log.info("tempCode：{},bizId：{}", tempCode, bizId);
        RuleInfoVo ruleInfoVo = droolsRuleService.genRule(tempCode, bizId);
        log.info("规则 ruleInfo：{}", ruleInfoVo);
        return ruleInfoVo;
    }

    /**
     * 删除规则
     *
     * @param groupId 分组ID
     * @return
     * @throws Exception
     */
    @PostMapping("/delRule/{groupId}")
    public String delRule(@PathVariable("groupId") Long groupId) throws Exception {
        log.info("规则分组：{}", groupId);
        droolsRuleService.delRule(groupId);
        return "success";
    }

    /**
     * 重新加载所有规则
     *
     * @return
     */
    @GetMapping("/reload")
    public String reload() {
        log.info("reload all");
        initRuleLoader.reloadAll();
        return "success";
    }

    /**
     * 重新加载给定分组下的规则
     *
     * @param tempCode 参见 com.evergrande.cloud.deal.drools.constant.TemplateGenEnum
     * @param groupId  分组ID
     */
    @GetMapping("/reload/{tempCode}/{groupId}")
    public String reload(@PathVariable("tempCode") String tempCode,
                         @PathVariable("groupId") Long groupId) {
        log.info("reload tempCode：{},groupId：{}", tempCode, groupId);
        boolean reload = initRuleLoader.reload(tempCode, groupId);
        return "success:" + reload;
    }

    /**
     * 触发给定分组规则
     *
     * @param groupId  分组ID
     * @param tempCode 参见 com.evergrande.cloud.deal.drools.constant.TemplateGenEnum
     * @return
     */
    @PostMapping("/fire/{tempCode}/{groupId}")
    public Object fire(@PathVariable("tempCode") String tempCode,
                       @PathVariable("groupId") Long groupId,
                       @RequestBody Map<String, Object> param) {
        log.info("fire tempCode：{},groupId：{},param：{}", groupId, tempCode, param);
        Object obj = droolsRuleService.fire(tempCode, groupId, param);
        log.info("匹配后的返回：{}", obj);
        return obj;
    }
}
