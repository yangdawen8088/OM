
zero2one.drop = function (id, paramId){
 //防止报"Dropzone already attached."的错误
Dropzone.autoDiscover = false;
// 上传广告图片
$("#"+id).dropzone({
    url: "/fileUpload/noticeFile",
    dictDefaultMessage: '点击上传图片',
    method: "post",  // 也可用put
    paramName: "file", // 默认为file
    maxFiles: 1,// 一次性上传的文件数量上限
    maxFilesize: 5, // 文件大小，单位：MB
    acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
    addRemoveLinks: true,
    parallelUploads: 1,// 一次上传的文件数量
    dictDefaultMessage: '拖动文件至此或者点击上传',
    dictMaxFilesExceeded: "您最多只能上传3个文件！",
    dictResponseError: '文件上传失败!',
    dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
    dictFallbackMessage: "浏览器不受支持",
    dictFileTooBig: "文件过大上传文件最大支持.",
    dictRemoveLinks: "删除",
    dictCancelUpload: "取消",
    init: function () {
        this.on("addedfile", function (file) {
        });
        this.on("success", function (file, data) {
            $("#"+paramId).val(data.data);
        });
        this.on("error", function (file, data) {
        });
        this.on("removedfile", function (file) {
            // 删除文件时触发的方法
            var fileUrl = $("#"+paramId).val();
            zero2one.requestV("/removeFile",{imageUlr: fileUrl},function (data) {
                $("#"+paramId).val("");
            });
        });
    }
});
};

zero2one.drop = function (id, paramId,message){
    //防止报"Dropzone already attached."的错误
    Dropzone.autoDiscover = false;
// 上传广告图片
    $("#"+id).dropzone({
        url: "/fileUpload/noticeFile",
        dictDefaultMessage: '点击上传图片',
        method: "post",  // 也可用put
        paramName: "file", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 5, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: message,
        dictMaxFilesExceeded: "您最多只能上传1个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        init: function () {
            this.on("addedfile", function (file) {
            });
            this.on("success", function (file, data) {
                $("#"+paramId).val(data.data);
            });
            this.on("error", function (file, data) {
            });
            this.on("removedfile", function (file) {
                // 删除文件时触发的方法
                var fileUrl = $("#"+paramId).val();
                zero2one.requestV("/removeFile",{imageUlr: fileUrl},function (data) {
                    $("#"+paramId).val("");
                });
            });
        }
    });
};


zero2one.drop1 = function (id, paramId,message){
    //防止报"Dropzone already attached."的错误
    Dropzone.autoDiscover = false;
// 上传广告图片
    $(document.getElementById(id)).dropzone({
        url: "/fileUpload/noticeFile",
        dictDefaultMessage: '点击上传图片',
        method: "post",  // 也可用put
        paramName: "file", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 5, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: message,
        dictMaxFilesExceeded: "您最多只能上传1个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        init: function () {
            this.on("addedfile", function (file) {
            });
            this.on("success", function (file, data) {
                $(document.getElementById(paramId)).val(data.data);
            });
            this.on("error", function (file, data) {
            });
            this.on("removedfile", function (file) {
                // 删除文件时触发的方法
                var fileUrl = $("#"+paramId).val();
                zero2one.requestV("/removeFile",{imageUlr: fileUrl},function (data) {
                    $(document.getElementById(paramId)).val("");
                });
            });
        }
    });
};
