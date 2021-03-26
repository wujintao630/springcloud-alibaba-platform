package com.tonytaotao.sca.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单信息对象
 * @author tonytaotao
 * @since 2019-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserOrderVO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long commodityId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;

}
