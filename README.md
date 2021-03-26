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