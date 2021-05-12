package com.zero2oneit.mall.common.utils.tree;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HomeMenuTreeUtils {

    /**
     * 获取关联父节点数据集合列表
     * 由对应的子节点到根节点(root)
     *
     * @param id        要搜索对应父节点节点的id
     * @param listNodes 要处理列表集合节点数据(不是组合成树状图后的数据)
     */
    public static <T extends HomeMenu> List<T> getParentList(List<T> listNodes, String id) {
        if (StringUtils.isEmpty(id) || CollectionUtils.isEmpty(listNodes)) {
            return null;
        }
        // 数据保存的对象
        List<T> treeNodes = new ArrayList<>();
        int length = listNodes.size();
        // 防止死循环问题
        byte[] nodeIndex = new byte[length];
        T t;
        for (int i = 0; i < length; i++) {
            t = listNodes.get(i);
            // 循环找到节点id,赋值id等于该节点pid
            if (id.equals(t.getId()) && nodeIndex[i] == 0) {
                nodeIndex[i] = 1;
                treeNodes.add(t);
                id = t.getPid();
                // 父主键为空,null,"0",结束循环
                if (StringUtils.isEmpty(id) || "null".equals(id) || "0".equals(id)) {
                    break;
                }
                i = -1;
            }
        }
        return treeNodes;
    }

    /**
     * 获取关联子节点数据集合列表
     * 由对应的子节点向子节点搜索
     *
     * @param listNodes 要处理列表集合节点数据(不是组合成树状图后的数据)
     * @param ids       要搜索对应子节点的id集合
     */
    public static <T extends HomeMenu> List<T> getChildList(List<T> listNodes, List<String> ids) {
        return CollectionUtils.isEmpty(ids) ? null : getChildList(listNodes, ids.toArray(new String[ids.size()]));
    }

    /**
     *
     * @param listNodes 要处理列表集合节点数据(不是组合成树状图后的数据)
     * @param id 要搜索对应子节点的id
     * @param <T>
     * @return
     */
    public static <T extends HomeMenu> List<T> getChildList(List<T> listNodes, String id) {
        return StringUtils.isEmpty(id) ? null : getChildList(listNodes, new String[]{id});
    }

    /**
     *
     * @param listNodes 要处理列表集合节点数据(不是组合成树状图后的数据)
     * @param ids 要搜索对应子节点的id(数组)
     * @param <T>
     * @return
     */
    public static <T extends HomeMenu> List<T> getChildList(List<T> listNodes, String[] ids) {
        if (ids == null || ids.length == 0 || CollectionUtils.isEmpty(listNodes)) {
            return null;
        }
        // 数据保存的对象
        List<T> treeNodes = new ArrayList<>();
        int length = listNodes.size();
        // 防止死循环问题
        byte[] nodeIndex = new byte[length];
        // 循环获取要获取节点
        T t;
        for (String id : ids) {
            for (int i = 0; i < length; i++) {
                t = listNodes.get(i);
                if (id.equals(t.getId())) {
                    treeNodes.add(t);
                    nodeIndex[i] = 1;
                }
            }
        }
        String tempId;
        int index = 0;
        while (index < treeNodes.size()) {
            tempId = treeNodes.get(index).getId();
            if (!StringUtils.isEmpty(tempId)) {
                for (int i = 0; i < length; i++) {
                    t = listNodes.get(i);
                    if (tempId.equals(t.getPid()) && nodeIndex[i] == 0) {
                        nodeIndex[i] = 1;
                        treeNodes.add(t);
                    }
                }
            }
            index++;
        }
        return treeNodes;
    }

    /**
     * 封装整个树状图数据
     *
     * @param listNodes 要处理列表集合节点数据
     */
    public static <T extends HomeMenu> List<T> assembleTree(List<T> listNodes) {
        List<T> newTreeNodes = new ArrayList<>();
        // 循环赋值最上面的节点数据
        // 赋值最上面节点的值
        newTreeNodes.addAll(listNodes.stream()
                .filter(t -> StringUtils.isEmpty(t.getPid()) || "null".equals(t.getPid()) || "0".equals(t.getPid()))
                .collect(Collectors.toList()));
        // 循环处理子节点数据
        for (T t : newTreeNodes) {
            //递归
            assembleTree(t, listNodes);
        }
        return newTreeNodes;
    }

    /**
     * 封装层单个树子节点数据
     *
     * @param id        根目录节点id
     * @param listNodes 要处理的列表数据
     */
    public static <T extends HomeMenu> T assembleTreeById(String id, List<T> listNodes) {
        if (StringUtils.isEmpty(id) || CollectionUtils.isEmpty(listNodes)) {
            return null;
        }
        // 获取对应的节点
        T node = null;
        for (T temp : listNodes) {
            if (id.equals(temp.getId())) {
                node = temp;
                break;
            }
        }
        assembleTree(node, listNodes);
        return node;
    }

    /**
     * 根据节点封装树状图集合数据
     *
     * @param node      处理的节点(当前节点)
     * @param listNodes 要处理的列表数据
     */
    static <T extends HomeMenu> void assembleTree(T node, List<T> listNodes) {
        if (node != null && !CollectionUtils.isEmpty(listNodes)) {
            // 循环节点数据，如果是子节点则添加起来
            listNodes.stream().filter(t -> Objects.equals(t.getPid(), node.getId())).forEachOrdered(node::addChild);
            // 循环处理子节点数据,递归
            if (!CollectionUtils.isEmpty(node.getChildren())) {
                for (Object t : node.getChildren()) {
                    //递归
                    assembleTree((T) t, listNodes);
                }
            }
        }
    }

    /**
     * 主键输出
     *
     * @param treeNodes 节点
     * @return String 注解集合
     */
    public static <T extends HomeMenu> String idToString(List<T> treeNodes) {
        return idToString(treeNodes, ",");
    }

    /**
     * 主键输出
     *
     * @param treeNodes 节点
     * @param c       拼接字符串
     * @return String 注解集合
     */
    public static <T extends HomeMenu> String idToString(List<T> treeNodes, String c) {
        StringBuilder pks = new StringBuilder();
        if (treeNodes != null) {
            for (T t : treeNodes) {
                pks.append(t.getId()).append(c);
            }
        }
        return pks.length() > 0 ? pks.delete(pks.length() - c.length(), pks.length()).toString() : "";
    }

}
