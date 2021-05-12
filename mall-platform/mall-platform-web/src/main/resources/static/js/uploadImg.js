/**
 * 初始化上传事件
 * @param className ：操作样式
 *        TODO 当表单中有多个上传图片功能时，className的值要查看css/uploadImg.css中的25行来操作
 * @param id ：操作域
 * @param imgSize ：图片大小
 * @param imgNum : 图片个数
 */
function uploadImg(className, id, imgSize, imgNum){

    var imgFile = []; //文件流
    var imgSrc = []; //图片路径
    var imgName = []; //图片名字

    // 鼠标经过显示删除按钮
    $(className).on('mouseover', '.content-img-list-item', function() {
        $(this).children('div').removeClass('hide');
    });

    // 鼠标离开隐藏删除按钮
    $(className).on('mouseleave', '.content-img-list-item', function() {
        $(this).children('div').addClass('hide');
    });

    // 单个图片删除
    $(className).on("click", '.content-img-list-item a .gcllajitong', function() {
        var index = $(this).parent().parent().parent().index();
        imgSrc.splice(index, 1);
        imgFile.splice(index, 1);
        imgName.splice(index, 1);
        if($("#addOrEditStatus").val() == 1){
            $("input[name='deleteUrl']").val($(this).parent().parent().prev()[0].currentSrc +","+ $("input[name='deleteUrl']").val());
        }
        $(this).parent().parent().parent().remove();
    });

    //显示原图片
    $(className).on("click", '.content-img-list-item a .gclfangda', function() {
        window.open().document.write("<img src="+$(this).parent().parent().prev()[0].currentSrc+" />");
    });

    //图片上传
    $('#'+ id).on('change', function(e) {
        var imgSize = this.files[0].size;
        var fileList = this.files;

        //限制图片大小、格式、上传个数
        if (imgSize > imgSize) {// 1024 * 500 * 4
            return alert("上传图片不能超过2M");
        }

        if (imgName.length > imgNum){
            return alert("最多可以上传"+ imgName.length + "张图片");
        }

        for (var i = 0; i < fileList.length; i++) {
            var imgSrcI = getObjectURL(fileList[i]);
            imgName.push(fileList[i].name);
            imgSrc.push(imgSrcI);
            imgFile.push(fileList[i]);
        }

        if($("#addOrEditStatus").val() == 1){
            editNewContent(className, imgSrc);
        }else{
            addNewContent(className, imgSrc);
        }
        this.value = null; //上传相同图片
    });

    return imgFile;
}

//添加图片展示
function addNewContent(obj, imgSrc) {
    $(obj).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<li class="content-img-list-item"><img src="' + imgSrc[a] + '" alt="">' +
            '<div class="hide"><a index="' + a + '" class="delete-btn"><i class="gcl gcllajitong"></i></a>' +
            '<a index="' + a + '" class="big-btn" type="button" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="gcl gclfangda"></i></a></div></li>');
    }
}

function editNewContent(obj, imgSrc) {
    var oldBox = $(obj).html();
    $(obj).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        $(obj).html(oldBox + '<li class="content-img-list-item"><img src="' + imgSrc[a] + '" alt="">' +
            '<div class="hide"><a index="' + a + '" class="delete-btn"><i class="gcl gcllajitong"></i></a>' +
            '<a index="' + a + '" class="big-btn" type="button" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="gcl gclfangda"></i></a></div></li>');
    }
}

//建立可存取到file的url
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

/**
 * 编辑处理删除的图片
 * @param originalUrl
 * @param deleteUrl
 */
function handleImage(originalUrl, deleteUrl) {
    var picArr = null;
    if($("input[name="+originalUrl+"]").val().length > 0 && $("input[name="+deleteUrl+"]").val().length > 0){
        var delArr = $("input[name="+deleteUrl+"]").val().split(",");
        picArr = $("input[name="+originalUrl+"]").val().split(",");
        $.each(delArr, function (m, n) {
            $.each(picArr, function (i, v) {
                if(v == n){
                    picArr.splice(i, 1);
                }
            })
        });
        $("input[name="+originalUrl+"]").val(picArr == null ? "" : picArr.toString());
    }
}