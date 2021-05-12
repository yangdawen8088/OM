package com.zero2oneit.mall.common.query.member;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description: 会员上级管理表
 *
 * @author Atzel
 * @date 2020/7/23 10:17
 */
@Data
public class memberLeadersObject extends QueryObject {


    /**
     * 当前会员id
     */
    private String memberId;
}
