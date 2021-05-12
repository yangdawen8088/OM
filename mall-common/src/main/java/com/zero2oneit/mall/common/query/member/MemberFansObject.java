package com.zero2oneit.mall.common.query.member;

import com.baomidou.mybatisplus.annotation.TableId;
import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description: 会员粉丝管理表
 *
 * @author Atzel
 * @date 2020/7/22 20:05
 */
@Data
public class MemberFansObject extends QueryObject {


    /**
     * 当前会员id
     */
    private String memberId;

    private int grade;

}
