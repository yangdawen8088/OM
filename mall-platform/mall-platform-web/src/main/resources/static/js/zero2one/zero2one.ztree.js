/**
 * 获取当前选中节点下面的所有子节点（包含自己）
 * @param treeId
 * @param treeNode
 * @returns {string}
 */
zero2one.getChildNodes = function (treeId, treeNode) {
    //var ztree = $.fn.zTree.getZTreeObj(treeId);//获取树对象
    var childNodes = $.fn.zTree.getZTreeObj(treeId).transformToArray(treeNode);//用ztree的方法transformToArray()获取当前选中节点（含选中节点）的子节点对象集合。
    var nodes = [];
    for(i = 0; i < childNodes.length; i++) {
        nodes[i] = childNodes[i].id;
    }
    return nodes.join(",");
};



/**
 * 获取当前选中权限节点下面的所有子节点（包含自己）
 * @param treeId
 * @returns {string}
 */
zero2one.getCheckedNodes = function (treeId, treeNode) {
    var childNodes = $.fn.zTree.getZTreeObj(treeId).getChangeCheckedNodes();
    var nodes = "";
    if(childNodes.length > 0){
        for(i = 0; i < childNodes.length; i++) {
            if(childNodes[i].functionId != null){
                nodes += childNodes[i].functionId +",";
            }
        }
    }
    return nodes.length == 0 ? nodes : nodes.substring(0,nodes.length-1);
};

/**
 * 获取当前选中权限节点下面的所有子节点（包含自己）
 * @param treeId
 * @returns {string}
 */
zero2one.getCheckedUserNodes = function (treeId) {
    var childNodes = $.fn.zTree.getZTreeObj(treeId).getChangeCheckedNodes();
    var nodes = "";
    if(childNodes.length > 0){
        for(i = 0; i < childNodes.length; i++) {
            if(childNodes[i].userId != null){
                nodes += childNodes[i].userId +",";
            }
        }
    }
    return nodes.length == 0 ? nodes : nodes.substring(0,nodes.length-1);
};
