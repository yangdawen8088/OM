package com.zero2oneit.mall.common.query.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author yjj
 * @date 2020/7/16 11:09
 */
@Data
public class InfoQueryObject extends QueryObject {

    //会员账号
    private String memberAccount;

    //会员昵称
    private String nickName;

    //联系电话
    private String memberPhone;

    //注册日期
    private String registerTime;

}
