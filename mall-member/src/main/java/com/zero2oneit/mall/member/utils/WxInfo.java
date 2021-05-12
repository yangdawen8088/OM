package com.zero2oneit.mall.member.utils;

import lombok.Data;

/**
 * Description: 微信一键登录返回用户信息
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/4/22
 */
@Data
public class WxInfo {

    private Long memberId;

    private String openId;

    private String nickname;

    private String avatarUrl;

    private String unionId;

    private Integer gender;

    private String phoneNumber;

    private String type;

    private String code;

    private String encryptedData;

    private String iv;

}
