#微服务框架
##技术选型
###springcloud-alibaba
##说明
###Nacos使用
####1、必须使用bootstrap.yml 或者 bootstrap.properties进行配置
####2、若需要分环境，例如DEV,TEST,UAT,PROD等，则需要在nacos中配置命名空间，然后在命名空间中放入配置文件，并在bootstrap中指定namespace 为 命名空间ID
####3、nacos中默认配置文件名为  spring.application.name 或者 spring.application.name-spring.profiles.active 或者spring.application.name-spring.profiles.active.后缀
####4、若想指定配置文件名，则需要配置   shared-configs 或者 extension-configs
####5、若存在相同配置名称， 则优先级为：默认配置文件 > extension-configs[n] > extension-configs[n-1] > shared-configs[n] > shared-configs[n-1]
####6、@Value 要和 @RefreshScope结合使用，这样nacos变更，系统同步变更

###Sentinel使用
####1、@SentinelResource，value表示配置的规则名称，blockHandler表示限流之后的自定义方法，fallback表示熔断之后的自定义方法
####2、blockHandler和fallback对应的方法中，方法参数和返回值需要与@SentinelResource注解的被调用方法保持一致
####3、sentinel规则默认保存在内存中，持久化可以保存在nacos, 其中配置文件中rule-type详见 com.alibaba.cloud.sentinel.datasource.RuleType
####4、限流配置示例
```
[
    {
        "resource": "placeOrder",
        "limitApp": "default",
        "grade": 1,
        "count": 10,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]

说明（字段详见com.alibaba.csp.sentinel.slots.block.flow.FlowRule）

resource:资源名，即@SentinelResource中的value
limitApp:流控针对的调用来源，若为 default 则不区分调用来源
grade：限流策略（0：代表根据并发数量来限流，1代表根据QPS来进行流量控制）， 详见com.alibaba.csp.sentinel.slots.block.RuleConstant
count：限流阈值
strategy：调用关系限流策略 (0:直接，1:关联，2:链路)
controlBehavior：流量控制效果（0:快速失败，1:Warm UP，2:排队等待）
clusterMode：是否为集群模式（true:是，false:否）

```

####5、熔断配置示例
```
[
    {
    "resource": "placeOrder",
    "count": 0.5,
    "grade": 1,
    "timeWindow": 2
  }
]

说明（字段详见com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule）

resource:资源名，即@SentinelResource中的value
grade：熔断策略 （0:慢调用比例 1:异常比例 2:异常数） 详见com.alibaba.csp.sentinel.slots.block.RuleConstant
   1. grade为0时：表示当资源的响应时间超过"最大RT"（count,最大响应时间，以ms为单位），在接下来的"统计时长"（statIntervalMs,以ms为单位）内，持续进入了 "最小请求数"（minRequestAmount,数字）的数量请求，且"比例阈值"（slowRatioThreshold,慢调用比例，0.0~1.0之间）超过设定值，则会在"熔断时长"（timeWindows,以s为单位）内进行熔断
   2. grade为1时：当资源的"统计时长"（statIntervalMs,以ms为单位）请求数大于等于"最小请求数"（minRequestAmount,数字），并且异常总数占通过量的比例超过"比例阈值"（count，0.0~1.0之间）时，则会在"熔断时长"（timeWindows,以s为单位）内进行熔断。
   3. grade为2时：当资源的"统计时长"（statIntervalMs,以ms为单位）请求数大于等于"最小请求数"（minRequestAmount,数字），并且异常数量超过"异常数"（count，数字）时，则会在"熔断时长"（timeWindows,以s为单位）内进行熔断。
```  

###Seata使用
####1 下载Seata源码 https://github.com/seata/seata/releases， seata-1.4.1.zip
####2 下载Seata服务端 https://github.com/seata/seata/releases， seata-server-1.4.1.zip
####3 初始化服务端所需数据库， seata-1.4.1\script\server\db\mysql.sql
```
CREATE DATABASE seata;
USE seata;

-- the table to store GlobalSession data
CREATE TABLE IF NOT EXISTS `global_table`
(
    `xid`                       VARCHAR(128) NOT NULL,
    `transaction_id`            BIGINT,
    `status`                    TINYINT      NOT NULL,
    `application_id`            VARCHAR(32),
    `transaction_service_group` VARCHAR(32),
    `transaction_name`          VARCHAR(128),
    `timeout`                   INT,
    `begin_time`                BIGINT,
    `application_data`          VARCHAR(2000),
    `gmt_create`                DATETIME,
    `gmt_modified`              DATETIME,
    PRIMARY KEY (`xid`),
    KEY `idx_gmt_modified_status` (`gmt_modified`, `status`),
    KEY `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- the table to store BranchSession data
CREATE TABLE IF NOT EXISTS `branch_table`
(
    `branch_id`         BIGINT       NOT NULL,
    `xid`               VARCHAR(128) NOT NULL,
    `transaction_id`    BIGINT,
    `resource_group_id` VARCHAR(32),
    `resource_id`       VARCHAR(256),
    `branch_type`       VARCHAR(8),
    `status`            TINYINT,
    `client_id`         VARCHAR(64),
    `application_data`  VARCHAR(2000),
    `gmt_create`        DATETIME(6),
    `gmt_modified`      DATETIME(6),
    PRIMARY KEY (`branch_id`),
    KEY `idx_xid` (`xid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- the table to store lock data
CREATE TABLE IF NOT EXISTS `lock_table`
(
    `row_key`        VARCHAR(128) NOT NULL,
    `xid`            VARCHAR(96),
    `transaction_id` BIGINT,
    `branch_id`      BIGINT       NOT NULL,
    `resource_id`    VARCHAR(256),
    `table_name`     VARCHAR(32),
    `pk`             VARCHAR(36),
    `gmt_create`     DATETIME,
    `gmt_modified`   DATETIME,
    PRIMARY KEY (`row_key`),
    KEY `idx_branch_id` (`branch_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
```
####4 初始化业务端在AT模式下的数据表，在业务数据库中执行， seata-1.4.1\script\client\at\db\mysql.sql
```
-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB  AUTO_INCREMENT = 1  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';
```
####5 修改seata所需配置并推送到nacos
#####5.1 修改配置，seata-1.4.1\script\config-center\config.txt
```
service.vgroupMapping.tonytaotao_group=default
store.mode=db
store.db.datasource=druid
store.db.dbType=mysql
store.db.driverClassName=com.mysql.jdbc.Driver
store.db.url=jdbc:mysql://127.0.0.1:3306/seata?useUnicode=true
store.db.user=root
store.db.password=123456
```
#####5.2 推送到nacos，seata-1.4.1\script\config-center\nacos
```
打开git bash
sh nacos-config.sh -h 127.0.0.1 -p 8848 -t 命名空间ID -g SEATA_GROUP 
或 sh nacos-config.sh -h 127.0.0.1 -p 8848 -g SEATA_GROUP
```
####6 修改seata服务端配置
#####6.1 修改registry.conf，seata-server-1.4.1\seata\conf\registry.conf
```
registry {
  type = "nacos"
  loadBalance = "RandomLoadBalance"
  loadBalanceVirtualNodes = 10

  nacos {
    application = "seata-server"
    serverAddr = "127.0.0.1:8848"
    group = "SEATA_GROUP"
    namespace = "seata"
    cluster = "default"
    username = "nacos"
    password = "nacos"
  }
}

config {
   type = "nacos"

   nacos {
    serverAddr = "127.0.0.1:8848"
    group = "SEATA_GROUP"
    namespace = "seata"
    username = "nacos"
    password = "nacos"
  }
}
```
#####6.2 修改file.conf，seata-server-1.4.1\seata\conf\file.conf
```
store {
    mode = "db"
    db {
        ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp)/HikariDataSource(hikari) etc.
        datasource = "druid"
        ## mysql/oracle/postgresql/h2/oceanbase etc.
        dbType = "mysql"
        driverClassName = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://127.0.0.1:3306/seata"
        user = "root"
        password = "123456"
        minConn = 5
        maxConn = 100
        globalTable = "global_table"
        branchTable = "branch_table"
        lockTable = "lock_table"
        queryLimit = 100
        maxWait = 5000
      }
}
```
####7 启动seata
```
seata-server.bat -p 8091 -m db
```



