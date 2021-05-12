package com.zero2oneit.mall.common.bean.auth;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * app 微信授权
 * @Title: AppWxLoginObject
 * @Description:
 * @Auther:zhj
 * @Version: 1.0
 * @create 2020/7/18 16:56
 */
@Data
public class AppWxLoginObject extends QueryObject {

    private String openId;
    private String nickname;
    private String avatarUrl;
    private String unionId;
    private Integer gender;
    private String phone;
    private String code;
    private String password;
    private String type;
    private String invitation;
    private String wxApplteOpenid;

}
