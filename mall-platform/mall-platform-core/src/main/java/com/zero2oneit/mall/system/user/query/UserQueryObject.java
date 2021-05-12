package com.zero2oneit.mall.system.user.query;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

@Data
public class UserQueryObject extends QueryObject {

    private String username;

    private String account;

}
