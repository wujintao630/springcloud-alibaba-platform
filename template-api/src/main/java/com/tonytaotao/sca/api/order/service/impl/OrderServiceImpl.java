package com.tonytaotao.sca.api.order.service.impl;

import com.tonytaotao.sca.api.order.vo.PlaceOrderReq;
import com.tonytaotao.sca.api.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务接口实现类
 * </p>
 *
 * @author tonytaotao
 * @since 2019-10-25
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public Boolean placeOrder(PlaceOrderReq placeOrderReq) {


        return Boolean.FALSE;

    }
}
