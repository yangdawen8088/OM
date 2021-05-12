# Linux管理

在线查询Linux命令：

* https://www.runoob.com/linux/linux-install.html
* https://www.linuxcool.com/
* https://man.linuxde.net/



## 1.Linux系统目录结构

Linux系统的目录结构是一个树状结构，每一个文件或目录都从根目录开始，并且根目录只能有一个。

![img](https://lxh--cloud.oss-cn-beijing.aliyuncs.com/2020/0034gxhity6QiX7R1XA5a.PNG)



### 1.1 目录结构说明

* /bin：存放普通用户可执行的命令，系统中任何用户都可执行。 
* /boot：存放Linux内核文件以及开机菜单与开机所配置文件。 
* /dev：存放设备文件的存储目录，如硬盘、光驱等。 
* **/etc：存放系统的配置文件，如用户账号密码、各种服务的起始文件等。只有root有权利修改。** 
* /home：普通用户的主目录，每个用户在该目录下都有一个与用户同名的目录。 
* /lib：存放开机时用到的函数库，以及在/bin或/sbin下的命令会调用的函数库。 
* /media：存放可删除的设备，如软盘、光盘、DVD等设备都暂时挂载于此。 
* /mnt：暂时挂载某些额外的设备。 
* **/opt：存放第三方软件。** （安装需要一步步操作的）
* /root：超级用户root的主目录。 
* /sbin：存放开机、修复、还原系统所需要的命令。 
* **/usr：用户需要安装的第三方应用程序，一般都放到这里。** (绿色版软件，解压即用则放在这里)
* /tmp：这是让一般用户或者正在执行的程序暂时放置文件的地方，任何人都能访问，需要定时清理。
* /proc：该目录是一个虚拟文件系统，只有在系统运行时才存在，该目录下的数据存放在内存中，不占硬盘空间，例如系统内核、进程、外部设备的状态及网络状态等。通过访问该目录下的文件，可以获取系统的状态信息并且修改某些系统的配置信息。 
* /sys：与/proc类似，也是一个虚拟文件系统，主要也是记录与内核相关的信息，包括目前已加载的内核模块与内核检测到的硬件设备信息等。这个目录同样不占硬盘空间。 
* /lost+found：当文件系统发生错误时，存放一些丢失的片段。



## 2.常用的命令

参考地址: https://blog.csdn.net/l18848956739/article/details/93859531

![img](https://lxh--cloud.oss-cn-beijing.aliyuncs.com/2020/31127-20170530141401383-1329040140.png)



### 2.1 关机  

* shutdown -h now 关闭系统(1) 
* init 0 关闭系统(2) 
* telinit 0 关闭系统(3) 
* shutdown -h hours:minutes & 按预定时间关闭系统 
* shutdown -c 取消按预定时间关闭系统 
* shutdown -r now 重启(1) 
* reboot 重启(2) 
* logout 注销 
* clear：清除屏幕信息
* history：用于显示执行过的历史命令记



### 2.2 文件和目录 

* cd / 进入根目录
* cd /home 进入 '/ home' 目录' 
* cd .. 返回上一级目录 
* cd ../.. 返回上两级目录 
* cd 进入个人的主目录 
* cd ~user1 进入个人的主目录 
* cd - 返回上次所在的目录 
* pwd 显示工作路径 
* ls 查看目录中的文件   
* ls -F 查看目录中的文件 
* ls -l 显示文件和目录的详细资料   等价于  ll
* ls -a 显示隐藏文件 
* ls *[0-9]* 显示包含数字的文件名和目录名 
* tree 显示文件和目录由根目录开始的树形结构(1) 
* lstree 显示文件和目录由根目录开始的树形结构(2) 
* mkdir dir1 创建一个叫做 'dir1' 的目录' 
* mkdir dir1 dir2 同时创建两个目录 
* mkdir -p /tmp/dir1/dir2 创建一个目录树 
* rm -f file1 删除一个叫做 'file1' 的文件' 
* rmdir dir1 删除一个叫做 'dir1' 的目录' 
* rm -rf dir1 删除一个叫做 'dir1' 的目录并同时删除其内容 
* rm -rf dir1 dir2 同时删除两个目录及它们的内容 
* mv dir1 new_dir 重命名/移动 一个目录 
* cp file1 file2 复制一个文件 
* cp dir/* . 复制一个目录下的所有文件到当前工作目录 
* cp -a /tmp/dir1 . 复制一个目录到当前工作目录 
* cp -a dir1 dir2 复制一个目录 



### 2.3 文件搜索

* find / -name file1 从 '/' 开始进入根文件系统搜索文件和目录 
* find / -user user1 搜索属于用户 'user1' 的文件和目录 
* find /home/user1 -name \*.bin 在目录 '/ home/user1' 中搜索带有'.bin' 结尾的文件 
* find /usr/bin -type f -atime +100 搜索在过去100天内未被使用过的执行文件 
* find /usr/bin -type f -mtime -10 搜索在10天内被创建或者修改过的文件 
* find / -name \*.rpm -exec chmod 755 '{}' \; 搜索以 '.rpm' 结尾的文件并定义其权限 
* find / -xdev -name \*.rpm 搜索以 '.rpm' 结尾的文件，忽略光驱、捷盘等可移动设备 



### 2.6 **磁盘空间** 

* df：用于显示磁盘的使用情况

  * df -h:：格式化显示输出磁盘使用情况

* du：显示当前目录及其子目录的大小

  * du -sh汇总显示该目录的大小

* top：任务进程  (退出top命令，输入:q 或 Ctrl + c)

  * 任务进程

    第一行：10:01:23 — 当前系统时间,126 days, 14:29 — 系统已经运行了126天14小时29分钟（在这期间没有重启过）,2 users — 当前有2个用户登录系统

    load average: 1.15, 1.42, 1.44 — load average后面的三个数分别是1分钟、5分钟、15分钟的负载情况。

    第二行：Tasks — 任务（进程），系统现在共有183个进程，其中处于运行中的有1个，182个在休眠（sleep），stoped状态的有0个，zombie状态（僵尸）的有0个。

    第三行：cpu状态

    6.7% us — 用户空间占用CPU的百分比。

    0.4% sy — 内核空间占用CPU的百分比。

    0.0% ni — 改变过优先级的进程占用CPU的百分比

    92.9% id — 空闲CPU百分比

    0.0% wa — IO等待占用CPU的百分比

    0.0% hi — 硬中断（Hardware IRQ）占用CPU的百分比

    0.0% si — 软中断（Software Interrupts）占用CPU的百分比

    第四行：内存状态

    8306544k total — 物理内存总量（8GB）

    7775876k used — 使用中的内存总量（7.7GB）

    530668k free — 空闲内存总量（530M）

    79236k buffers — 缓存的内存量 （79M）

    第五行：swap交换分区

    2031608k total — 交换区总量（2GB）

    2556k used — 使用的交换区总量（2.5M）

    2029052k free — 空闲交换区总量（2GB）

    4231276k cached — 缓冲的交换区总量（4GB）

    第五行以下:

    PID — 进程id

    USER — 进程所有者

    PR — 进程优先级

    NI — nice值。负值表示高优先级，正值表示低优先级

    VIRT — 进程使用的虚拟内存总量，单位kb。VIRT=SWAP+RES

    RES — 进程使用的、未被换出的物理内存大小，单位kb。RES=CODE+DATA

    SHR — 共享内存大小，单位kb

    S — 进程状态。D=不可中断的睡眠状态 R=运行 S=睡眠 T=跟踪/停止 Z=僵尸进程

    %CPU — 上次更新到现在的CPU时间占用百分比

    %MEM — 进程使用的物理内存百分比

    TIME+ — 进程使用的CPU时间总计，单位1/100秒

    COMMAND — 进程名称（命令名/命令行）

* free：内存占用情况

  * 内存占用情况:

    total：内存总数；

    used：已经使用的内存数；

    free：空闲的内存数；

    buffers Buffer：缓存内存数；

    cached Page：缓存内存数。

  



### 2.7 打包和压缩文件 

* bunzip2 file1.bz2 解压一个叫做 'file1.bz2'的文件 
* bzip2 file1 压缩一个叫做 'file1' 的文件 
* gunzip file1.gz 解压一个叫做 'file1.gz'的文件 
* gzip file1 压缩一个叫做 'file1'的文件 
* gzip -9 file1 最大程度压缩 
* rar a file1.rar test_file 创建一个叫做 'file1.rar' 的包 
* rar a file1.rar file1 file2 dir1 同时压缩 'file1', 'file2' 以及目录 'dir1' 
* rar x file1.rar 解压rar包 
* unrar x file1.rar 解压rar包 
* tar -cvf archive.tar file1 创建一个非压缩的 tarball 
* tar -cvf archive.tar file1 file2 dir1 创建一个包含了 'file1', 'file2' 以及 'dir1'的档案文件 
* tar -tf archive.tar 显示一个包中的内容 
* tar -xvf archive.tar 释放一个包 
* tar -xvf archive.tar -C /tmp 将压缩包释放到 /tmp目录下 
* tar -cvfj archive.tar.bz2 dir1 创建一个bzip2格式的压缩包 
* tar -jxvf archive.tar.bz2 解压一个bzip2格式的压缩包 
* tar -cvfz archive.tar.gz dir1 创建一个gzip格式的压缩包 
* tar -zxvf archive.tar.gz 解压一个gzip格式的压缩包 
* zip file1.zip file1 创建一个zip格式的压缩包 
* zip -r file1.zip file1 file2 dir1 将几个文件和目录同时压缩成一个zip格式的压缩包 
* unzip file1.zip 解压一个zip格式压缩包 



### 2.8 RPM 包

* rpm -ivh package.rpm 安装一个rpm包 
* rpm -ivh --nodeeps package.rpm 安装一个rpm包而忽略依赖关系警告 
* rpm -U package.rpm 更新一个rpm包但不改变其配置文件 
* rpm -F package.rpm 更新一个确定已经安装的rpm包 
* rpm -e package_name.rpm 删除一个rpm包 
* rpm -qa 显示系统中所有已经安装的rpm包 
* rpm -qa | grep httpd 显示所有名称中包含 "httpd" 字样的rpm包 
* rpm -qi package_name 获取一个已安装包的特殊信息 
* rpm -qg "System Environment/Daemons" 显示一个组件的rpm包 
* rpm -ql package_name 显示一个已经安装的rpm包提供的文件列表 
* rpm -qc package_name 显示一个已经安装的rpm包提供的配置文件列表 
* rpm -q package_name --whatrequires 显示与一个rpm包存在依赖关系的列表 
* rpm -q package_name --whatprovides 显示一个rpm包所占的体积 
* rpm -q package_name --scripts 显示在安装/删除期间所执行的脚本l 
* rpm -q package_name --changelog 显示一个rpm包的修改历史 
* rpm -qf /etc/httpd/conf/httpd.conf 确认所给的文件由哪个rpm包所提供 
* rpm -qp package.rpm -l 显示由一个尚未安装的rpm包提供的文件列表 
* rpm --import /media/cdrom/RPM-GPG-KEY 导入公钥数字证书 
* rpm --checksig package.rpm 确认一个rpm包的完整性 
* rpm -qa gpg-pubkey 确认已安装的所有rpm包的完整性 
* rpm -V package_name 检查文件尺寸、 许可、类型、所有者、群组、MD5检查以及最后修改时间 
* rpm -Va 检查系统中所有已安装的rpm包- 小心使用 
* rpm -Vp package.rpm 确认一个rpm包还未安装 



### 2.9 YUM 软件包升级器

* yum install package_name 下载并安装一个rpm包 
* yum localinstall package_name.rpm 将安装一个rpm包，使用你自己的软件仓库为你解决所有依赖关系 
* yum update package_name.rpm 更新当前系统中所有安装的rpm包 
* yum update package_name 更新一个rpm包 
* yum remove package_name 删除一个rpm包 
* yum list 列出当前系统中安装的所有包 
* yum search package_name 在rpm仓库中搜寻软件包 
* yum clean packages 清理rpm缓存删除下载的包 
* yum clean headers 删除所有头文件 
* yum clean all 删除所有缓存的包和头文件 



### 2.10 **查看文件内容** 

* cat file1 从第一个字节开始正向查看文件的内容 
* tac file1 从最后一行开始反向查看一个文件的内容 
* more file1 查看一个长文件的内容 
* less file1 类似于 'more' 命令，但是它允许在文件中和正向操作一样的反向操作 
* head -2 file1 查看一个文件的前两行 
* tail -2 file1 查看一个文件的最后两行 
* tail -f /var/log/messages 实时查看被添加到一个文件中的内容



### 2.14 网络

* fconfig eth0 显示一个以太网卡的配置 
* ifup eth0 启用一个 'eth0' 网络设备 
* ifdown eth0 禁用一个 'eth0' 网络设备 
* ifconfig eth0 192.168.1.1 netmask 255.255.255.0 控制IP地址 
* ifconfig eth0 promisc 设置 'eth0' 成混杂模式以嗅探数据包 (sniffing) 
* dhclient eth0 以dhcp模式启用 'eth0' 
* ip addre 查看网络地址



### 2.15 **用户和群组** 

* groupadd group_name 创建一个新用户组 
* groupdel group_name 删除一个用户组 
* groupmod -n new_group_name old_group_name 重命名一个用户组 
* useradd -c "Name Surname " -g admin -d /home/user1 -s /bin/bash user1 创建一个属于 "admin" 用户组的用户 
* useradd user1 创建一个新用户 
* userdel -r user1 删除一个用户 ( '-r' 排除主目录) 
* usermod -c "User FTP" -g system -d /ftp/user1 -s /bin/nologin user1 修改用户属性 
* passwd 修改口令 
* passwd user1 修改一个用户的口令 (只允许root执行) 
* chage -E 2005-12-31 user1 设置用户口令的失效期限 
* pwck 检查 '/etc/passwd' 的文件格式和语法修正以及存在的用户 
* grpck 检查 '/etc/passwd' 的文件格式和语法修正以及存在的群组 
* newgrp group_name 登陆进一个新的群组以改变新创建文件的预设群组 



## 3.VI编辑器

### 3.1VI编辑器概述

Vi编辑器是什么

* Vi编辑器是一款可以用来创建和编辑文本文件的工具，vi就是visual interface（可视化接口）的简写，它有个增强版：vim，就是vi improve，它是一个模式化编辑器: 在不同的模式下,我们的机键动作被解释的意义是不同的。
* 注意：在vi编辑器中如果要看下面的内容不要用鼠标滚动文件内容,应该使用键盘上下键进行操作

如何使用

* vi /filename
  * 如果该文件是不存在的，就是新增文件
  * 如果该文件是存在的，就是修改文件

Vi编辑器的三种模式

* 编辑模式(命令模式) ：所有的机键动作都会理解为编辑整个文档的操作,默认为编辑模式。
* 输入模式：大部分机键动作都会理解为输入的字符。
* 末行模式：在末行模式,输入很多文件管理命令。

![img](https://www.runoob.com/wp-content/uploads/2014/07/vim-vi-workmodel.png)



### 3.2 VI编辑器命令

* 关闭文件：在末行模式下
  * q: 退出
  * q!: 退出不保存
  * wq: 保存退出
* 翻页操作：在编辑模式下
  * ctrl+f ：向下翻一页
  * ctrl+b ：向上翻一页
* 移动光标
  * 逐字符移动：在输入模式下按键盘方向键
  * 行内移动：编辑模式下，0:跳转到行首、$: 跳转到行尾、^: 跳转到行首的第一个字母位置
  * 行间跳转：在末行模式下，直接输入行号就可以跳转到对应行，注意:可以先把行号显示出来:set number
  * 其他跳转：在编辑模式下，G:跳转行最后一行，gg: 跳转到第一行
* 删除操作
  * dd: 删除光标所在行 (编辑模式)
  * 3dd:从光标行开始删除3行(编辑模式)
  * 1,4d 删除第一行到第四行(末行模式)
* 复制和粘贴操作：在编辑模式下
  * yy 复制光标所在行
  * 2yy 从光标行开始复制2行
  * p: 粘贴命令
  * u: 撤销操作
* 文件查找：在末行模式下
  * /parttern： 从前往后查找   如： /8080
  * ?parttern ：从后往前查找   如：？8080
  * n:  下一个匹配的字符串
  * N: 上一个匹配的字符串
* 文本的替换：在末行模式下
  * 格式：[startNum,endNum | %]s/partter/string/gi
  * 举例：1,5s/http/hp/gi或者%s/http/hp/gi
  * 解析：s:替换字符串，g:全局替换，i:忽略大小写



## 4.文件管理



### 4.1 文件系统概述

* Linux采用的是树型结构。最上层是根目录，其他的所有目录都是从根目录出发而生成的。
* / 是系统的根目录
* 文件系统是一个树状结构
* **绝对路径** 以/开头
* **相对路径** 不是/开头的路径都是相对路径



### 4.2 创建文件和目录

* 创建目录
  * mkdir 目录名  如：mkdir test
  * mkdir -p 目录名1/目录名2 创建层级目录 可以是相对目录,也可以是绝对目录  如：mkdir -p test/cmt
* 创建文件 
  * vi 文件名             如： vi test.txt
  * touch 文件名     如：touch demo.txt



### 4.3 文件的拷贝和移动

* 文件的拷贝
  * cp [文件名]    如：cp file1 file2 复制一个文件     如：cp demo.text /test/tg1
  * cp -r [目录名]：表示拷贝目录， -r 表示递归        如： cp -r GI/test/tg1 /
* 文件的移动
  * mv 用法与cp一样
  * mv dir1 new_dir 重命名/移动 一个目录 



### 4.4 删除文件和目录

* 删除文件
  * rm [文件名]
  * rm -f [文件名]，不提示
* 删除目录及目录里面的文件
  * rm -r [目录名]，删除目录必须使用 -r 递归删除



### 4.5 文件查看命令

* 文件查看
  * cat [文件名]，整个文件的内容显示出来
  * less/more [文件名]，文件的内容分页显示，空格键下一页，b 键一页
  * tail [文件名]，默认在屏幕上显示该文件末尾10行
  * tail -行数 [文件名]，在屏幕上显示该文件末尾参数设置的行数
  * tail -f [文件名] 显示文件最新追加的内容

* 查找文件
  * find [查找路径] -参数 [parttern文件名的parttern]    如：find / -name 'test*'
  * 详细用法参考：https://man.linuxde.net/find
* 文件内容查找
  * grep parttern(匹配的内容) [文件名] ：grep 8080 test.txt
  * grep parttern(匹配的内容)



### 4.6 打包和压缩

在Linux系统中可以使用两种方式的文件打包和解压

* tar命令

  * tar命令的使用

    打包命令:tar -cvf <打包后的包名> 打包文件1 [打包文件2.....N] 注意:这里打包的可以是文件或者目录.
    解压命令:tar -xvf <包名称>

    带压缩的打包:tar -zcvf <压缩后的包名> 压缩文件1 [压缩文件2.....N]
    带压缩的解压:tar -zxvf <压缩包名>

    例子:
    普通打包: tar -cvf test.tar file1.txt file2.txt folder1
    普通解压: tar -xvf test.tar
    压缩打包: tar -zcvf test.tar.gz file1.txt file2.txt folder1
    压缩解压: tar -zxvf test.tar.gz

* zip命令

  * zip命令的使用之前先下载安装：输入命令：yum install zip  ，安装过程中提示都输入：y 即可

  * unzip命令的使用之前先下载安装：输入命令：yum install unzip  ，安装过程中提示都输入：y 即可

  * zip命令的使用

    zip打包操作
    用法：例如： zip -r test.zip ./* 将当前目录下的所有文件和目录全部压缩成test.zip文件,-r表示递归压缩子目录下所有文件
    unzip解压操作

    用法：例如：unzip p2p.war -d p2p 将当前目录下的p2p.war包解压成p2p目录

    用法：例如：unzip p2p.zip 解压到当前文件夹



## 5.用户管理



### 5.1 创建用户

* useradd [用户名]  创建一个用户

* useradd -G [组名] [用户名]：创建用户并分配一个组

* 在 /etc/passwd 该配置文件可以查看系统中有哪些用户

  * **查看系统用户：**cat /etc/passwd  以冒号(:)为分割

    1	用户名

    2	用户的密码，用x替代

    3	用户的uid,一般情况下root为0，1-499默认为系统账号，有的更大些到1000，500-65535为用户的可登录账号，有的系统从1000开始。

    4	用户的gid,linux的用户都会有两个ID,一个是用户uid，一个是用户组id，在我们登录的时候，输入用户名和密码，其实会先到/etc/passwd查看是否有你输入的账号或者用户名，有的话将该账号与对应的UID和GID(在/etc/group中)读出来。然后读出主文件夹与shell的设置，然后再去检验密码是否正确，正确的话正常登录。

    5	用户的账号说明解释

    6	用户的家目录文件夹

    7	用户使用的shell，如果换成/sbin/nologin/就是默认没有登录环境的。



### 5.2 创建组

* groupadd [组名]
* 在 /etc/group 该配置文件可以查看系统中有哪些组



### 5.3修改用户组属性

* usermod -G [组名] [用户名]



### 5.4 删除用户

* userdel [用户名]
* -f：强制删除用户，即使用户已登录 
* -r：删除与用户相关的所有文件



### 5.5 删除组

* groupdel [组名]



### 5.6 修改密码

* passwd [用户名]     ： 给新用户设置密码



### 5.7 修改目录或文件权限

* chmod [参数] [权限表达式] [目录名或文件名] 

  * rwx : 表示读、写、执行
  * 777 解析 ： rwx   rwx   rwx   
  * rwx：111 读写执行权限都有，16进制计算：1+2+4 =7   ==>  2零次方 + 2壹次方 + 2贰次方
  * ![image-20210118165113650](C:\Users\PC\AppData\Roaming\Typora\typora-user-images\image-20210118165113650.png)

  * 如：chmod 777 file，chmod +x file

* -R：处理指定目录以及其子目录下的所有文件



### 5.8 修改目录或文件的属主和属组

* chown [参数] [属主]:[属组] [目录名或文件名]
  * 如：chown username:groupname file
* -R：处理指定目录以及其子目录下的所有文件



### 5.9 用户账号切换

* su [用户名]



## 6.系统服务管理



### 6.1服务管理

* **systemctl**工具

  * 启动服务：systemctl start <服务名>

  * 关闭服务：systemctl stop <服务名>
  * 重启服务：systemctl restart <服务名>
  * 查看服务状态：systemctl status <服务名>
  * 添加开机启动项：systemctl enable <服务名>
  * 禁止开机启动项：systemctl disable <服务名>
  * 查看开机启动项：systemctl list-unit-files

* **进程查看**

  * ps -ef | grep java

* **杀死进程**

  * kill -9 pid



## 7.网络管理



### 7.1 网络的基本概念

* ip地址：在网络通信中主机的标识符（好比手机号码）
* mac地址：主机的物理网卡的唯一标识符（好比身份证号码）
* 子网掩码：用于区分主机的IP地址中的网络地址和主机地址，并由此确定该主机的IP地址的网段
* 网关：就是一个网络中的主机连接到另一个网络的主机的关口
* DNS：域名解析服务器，把域名解析成对应的IP地址



### 7.2 网络相关配置

* 查看网络卡信息
  * ip addr
* 存放网卡配置文件对应的目录
  * /etc/sysconfig/network-scripts/
    该文件内容：
    NAME=ens33#网卡设备名称
    ONBOOT=no#启动时是否激活 yes | no
    BOOTPROTO=static#协议类型 dhcp bootp none
    IPADDR=192.168.1.90#网络IP地址
    NETMASK=255.255.255.0#子网掩码
    GATEWAY=192.168.1.1#网关地址
    BROADCAST=192.168.1.255#广播地址
    HWADDR=00:0C:29:FE:1A:09#网卡MAC地址
    TYPE=Ethernet#网卡类型为以太网
* 如果修改了网卡配置信息，那么需要重启网络才能生效
  * 网络服务名为：network



### 7.3 设置静态IP

* 注：我们安装好虚拟主机后，IP地址是自动获取的，这样的话，重启主机后，IP地址就有可能改变，导致一些配置失效，所以我们最好把主机的IP地址设置为静态。那么，设置静态IP只需要在网卡配置文件添加以下配置：
  BOOTPROTO=static
  IPADDR=
  NETMASK=
  GATEWAY=
* 配置静态IP时，其实跟WIN 10配置方式一样，还需要配置DNS
  * 参考配置方式：https://blog.csdn.net/qq_37141865/article/details/86544320







### 7.4 防火墙

* 防火墙配置命令：firewall-cmd
* 防火墙帮助：
  * fireweall-cmd --help
* 查看防火墙状态：
  * firewall-cmd --state
* 启动防火墙
  * systemctl start firewall
* 禁用防火墙
  * systemctl stop firewall
* 设置开机启动防火墙
  * systemctl enable firewall
* 停止并禁用开机启动防火墙
  * systemctl disable firewall
* 查看所有打开的端口：
  * firewall-cmd --list-ports
* 开启端口：
  * firewall-cmd --add-port=端口号/tcp --permanent（--permanent参数为永久生效，不加则重启后失效）
* 更新防火墙规则：
  * firewall-cmd --reload （新增或删除端口需要重新加载防火墙规则）
* 删除端口：
  * firewall-cmd --remove-port=端口号/tcp --permanent
* centos7的防火墙程序服务名为：firewalld





