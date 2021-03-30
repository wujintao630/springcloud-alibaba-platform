package com.tonytaotao.sca.api.order.service.impl;

import com.tonytaotao.sca.api.invoke.BusinessFeign;
import com.tonytaotao.sca.api.order.vo.PlaceOrderReq;
import com.tonytaotao.sca.api.order.service.OrderService;
import com.tonytaotao.sca.common.vo.CommodityStorageVO;
import com.tonytaotao.sca.common.vo.UserAccountVO;
import com.tonytaotao.sca.common.base.GlobalResult;
import com.tonytaotao.sca.common.vo.UserOrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BusinessFeign businessFeign;

    @Override
    public GlobalResult<String> placeOrder(PlaceOrderReq placeOrderReq) {


        UserOrderVO userOrderVO = new UserOrderVO();
        userOrderVO.setUserId(placeOrderReq.getUserId());
        userOrderVO.setCommodityId(placeOrderReq.getCommodityId());
        userOrderVO.setQuantity(placeOrderReq.getQuantity());
        userOrderVO.setTotalMoney(placeOrderReq.getTotalMoney());
        businessFeign.addUserOrder(userOrderVO);

        UserAccountVO userAccountVO = new UserAccountVO();
        userAccountVO.setId(1L);
        userAccountVO.setBalance(placeOrderReq.getTotalMoney());
        businessFeign.subUserAccountBalance(userAccountVO);

        CommodityStorageVO commodityStorageVO = new CommodityStorageVO();
        commodityStorageVO.setId(1L);
        commodityStorageVO.setStorageAmount(placeOrderReq.getQuantity());

        return GlobalResult.DefaultSuccess();

    }
}
