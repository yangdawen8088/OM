package com.zero2oneit.mall.common.query.member;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:
 *
 * @author leon
 * @date 2020/8/8 17:11
 */
@Data
public class MemberShareQueryObject extends QueryObject {
    private Integer status;
    private String memberId;
    private Integer level;

}
