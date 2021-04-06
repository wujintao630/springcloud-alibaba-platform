package com.tonytaotao.spring.cloud.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tonytaotao
 */
@ConfigurationProperties(prefix = TonytaotaoSwaggerProperties.PREFIX, ignoreUnknownFields = true)
public class TonytaotaoSwaggerProperties {

    static final String PREFIX = "tonytaotao.swagger";

    /**
     * 扫描路径
     */
    private String basePackage;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 版本号
     */
    private String version;

    /**
     * 服务地址
     */
    private String serviceUrl;

    /**
     * 开源协议
     */
    private String license;

    /**
     * 开源协议地址
     */
    private String licenseUrl;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }
}
