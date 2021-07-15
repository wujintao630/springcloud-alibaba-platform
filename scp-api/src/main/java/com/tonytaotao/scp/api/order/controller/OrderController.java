package com.tonytaotao.scp.api.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.tonytaotao.scp.api.order.service.OrderService;
import com.tonytaotao.scp.api.order.vo.PlaceOrderReq;
import com.tonytaotao.scp.common.base.GlobalResult;
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
@RequestMapping("/order")
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
    @SentinelResource(value = "placeOrder", blockHandler = "blockHandlerException", fallback = "fallbackException")
    public GlobalResult placeOrder(@RequestBody @ApiParam(value = "下单请求json对象", required = true) @Validated PlaceOrderReq placeOrderReq) {

        try {
            GlobalResult<String> globalResult = service.placeOrder(placeOrderReq);
        } catch (Exception e) {
            return GlobalResult.DefaultFailure(e.getMessage());
        }

        return GlobalResult.DefaultSuccess();
    }

    /**
     * 限流
     * @return
     */
    public GlobalResult blockHandlerException(PlaceOrderReq placeOrderReq, BlockException blockException) {
        return GlobalResult.DefaultFailure("操作频繁，限流，请稍后重试");
    }

    /**
     * 熔断
     * @param placeOrderReq
     * @return
     */
    public GlobalResult fallbackException(PlaceOrderReq placeOrderReq) {
        return GlobalResult.DefaultFailure("服务异常，熔断，请稍后重试");
    }

}
