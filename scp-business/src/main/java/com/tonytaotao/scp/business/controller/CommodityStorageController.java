package com.tonytaotao.scp.business.controller;

import com.tonytaotao.scp.business.entity.CommodityStorage;
import com.tonytaotao.scp.business.service.CommodityStorageService;
import com.tonytaotao.scp.common.base.GlobalResult;
import com.tonytaotao.scp.common.vo.CommodityStorageVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品库存API
 * @author tonytaotao
 */
@RestController
@RequestMapping("/commodityStorage")
@Slf4j
public class CommodityStorageController {

    @Autowired
    CommodityStorageService commodityStorageService;

    /**
    * 扣减商品库存
     * @param commodityStorageVO
     *@return
    */
    @ApiOperation(value = "待扣减商品库存信息", notes = "待扣减商品库存信息")
    @ApiImplicitParam(name = "entity", value = "要操作的json对象", required = true, paramType = "body", dataType = "CommodityStorageVO")
    @PostMapping("/subCommodityStorage")
    public GlobalResult<String> subCommodityStorage(@RequestBody CommodityStorageVO commodityStorageVO) {

        CommodityStorage commodityStorage = commodityStorageService.getById(commodityStorageVO.getId());
        if (commodityStorage.getStorageAmount() < commodityStorageVO.getStorageAmount()) {
            return GlobalResult.DefaultFailure("库存不足");
        }

        commodityStorage.setId(commodityStorageVO.getId());
        commodityStorage.setStorageAmount(commodityStorage.getStorageAmount() - commodityStorageVO.getStorageAmount());
        commodityStorageService.updateById(commodityStorage);
        return GlobalResult.DefaultSuccess();
    }
}
