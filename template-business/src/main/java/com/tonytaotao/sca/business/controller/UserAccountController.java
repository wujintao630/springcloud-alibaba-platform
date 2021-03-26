package com.tonytaotao.sca.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tonytaotao.sca.business.entity.UserAccount;
import com.tonytaotao.sca.business.service.UserAccountService;
import com.tonytaotao.sca.common.vo.UserAccountVO;
import com.tonytaotao.sca.common.base.GlobalResult;
import com.tonytaotao.sca.business.common.QueryPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
*      前端控制器
* </p>
 *
 * @author tonytaotao
 * @since 2019-10-22
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

        UserAccount userAccount = userAccountService.getById(id);
        if (userAccount != null) {
            UserAccountVO userAccountVO = new UserAccountVO();
            BeanUtils.copyProperties(userAccount, userAccountVO);
            return GlobalResult.DefaultSuccess(userAccountVO);
        }
        return GlobalResult.DefaultFailure("数据不存在");

    }

    /**
     *分页获取列表
     * @param query
     * @return
     */
    @ApiOperation(value = "分页获取详细信息，带上查询条件", notes = "根据查询条件分页获取明细")
    @PostMapping("/getUserAccountPage")
    public GlobalResult<IPage<List<UserAccount>>> getUserAccountPage(@RequestBody @ApiParam(value = "查询条件json对象", required = true) QueryPage<UserAccount> query) {
        IPage page = userAccountService.page(query.getPage(), new QueryWrapper<>(query.getQueryEntity()));
        return GlobalResult.DefaultSuccess(page);
    }

    /**
    * 新增或者更新信息
     * @param userAccountVO
     *@return
    */
    @ApiOperation(value = "新增或者更新信息", notes = "新增或者更新信息")
    @ApiImplicitParam(name = "userAccountVO", value = "要保存的json对象", required = true, paramType = "body", dataType = "UserAccount")
    @PostMapping("/saveOrUpdateUserAccount")
    public GlobalResult<String> saveOrUpdateUserAccount(@RequestBody UserAccountVO userAccountVO) {
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(userAccountVO, userAccount);
        userAccountService.saveOrUpdateUserAccount(userAccount);
        return GlobalResult.DefaultSuccess();
    }

    /**
     *删除信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除信息", notes = "根据url的id来删除信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "Path")
    @DeleteMapping("/deleteUserAccountById/{id}")
    public GlobalResult<Boolean> deleteUserAccountById(@PathVariable Long id) {
        return GlobalResult.DefaultSuccess(userAccountService.deleteUserAccountById(id));
    }

}
