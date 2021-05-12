var imgFile = [];
var imgSrc = [];
var imgName = [];

$(function() {

    $('.content-img-list').on('mouseover', '.content-img-list-item', function() {
        $(this).children('div').removeClass('hide');
    });
    $('.content-img-list').on('mouseleave', '.content-img-list-item', function() {
        $(this).children('div').addClass('hide');
    });
    $(".content-img-list").on("click", '.content-img-list-item a .gcllajitong', function() {
        var index = $(this).parent().parent().parent().index();
        imgSrc.splice(index, 1);
        imgFile.splice(index, 1);
        imgName.splice(index, 1);
        var boxId = ".content-img-list";
        addNewContent(boxId);
        if (imgSrc.length < 5) {
            $('.content-img .file').show();
        }
    });
    $(".content-img-list").on("click", '.content-img-list-item a .gclfangda', function() {
        var index = $(this).parent().parent().parent().index();
        $(".img-up-content").html("");
        var bigimg = $(".img-up-content").html();
        $(".img-up-content").html(bigimg + '<div class="show"><img src="' + imgSrc[index] + '" alt=""><div>');
    });

    $(".content-img-list").on("click", '.content-img-list-item a .gclleft', function() {
        var index = $(this).parent().parent().parent().index();
        if(index!=0){
            var tempOption = imgSrc[index - 1];
            imgSrc[index - 1]=imgSrc[index];
            imgSrc[index]=tempOption;

            tempOption = imgFile[index - 1];
            imgFile[index - 1]=imgFile[index];
            imgFile[index]=tempOption;

            tempOption = imgName[index - 1];
            imgName[index - 1]=imgName[index];
            imgName[index]=tempOption

        }
        var boxId = ".content-img-list";
        addNewContent(boxId);
    });

    $(".content-img-list").on("click", '.content-img-list-item a .gclright', function() {
        var index = $(this).parent().parent().parent().index();
        if(index!=imgSrc.length-1){
            var tempOption = imgSrc[index + 1];
            imgSrc[index + 1]=imgSrc[index];
            imgSrc[index]=tempOption;

            tempOption = imgFile[index + 1];
            imgFile[index + 1]=imgFile[index];
            imgFile[index]=tempOption;

            tempOption = imgName[index + 1];
            imgName[index + 1]=imgName[index];
            imgName[index]=tempOption

        }
        var boxId = ".content-img-list";
        addNewContent(boxId);
    });
});

$('#upload').on('change', function(e) {
    var imgSize = this.files[0].size;
    if (imgSize > 1024 * 3000 * 1) {
        return alert("上传图片不能超过3M");
    }
    if (this.files[0].type != 'image/png' && this.files[0].type != 'image/jpeg' && this.files[0].type != 'image/gif') {
        return alert("图片上传格式不正确");
    }
    var imgBox = '.content-img-list';
    var fileList = this.files;
    for (var i = 0; i < fileList.length; i++) {
        var imgSrcI = getObjectURL(fileList[i]);
        imgName.push(fileList[i].name);
        imgSrc.push(imgSrcI);
        imgFile.push(fileList[i]);
    }
    addNewContent(imgBox);
	if (imgSrc.length === 5) {
    $('.content-img .file').hide();
    }
    this.value = null;
});

/*$('#btn-submit-upload').on('click', function() {
    var formFile = new FormData();
    $.each(imgFile, function(i, file) {
        formFile.append('myFile[]', file);
    });

});*/

function addNewContent(obj) {
    $(obj).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        // $(obj).html(oldBox + '<li class="content-img-list-item"><img src="' + imgSrc[a] + '" alt="">' + '<div class="hide"><a index="' + a + '" class="delete-btn"><i class="gcl gcllajitong"></i></a>' + '<a index="' + a + '" class="big-btn" type="button" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="gcl gclfangda"></i></a></div></li>');
        $(obj).html(oldBox + '<li class="content-img-list-item"><img src="' + imgSrc[a] + '" alt="">' +
            '<div class="hide"><a index="' + a + '" class="delete-btn"><i class="gcl gcllajitong"></i></a>' +
            '<a index="' + a + '" class="delete-btn"><i class="gcl gclleft"></i></a>'+
            '<a index="' + a + '" class="delete-btn"><i class="gcl gclright"></i></a>'+
            '<a index="' + a + '" class="big-btn" type="button" data-toggle="modal" data-target=".bs-example-modal-lg"><i class="gcl gclfangda"></i></a>' +
            '</div></li>');
    }
}

function checkNewContent(obj) {
    $(obj).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<li class="content-img-list-item"><img src="' + imgSrc[a] + '" alt=""></li>');
    }
}

function getObjectURL(file) {
    var url = null;
	 var formData = new FormData();
		formData.append('file', file);
	 $.ajax({
		 url:'/fileUpload/noticeFile',
		 type:'POST',
		 data:formData,
		 processData:false,
		 contentType:false,
         async: false,
		 success:function(data){
             url=data.data;
		 }
	 });
	 return url;
}

function getImgSrc() {
    return imgSrc;
}
function setImgSrc(arr,type) {
    imgSrc=arr;
    if (type===10){
        checkNewContent('.content-img-list');
        $('.content-img .file').hide();
    } else {
        addNewContent('.content-img-list');
        if (arr.length === 5) {
            $('.content-img .file').hide();
        }
    }
}
function getImgName() {
    return imgName;
}
