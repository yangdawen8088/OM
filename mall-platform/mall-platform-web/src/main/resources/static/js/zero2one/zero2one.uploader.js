/**
 * 多图片上传
 * @param cname:classname
 * @param Open:是否开启多图片上传 true开启 false 不开启
 * @param Iname:Input框的ID
 */
zero2one.webUploader = function (cname,Open,Iname) {
// 通用绑定，
    $('.'+cname).each(function () {
        var $input_file = $(this).find('input'),
            $div = $(this).find('div'),
            $input_file_name = Iname,
            $multiple = Open,  // 是否选择多个文件
            $ext = $input_file.data('ext'),       // 支持的文件后缀，示例以图片为例
            $size = $input_file.data('size');      // 支持最大的文件大小
        var $file_list = $('#file_list_' + $input_file_name);
        var ratio = window.devicePixelRatio || 1;
        var thumbnailWidth = 500 * ratio;
        var thumbnailHeight = 500 * ratio;

        var uploader = WebUploader.create({
            auto: true,
            duplicate: true,
            resize: false,
            swf: './js/webuploader/Uploader.swf',
            server: '/remote/upload/video',
            pick: {
                id: '#picker_' + $input_file_name,
                multiple: $multiple
            },
            fileSingleSizeLimit: $size,
            accept: {
                title: 'Images',
                extensions: $ext,
                mimeTypes: 'image/*'
            }
        });

        uploader.on('fileQueued', function (file) {
            var $li = $('<li class="pic-item" style="height: 180px;width: 210px" id="' + file.id + '">' +
                '  <figure style="height:180px;width: 210px">' +
                '    <img style="height: 180px;width: 210px">' +
                '    <figcaption>' +
                '      <a class="btn btn-round btn-square btn-danger btn-remove-pic" href="#!"><i class="mdi mdi-delete"></i></a>' +
                '    </figcaption>' +
                '  </figure>' +
                '</li>'),
                $img = $li.find('img');
            if ($multiple) {
                $file_list.append($li);
            } else {
                $file_list.html($li);
                $input_file.val('');
            }
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }
                $img.attr('src', src);
            }, thumbnailWidth, thumbnailHeight);
            $('<div class="progress progress-sm"><div class="progress-bar progress-bar-primary progress-bar-striped" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%"></div></div>').appendTo($li);
        });
        uploader.on('uploadProgress', function (file, percentage) {
            var $percent = $('#' + file.id).find('.progress-bar');
            $percent.css('width', percentage * 100 + '%');
        });
        uploader.on('uploadSuccess', function (file, response) {
            var $li = $('#' + file.id);
            if (response.code == 200) { // 返回200成功
                if ($multiple) {
                    if ($input_file.val()) {
                        $input_file.val($input_file.val() + ',' + response.data);
                    } else {
                        $input_file.val(response.data);
                    }
                    $li.find('.btn-remove-pic').attr('data-id', response.data);
                } else {
                    $input_file.val(response.data);
                }
                $div.hide()
            }
            $('<div class="' + response.class + '"></div>').text(response.info).appendTo($li);
            $li.find('a.btn-link-pic').attr('href', response.picurl);
        });
        uploader.on('uploadError', function (file) {
            var $li = $('#' + file.id);
            $('<div class="error">上传失败</div>').appendTo($li);
        });
        uploader.on('error', function (type) {
            switch (type) {
                case 'Q_TYPE_DENIED':
                    alert('图片类型不正确，只允许上传后缀名为：' + $ext + '，请重新上传！');
                    //lightyear.notify('图片类型不正确，只允许上传后缀名为：' + $ext + '，请重新上传！', 'danger');
                    break;
                case 'F_EXCEED_SIZE':
                    alert('图片不得超过' + ($size / 1024) + 'kb，请重新上传！');
                    //lightyear.notify('图片不得超过' + ($size / 1024) + 'kb，请重新上传！', 'danger');
                    break;
            }
        });
        uploader.on('uploadComplete', function (file) {
            setTimeout(function () {
                $('#' + file.id).find('.progress').remove();
            }, 500);
        });
        // 删除操作
        $file_list.delegate('.btn-remove-pic', 'click', function () {
            if (confirm("你确认要删除该图片吗?")) { // 可改为对话框插件
                if ($multiple) {
                    var id = $(this).data('id'),
                        ids = $input_file.val().split(',');
                    if (id) {
                        for (var i = 0; i < ids.length; i++) {
                            if (ids[i] == id) {
                                ids.splice(i, 1);
                                break;
                            }
                        }
                        $input_file.val(ids.join(','));
                        console.log($input_file)
                    }
                } else {
                    $input_file.val('');
                    console.log($input_file)
                }
                $(this).closest('.pic-item').remove();
                console.log($input_file);
                $div.show()
            }
        });
        // 接入图片查看插件
        $(this).magnificPopup({
            delegate: 'a.btn-link-pic',
            type: 'image',
            gallery: {
                enabled: true
            }
        });
    });
};

zero2one.callBack = function(images){
    var ids = images;
    for (var i = 0; i < ids.length; i++) {
        if (ids[i]!=null&&ids[i]!=''){
            $("#file_list_photo").append('<li class="pic-item" style="height: 180px;width: 210px" id="' + ids[i] + '">' +
                '  <figure style="height:180px;width: 210px">' +
                '    <img style="height: 180px;width: 210px" src="' + ids[i] + '" >' +
                '    <figcaption>' +
                '      <a class="btn btn-round btn-square btn-danger btn-remove-pic" href="#!" data-id="' + ids[i] + '"><i class="mdi mdi-delete"></i></a>' +
                '    </figcaption>' +
                '  </figure>' +
                '</li>')
        }

    }
};
