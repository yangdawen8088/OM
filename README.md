# OM

OM（OPEN-MALL）开源项目六个月左右开发周期，每天都会持续更新代码，敬请期待......
最新更新信息已发布在Wiki


## 前言

OM（OPEN-MALL）项目致力于打造分布式开源电商平台，开源板块包含社区团购（客户端、团长端、供应商端、仓库端、骑手端）、跨境电商、全球购一件代发（商家端）、MT外卖（商家端、骑手配送端）, 本开源项目仅供学习，有不足的地方请留下宝贵的意见。



## 项目文档

- 文档地址：XXX
- 备用地址：XXX



## 项目介绍

OM（OPEN-MALL）项目是分布式开源电商平台，包括前台商城平台及后台管理系统，前台商城系统包含首页门户、商品推荐、商品搜索、商品展示、购物车、订单流程、会员中心、客户服务、帮助中心等模块。后台管理系统包含商品管理、订单管理、会员管理、促销管理、运营管理、内容管理、统计报表、财务管理、权限管理、设置等模块。



### 项目演示

#### 商城管理平台

前端项目`mall-admin-web`地址：XXX

项目演示地址：XXX

演示图：

![img](https://sbb-osss.oss-cn-beijing.aliyuncs.com/gitee_picture/login.jpg)

![img](https://sbb-osss.oss-cn-beijing.aliyuncs.com/gitee_picture/index.jpg)

![img](https://sbb-osss.oss-cn-beijing.aliyuncs.com/gitee_picture/market.jpg)

![img](https://sbb-osss.oss-cn-beijing.aliyuncs.com/gitee_picture/member.jpg)

![img](https://sbb-osss.oss-cn-beijing.aliyuncs.com/gitee_picture/product.jpg)

![img](https://sbb-osss.oss-cn-beijing.aliyuncs.com/gitee_picture/station.jpg)


#### 社区团购

前端项目`mall-app-web`地址：敬请期待......

项目演示地址：XXX
![img](https://sbb-osss.oss-cn-beijing.aliyuncs.com/gitee_picture/Sign_in001.png)



#### 团长端

前端项目`mall-app-web`地址：敬请期待......

项目演示地址：XXX



#### 商家端

前端项目`mall-app-bussisess`地址：敬请期待......

项目演示地址：XXX



#### 骑手端

前端项目`mall-app-web`地址：敬请期待......

项目演示地址：XXX



#### 跨境电商

前端项目`mall-app-web`地址：敬请期待......

项目演示地址：XXX



#### 全球购一件代发

前端项目`mall-app-web`地址：敬请期待......

项目演示地址：XXX



#### MT外卖

前端项目`mall-app-web`地址：敬请期待......

项目演示地址：XXX



### 项目结构

```XML
<!--模块化-->
<modules>
    <module>mall-common</module> -->  公共代码块 
    <module>mall-gateway</module> -->  网关微服务
    <module>mall-pay</module> -->  支付微服务
    <module>mall-report</module> -->  报表统计微服务
    <module>mall-oss</module> -->  文件存储对象微服务
    <module>mall-order</module> -->  订单微服务
    <module>mall-generator</module> -->  自动生成代码
    <module>mall-cart</module> -->  购物车微服务
    <module>mall-member</module> -->  会员微服务
    <module>mall-job</module> -->  分布式任务微服务
    <module>mall-business</module> -->  商家微服务
    <module>mall-goods</module> -->  商品微服务
    <module>mall-auth</module> -->  登录、注册验证授权微服务
    <module>mall-erp</module> -->  仓库管理微服务
    <module>mall-platform</module> -->  平台管理微服务
    <module>mall-search</module> -->  搜索微服务
    <module>mall-market</module> -->  营销管理微服务
    <module>mall-log</module> -->  日志微服务
    <module>mall-sms</module> -->  短信、邮件微服务
</modules>
```



### 技术选型



#### 后端技术

| 技术               | 版本          | 官网                                            |
| ------------------ | ------------- | ----------------------------------------------- |
| SpringBoot         | 2.3.6         | https://spring.io/projects/spring-boot          |
| SpringCloudAlibaba | 2.2.3.RELEASE | https://spring.io/projects/spring-cloud-alibaba |
| SpringCloud        | Hoxton.SR9    | https://spring.io/projects/spring-cloud         |
| Mybatis-Plus       | 3.x           | https://mybatis.plus/                           |
|                    |               |                                                 |



#### 前端技术

| 技术      | 版本   | 官网                      |
| --------- | ------ | ------------------------- |
| Bootstrap | v3.3.7 | https://www.bootcss.com/  |
| Vue       | v3.0   | https://cn.vuejs.org/     |
| Uni-App   |        | https://uniapp.dcloud.io/ |



#### 架构图

##### 系统架构图

![img](https://sbb-osss.oss-cn-beijing.aliyuncs.com/gitee_picture/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20210506150306.png)



## 环境搭建

### 开发工具

| 工具          | 版本     | 官网                                    |
| ------------- | -------- | --------------------------------------- |
| IDEA          | 2019.3.x | https://www.jetbrains.com/idea/download |
| HBuilder X    | 2.9.8    | https://www.dcloud.io/hbuilderx.html    |
| Typora        | 0.9.98   | https://typora.io/                      |
| Navicat       | 12.1.18  | http://www.formysql.com/xiazai.html     |
| SQLyog        | 13.1.6   | https://sqlyog.en.softonic.com/         |
| PowerDesigner | 16.x     | http://powerdesigner.de/                |



### 开发环境

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 8.0.18 | https://www.mysql.com/                                       |
| Redis         | 5.X    | https://redis.io/download                                    |
| RabbitMQ      | 3.7.14 | http://www.rabbitmq.com/download.html                        |
| Nginx         | 1.10   | http://nginx.org/en/download.html                            |
| Elasticsearch | 7.6.2  | https://www.elastic.co/downloads/elasticsearch               |
| Nacos         | 2.0.0  | https://github.com/alibaba/nacos/releases                    |
| Logstash      | 7.6.2  | https://www.elastic.co/cn/downloads/logstash                 |



### 搭建步骤

```html
Windows环境部署
```

* 1.先搭建Nacos
  * 参考nacos.md文档操作即可
  * nacos配置文件（注：上传项目空间有限进群联系管理员获取相关资料）
* 



```html
Linux环境部署
```

* 


