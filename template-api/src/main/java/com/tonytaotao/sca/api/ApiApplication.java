package com.tonytaotao.sca.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tonytaotao
 * @SpringBootApplication 为了防止某些配置没有扫描到，可以指定包的扫描路径
 * @EnableAspectJAutoProxy 激活Aspect 自动代理 <aop:aspectj-autoproxy/>
 *      proxy-target-class : 默认为false 表示使用JDK 动态代理。如果实现了至少一个接口，Spring 会优先选择使用JDK 动态代理，若果没有实现任何接口，则spring 会选择CGLIB 动态代理，强制使用CGLIB 动态代理，使用以下配置
 *      exposeProxy : springAOP 只会拦截public 方法，不会拦截provided 和private 方法，并且不会拦截public 方法内部调用的其他方法，也就是说只会拦截代理对象的方法，即增强的是代理对象，而不是原对象, 设置就可以暴露出代理对象，拦截器会获取代理对象，并且将代理对象转换成原对象。从而对内部调用的方法进行增强
 *@EnableFeignClients  一定要指明Feign接口所在的包路径,不然就会报@Autowired异常
 */
@SpringBootApplication(scanBasePackages = "com.tonytaotao")
@RestController
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableFeignClients(basePackages = {"com.tonytaotao.sca.api.invoke"})
@EnableDiscoveryClient
public class ApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}
