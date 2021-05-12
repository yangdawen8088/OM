package com.zero2oneit.mall.system.server.entity;

import com.zero2oneit.mall.common.utils.tree.BaseTreeNode;
import lombok.Data;

/**
 * Description:
 *
 * @author Tg
 * @email zero2oneit@163.com
 * @date 2021/1/21
 */
@Data
public class SysServer extends BaseTreeNode {

    //级别：0-顶级 1- 一级 2-二级
    private int serverLevel;

}
