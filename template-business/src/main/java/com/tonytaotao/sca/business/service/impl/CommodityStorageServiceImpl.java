package com.tonytaotao.sca.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tonytaotao.sca.business.entity.CommodityStorage;
import com.tonytaotao.sca.business.mapper.CommodityStorageMapper;
import com.tonytaotao.sca.business.service.CommodityStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 商品库存服务接口实现
 * @author tonytaotao
 */
@Service
@Slf4j
public class CommodityStorageServiceImpl extends ServiceImpl<CommodityStorageMapper, CommodityStorage> implements CommodityStorageService {

}
