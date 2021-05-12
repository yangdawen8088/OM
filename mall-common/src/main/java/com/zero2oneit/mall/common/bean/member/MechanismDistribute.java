package com.zero2oneit.mall.common.bean.member;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description:机制配置表
 *
 * @author Atzel
 * @date 2020/9/2 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_mechanism_distribute")
public class MechanismDistribute<integral> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置id
     */
    private Long id;

    /**
     * 配置级别
     */
    private String configurationLevel;

    /**
     * 一级
     */
    private BigDecimal oneLevel;

    /**
     * 二级
     */
    private BigDecimal twoLevel;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 商场开关: 0-关，1-开
     */
    private Integer marketKg;

    /**
     * 驿站开关
     */
    private Integer postKg;

    /**
     * 候鸟开关
     */
    private Integer migrantKg;
}
