package com.zero2oneit.mall.common.query.member;

import com.zero2oneit.mall.common.utils.query.QueryObject;
import lombok.Data;

/**
 * Description:配置机制
 *
 * @author Atzel
 * @date 2020/9/2 15:39
 */
@Data
public class DistributionObject extends QueryObject {

    /**
     * 配置级别
     */
    private String configurationLevel;

    //测试
    private String type;



}
