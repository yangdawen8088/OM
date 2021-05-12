package com.zero2oneit.mall.common.query.member;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/1 17:31
 */
@Data
public class StationManageQueryObject extends QueryObject {
    private String stationName;
    private String memberName;
    private String phone;
}
