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
    default String generateRule() throws IOException {
        String drlString = applyRuleTemplate();
        log.info("\n************ 生成的drl start ***********\n{}\n************ 生成的drl end ***********", drlString);
        return drlString;
    }

    /**
     * 根据Rule生成drl的String
     *
     * @return
     */
    default String applyRuleTemplate() throws IOException {
        List<Map<String, Object>> data = prepareData();
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();
        ClassPathResource classPathResource = new ClassPathResource(getTemplateFileName());
        InputStream inputStream = classPathResource.getInputStream();
        return objectDataCompiler.compile(data, inputStream);
    }

//    /**
//     * 根据String格式的Drl生成Maven结构的规则
//     *
//     * @param rules
//     */
//    default void createOrRefreshDrlInMemory(List<String> rules) {
//        for (String str : rules) {
//            createOrRefreshDrlInMemory(str);
//        }
//    }

//    /**
//     * 根据String格式的Drl生成Maven结构的规则
//     *
//     * @param rule
//     */
//    default void createOrRefreshDrlInMemory(String rule) {
//        KieServices kieServices = RuleUtils.getKieServices();
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        kieFileSystem.write("src/main/resources/droolRule" + UUID.randomUUID() + ".drl", rule);
//        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem).buildAll();
//        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
//            log.error("create rule in kieFileSystem Error", kb.getResults());
//            throw new RuntimeException("生成规则文件失败");
//        }
//    }


    /**
     * 准备生成规则需要的数据，供模板使用
     *
     * @return
     */
    List<Map<String, Object>> prepareData();

    /**
     * 获取模板文件名
     *
     * @return
     */
    String getTemplateFileName();

}