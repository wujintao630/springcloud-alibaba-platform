package com.tonytaotao.scp.test.service.impl;

import com.tonytaotao.scp.test.entity.UserAccount;
import com.tonytaotao.scp.test.entity.UserOrder;
import com.tonytaotao.scp.test.mapper.UserAccountMapper;
import com.tonytaotao.scp.test.mapper.UserOrderMapper;
import com.tonytaotao.scp.test.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author tonytaotao
 */
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TransactionService transactionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String testTransaction() throws Exception {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(2L);
        userOrder.setCommodityId(1L);
        userOrder.setQuantity(1);
        userOrder.setTotalMoney(BigDecimal.TEN);
        userOrderMapper.insert(userOrder);



            applicationContext.getBean(TransactionServiceImpl.class).addUserAccount();



        return "Success";
    }

    @Override
   // @Transactional(rollbackFor = Exception.class)
    public void addUserAccount() throws Exception {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(2L);
        userAccount.setBalance(BigDecimal.ONE);
        userAccountMapper.insert(userAccount);

        throw new Exception("test");
    }
}
