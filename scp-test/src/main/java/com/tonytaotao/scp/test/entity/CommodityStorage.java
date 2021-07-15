package com.tonytaotao.scp.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品库存表
 * @author tonytaotao
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CommodityStorage extends Model<CommodityStorage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    private Integer commodityId;

    /**
     * 库存数量
     */
    private Integer storageAmount;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
