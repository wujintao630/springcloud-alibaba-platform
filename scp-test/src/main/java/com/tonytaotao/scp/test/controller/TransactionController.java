package com.tonytaotao.scp.test.controller;

import com.tonytaotao.scp.common.base.GlobalResult;
import com.tonytaotao.scp.test.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tonytaotao
*/
@RestController
@RequestMapping("/transaction")
@Slf4j
public class TransactionController {

    @Autowired
    TransactionService userOrderService;

    /**
     * 事务测试
     * @return
     */
    @RequestMapping("/testTransaction")
    public GlobalResult<String> testTransaction() throws Exception {
        userOrderService.testTransaction();
        return GlobalResult.DefaultSuccess();
    }


}
