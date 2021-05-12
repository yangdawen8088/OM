package com.zero2oneit.mall.common.bean.member;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @author yjj
 * @date 2020/7/15 20:06
 */
@Data
@TableName(value = "member_info")
public class MemberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "member_id", type = IdType.ID_WORKER_STR)

    private String memberId;

    private String memberAccount;

    private String memberPassword;

    private String nickName;

    private String wxOpenid;

    private String wxApplteOpenid;

    private String memberName;

    private String memberAvatar;

    private String memberGrade;

    private Integer sex;

    private String payPwd;

    private String memberPhone;

    private String memberEmail;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;

    private Date registerTime;

    @TableField(strategy = FieldStrategy.IGNORED)
    private String stationId;

    private Integer gradeId;

    private String userQrCode;

    @TableField(strategy = FieldStrategy.IGNORED)
    private Integer bypassAccount;//1是子账号，0是主账号

    @TableField(exist = false) //表示该属性不为数据库表字段，但又是必须使用的。
    private String token;

    private Integer starId;

    private Integer statusId;
}
