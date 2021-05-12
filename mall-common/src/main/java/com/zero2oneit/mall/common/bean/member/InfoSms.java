package com.zero2oneit.mall.common.bean.member;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author yjj
 * @date 2020/7/16 11:38
 */
@Data
public class InfoSms implements Serializable {
    private String memberId;
    private String msg;
    private String memberPhone;
    private String payPwd;
}
