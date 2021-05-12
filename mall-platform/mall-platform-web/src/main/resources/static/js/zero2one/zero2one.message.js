/**
 * 请求消息处理
 * @param data
 */
zero2one.msg = function (data) {
    if (data.success) {
        window.location.reload();
    } else if(data.msg == "gq"){
        window.parent.location.reload();
    } else{
        $.messager.alert("温馨提示", data.msg)
    }
};

zero2one.gq = function (data) {
    if(data.msg == "gq"){
        window.parent.location.reload();
        return;
    }
    if(data.msg == "wh"){
        window.location.href = "/wh.html";

    }
};

