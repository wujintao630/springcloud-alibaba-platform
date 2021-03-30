package com.tonytaotao.sca.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tonytaotao.sca.business.entity.UserOrder;
import com.tonytaotao.sca.business.mapper.UserOrderMapper;
import com.tonytaotao.sca.business.service.UserOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *  用户订单服务接口实现
 * @author tonytaotao
 */
@Service
@Slf4j
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {

}
