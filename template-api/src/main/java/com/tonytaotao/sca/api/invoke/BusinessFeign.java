package com.tonytaotao.sca.api.invoke;

import com.tonytaotao.sca.common.vo.UserAccountVO;
import com.tonytaotao.sca.common.base.GlobalResult;
import com.tonytaotao.sca.common.vo.UserOrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 业务模块调用
 *
 * @author wujintao
 */
@FeignClient(name = "template-business")
public interface BusinessFeign {

    /**
     * 获取用户账户信息
     * @param id
     * @return
     */
    @GetMapping("/business/account/getUserAccountDetail/{id}")
    GlobalResult<UserAccountVO> getUserAccount(@PathVariable("id") Long id);

    /**
     * 扣减用户余额
     * @param userAccountVO
     * @return
     */
    @PostMapping("/business/account/saveOrUpdateUserAccount")
    GlobalResult<String> subUserAccountBalance(@RequestBody UserAccountVO userAccountVO);

    /**
     * 增加订单
     * @param userOrderVO
     * @return
     */
    @PostMapping("/business/order/saveOrUpdateOrder")
    GlobalResult<String> addUserOrder(@RequestBody UserOrderVO userOrderVO);

}
