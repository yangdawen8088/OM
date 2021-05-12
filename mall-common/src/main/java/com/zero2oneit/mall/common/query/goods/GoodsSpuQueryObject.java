package com.zero2oneit.mall.common.query.goods;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-03-08
 */
@Data
public class GoodsSpuQueryObject extends QueryObject {

    private String status;

    private Integer flags;

    private String productName;

    private String businessName;

}
