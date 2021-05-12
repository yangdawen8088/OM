/**
 * 表单通用CURD操作
 */

/**
 * 加载列表数据
 * @param requestUrl ：请求路径
 * @param columns 表头信息
 */
zero2one.table = function (requestUrl, columns) {
    zero2one.datagrid("grid_data", requestUrl,function (params) {
        return $.extend(zero2one.tableInitParam(params), zero2one.form2json("searchForm"));
    }, columns);
};

/**
 * 列表参数查询重新加载
 */
zero2one.query = function () {
    $("#searchFormBtn").click(function () {
        $('#grid_data').bootstrapTable('refresh');
    });
};

/**
 * 批量删除：删除一个或多个
 * @param requestUrl
 */
zero2one.delete = function (requestUrl) {
    $("#deleteBtn").click(function () {
        var datas = $('#grid_data').bootstrapTable('getSelections');
        if(datas.length > 0){
            $.messager.confirm("温馨提示", "您确定要删除选中的数据吗?", function () {
                zero2one.request(requestUrl,{"ids": zero2one.array2str(datas)},function (data) {
                    window.location.reload();
                });
            })
        }else{
            $.messager.alert("温馨提示", "请选择一条或多条数据操作");
        }
    });
};

/**
 * 点击添加弹出模态框
 */
zero2one.addModal = function () {
    $("#addBtn").click(function () {
        $("#addOrEditForm")[0].reset();
        $("input[type='hidden']").val("");//清空hidden值
        $("#addOrEditModal").modal("show");
    });
};

/**
 * 点击编辑弹出模态框
 */
zero2one.editModal = function () {
    $("#editBtn").click(function () {
        var datas = $('#grid_data').bootstrapTable('getSelections');
        if(datas.length == 1){
            $("#addOrEditForm").formloader({data: datas[0]});
        }else{
            $.messager.alert("温馨提示", "请选择一条数据操作");
            return;
        }
        $("#addOrEditModal").modal("show");
    });
};

/**
 * 点击保存按钮提交表单
 */
zero2one.save = function (requestUrl) {
    $("#saveBtn").click(function () {
        var bv = $('#addOrEditForm').data('bootstrapValidator');
        bv.validate();
        if (bv.isValid()) {
            zero2one.request(requestUrl, $("#addOrEditForm").serialize(),function (data) {
                if (data.success) {
                    window.location.reload();
                }else {
                    $.messager.alert("温馨提示", data.msg)
                }
            });
        }
    });
};