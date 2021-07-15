package com.tonytaotao.scp.api.order.service;

import com.tonytaotao.scp.api.order.vo.PlaceOrderReq;
import com.tonytaotao.scp.common.base.GlobalResult;

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
