package com.tonytaotao.sca.api.order.controller;

import com.tonytaotao.sca.api.order.vo.PlaceOrderReq;
import com.tonytaotao.sca.common.base.GlobalResult;
import com.tonytaotao.sca.api.order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*      前端控制器
* </p>
 *
 * @author tonytaotao
 * @since 2019-10-25
*/
@RestController
@RequestMapping("/order/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService service;

    /**
     *下订单
     * @param placeOrderReq
     * @return
     */
    @ApiOperation(value = "下订单", notes = "下订单")
    @PostMapping("/placeOrder")
    public GlobalResult placeOrder(@RequestBody @ApiParam(value = "下单请求json对象", required = true) @Validated PlaceOrderReq placeOrderReq) {
        return GlobalResult.DefaultSuccess(service.placeOrder(placeOrderReq));
    }

}
