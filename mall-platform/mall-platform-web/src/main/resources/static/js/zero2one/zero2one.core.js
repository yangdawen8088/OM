/**
 * 加载表格数据
 * @param tableId ：表格id
 * @param url ：请求路径
 * @param queryParams ：查询参数
 * @param columns ：配置表头
 */
zero2one.datagrid = function(tableId, url, queryParams, columns){

    $('#'+tableId).bootstrapTable({
        classes: 'table table-bordered table-hover table-striped',
        url: url,
        method: 'post',
        dataType : 'json',        // 因为本示例中是跨域的调用,所以涉及到ajax都采用jsonp,
        uniqueId: 'id',
        idField: 'id',             // 每行的唯一标识字段
        toolbar: '#toolbar',       // 工具按钮容器
        singleSelect: false,        //开启单选,想要获取被选中的行数据必须要有该参数
        clickToSelect: true,     // 是否启用点击选中行
        showColumns: false,         // 是否显示所有的列
        showRefresh: false,         // 是否显示刷新按钮
        showToggle: false,        // 是否显示详细视图和列表视图的切换按钮(clickToSelect同时设置为true时点击会报错)
        pagination: true,                    // 是否显示分页
        sortOrder: "desc",                    // 排序方式
        queryParams: queryParams,                                   // 传递参数
        sidePagination: "server",            // 分页方式：client客户端分页，server服务端分页
        pageNumber: 1,                       // 初始化加载第一页，默认第一页
        pageSize: 10,                        // 每页的记录行数
        pageList: [10, 25, 50, 100],         // 可供选择的每页的行数
        //search: true,                      // 是否显示表格搜索，此搜索是客户端搜索
        //showExport: true,        // 是否显示导出按钮, 导出功能需要导出插件支持(tableexport.min.js)
       // exportDataType: "basic", // 导出数据类型, 'basic':当前页, 'all':所有数据, 'selected':选中的数据
        columns: columns,
        onLoadSuccess: function(data){
            $("[data-toggle='tooltip']").tooltip();
            if(data.msg != undefined){
                if(data.msg == "gq"){
                    window.parent.location.reload();
                    return;
                }
                if(data.msg == "wh"){
                    window.location.href = "/wh.html";
                }
            }
        }
    });

};

/**
 * 加载表格数据可回调
 * @param tableId ：表格id
 * @param url ：请求路径
 * @param queryParams ：查询参数
 * @param columns ：配置表头
 */
zero2one.datagrids = function(tableId, url, queryParams, columns, callback){

    $('#'+tableId).bootstrapTable({
        classes: 'table table-bordered table-hover table-striped',
        url: url,
        method: 'post',
        dataType : 'json',        // 因为本示例中是跨域的调用,所以涉及到ajax都采用jsonp,
        uniqueId: 'id',
        idField: 'id',             // 每行的唯一标识字段
        toolbar: '#toolbar',       // 工具按钮容器
        singleSelect: false,        //开启单选,想要获取被选中的行数据必须要有该参数
        clickToSelect: false,     // 是否启用点击选中行  singleSelect、clickToSelect 都为false表示多选
        showColumns: false,         // 是否显示所有的列
        showRefresh: false,         // 是否显示刷新按钮
        showToggle: false,        // 是否显示详细视图和列表视图的切换按钮(clickToSelect同时设置为true时点击会报错)
        pagination: true,                    // 是否显示分页
        sortOrder: "desc",                    // 排序方式
        queryParams: queryParams,                                   // 传递参数
        sidePagination: "server",            // 分页方式：client客户端分页，server服务端分页
        pageNumber: 1,                       // 初始化加载第一页，默认第一页
        pageSize: 10,                        // 每页的记录行数
        pageList: [10, 25, 50, 100],         // 可供选择的每页的行数
        //search: true,                      // 是否显示表格搜索，此搜索是客户端搜索
        //showExport: true,        // 是否显示导出按钮, 导出功能需要导出插件支持(tableexport.min.js)
       // exportDataType: "basic", // 导出数据类型, 'basic':当前页, 'all':所有数据, 'selected':选中的数据
        columns: columns,
        onLoadSuccess: function(data){
            $("[data-toggle='tooltip']").tooltip();
            if(data.msg != undefined){
                if(data.msg == "gq"){
                    window.parent.location.reload();
                    return;
                }
                if(data.msg == "wh"){
                    window.location.href = "/wh.html";
                }
            }
            callback(data);
        }
    });

};

/**
 * 表格单击只选择一行
 * @param tableId
 */
zero2one.girdClick = function(tableId){
    $('#'+tableId).on('click-row.bs.table', function (e,row,$element) {
        $('#'+tableId).bootstrapTable("uncheckAll");
    });
};

zero2one.reload = function(tableId){
    $('#'+tableId).bootstrapTable('refresh');
};

zero2one.success = function(data){
    if (data.success) {
        $.messager.alert("温馨提示", "操作成功,2S之后关闭");
        setTimeout(function () {
            window.location.reload();
        }, 2000);
    }
};

/**
 * 转换时间格式 yyyy-MM-dd HH:mm:ss
 * @param dateVal ：时间值
 * @returns {string}
 */
zero2one.timestamp2Str = function(dateVal) {
    if(undefined == dateVal){
        return;
    }
    var d = new Date(dateVal).toJSON();
    return new Date(+new Date(d)+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');
};

/**
 * <pre>
 * 将表单数据转换成JSON数据格式
 * @param formId
 * @returns
 * </pre>
 */
zero2one.form2json = function(formId) {
    var arr = $("#"+formId).serializeArray();
    var data = {};
    $.each(arr, function(){data[this.name]=this.value;});
    return data;
};

/**
 * <pre>
 * 将表单数据转换成JSON数据格式
 * @param params
 * @returns
 * </pre>
 */
zero2one.tableInitParam = function(params) {
    var initParam = {
        pageSize: params.limit,         // 每页数据量
        currentPage: (params.offset / params.limit) + 1
    };
    return initParam;
};

/**
 * json数组转字符串
 * @param arr
 * @returns {string}
 */
zero2one.array2str = function(arr) {
    var str="";
    $.each(arr,function (i,v) {
        str += v.id+",";
    });
    return str.substring(0,str.length-1);
};

/**
 * 动态根据参数名称将json数组转字符串
 * @param arr
 * @param paramName
 * @returns {string}
 */
zero2one.dynamicArray2str = function(arr, paramName) {
    var str="";
    for (var i = 0; i < arr.length; i++) {
        str += arr[i][paramName]+",";
    }
    return str.substring(0,str.length-1);
};

/**
 * json数组转字符串
 * @param arr
 * @returns {string}
 */
zero2one.r = function() {
    var timestamp=new Date().getTime();
    return timestamp;
};

/**
 * <pre>
 * 表单带有文件上传发送请求
 * @param formId 表单ID
 * @param requestUrl 请求的地址
 * @param params FormData对象
 * @param callback 请求成功后的回调函数
 * </pre>
 */
zero2one.requestU = function(formId, requestUrl, params, callback){

    //组装表单参数
    $.each($("#"+formId).serializeArray(), function(){params.append(this.name, this.value)});

    $.ajax({
        type: 'post',
        url: requestUrl,
        data: params,
        dataType: 'json',
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
        success: function(data){
            if(data.msg == "gq"){
                window.parent.location.reload();
                return;
            }
            if(data.msg == "wh"){
                window.location.href = "/wh.html";
                return;
            }
            if(!data.success){
                $.messager.alert("温馨提示", data.msg);
                return;
            }
            if (callback) {
                callback(data);
            }
        }
    });
};

/**
 * <pre>
 * 发送请求
 * @param requestUrl 请求的地址
 * @param params 请求参数
 *          1. 字符串  "id=1&name=admin"
 *          2. 对象    {id:1, name:"admin"}
 *          3. 其他    erp.id("testListForm").serialize() + "&id=1&name=admin"
 *          （可把一个或多个form转换成字符串参数，并可拼上一些自定义的字符串参数）
 * @param callback 请求成功后的回调函数
 * </pre>
 */
zero2one.request = function(requestUrl, params, callback){
    $.ajax({
        type: 'post',
        url: requestUrl,
        data: params,
        cache: false,
        dataType: 'json',
        success: function(data){
            if(data.msg == "gq"){
                window.parent.location.reload();
                return;
            }
            if(data.msg == "wh"){
                window.location.href = "/wh.html";
                return;
            }
            if(!data.success){
                $.messager.alert("温馨提示", data.msg);
                return;
            }
            if (callback) {
                callback(data);
            }
        }
    });
};

/**
 * <pre>
 * 发送请求
 * @param requestUrl 请求的地址
 * @param params 请求参数
 *          1. 字符串  "id=1&name=admin"
 *          2. 对象    {id:1, name:"admin"}
 *          3. 其他    erp.id("testListForm").serialize() + "&id=1&name=admin"
 *          （可把一个或多个form转换成字符串参数，并可拼上一些自定义的字符串参数）
 * @param callback 请求成功后的回调函数
 * </pre>
 */
zero2one.requestV = function(requestUrl, params, callback){
    $.ajax({
        type: 'post',
        url: requestUrl,
        data: params,
        cache: false,
        dataType: 'json',
        success: function(data){
            if(data.msg == "gq"){
                window.parent.location.reload();
                return;
            }
            if(data.msg == "wh"){
                window.location.href = "/wh.html";
                return;
            }
            if (callback) {
                callback(data);
            }
        }
    });
};

/**
 * 在iframe子页面打开一个选项卡
 * @param title
 * @param url
 */
zero2one.tab = function (title, url, did) {
     parent.$(parent.document).data('multitabs').create({
         iframe : true,   //指定为iframe模式，当值为false的时候，为智能模式，自动判断（内网用ajax，外网用iframe）。缺省为false。
         title : title,   //标题（可选），没有则显示网址
         url : url,        //链接（必须），如为外链，强制为info页
         did: did //指定生成的tab的id
     }, true);            //true 则激活新增的tab页
};


/**
 * 001.关闭某个tab (通过id或者关闭当前) 并刷新上一个tab 默认会刷新
 * @param {Object} id: 关闭哪个data-id
 * @param {Boolean} refresh: 是否刷新goId
 * @param {String} goId : 关闭tab后 激活哪个tab
 * 子页面 使用top.common.closeTab  或者parent.common.closeTab
 */
zero2one.closeTab = function(id, refresh, goId) {
    var $tabs = $(document).data("multitabs") || parent.$(parent.document).data("multitabs");
    if (!$tabs) return;
    refresh = refresh === false ? false : true;
    var $navTab;
    var $navPanelList = $tabs.$element.navPanelList;
    if (id) {
        //参考源码中的 _exist 方法
        $navTab = $navPanelList.find('a[data-id="' + id + '"]:first');
    } else { //没有传入id则关闭当前激活的tab
        //参见源码中的active方法
        $navTab = $navPanelList.find('li.active:first a');
    }
    if(goId && refresh) { //激活某个id
        $navPanelList.find('a[data-id="' + goId + '"]:first').trigger("click");
    }
    $tabs.close($navTab);
};

/*
** 时间戳转换成指定格式日期
** eg.
** dateFormat(11111111111111, 'Y年m月d日 H时i分')
** → "2322年02月06日 03时45分"
*/
zero2one.dateFormat = function (timestamp, formats) {
    if(timestamp == null || timestamp.length == 0){
        return ;
    }
    // formats格式包括
    // 1. Y-m-d
    // 2. Y-m-d H:i:s
    // 3. Y年m月d日
    // 4. Y年m月d日 H时i分
    formats = formats || 'Y-m-d';

    var zero = function (value) {
        if (value < 10) {
            return '0' + value;
        }
        return value;
    };

    var myDate = timestamp? new Date(timestamp): new Date();

    var year = myDate.getFullYear();
    var month = zero(myDate.getMonth() + 1);
    var day = zero(myDate.getDate());

    var hour = zero(myDate.getHours());
    var minite = zero(myDate.getMinutes());
    var second = zero(myDate.getSeconds());

    return formats.replace(/Y|m|d|H|i|s/ig, function (matches) {
        return ({
            Y: year,
            m: month,
            d: day,
            H: hour,
            i: minite,
            s: second
        })[matches];
    });
};

/**
 * 根据参数名称获取URL中的参数值
 * @param paramName
 * @returns {string|null}
 */
zero2one.getUrlParam = function(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
};