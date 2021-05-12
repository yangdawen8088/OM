package com.zero2oneit.mall.common.query.member;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:
 *
 * @author Atzel
 * @date 2021/2/5 17:55
 */
@Data
public class MemberShareObject extends QueryObject {

    /**
     * 商品名称
     */
    private String title;

    /**
     * 购买人昵称
     */
    private String nickName;

    /**
     * 购买人手机号
     */
    private String memberPhone;

    //创建时间期间查询
    private String startTime;//开始时间
    private String endTime;//结束时间

}
