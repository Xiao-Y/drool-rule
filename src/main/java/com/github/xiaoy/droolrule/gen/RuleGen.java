package com.github.xiaoy.droolrule.gen;

import org.drools.template.ObjectDataCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 规则生成器
 */
public interface RuleGen {

    Logger log = LoggerFactory.getLogger(RuleGen.class);

    /**
     * 根据传递进来的参数对象生规则
     */
    default String generateRule(Object obj) throws Exception {
        String drlString = applyRuleTemplate(obj);
        log.info("\n************ 生成的drl start ***********\n{}\n************ 生成的drl end ***********", drlString);
        return drlString;
    }

    /**
     * 根据Rule生成drl的String
     *
     * @return
     */
    default String applyRuleTemplate(Object obj) throws Exception {
        List<Map<String, Object>> data = prepareData(obj);
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
        ClassPathResource classPathResource = new ClassPathResource(getTemplateFileName());
        InputStream inputStream = classPathResource.getInputStream();
        return objectDataCompiler.compile(data, inputStream);
    }

    /**
     * 准备生成规则需要的数据，供模板使用
     *
     * @return
     */
    List<Map<String, Object>> prepareData(Object obj);

    /**
     * 获取模板文件名
     *
     * @return
     */
    String getTemplateFileName();

}