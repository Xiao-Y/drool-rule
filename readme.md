## 一、服务版本

```
spring-boot 2.4.1

drools 7.14.0.Final

mysql 8

mybatis-plus 3.2.0
```

## 二、完成的功能

1.通过模板生成 `drl`

2.启动时加载数据库中 `drl`

3.动态加载（可以指定场景） `drl`

4.根据场景id触发规则

## 三、怎么启动
1.修改 `application-dev.yml` 中的数据库文件

2.`RuleApplication` 启动

3.访问 `http:127.0.0.1:8080/temp/{ruleGenName}`
 `ruleGenName` 可以查看 `com.github.xiaoy.droolrule.constant.DroolsRuleGenCst` 可以根据不同的生成器生成不同的 `drl`

4.第一次生成模板时，需要访问 `http:127.0.0.1:8080/reload`  将规则加到内存中。

5.如果重新生成了 `drl` 文件，需要访问 `http:127.0.0.1:8080/reload/{sceneId}` 刷新内存中的规则

6.触发规则：`http:127.0.0.1:8080/fire/{sceneId}/{id}` `sceneId` 为需要触发的一组规则，
`id` 为业务`id`，通过`id` 查询出规则所需要的数据，给规则引擎匹配
