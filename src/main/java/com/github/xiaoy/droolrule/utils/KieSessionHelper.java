package com.github.xiaoy.droolrule.utils;

import com.github.xiaoy.droolrule.init.InitRuleLoader;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * KieSession助手类
 *
 * @author billow
 * @Date 2021/1/10 16:38
 **/
@Slf4j
@Component
public class KieSessionHelper {

    @Autowired
    private InitRuleLoader initRuleLoader;

    /**
     * 获取KieSession
     *
     * @param groupId 分组ID
     * @return KieSession
     */
    public KieSession getKieSessionByGroupId(long groupId) {
        KieContainer kieContainer = initRuleLoader.getKieContainerByGroupId(groupId);
        if (kieContainer == null) {
            boolean reload = initRuleLoader.reload(groupId);
            if (!reload) {
                log.error("没有找到对应的分组,groupId:{}", groupId);
                throw new RuntimeException("没有找到对应的分组");
            }
        }

        KieSession kieSession = kieContainer.getKieBase().newKieSession();
        kieSession.setGlobal("logger", log);
        return kieSession;
    }
}