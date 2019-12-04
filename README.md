
# 优雅实现接口参数校验 

hibernate-validator是Hibernate项目中的一个数据校验框架，是Bean Validation 的参考实现，hibernate-validator除了提供了JSR 303规范中所有内置constraint 的实现，还有一些附加的constraint。

使用hibernate-validator能够将数据校验从业务代码中脱离出来，增加代码可读性，同时也让数据校验变得更加方便、简单。

官网地址：http://hibernate.org/validator/


在RESTful Web Services的接口服务中，会有各种各样的入参，我们不可能完全不做任何校验就直接进入到业务处理的环节，通常我们会有一个基础的数据验证的机制，待这些验证过程完毕，结果无误后，参数才会进入到正式的业务处理中。
而数据验证又分为两种，一种是无业务关联的规则性验证，一种是根据现有数据进行的联动性数据验证（简单来说，参数的合理性，需要查数据库）。而Hibernate-Validator则适合做无业务关联的规则性验证。


如果项目的框架是spring boot的话，在spring-boot-starter-web 中已经包含了Hibernate-validator的依赖。


Java Bean Validation 最佳实践
参数校验是我们程序开发中必不可少的过程。用户在前端页面上填写表单时，前端js程序会校验参数的合法性，当数据到了后端，为了防止恶意操作，保持程序的健壮性，后端同样需要对数据进行校验。后端参数校验最简单的做法是直接在业务方法里面进行判断，当判断成功之后再继续往下执行。但这样带给我们的是代码的耦合，冗余。当我们多个地方需要校验时，我们就需要在每一个地方调用校验程序,导致代码很冗余，且不美观。

那么如何优雅的对参数进行校验呢？JSR303就是为了解决这个问题出现的，本篇文章主要是介绍 JSR303，Hibernate Validator 等校验工具的使用，以及自定义校验注解的使用。

校验框架介绍
JSR303 是一套JavaBean参数校验的标准，它定义了很多常用的校验注解，我们可以直接将这些注解加在我们JavaBean的属性上面，就可以在需要校验的时候进行校验了。注解如下：

##

### JSR303定义的校验类型

Constraint	详细信息
@Null	被注释的元素必须为 null
@NotNull	被注释的元素必须不为 null
@AssertTrue	被注释的元素必须为 true
@AssertFalse	被注释的元素必须为 false
@Min(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@DecimalMin(value)	被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value)	被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Size(max, min)	被注释的元素的大小必须在指定的范围内
@Digits (integer, fraction)	被注释的元素必须是一个数字，其值必须在可接受的范围内
@Past	被注释的元素必须是一个过去的日期
@Future	被注释的元素必须是一个将来的日期
@Pattern(value)	被注释的元素必须符合指定的正则表达式

## Hibernate Validator 附加的 constraint
Constraint	详细信息
@Email	被注释的元素必须是电子邮箱地址
@Length	被注释的字符串的大小必须在指定的范围内
@NotEmpty	被注释的字符串的必须非空
@Range	被注释的元素必须在合适的范围内

## 在代码里校验入参
//obj为包含Hibernate Validator注解的POJO
//快速失败模式
ValidResult validResult = ValidationUtil.fastFailValidate(obj);

//obj为包含Hibernate Validator注解的POJO
//全部校验模式
ValidResult validResult = ValidationUtil.allCheckValidate(obj);


大家看到在校验不通过时，返回的异常信息是不友好的，此时可利用统一异常处理，对校验异常进行特殊处理，特别说明下，对于异常处理类,共有以下几种情况(被@RequestBody和@RequestParam注解的请求实体，校验异常类是不同的)

## hibernate的校验模式

### 
我个人比较推荐使用全局异常拦截处理的方式去处理Hibernate-Validator的验证失败后的处理流程，这样能能减少Controller层或Services层的代码逻辑处理。虽然它也能在Controller中增加BindingResult的实例来获取数据，但是并不推荐。


## 自定义校验规则

我们甚至可以在自定义注解中做更加灵活的处理，甚至把与数据库的数据校验的也写成自定义注解，来进行数据验证的调用。


## 分组校验

同一个校验规则，不可能适用于所有的业务场景，对此，对每一个业务场景去编写一个校验规则，又显得特别冗余。这里我们刚好可以用到Hibernate-Validator的分组功能。


使用分组能极大的复用需要验证的类信息。而不是按业务重复编写冗余的类。然而Hibernate-Validator还提供组序列的形式进行顺序式校验，此处就不重复列举了。我认为顺序化的校验，场景更多的是在业务处理类，例如联动的属性验证，值的有效性很大程度上不能从代码的枚举或常量类中来校验。

## 组序列

## 级联对象属性校验

对象内部包含另一个对象作为属性，属性上加@Valid，可以验证作为属性的对象内部的验证