package com.tonytaotao.sca.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tonytaotao.sca.business.common.QueryPage;
import com.tonytaotao.sca.business.entity.UserOrder;
import com.tonytaotao.sca.business.service.UserOrderService;
import com.tonytaotao.sca.common.base.GlobalResult;
import com.tonytaotao.sca.common.vo.UserOrderVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户订单API
 * @author tonytaotao
*/
@RestController
@RequestMapping("/order")
@Slf4j
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;

    /**
    *获取详细信息
     * @param id
     * @return
    */
    @ApiOperation(value = "获取详细信息", notes = "根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "Path")
    @GetMapping("/getOrderDetail/{id}")
    public GlobalResult<com.tonytaotao.sca.business.entity.UserOrder> getOrderDetailById(@PathVariable Long id) {
        return GlobalResult.DefaultSuccess(userOrderService.getById(id));
    }

    /**
     *分页获取列表
     * @param query
     * @return
     */
    @ApiOperation(value = "分页获取详细信息，带上查询条件", notes = "根据查询条件分页获取明细")
    @PostMapping("/getOrderPage")
    public GlobalResult<IPage<List<com.tonytaotao.sca.business.entity.UserOrder>>> getOrderPage(@RequestBody @ApiParam(value = "查询条件json对象", required = true) QueryPage<com.tonytaotao.sca.business.entity.UserOrder> query) {
        IPage page = userOrderService.page(query.getPage(), new QueryWrapper<>(query.getQueryEntity()));
        return GlobalResult.DefaultSuccess(page);
    }

    /**
    * 新增或者更新信息
     * @param userOrderVO
     *@return
    */
    @ApiOperation(value = "新增或者更新信息", notes = "新增或者更新信息")
    @ApiImplicitParam(name = "entity", value = "要保存的json对象", required = true, paramType = "body", dataType = "UserOrderVO")
    @PostMapping("/saveOrUpdateOrder")
    public GlobalResult<String> saveOrUpdateOrder(@RequestBody UserOrderVO userOrderVO) {
        com.tonytaotao.sca.business.entity.UserOrder userOrder = new UserOrder();
        BeanUtils.copyProperties(userOrderVO, userOrder);
        userOrderService.saveOrUpdate(userOrder);
        return GlobalResult.DefaultSuccess();
    }

    /**
     *删除信息
     * @param id
     * @return
     */
    @ApiOperation(value = "删除信息", notes = "根据url的id来删除信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "Path")
    @DeleteMapping("/deleteOrderById/{id}")
    public GlobalResult<Boolean> deleteOrderById(@PathVariable Long id) {
        return GlobalResult.DefaultSuccess(userOrderService.removeById(id));
    }
}
