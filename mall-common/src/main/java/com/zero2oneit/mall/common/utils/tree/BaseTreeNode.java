package com.zero2oneit.mall.common.utils.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BaseTreeNode implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String id;//节点id

    public String name;//节点名称

    public String parentId;//节点父id

    public Boolean open; //是否展开，默认true,展开

    public List<? super BaseTreeNode> children;//孩子节点

    public void addChild(BaseTreeNode baseTreeNode) {
        if (this.children == null) {
            this.setChildren(new ArrayList());
        }
        this.getChildren().add(baseTreeNode);
    }

}
