# nacos-server-1.4.0 搭建



## 1.环境要求

需要先安装JDK和mysql，JDK我本地用的是1.8,   mysql我本地用的是：8.0.18



## 2.下载nacos

nacos下载地址：https://github.com/alibaba/nacos/releases

* nacos-server-1.4.0.tar.gz   --->  Linux系统下载这版本
* nacos-server-1.4.0.zip    ---->    Window系统下载这版本

![img](https://img2020.cnblogs.com/blog/1326237/202011/1326237-20201105143413058-719781462.png)



## 3.操作数据库

* 下载后解压，找到config目录。如图：

  ![img](https://img2020.cnblogs.com/blog/1326237/202011/1326237-20201105143509714-1218775170.png)

* 先在mysql数据库创建数据库，库名为：nacos_config 

* 然后将nacos-mysql.sql文件导入到库名为：nacos_config 的数据库中

  

![img](https://img2020.cnblogs.com/blog/1326237/202011/1326237-20201105143617883-1341235301.png)



## 4.修改配置文件

修改nacos\conf目录下的application.properties文件，如图：

* 标红的位置注释去掉，然后改下数据库的链接信息

![img](https://img2020.cnblogs.com/blog/1326237/202011/1326237-20201105143813030-1789086401.png)



修改完毕后找到nacos\bin目录，以文本编辑器打开startup.cmd 

* 【注：cluster是集群模式，windows我这里非集群，所以需要换成“standalone”】
* 集群模式下个版本更新

![img](https://img2020.cnblogs.com/blog/1326237/202011/1326237-20201105144027888-139353445.png)





## 5.启动nacos

* 修改完毕后找到nacos\bin目录， 双击startup.cmd   启动nacos

![img](https://img2020.cnblogs.com/blog/1326237/202011/1326237-20201105144148319-1575650271.png)



## 6.登录nacos

* nacos默认账号密码均为：nacos        登录成功效果图：

​     ![img](https://img2020.cnblogs.com/blog/1326237/202011/1326237-20201105144751628-863300824.png)