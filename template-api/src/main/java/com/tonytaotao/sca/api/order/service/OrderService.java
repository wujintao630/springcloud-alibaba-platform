package com.tonytaotao.sca.api.order.service;

import com.tonytaotao.sca.api.order.vo.PlaceOrderReq;
import com.tonytaotao.sca.common.base.GlobalResult;

/**
 * <p>
 *  服务接口类
 * </p>
 *
 * @author tonytaotao
 * @since 2019-10-25
 */
public interface OrderService {

    GlobalResult<String> placeOrder(PlaceOrderReq placeOrderReq);

}
