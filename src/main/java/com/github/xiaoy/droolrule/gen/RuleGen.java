package com.github.xiaoy.droolrule.gen;

import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规则生成器
 */
public abstract class RuleGen<T> {

    protected final static Logger log = LoggerFactory.getLogger(RuleGen.class);

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 根据传递进来的参数对象生规则
     */
    public String generateRule(Object obj) throws Exception {
        String drlString = applyRuleTemplate(obj);
        log.info("\n************ 生成的drl start ***********\n{}\n************ 生成的drl end ***********", drlString);
        return drlString;
    }

    /**
     * 根据Rule生成drl的String
     *
     * @return
     */
    private String applyRuleTemplate(Object obj) throws Exception {
        List<T> data = prepareData(obj);
        Map<String, Object> maps = new HashMap<>();
        maps.put("root", data);
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(getTemplateFileName());
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, maps);
    }

    /**
     * 准备生成规则需要的数据，供模板使用
     *
     * @return
     */
    protected abstract List<T> prepareData(Object obj);

    /**
     * 获取模板文件名
     *
     * @return
     */
    protected abstract String getTemplateFileName();

}