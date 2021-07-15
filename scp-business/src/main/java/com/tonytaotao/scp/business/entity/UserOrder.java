package com.tonytaotao.scp.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户订单表
 * @author tonytaotao
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserOrder extends Model<UserOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
