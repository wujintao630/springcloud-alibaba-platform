package com.tonytaotao.sca.api.order.service.impl;

import com.tonytaotao.sca.api.invoke.BusinessFeign;
import com.tonytaotao.sca.api.order.vo.PlaceOrderReq;
import com.tonytaotao.sca.api.order.service.OrderService;
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

        GlobalResult<UserAccountVO> userAccountResult = businessFeign.getUserAccount(placeOrderReq.getUserId());
        if (userAccountResult.isFailure()) {
            return GlobalResult.DefaultFailure(userAccountResult.getMsg());
        }

        UserAccountVO userAccountVO = userAccountResult.getData();
        if (userAccountVO.getBalance().compareTo(placeOrderReq.getTotalMoney()) == -1) {
            return GlobalResult.DefaultFailure("余额不足");
        }

        userAccountVO.setBalance(userAccountVO.getBalance().subtract(placeOrderReq.getTotalMoney()));
        businessFeign.subUserAccountBalance(userAccountVO);

        UserOrderVO userOrderVO = new UserOrderVO();
        userOrderVO.setUserId(placeOrderReq.getUserId());
        userOrderVO.setCommodityId(placeOrderReq.getCommodityId());
        userOrderVO.setQuantity(placeOrderReq.getQuantity());
        userOrderVO.setTotalMoney(placeOrderReq.getTotalMoney());
        businessFeign.addUserOrder(userOrderVO);

        return GlobalResult.DefaultSuccess();

    }
}
