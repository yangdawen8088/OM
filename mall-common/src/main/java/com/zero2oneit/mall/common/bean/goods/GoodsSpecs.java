package com.zero2oneit.mall.common.bean.goods;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-05
 */
@Data
@TableName("goods_specs")
public class GoodsSpecs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId
    private Long id;
    /**
     * 规格名称
     */
    private String specsName;

}
