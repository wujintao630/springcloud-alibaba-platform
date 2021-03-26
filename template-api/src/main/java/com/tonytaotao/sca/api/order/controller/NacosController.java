package com.tonytaotao.sca.api.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tonytaotao
 */
@RefreshScope
@RestController
@RequestMapping("/nacos")
@Slf4j
public class NacosController {

    @Value("${name}")
    private String name;


    @RequestMapping(value = "/getName", method = RequestMethod.GET)
    public String index() {
        return "name:" + name ;
    }

}
