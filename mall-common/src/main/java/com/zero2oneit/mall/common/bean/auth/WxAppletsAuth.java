package com.zero2oneit.mall.common.bean.auth;

import com.zero2oneit.mall.common.bean.member.MemberInfo;
import lombok.Data;

/**
 * Description:
 *
 * @author yjj
 * @date 2021/3/5 17:07
 */
@Data
public class WxAppletsAuth extends MemberInfo {
    private String code;
    private String iv;
    private String encryptedData;
    private String unionId;
    private String invitation;
}
