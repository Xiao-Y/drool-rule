package com.github.billow.droolrule.test2;

import com.github.billow.droolrule.DroolsApplicationTests;
import com.github.xiaoy.droolrule.param.FixedSectionParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author liuyongtao
 * @since 2021-1-8 8:28
 */
@Slf4j
public class RuleTest extends DroolsApplicationTests {

    @Autowired
    private KieSession kieSession;


    @Test
    public void test2() {

        FixedSectionParam param = new FixedSectionParam("1", "2", new BigDecimal(100000000),
                new BigDecimal(50000));

        kieSession.setGlobal("logger", log);

        kieSession.insert(param);
        int size = kieSession.fireAllRules();
        log.info("匹配到：{}", size);
        log.info("结果：{}", param);

    }
}
