# file_management_sys
-------------------------


# 前言
file_management_sys项目希望完成一个文件共享系统，采用现阶段流行技术来实现，总的目的是用来熟悉这些技术。

# 项目介绍
file_management_sys 是一个文件共享系统，包括前端文件展示系统和后台管理系统，基于SpringBoot + MyBatis实现。前端文件展示系统包括文件分类和展示界面，文件搜索和文件上传等模块。后台管理系统包含文件管理，权限管理等模块。
## 项目演示
### 用户端演示
![image](https://github.com/ShuaiMou/file_management_sys/blob/master/project_resource/user.gif)

### 管理端演示
![image](https://github.com/ShuaiMou/file_management_sys/blob/master/project_resource/admin.gif)


# 技术选型
## 后端技术

| 技术             | 说明          | 官网                                         |
| :----------     | :----------  | :----------                                  |
| Spring Boot     | 容器          |  https://spring.io/projects/spring-boot       |
| MyBatis         | ORM 框架      | https://mybatis.org/mybatis-3/zh/index.html   |
| MySQL           | 关系型数据库   | https://dev.mysql.com/doc/refman/8.0/en/       |
| RocketMQ        | 消息队列      | https://rocketmq.apache.org/docs/quick-start/   |
|  Redis          | 分布式缓存     |  https://redis.io/                              |
|  Elasticsearch  |  搜索引擎     |  https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html |
| Nginx           | 负载均衡      | https://www.nginx.com/                        |
|  LogBack        |   日志        |  http://logback.qos.ch/manual/index.html |
|  Lombok	        | 简化对象封装工具| https://github.com/rzwitserloot/lombok  |

## 前端技术
| 技术             | 说明          | 官网                             |
| :----------     | :----------  | :----------                      |
| Vue              |前端框架         | https://vuejs.org/v2/guide/   |
| Vuex             |前端缓存处理      | https://vuex.vuejs.org        |
| vue-router       |通过路由构建单页面项目     |https://router.vuejs.org|
| iView            |组件模版          | https://iviewui.com/ |

# 环境搭建
初始搭建均基于阿里云ECS服务器的centos系统。
## 开发工具

| 工具                   | 说明                    | 官网                                         |
| :----------           | :----------            | :----------                                  |
|    IDEA                |     开发IDE            | https://www.jetbrains.com/idea/              |
| Redis Desktop Manager  | redis客户端连接工具	 |https://redisdesktop.com/download |
| MySQL workbench        |   MySQL 可视化工具       | https://www.mysql.com/cn/products/workbench/|
| GitHub                |  版本管理工具            | https://github.com |
| Maven                  | 项目管理                 |https://maven.apache.org|
|PostMan                |     接口请求测试           |https://www.getpostman.com/|
|Gifox                  | gif录制工具                |https://gifox.io/|
|Axure                  | 原型设计工具	             | https://www.axure.com/|
|bootschool             | banner 生成工具              |https://www.bootschool.net/ascii|
|MarkDown               | MarkDown语法说明              |http://www.markdown.cn|
|RoeketMQ 可视化控制台    |在子项目rocketmq-console里面，打包mvn clean package -Dmaven.test.skip=true|https://github.com/apache/rocketmq-externals|


## 开发环境

| 工具                   | 版本                    | 官网                                       |
| :----------           | :----------            | :----------                                  |
|SpringBoot             |       2.2.4                | https://spring.io/projects/spring-boot   |
|    JDK                |     1.8               | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html             |
| Mysql                 |  5.7.26       	     |https://www.mysql.com/|
| Redis                 |    4.0.10         | https://redis.io|
| Nginx                |  待定                |  https://www.nginx.com|
| Elasticsearch        | 6.8.3                 |https://www.elastic.co/cn/elasticsearch|
| RocketMQ              |    4.6.0          |  https://rocketmq.apache.org            |

# 相关学习文档
|技术                         |  说明                       |网址                                                                     |    
|:---------                      |:----------                         |:---------                                                                  |
|Spring Data Elasticsearch  | spring data整合 Elasticsearch   | https://github.com/spring-projects/spring-data-elasticsearch/blob/master/README.adoc|
|RocketMQ                   | 阿里中间件团队博客                 |http://jm.taobao.org/2017/01/12/rocketmq-quick-start-in-10-minutes/|
# 数据库设计

[MySQL数据库生成代码，数据库设计待完善..](https://github.com/ShuaiMou/file_management_sys/blob/master/project_resource/fms_tables.sql)


