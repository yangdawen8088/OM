/**
 * boostrap combox 插件
 * @param id : 操作文本框的id
 * @param url ：请求
 * @param paramId ：
 * @param paramNameId
 * @param aliasName
 */
zero2one.suggest = function (id, url, paramId, paramNameId,aliasName) {
    $("#"+id).bsSuggest({
            emptyTip: "未检索到匹配的数据",
            allowNoKeyword: false, //是否允许无关键字时请求数据。为 false 则无输入时不执行过滤请求
            multiWord: true, //以分隔符号分割的多关键字支持
            separator: ",", //多关键字支持时的分隔符，默认为空格
            getDataMethod: "url", //获取数据的方式，总是从 URL 获取
            effectiveFields:["id","name"],
            effectiveFieldsAlias:{id:"id",name: aliasName},
            hideOnSelect: true,
            url: url, //优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数
            fnProcessData: function(json) {
                // url 获取数据时，对数据的处理，作为 fnGetData 的回调函数
                var data = { value: [] };
                if (!json || !json.value || json.value.length === 0) {
                    return false;
                }
                //遍历数据
                $.each(json.value, function (i, v) {
                    data.value.push({id: v.id, name: v.name });
                });
                //字符串转化为 js 对象
                return data;
            }
        }).on("onSetSelectValue", function(e, keyword, data) {
            $("#"+paramId).val(data.id);
            $("#"+paramNameId).val(data.name);
    })

};

/**
 * boostrap combox 插件
 * @param id : 操作文本框的id
 * @param url ：请求
 * @param paramId ：
 * @param paramNameId
 * @param aliasName
 */
zero2one.suggest1 = function (id, url, paramId, paramNameId,aliasName) {
    $("#"+id).bsSuggest({
        emptyTip: "未检索到匹配的数据",
        allowNoKeyword: false, //是否允许无关键字时请求数据。为 false 则无输入时不执行过滤请求
        multiWord: true, //以分隔符号分割的多关键字支持
        separator: ",", //多关键字支持时的分隔符，默认为空格
        getDataMethod: "url", //获取数据的方式，总是从 URL 获取
        effectiveFields:["id","name"],
        effectiveFieldsAlias:{id:"商家id",name: aliasName},
        hideOnSelect: true,
        url: url, //优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数
        fnProcessData: function(json) {
            // url 获取数据时，对数据的处理，作为 fnGetData 的回调函数
            var data = { value: [] };
            if (! json.data || ! json.data.value ||  json.data.value.length === 0) {
                return false;
            }
            //遍历数据
            $.each( json.data.value, function (i, v) {
                data.value.push({id: v.id, name: v.name });
            });
            //字符串转化为 js 对象
            return data;
        }
    }).on("onSetSelectValue", function(e, keyword, data) {
        $("#"+paramId).val(data.id);
        $("#"+paramNameId).val(data.name);
    })

};

/**
 * boostrap combox 插件
 * @param id : 操作文本框的id
 * @param url ：请求
 * @param paramId ：
 * @param paramNameId
 * @param aliasName
 */
zero2one.suggest2 = function (id, url, paramId, paramNameId,aliasName,selectName) {
    $("#"+id).bsSuggest({
        emptyTip: "未检索到匹配的数据",
        allowNoKeyword: false, //是否允许无关键字时请求数据。为 false 则无输入时不执行过滤请求
        multiWord: true, //以分隔符号分割的多关键字支持
        separator: ",", //多关键字支持时的分隔符，默认为空格
        getDataMethod: "url", //获取数据的方式，总是从 URL 获取
        effectiveFields:["id","name"],
        effectiveFieldsAlias:{id:"id",name: aliasName},
        hideOnSelect: true,
        url: url, //优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数
        fnProcessData: function(json) {
            // url 获取数据时，对数据的处理，作为 fnGetData 的回调函数
            var data = { value: [] };
            if (! json.data || ! json.data.value ||  json.data.value.length === 0) {
                return false;
            }
            //遍历数据
            $.each( json.data.value, function (i, v) {
                data.value.push({id: v.id, name: v.stationName });
            });
            //字符串转化为 js 对象
            return data;
        }
    }).on("onSetSelectValue", function(e, keyword, data) {
        $("#"+paramId).val(data.id);
        $("#"+paramNameId).val(data.name);
        //查询该店铺的分类
        $.post("/remote/BusinCate/getByBusinessId",{businessId:data.id},function (res) {
            if (res.success){
                $("#b_type").empty();
                var tempArr = res.data;
                if (tempArr==null||tempArr.length===0){
                    $("#b_hide_div").show();
                    $("#b_show_div").hide();
                    return;
                }else {
                    $("#b_hide_div").hide();
                    $("#b_show_div").show();
                }
                var str='<option value="0">请选择商店分类</option>';
                $.each(tempArr,function (index,item) {
                    str+='<option value="'+item.id+'">'+item.name+'</option>'
                });
                $("#b_type").append(str);
            }
        })
    })

};


/**
 * boostrap combox 插件
 * @param id : 操作文本框的id
 * @param url ：请求
 * @param paramId ：
 * @param paramNameId
 * @param aliasName
 */
zero2one.suggest5 = function (id, url, paramId, paramNameId,aliasName) {
    $("#"+id).bsSuggest({
        emptyTip: "未检索到匹配的数据",
        allowNoKeyword: false, //是否允许无关键字时请求数据。为 false 则无输入时不执行过滤请求
        multiWord: true, //以分隔符号分割的多关键字支持
        separator: ",", //多关键字支持时的分隔符，默认为空格
        getDataMethod: "url", //获取数据的方式，总是从 URL 获取
        effectiveFields:["id","name"],
        effectiveFieldsAlias:{id:"id",name: aliasName},
        hideOnSelect: true,
        url: url, //优先从url ajax 请求 json 帮助数据，注意最后一个参数为关键字请求参数
        fnProcessData: function(json) {
            // url 获取数据时，对数据的处理，作为 fnGetData 的回调函数
            var data = { value: [] };
            if (!json || !json.value || json.value.length === 0) {
                return false;
            }
            //遍历数据
            $.each(json.value, function (i, v) {
                data.value.push({id: v.id, name: v.name });
            });
            //字符串转化为 js 对象
            return data;
        }
    }).on("onSetSelectValue", function(e, keyword, data) {
        $("#"+paramId).val(data.id);
        $("#"+paramNameId).val(data.name);
        $("#"+paramNameId).attr("readOnly","true");
    })

};
