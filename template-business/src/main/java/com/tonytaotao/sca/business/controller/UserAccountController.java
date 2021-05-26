package com.tonytaotao.sca.business.controller;

import com.tonytaotao.sca.business.entity.UserAccount;
import com.tonytaotao.sca.business.service.UserAccountService;
import com.tonytaotao.sca.common.base.GlobalResult;
import com.tonytaotao.sca.common.vo.UserAccountVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户账户API
 * @author tonytaotao
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    /**
    *获取详细信息
     * @param id
     * @return
    */
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "Path")
    @GetMapping("/getUserAccountDetail/{id}")
    public GlobalResult<UserAccountVO> getUserAccountDetailById(@PathVariable Long id) {

        com.tonytaotao.sca.business.entity.UserAccount userAccount = userAccountService.getById(id);
        if (userAccount != null) {
            UserAccountVO userAccountVO = new UserAccountVO();
            BeanUtils.copyProperties(userAccount, userAccountVO);
            return GlobalResult.DefaultSuccess(userAccountVO);
        }
        return GlobalResult.DefaultFailure("数据不存在");

    }

    /**
    * 扣减账户余额
     * @param userAccountVO
     *@return
    */
    @ApiOperation(value = "待扣减账户余额信息", notes = "待扣减账户余额信息")
    @ApiImplicitParam(name = "entity", value = "要操作的json对象", required = true, paramType = "body", dataType = "UserAccountVO")
    @PostMapping("/subUserAccountBalance")
    public GlobalResult<String> subUserAccountBalance(@RequestBody UserAccountVO userAccountVO) {
        UserAccount userAccount = userAccountService.getById(userAccountVO.getId());
        if (userAccount.getBalance().compareTo(userAccountVO.getBalance()) == -1) {
            return GlobalResult.DefaultFailure("余额不足");
        }

        userAccount.setId(userAccountVO.getId());
        userAccount.setBalance(userAccount.getBalance().subtract(userAccountVO.getBalance()));
        userAccountService.updateById(userAccount);
        return GlobalResult.DefaultSuccess();
    }



}
