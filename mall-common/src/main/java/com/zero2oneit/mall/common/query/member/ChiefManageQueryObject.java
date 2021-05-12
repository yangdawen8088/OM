package com.zero2oneit.mall.common.query.member;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:
 *
 * @author lf
 * @date 2021/4/28 18:14
 */
@Data
public class ChiefManageQueryObject extends QueryObject {

    private String memberId;
    private String pickName;
    private Integer statusId;

}
