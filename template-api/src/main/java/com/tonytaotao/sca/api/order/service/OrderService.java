package com.tonytaotao.sca.api.order.service;

import com.tonytaotao.sca.api.order.vo.PlaceOrderReq;

/**
 * <p>
 *  服务接口类
 * </p>
 *
 * @author tonytaotao
 * @since 2019-10-25
 */
public interface OrderService {

    Boolean  placeOrder(PlaceOrderReq placeOrderReq);

}
