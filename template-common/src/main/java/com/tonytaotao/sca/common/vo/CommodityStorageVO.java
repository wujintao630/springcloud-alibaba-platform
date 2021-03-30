package com.tonytaotao.sca.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品库存信息对象
 * @author tonytaotao
 * @since 2019-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommodityStorageVO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品ID
     */
    private Integer commodityId;

    /**
     * 库存数量
     */
    private Integer storageAmount;

}
