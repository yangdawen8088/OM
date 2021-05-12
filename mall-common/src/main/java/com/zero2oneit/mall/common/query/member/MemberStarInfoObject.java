package com.zero2oneit.mall.common.query.member;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:会员星级信息查询
 *
 * @author Atzel
 * @date 2021/2/19 11:45
 */
@Data
public class MemberStarInfoObject extends QueryObject {

    //会员名称
    private String memberAccount;
}
