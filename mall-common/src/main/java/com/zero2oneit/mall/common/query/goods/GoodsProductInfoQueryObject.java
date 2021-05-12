package com.zero2oneit.mall.common.query.goods;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021-02-22
 */
@Data
public class GoodsProductInfoQueryObject extends QueryObject {

    private String status;

    private Integer flags;

    private Integer typeId;

    private Integer moudleId;

    private String productName;

    private String typeName;

    private String businessName;

    private String saleArea;

}
