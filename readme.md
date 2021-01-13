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

3.访问 `http:127.0.0.1:8080/api/ruleApi/genRule/{tempCode}/{bizId}`
 `tempCode` 可以查看 `com.github.xiaoy.droolrule.constant.TemplateGenEnum` 可以根据不同的生成器生成不同的 `drl`.`bizId`为业务规则保存的位置主键

4.访问 `http:127.0.0.1:8080/api/ruleApi/reload/{tempCode}/{groupId}`  将指定的规则加到内存中。

5.如果重新生成了 `drl` 文件，需要访问 `http:127.0.0.1:8080/api/ruleApi/{tempCode}/reload/{groupId}` 刷新内存中的规则

6.触发规则：`http:127.0.0.1:8080/api/ruleApi/fire/{tempCode}/{groupId}` 其中 `groupId` 为需要触发的一组规则.
Map参数中key,可以找对应的实体类,如 `com.github.xiaoy.droolrule.param.DeveloperSettlementParam`
```json
{
	"assessmentDate": "2021-01-12",
	"commissionType": "1",
	"houseType": "1",
	"jumpPoint": "1",
	"salesAmount": 1000000
}
```

7.访问 `http:127.0.0.1:8080/api/ruleApi/reload` 加载所有规则到内存中

## 四、主要文件说明
`droolsRuleTemp/DeveloperSettlement.ftl` 对应业务的规则模板，使用的 `freemarker` 模板引擎

`com.github.xiaoy.droolrule.gen.param.DeveloperSettlementTemp` 模板对应参数，用于生成模板。
业务在页面上修改规则时，会将必要的参数填充进去，以便生成对应的 `drl` 文件。

`com.github.xiaoy.droolrule.param.DeveloperSettlementParam` 用于匹配规则，返回值会写入到 `List<BigDecimal> result` 里面，
因为可能会匹配到多个，所以使用集合。

`com.github.xiaoy.droolrule.gen.impl.BaseRuleGen` 模板生成类基类，提供公用方法

`com.github.xiaoy.droolrule.gen.impl.DeveloperSettlementRuleGenImpl` 对应模板的生成器，调用 `com.github.xiaoy.droolrule.gen.RuleGen.generateRule`
方法时，会生成 `drl` 文件的字符串，会保存到 `re_rule_config` 表中，会返回一个 `groupId`，业务数据中需要保存这个值，以便在调用规则时使用。

`com.github.xiaoy.droolrule.init.InitRuleLoader` 在启动时，会去查询 `re_rule_info` 表中有效的数据，将对应的规则加入到内存中。

`com.github.xiaoy.droolrule.utils.KieSessionHelper` 通过 `groupId` 获取对应的 `KieSession` ，以便操作对应分组的规则。

 `com.github.xiaoy.droolrule.utils.SnowFlakeUtil` 生成  `groupId` 工具类，雪花算法，生成唯一
 
 `com.github.xiaoy.droolrule.api.RuleApi` 提供一个示例 api
 
 
 ## 五.添加新模板的步骤
 1.在 `droolsRuleTemp` 中编写对就的规则模板
 
 2.在 `com.github.xiaoy.droolrule.gen.param` 中添加对应的模板参数实体类
 
 3.继承 `com.github.xiaoy.droolrule.gen.RuleGen` 实现 `getTemplateFileName` 返回模板名称 和 `checkTempData` 查检模板数据的方法
 
 4.实现 `com.github.xiaoy.droolrule.service.RuleInfoService`,接口主要用于规则的查询,规则的更新和规则参数的查询
 
 5.在 `com.github.xiaoy.droolrule.param` 中添加对应的规则匹配实体类,用于规则匹配
 
 6.在 `com.github.xiaoy.droolrule.constant.DroolsRuleGenCst` 中添加模板生成器类(实现 `RuleGen` 接口的类)和规则服务类(实现 `RuleInfoService` 接口的类)
 
 7.在 `com.github.xiaoy.droolrule.constant.TemplateGenEnum` 中添加添加规则枚举
 
 
 
 项目地址：https://github.com/Xiao-Y/drool-rule