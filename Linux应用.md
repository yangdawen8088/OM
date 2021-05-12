# Linux应用(单机版)

**搭建环境要求：虚拟机 VMware 15.5.6  + CentOS 7.8**



## 1.Linux系统软件安装

* 知道Linux系统软件安装的方式有哪些
* 能在Linux系统上安装各种软件



### 1.1 软件的安装

* Linux系统是使用软件包管理器来进行软件的安装、卸载和查询等操作

* 软件包管理器又分为后端工具和前端工具。
  * 后端工具有rpm， dpt
  * 前端工具有yum
* 后端工具存在的问题
  * 软件之间的依赖问题比较复杂
  * 如：X----Y----Z   ：安装X软件需要依赖Y软件而Y软件又依赖Z软件
* 前端工具yum
  * 前端工具yum的出现是为了解决后端工具软件依赖关系的问题
  * yum工具会根据你要安装的软件解析该软件的依赖树，然后把整个依赖树的软件从网上（yum库）下载下来，再进行安装



### 1.2 rpm 常用命令

* 安装一个包 

* rpm -ivh <包名>

  * --nodeps 如果该RPM包的安装
  * 如：rpm -ivh --nodeps <包名>

  * --force 即使覆盖属于其它包的文件也强迫安装 
  * 如：rpm -ivh --force <包名>

* 查询一个包是否被安装 

  * rpm -q <软件名>

* 得到被安装的包的信息 

  * rpm -qi < 软件名> 

* 列出该包中有哪些文件 

  * rpm -ql < 软件名> 

* 列出服务器上的一个文件或目录属于哪一个RPM包 

  * rpm -qf <文件或目录名>

* 列出所有被安装的rpm package 

  * rpm -qa 

* 卸载一个包 

  * rpm -e <软件名>



### 1.3 yum常用命令

* 查看yum库中的所有包
  * yum list
  * yum list | grep (匹配的内容)
* 安装命令
  * yum install <软件名>
* 查看已经安装的软件包
  * yum list installed
  * yum list installed | grep (匹配的内容)
* 卸载软件包
  * yum remove  <软件名>





## 2.JDK1.8+安装

* 下载地址：https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html 下载 [jdk-8u271-linux-x64.tar.gz](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html#license-lightbox)

* 上传到linux系统/usr/local 目录下

* tar -zxvf jdk-8u271-linux-x64.tar.gz 解压之后为  jdk1.8.0_271文件夹

* mv jdk1.8.0_271 jdk8 重命名文件夹名称

* 切换到 /etc 目录 操作 profile文件：vi /etc/profile

* ```properties
  1.进入=profile文件先输入 G 跳到末行
  2.按=o 换行编辑模式
  3.设置环境变量=
  JAVA_HOME=/usr/local/jdk8
  PATH=$PATH:$JAVA_HOME/bin   #$PATH 第一个PATH表示系统原有的变量  ：表示分隔符类似WIN系统的分号
  export=PATH JAVA_HOME
  ```

* 退出保存profile文件

* 修改完profile文件需重新加载profile文件 ：source /etc/profile

* 最后输入java命令测试java环境是否搭建好



## 3.Tomcat8.5.x 安装

* 下载地址：https://tomcat.apache.org/download-80.cgi 下载 https://mirrors.bfsu.edu.cn/apache/tomcat/tomcat-8/v8.5.61/bin/apache-tomcat-8.5.61-deployer.tar.gz
* 上传到linux系统/usr/local 目录下
* tar -zxvf apache-tomcat-8.0.50.tar.gz 解压之后为 apache-tomcat-8.0.50文件夹
* 在/usr/loca/apache-tomcat-8.0.50 目录下输入：./bin/start.sh  启动tomcat服务
* 查看tomcat服务启动是否成功：ps -ef | grep tomcat
* tomcat默认端口是8080，需要在防火墙那开启8080端口，浏览器才可以访问
* 访问：当前自己本地IP地址 + 端口
  * 如：http://192.168.0.111:8080/     注：这是我个人本地连接，不要拷贝



## 4.MySQL8.0.22 安装

* 下载地址：https://downloads.mysql.com/archives/community/ 下载 https://downloads.mysql.com/archives/get/p/23/file/mysql-8.0.22-linux-glibc2.12-x86_64.tar.xz

* 将压缩包扔到Linux的 /usr/local文件夹中

* 使用解压命令解压: tar xvf /usr/local/mysql-8.0.22-linux-glibc2.12-x86_64.tar.xz

* mv mysql-8.0.22-linux-glibc2.12-x86_64 mysql 名称变更为 mysql

* 进入被重命名过的文件夹中,再创建一个文件夹,取名:data

  * cd /usr/local/mysql
  * mkdir data

* vi /etc/my.cnf  

  * 如果该文件不存在的. 则手动创建一个（mysql8.0.22中默认已创建）

  * ```cnf
    [mysqld]
    port=3306
    basedir=/usr/local/mysql
    datadir=/usr/local/mysql/data
    max_connections=10000
    max_connect_errors=10
    character-set-server=UTF8
    default-storage-engine=INNODB
    lower_case_table_names=1
    sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
    symbolic-links=0
    
    [mysql_safe]
    log-error=/usr/local/mysql/data/mysqld.log
    pid-file=/usr/local/mysql/data/mysql.pid
    ```

  * :wq (保存文件)

* 创建mysql用户, 进行授权

  * 创建mysql用户,用户组(将mysql用户放进用户组内)
  * (1): groupadd mysql
  * (2): useradd -g mysql mysql
  * 授权
  * (1) chown -R mysql.mysql /usr/local/mysql
  * (2) chmod 755 /usr/local/mysql
  * (3) chmod 644 /etc/my.cnf

* 生成mysql基础数据

  * ```
    (1) cd /usr/local/mysql/bin
     
      (2) 生成mysql数据库基础数据: ./mysqld --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data --lower_case_table_names=1 --initialize 
     
    文字说明:
    --user=mysql : 指定运行mysqld进程的用户名。可更改，主要为了后续添加多个mysql管理员时方便
    --basedir=/usr/local/mysql: 安装目录
    --datadir=/usr/local/mysql/mysql-files: 数据存放目录
    --lower_case_table_names=1: 不区分大小写(这个根据自己需求来,可以先搜索看看)
    --我个人的密码：Qg?AxJgZY3AJ 记录一下方便等下操作
     
    注: 
    1.该命令运行后, 会产生一个临时登录密码, 在返回结果的最后一行(可搜索看看)
    2.执行该命令时, 可能会报错: ./mysqld: error while loading shared libraries: libnuma.so.1: cannot open shared object file: No such file or directory
    3.解决办法(可选) | 下面两个我自己在执行完第一个命令之后就可以了: 
        (1)安装numactl. 我的linux系统是Centos的, 执行命令: yum -y install numactl 
        (2)安装libaio.  我的linux系统是Centos的, 执行命令: yum install -y libaio
    4.安装完成后, 再次执行上面的 "生成mysql数据库基础数据" 命令即可
    5.命令执行后, 记得将最后一行的临时登录密码记录下来, 后续会被使用到
  ```
    

* 添加mysql服务到系统,并完成授权

  * (1) cd /usr/local/mysql
  * (2) cp -a ./support-files/mysql.server /etc/init.d/mysql
  * (3) chmod +x /etc/init.d/mysql
  * (4) chkconfig --add mysql

* mysql启动: service mysql start

* mysql状态查看: service mysql status

* mysql登录

  * ```
    (1) mysql -uroot -p 
    (2) 需要输入的密码是 第9步中运行生成基础数据后, 返回结果中给出的临时密码
     
    注:
    (1) 执行 mysql -uroot -p 时可能报错: -bash: mysql: command not found
    (2) 解决办法 | 执行命令: alias mysql=/usr/local/mysql/bin/mysql
    (3) 执行命令后, 再次输入 mysql -uroot -p
    ```

* 修改密码

  * ```
    (1) ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'xxxx'; (xxxx - 这里需要自己设置密码)  
    (2) flush privileges; (密码生效)
    ```

* 允许远程连接

  * ```
    (1) use mysql;
    (2) update user set host='%' where user='root'; 
    (3) flush privileges;
    (4) 在防火墙开放 3306 端口 firewall-cmd --add-port=3306/tcp --permanent
     
    注:
    (1): 成功执行后, 表明该mysql已经启动了远程连接登录. 如果这时候你使用工具进行连接时候是失败的, 你需要查看下你的服务器安全组是否开放了3306(mysql连接默认端口)端口
    ```

* 设置mysql, 开机自启动

  * ```
    (1) 复制mysql安装目录下的mysql.server : cp /usr/local/mysql/support-files/mysql.server /etc/rc.d/init.d/mysqld
    (2) 赋予权限：chmod +x /etc/init.d/mysqld
    (3) 添加服务: chkconfig --add mysqld
    (4) 查看服务列表:  chkconfig --list
     
    注: 
    1. mysql安装目录为: /usr/lcoal/mysql - 执行第一个命令时,注意路径
    2. 执行(4)后, 看到mysql行数据  - 3,4,5都为"开"或"on"表示成功
    ```

  * mac终端输入mysql -u root -p出现错误：-bash: mysql: command not found
    输入：`alias mysql=/usr/local/mysql/bin/mysql`
    再重复mysql -u root -p，输入mysql口令即可

  * 如果远程连接不了则重启虚拟机



## 5.Redis6.0.x 安装

* 下载地址：http://download.redis.io/releases/下载 http://download.redis.io/releases/redis-6.0.8.tar.gz

* 上传到linux系统/usr/local 目录下

* tar -zxvf redis-6.0.8.tar.gz 解压之后为 redis-6.0.8文件夹

* mv redis-6.0.8 redis 重命名文件夹名称

* 先安装gcc : yum install -y gcc   默认安装的版本是4.8.5 版本比较低

* 升级gcc版本

  * yum -y install centos-release-scl
  * yum -y install devtoolset-9-gcc devtoolset-9-gcc-c++ devtoolset-9-binutils
  * scl enable devtoolset-9 bash
  * echo "source /opt/rh/devtoolset-9/enable" >> /etc/profile
  * gcc -v   升级后查看gcc版本升级到9.3.1

* 再进入redis目录下编译

  * make && make install

* 备份Redis配置文件

  * cp redis.conf redis.conf.bak 

* 修改redis.conf配置文件

  * 修改redis.conf文件之前建议先去了解一下，百度关键字：redis.conf配置文件详解 

  * 查阅连接：https://blog.csdn.net/qq_37809967/article/details/110508298

  * 我们要改哪些配置信息

  * ```
    port：服务的端口号，默认为6379
    daemonize：是否后台运行，值为yes | no，默认为no，如果需要后台运行，则设置为yes
    requirepass：密码，默认是没有密码
    protected-mode：保护模式，值为yes | no默认是yes，不能远程访问该redis服务，如果需要远程访问，则该为no。
    
    ```

* 默认安装目录：/usr/local/bin

* 启动redis

  * 先将修改好的redis.conf配置文件复制到/usr/local/bin 目录下
  * 启动：./redis-server redis.conf
  * 查看 redis是否启动成功：ps -ef | grep redis

* 设置redis开机自启

  * 在/etc目录下新建redis目录
  * 将/usr/local/redis/redis.conf 文件复制一份到/etc/redis目录下，并命名为6379.conf
  * 命令：cp /usr/local/redis/redis.conf /etc/redis/6379.conf
  * 将redis安装包中的启动脚本复制一份放到/etc/init.d目录下
  * 命令：cp /usr/local/redis/utils/redis_init_script /etc/init.d/redisd
  * service redisd start 启动redis服务出现：/var/run/redis_6379.pid exists, process is already running or crashed 错误
  * 解决方案：rm -rf /var/run/redis_6379.pid
  * 然后再启动就可以了

  

注意：6.0.10以上需要gcc的版本10以上 查看gcc版本  gcc -v



## 6.Rabbitmq3.8.11 安装



### 6.1 先安装erlang

* 安装依赖

  * ```
    yum -y install gcc glibc-devel make ncurses-devel openssl-devel xmlto perl wget gtk2-devel binutils-devel
    ```

* erlang下载地址：http://erlang.org/download/otp_src_23.0.tar.gz  建议下载下来再传到服务器

* 将otp_src_23.0.tar.gz传到：/usr/local 目录下

* 解压：tar -zxvf otp_src_22.0.tar.gz

* 删除 otp_src_22.0.tar.gz  包 ： rm -f otp_src_22.0.tar.gz

* 切换目录: cd /usr/local/otp_src_22.0/

* 创建即将安装的目录: mkdir ../erlang

* 配置安装路径: ./configure --prefix=/usr/local/erlang

* 安装：make install      （安装的时间有点长，耐心等待）

* 查看一下是否安装成功 ：ll /usr/local/erlang/bin

* 添加环境变量：echo 'export PATH=$PATH:/usr/local/erlang/bin' >> /etc/profile

* 刷新环境变量：source /etc/profile

* 测试一下erlang是否安装成功：输入命令：erl

* 在里面输入halt().命令退出来（那个点号别忘记）

* 到此安装erlang完成



### 6.2 再安装RabbitMQ

* RabbitMQ下载地址：https://github.com/rabbitmq/rabbitmq-server/releases/ 下载版本：https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.8.11/rabbitmq-server-generic-unix-3.8.11.tar.xz

* RabbitMQ3.8.11 要求 erlang版本在 2.2.x 至 2.3.x

* 将rabbitmq-server-generic-unix-3.8.11.tar.xz传到：/usr/local 目录下

* 由于是tar.xz格式的所以需要用到xz，没有的话就先安装 

  * ```
    yum install -y xz
    ```

* 第一次解压: /bin/xz -d rabbitmq-server-generic-unix-3.8.11.tar.xz

* 第二次解压: tar -zxvf rabbitmq-server-generic-unix-3.8.11.tar

* 删除 rabbitmq-server-generic-unix-3.8.11.tar  包 ： rm -f rabbitmq-server-generic-unix-3.8.11.tar

* 更改名称：mv /usr/local/rabbitmq_server-3.8.11  rabbitmq

* 配置环境变量: echo 'export PATH=$PATH:/usr/local/rabbitmq/sbin' >> /etc/profile

* 刷新环境变量: source /etc/profile

* 创建配置目录: mkdir /etc/rabbitmq

* 管理RabbitMQ

  * 启动：rabbitmq-server -detached
  * 停止：rabbitmqctl stop
  * 查看状态：rabbitmqctl status

* 防火墙开放端口

  * 开放：5672、15672 端口
  * firewall-cmd --add-port=5672/tcp --permanent         添加开放端口
  * firewall-cmd --add-port=15672 /tcp --permanent     添加开放端口
  * firewall-cmd --reload  : 新增或删除端口需要重新加载防火墙规则
  * firewall-cmd --list-ports : 查看开放端口

* RabbitMQ WEB页面管理

  * 开启web插件 ：rabbitmq-plugins enable rabbitmq_management
  * 默认账号密码：guest guest（这个账号只允许本机访问）s 

* 用户管理

  * 查看所有用户 : rabbitmqctl list_users

  * 添加一个用户: rabbitmqctl add_user tg 123456

  * 配置权限: 

    ```
    rabbitmqctl set_permissions -p "/" tg ".*" ".*" ".*"
    ```

  * 查看用户权限: rabbitmqctl list_user_permissions tg

  * 设置tag : rabbitmqctl set_user_tags tg administrator

  * 删除用户（测试情况，建议不要删除）: rabbitmqctl delete_user guest    

* 如果操作过程中出现这个错

  * ```
    Error: unable to perform an operation on node 'rabbit@localhost'. Please see diagnostics information and suggestions below.
    
    Most common reasons for this are:
    
     * Target node is unreachable (e.g. due to hostname resolution, TCP connection or firewall issues)
     * CLI tool fails to authenticate with the server (e.g. due to CLI tool's Erlang cookie not matching that of the server)
     * Target node is not running
    
    In addition to the diagnostics info below:
    
     * See the CLI, clustering and networking guides on https://rabbitmq.com/documentation.html to learn more
     * Consult server logs on node rabbit@localhost
     * If target node is configured to use long node names, don't forget to use --longnames with CLI tools
    
    DIAGNOSTICS
    ===========
    
    attempted to contact: [rabbit@localhost]
    
    rabbit@localhost:
      * connected to epmd (port 4369) on localhost
      * epmd reports: node 'rabbit' not running at all
                      no other nodes on localhost
      * suggestion: start the node
    
    Current node details:
     * node name: 'rabbitmqcli-57247-rabbit@localhost'
     * effective user's home directory: /var/lib/rabbitmq
     * Erlang cookie hash: unswVCIRfHzkYWCn9Y3/lw==
    实际上这有可能是因为我们停止的时候进程并未完全关闭,导致后续重启失败,rabbitmq没有启动
    
    Rabbitmq-server默认占用的端口号有：5672、15672、25672、4369
    
    命令查看这几个端口的进程情况:
    
    [root@public44 ~]# ss -tnlp | grep 4369
    [root@public44 ~]# ss -tnlp | grep 15672
    [root@public44 ~]# ss -tnlp | grep 5672
    [root@public44 ~]# ss -tnlp | grep 25672
    
    发现4369这个端口的确被进程占用
    
    然后kill - 9 52028
    
    然后重新再启动就可以了
    ```

    

## 7.Elasticsearch7.6.2+Kibana7.6.2+中文分词7.6.2



### 7.1 elasticsearch7.6.2 安装

* https://mirrors.huaweicloud.com/（华为开源镜像站，下载速度比较快点）下载地址 https://repo.huaweicloud.com/elasticsearch/7.6.2/

* 将elasticsearch-7.6.2-linux-x86_64.tar.gz传到：/usr/local 目录下

* 解压：tar -zxvf elasticsearch-7.6.2-linux-x86_64.tar.gz

* 删除 elasticsearch-7.6.2-linux-x86_64.tar.gz  包 ： rm -f elasticsearch-7.6.2-linux-x86_64.tar.gz

* 重命名：mv elasticsearch-7.6.2 elasticsearch

* 在 elasticsearch 目录下创建 data 文件夹存放数据：mkdir data

* 修改 elasticsearch 配置文件

  * vi config/elasticsearch.yml

  * ```
    cluster.name: grootes #集群名称
    node.name: node-1 #节点名称
    #数据和日志的存储目录
    path.data: /usr/local/elasticsearch/data
    path.logs: /usr/local/elasticsearch/logs
    #设置绑定的ip，设置为0.0.0.0以后就可以让任何计算机节点访问到了
    network.host: 0.0.0.0
    http.port: 9200 #端口
    #设置在集群中的所有节点名称，这个节点名称就是之前所修改的，当然你也可以采用默认的也行，目前是单机，放入一个节点即可
    cluster.initial_master_nodes: ["node-1"]
    ```

* 修改内存配置

  * 如果你的内存足够大可以不改，咱是永不倒这么大内存所以降一下，由默认1G降为512m

  * 生产环境可以调大内存大小

  * vi ./config/jvm.options

  * ```
    #-Xms1g
    #-Xmx1g
    -Xms512m
    -Xmx512m
    ```

* 创建 elasticsearch 专用账号

  * elasticsearch产品是不允许使用root账户启动的

  * ```
    # 创建用户
    adduser grootes
    # 设置密码
    passwd grootes
    # 输入两遍密码，如果有提示密码过于简单，请设置复杂一点的密码 如：tg123456
    ```

  * 帐号服目录权限

  * ```
    # 将es和kibana目录赋权限给新建用户，/usr/local/elasticsearch/为es所在上级目录
    chown grootes /usr/local/ -R
    ```

  * 设置用户相关限制

  * ```
    vi /etc/security/limits.conf，在末尾加上：
    grootes soft nofile 65536
    grootes hard nofile 65536
    grootes soft nproc 4096
    grootes hard nproc 4096
     
    vi /etc/security/limits.d/20-nproc.conf，增加：
    grootes    soft    nproc     4096
    
    vi /etc/sysctl.conf
    fs.file-max = 65536
    vm.max_map_count = 262144
    
    ```

  * 使配置文件生效：执行 sysctl -p

  * 用户添加sudo权限 ：vi /etc/sudoers

  * ```
    找到“root    ALL=(ALL)       ALL”一行，在下面插入新的一行，（插入后效果如下：）
    root    ALL=(ALL)       ALL
    grootes ALL=(ALL)       ALL
    ```

* 开放端口

  * firewall-cmd --add-port=9200/tcp --permanent
  * firewall-cmd --reload

* 启动elasticsearch

  * 切换用户：su grootes

  * 启动elasticsearch：./bin/elasticsearch

  * 无报错的话，访问连接为：192.168.0.128:9200  (注意：这是我本地的IP地址，别复制黏贴访问)

  * ```
    1、启动过程中如果提示future versions of Elasticsearch will require Java 11; your Java version from [/usr/local/jdk/jre] 
    
    2、本机环境是 JDK 8 ，它会提醒后面版本需要 JDK 11 支持。但它是向下兼容的
    
    3、个人建议JDK版本可以11以上去测试，在这里就不在演示了
    ```

    

  * ![img](https://img-blog.csdnimg.cn/20200420210853716.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NhcHRhaW5KYXZh,size_16,color_FFFFFF,t_70)

* 停止运行及查看日志

  * ctrl+c停止elasticsearch运行，重新执行后台启动命令 ：nohup ./bin/elasticsearch > nohup.out 2>&1 &
  * 启动日志将输出至执行命令的文件夹下的nohup.out文件中
  * 查看日志命令：cat nohup.out

* 后台关闭程序命令

  * ```
    # 先执行
    nohup ./bin/elasticsearch > nohup.out 2>&1 &
    # 查询进程
    ps -aef | grep elasticsearch
    #  kill -9 pid 杀死进程
    kill -9 123456
    ```



### 7.2 kibana7.6.2 安装

​      **kibana安装就简单多了**

* https://mirrors.huaweicloud.com/（华为开源镜像站，下载速度比较快点）下载地址 https://repo.huaweicloud.com/kibana/7.6.2/

* 将kibana-7.6.2-linux-x86_64.tar.gz传到：/usr/local 目录下

* 解压：tar -zxvf kibana-7.6.2-linux-x86_64.tar.gz

* 删除 kibana-7.6.2-linux-x86_64.tar.gz  包 ： rm -f kibana-7.6.2-linux-x86_64.tar.gz

* 重命名：mv kibana-7.6.2 kibana

* 开放端口

  * firewall-cmd --add-port=5601/tcp --permanent
  * firewall-cmd --reload

* 切换用户操作：su grootes

* 修改配置文件

  * ```
    # 切换到kibana目录下
    cd /usr/local/kibana
    # 编辑配置文件
    vi config/kibana.yml
    # 添加一下内容：
    server.port: 5601
    server.host: "0.0.0.0"
    elasticsearch.hosts: ["http://localhost:9200"]
    ```

* 启动kibana

  * ./bin/kibana
  * 启动之前 先启动 elasticsearch
  * http://192.168.0.128:5601   (注意：这是我本地的IP地址，别复制黏贴访问)

* 停止运行及查看日志

  * ctrl+c停止kibana运行，重新执行后台启动命令 ：nohup ./bin/kibana> nohup.out 2>&1 &
  * 启动日志将输出至执行命令的文件夹下的nohup.out文件中
  * 查看日志命令：cat nohup.out

* 后台关闭程序命令

  * ```
    # 先执行
    nohup ./bin/kibana > nohup.out 2>&1 &
    # 查询进程
    ps -aef | grep kibana
    #  kill -9 pid 杀死进程
    kill -9 123456
    ```



### 7.3 中文分词7.6.2

* 下载地址：https://github.com/medcl/elasticsearch-analysis-ik/releases 找到 7.6.2版本下载，版本一定对应

* 下载好相应版本的zip包，解压后放到elasticsearch/plugins/ik/目录下，然后需要删除原压缩包文件。

* 注意：要切换使用的grootes用户操作。

* ```
  切换到plugins目录：cd /usr/local/elasticsearch/plugins/
   
  创建ik目录：mkdir ik
   
  解压分词器文件：unzip elasticsearch-analysis-ik-7.6.2.zip
   
  删除原zip包命令：rm -rf elasticsearch-analysis-ik-7.6.2.zip
  ```

* 重启elasticsearch

  * 按照elasticsearch结束查询操作kill调es进程后重启es。打开kibana测试分词效果。