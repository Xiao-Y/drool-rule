>Drools（JBoss Rules）具有一个易于访问企业策略、易于调整以及易于管理的开源业务规则引擎，符合业内标准，速度快、效率高。业务分析师或审核
>人员可以利用它轻松查看业务规则，从而检验是否已编码的规则执行了所需的业务规则。

对系统的使用人员

* 把业务策略（规则）的创建、修改和维护的权利交给业务人员
* 提高业务灵活性
* 加强业务处理的透明度，业务规则可以被管理
* 减少对IT人员的依赖程度
* 避免将来升级的风险

对IT开发人员
* 简化系统架构，优化应用
* 提高系统的可维护性和维护成本
* 方便系统的整合
* 减少编写“硬代码”业务规则的成本和风险


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

3.动态加载（可以指定分组） `drl`

4.根据分组id触发规则

5.根据分组id删除分组规则

## 三、怎么启动
1.修改 `application-dev.yml` 中的数据库文件

2.`RuleApplication` 启动

3.访问 `http:127.0.0.1:8080/insertRule/{tempCode}?groupId=xxx`
 `tempCode` 可以查看 `com.github.xiaoy.droolrule.constant.TemplateGenEnum` 可以根据不同的生成器生成不同的 `drl`

4.第一次生成模板时，需要访问 `http:127.0.0.1:8080/reload/{groupId}`  将指定的规则加到内存中。

5.如果重新生成了 `drl` 文件，需要访问 `http:127.0.0.1:8080/reload/{groupId}` 刷新内存中的规则

6.触发规则：`http:127.0.0.1:8080/fire/{groupId}/{id}` `groupId` 为需要触发的一组规则，
`id` 为业务`id`，通过`id` 查询出规则所需要的数据，给规则引擎匹配

## 四、主要文件说明
`droolRule/templates/DeveloperSettlement.ftl` 对应业务的规则模板，使用的 `freemarker` 模板引擎

`com.github.xiaoy.droolrule.gen.param.DeveloperSettlementTemp` 模板对应参数，用于生成模板。
业务在页面上修改规则时，会将必要的参数填充进去，以便生成对应的 `drl` 文件。

`com.github.xiaoy.droolrule.param.DeveloperSettlementParam` 用于匹配规则，返回值会写入到 `List<BigDecimal> result` 里面，
因为可能会匹配到多个，所以使用集合。

`com.github.xiaoy.droolrule.gen.impl.BaseRuleGen` 模板生成类基类，提供公用方法

`com.github.xiaoy.droolrule.gen.impl.DeveloperSettlementRuleGenImpl` 对应模板的生成器，调用 `com.github.xiaoy.droolrule.gen.RuleGen.generateRule`
方法时，会生成 `drl` 文件的字符串，会保存到 `re_rule_info` 表中，会返回一个 `groupId`，业务数据中需要保存这个值，以便在调用规则时使用。

`com.github.xiaoy.droolrule.init.InitRuleLoader` 在启动时，会去查询 `re_rule_info` 表中有效的数据，将对应的规则加入到内存中。

`com.github.xiaoy.droolrule.utils.KieSessionHelper` 通过 `groupId` 获取对应的 `KieSession` ，以便操作对应分组的规则。

 `com.github.xiaoy.droolrule.utils.SnowFlakeUtil` 生成  `groupId` 工具类，雪花算法，生成唯一
 
 `com.github.xiaoy.droolrule.api.RuleApi` 提供一个示例 api
 
 
 项目地址：https://github.com/Xiao-Y/drool-rule