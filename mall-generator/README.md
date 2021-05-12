## 1.项目说明
* 代码生成器， 可在线生成entity、mapper、service、controller、前端、sql代码，
减少70%以上的开发任务

## 2.本地使用&部署
* 通过git下载地址：https://gitee.com/brother-ting/om/tree/master/mall-generator
* 生成代码前，先在generator.properties文件中
  * 编辑自己的包名（package）、模块名称（moduleName）、基本信息（author、email）、表名是否添加前缀（tablePrefix）
  * 再编辑serviceName 微服务名称、serverAddr 服务地址、serverNamespace 服务命名空间、otherFileName 配置文件名称
  * 添加完成之后再进行下一步操作
* 操作完generator.properties文件，在application-dev.yml文件中编辑自己对应的数据库信息
* Eclipse、IDEA运行GeneratorApplication.java，则可启动项目
* 项目访问路径：http://localhost
* 如需关注项目最新动态，请Watch、Star项目，同时也是对项目最好的支持