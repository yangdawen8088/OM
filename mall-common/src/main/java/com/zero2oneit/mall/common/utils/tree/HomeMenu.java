package com.zero2oneit.mall.common.utils.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class HomeMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String icon;
    private String url;
    private int is_home;
    private int is_out;
    private String pid;
    public List<HomeMenu> children;//孩子节点

    public void addChild(HomeMenu baseTreeNode) {
        if (this.children == null) {
            this.setChildren(new ArrayList());
        }
        this.getChildren().add(baseTreeNode);
    }

}
