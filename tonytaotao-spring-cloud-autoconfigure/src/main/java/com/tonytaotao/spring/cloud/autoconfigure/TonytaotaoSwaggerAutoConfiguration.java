package com.tonytaotao.spring.cloud.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author tonytaotao
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "tonytaotao.swagger", name = "enabled", havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties(TonytaotaoSwaggerProperties.class)
public class TonytaotaoSwaggerAutoConfiguration {

    @Autowired
    private TonytaotaoSwaggerProperties tonytaotaoSwaggerProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(tonytaotaoSwaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(tonytaotaoSwaggerProperties.getTitle())
                .description(tonytaotaoSwaggerProperties.getDescription())
                .termsOfServiceUrl(tonytaotaoSwaggerProperties.getServiceUrl())
                .license(tonytaotaoSwaggerProperties.getLicense())
                .licenseUrl(tonytaotaoSwaggerProperties.getLicenseUrl())
                .version(tonytaotaoSwaggerProperties.getVersion())
                .build();
    }

}
