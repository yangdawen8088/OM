// 使用命名空间。
(function(window) {

    // 如果需要在模态框中使用webuploader，那么必须先调用prepareModal，
    // 再调用initWebUploader。这样可以解决模态框中使用webuploader出现的BUG。
    // 例如prepareModal('#addProductModal')
    this.prepareModal = function(modalId) {
        var _$modal = $(modalId);
        _$modal.css('display', 'block');
        _$modal.addClass("webuploader-element-invisible");
        _$modal.on('show.bs.modal', function() {
            _$modal.removeClass("webuploader-element-invisible");
        });
    };

    /**
     * 返回一个webuploader实例
     *    调用方式: 如initWebUploader('#uploader', '#filePicker', '#filePicker2', 'uploadMethod')
     * 前三个参数对应的是页面的div的id，
     *
     * @param webUploaderId ：容器ID
     * @param filePickerId ： 文件区域ID
     * @param fileAddId : 文件添加ID
     * @param uploadUrl : 上传请求的路径
     */
    this.initWebUploader = function(webUploaderId, filePickerId, fileAddId, uploadUrl) {
            var $ = jQuery,    // just in case. Make sure it's not an other libaray.
            $wrap = $('#uploader'),
            // 图片容器
            $queue = $('<ul class="filelist"></ul>').appendTo( $wrap.find('.queueList') ),
            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find('.statusBar'),
            // 文件总体选择信息。
            $info = $statusBar.find('.info'),
            // 上传按钮
            $upload = $wrap.find('.uploadBtn'),
            // 没选择文件之前的内容。
            $placeHolder = $wrap.find('.placeholder'),
            // 总体进度条
            $progress = $statusBar.find('.progress').hide(),
            // 添加的文件数量
            fileCount = 0,
            // 添加的文件总大小
            fileSize = 0,
            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,
            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,
            // 可能有pedding, ready, uploading, confirm, done.
            state = '',
            // 所有文件的进度信息，key为file id
            percentages = {},
            supportTransition = (function(){
                var s = document.createElement('p').style,
                    r = 'transition' in s ||
                        'WebkitTransition' in s ||
                        'MozTransition' in s ||
                        'msTransition' in s ||
                        'OTransition' in s;
                s = null;
                return r;
            })(),
            // 允许上传的格式
            acceptExtensions = 'png,jpg,jpeg,bmp,gif,zip,rar,doc,xls,txt,ppt,rm,exe,rmvb,mp4,wma,wav,lrc,iso,xlsx,7z,pdf,mp3',
            // 允许上传的文件数量
            fileNumLimit = 5,
            // 总文件大小限制
            fileSizeLimit = 50 * 1024 * 1024,// 50M
            // 单个文件大小限制
            fileSingleSizeLimit = 2 * 1024 * 1024, // 2M
            // WebUploader实例
            uploader;

            if ( !WebUploader.Uploader.support() ) {
                alert( 'Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
                throw new Error( 'WebUploader does not support the browser you are using.' );
            }

            // 实例化
            uploader = WebUploader.create({
                pick: {
                    id: filePickerId,
                    label: '点击选择文件'
                },
                dnd: '#uploader .queueList',           // 指定Drag And Drop拖拽的容器，如果不指定，则不启动。
                paste: document.body,                  // 指定监听paste事件的容器，如果不指定，不启用此功能。

                // swf文件路径
                swf: '/js/webuploader/Uploader.swf',

                disableGlobalDnd: true,                 // 否禁掉整个页面的拖拽功能，如果不禁用，图片拖进来的时候会默认被浏览器打开。

                chunked: true,                          // 开启分片上传
                duplicate: false,                       // 去重， 根据文件名字、文件大小和最后修改时间来生成hash Key。true为可重复。
                server: uploadUrl,
                compress: false,                        // 图片在上传前不进行压缩
                fileNumLimit: fileNumLimit,             // 验证文件总数量
                fileSizeLimit: fileSizeLimit,           // 总文件大小限制
                fileSingleSizeLimit: fileSingleSizeLimit// 单个文件大小限制
           });

           // 添加“添加文件”的按钮，
           uploader.addButton({
               id: fileAddId,
               label: '继续添加'
           });

           setState('pedding');

           // 当有文件添加进来时执行，负责view的创建
           function addFile( file ) {
               var $li = $('<li id="' + file.id + '">' +
                   '<p class="title">' + file.name + '</p>' +
                   '<p class="imgWrap"></p>' +
                   '<p class="progress"><span class="progress-bar progress-bar-striped active"></span></p>' +
                   '</li>'),

                   $btns = $('<div class="file-panel">' +
                       '<span class="cancel"></span>' +
                       '<span class="rotateRight"></span>' +
                       '<span class="rotateLeft"></span></div>').appendTo($li),
                   $prgress = $li.find('p.progress .progress-bar'),
                   $wrap = $li.find('p.imgWrap'),
                   $info = $('<p class="error"></p>'),

                   showError = function (code) {
                       switch (code) {
                           case 'exceed_size':
                               text = '文件大小超出';
                               break;

                           case 'interrupt':
                               text = '上传暂停';
                               break;

                           case 'http':
                               text = 'http请求错误';
                               break;

                           case 'not_allow_type':
                               text = '文件格式不允许';
                               break;

                           default:
                               text = '上传失败，请重试';
                               break;
                       }

                       $info.text(text).appendTo($li);
                   };

               if (file.getStatus() === 'invalid') {
                   showError(file.statusText);
               } else {
                   $wrap.text('预览中');
                   if ('|png|jpg|jpeg|bmp|gif|'.indexOf('|' + file.ext.toLowerCase() + '|') == -1) {
                       var extArr = {
                           'zip': 'zip-box',
                           'rar': 'zip-box',
                           '7z': 'zip-box',
                           'doc': 'file-word',
                           'xls': 'file-excel',
                           'txt': 'file-document',
                           'ppt': 'file-powerpoint',
                           'xlsx': 'file-excel',
                           'pdf': 'file-pdf',
                           'rm': 'file-video',
                           'rmvb': 'file-video',
                           'mp4': 'file-video',
                           'mp3': 'file-music',
                           'wma': 'file-music',
                           'wav': 'file-music',
                           'exe': 'file',
                           'lrc': 'file',
                           'iso': 'file',
                           'xml': 'file-xml',
                           'php': 'language-php',
                           'py': 'language-python',
                           'go': 'language-go',
                           'c': 'language-c',
                           'cpp': 'language-cpp',
                           '.cs': 'language-csharp',
                           'js': 'language-javascript',
                           'html': 'internet-explorer',
                           'css': 'language-css3',
                       };
                       var thisFileExt = extArr[file.ext.toLowerCase()];
                       console.log(thisFileExt);
                       console.log(file.ext.toLowerCase());
                       thisFileExt == 'undifend' ? 'file' : thisFileExt;

                       $wrap.empty().addClass('notimage').append('<i class="file-preview mdi mdi-' + thisFileExt + '"></i>' +
                           '<span class="file-title" title="' + file.name + '">' + file.name + '</span>');
                   } else {
                       uploader.makeThumb(file, function (error, src) {
                           if (error || !src) {
                               $wrap.text('不能预览');
                           } else {
                               var $img = $('<img src="' + src + '">');
                               $wrap.empty().append($img);
                               $img.on('error', function () {
                                   $wrap.text('不能预览');
                               });
                           }
                       }, thumbnailWidth, thumbnailHeight);
                   }

                   percentages[file.id] = [file.size, 0];
                   file.rotation = 0;

                   /* 检查文件格式 */
                   if (!file.ext || acceptExtensions.indexOf(file.ext.toLowerCase()) == -1) {
                       showError('not_allow_type');
                       //uploader.removeFile(file);
                   }
               }

               file.on('statuschange', function (cur, prev) {
                   if (prev === 'progress') {
                       $prgress.hide().width(0);
                   } else if (prev === 'queued') {
                       $li.off('mouseenter mouseleave');
                       $btns.remove();
                   }

                   // 成功
                   if (cur === 'error' || cur === 'invalid') {
                       showError(file.statusText);
                       percentages[file.id][1] = 1;
                   } else if (cur === 'interrupt') {
                       showError('interrupt');
                   } else if (cur === 'queued') {
                       percentages[file.id][1] = 0;
                   } else if (cur === 'progress') {
                       $info.remove();
                       $prgress.css('display', 'block');
                   } else if (cur === 'complete') {
                       $li.append('<span class="success"></span>');
                   }

                   $li.removeClass('state-' + prev).addClass('state-' + cur);
               });

               $li.on('mouseenter', function () {
                   $btns.stop().animate({height: 30});
               });

               $li.on('mouseleave', function () {
                   $btns.stop().animate({height: 0});
               });

               $btns.on('click', 'span', function () {
                   var index = $(this).index(),
                       deg;

                   switch (index) {
                       case 0:
                           uploader.removeFile(file);
                           return;

                       case 1:
                           file.rotation += 90;
                           break;

                       case 2:
                           file.rotation -= 90;
                           break;
                   }

                   if (supportTransition) {
                       deg = 'rotate(' + file.rotation + 'deg)';
                       $wrap.css({
                           '-webkit-transform': deg,
                           '-mos-transform': deg,
                           '-o-transform': deg,
                           'transform': deg
                       });
                   } else {
                       $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
                   }

               });

               $li.appendTo($queue);
           }

           // 负责view的销毁
           function removeFile( file ) {
                var $li = $('#'+file.id);
                delete percentages[ file.id ];
                // TODO 不处理“进度”的更新。让调用方自己负责更新 目前先打开
                updateTotalProgress();
                $li.off().find('.file-panel').off().end().remove();
           }

            function updateTotalProgress() {
                var loaded = 0,
                    total = 0,
                    span  = $progress.find('.progress-bar'),
                    percent;

                $.each( percentages, function( k, v ) {
                    total += v[ 0 ];
                    loaded += v[ 0 ] * v[ 1 ];
                } );

                percent = total ? loaded / total : 0;

                span.text( Math.round( percent * 100 ) + '%' );
                span.css( 'width', Math.round( percent * 100 ) + '%' );
                updateStatus();
            }

        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            switch ( state ) {

                // 未选择文件
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.parent().removeClass('filled');
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                // 可以开始上传
                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $(fileAddId).removeClass( 'element-invisible');
                    $queue.parent().addClass('filled');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                // 上传中
                case 'uploading':
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                // 暂停上传
                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $upload.text( '开始上传' ).addClass( 'disabled' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        //console.log( '上传成功' );
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();

            if (!getQueueCount()) {
                $upload.addClass('disabled');
            } else {
                $upload.removeClass('disabled');
            }
        }


        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '个文件，共' + WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '个附件，'+
                        stats.uploadFailNum + '个附件上传失败，<a class="retry" href="#">重新上传</a>失败附件或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '个（' + WebUploader.formatSize( fileSize ) + '），已上传' + stats.successNum + '个';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '个';
                }
            }

            $info.html( text );
        }

        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }

            addFile( file );
            setState( 'ready' );
            updateTotalProgress();
        };

        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        uploader.on('filesQueued', function (file) {
            if (!uploader.isInProgress() && (state == 'pedding' || state == 'finish' || state == 'confirm' || state == 'ready')) {
                setState('ready');
            }
            updateTotalProgress();
        });

        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;

            }
        });

        uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                $percent = $li.find('.progress .progress-bar');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };

        uploader.onUploadSuccess = function( file, response ) {
            var $input_file = $wrap.find('input[type="hidden"]');
            if ( $input_file.val() ) {
                $input_file.val( $input_file.val() + ',' + response.id );
            } else {
                $input_file.val( response.id );
            }
        };

        uploader.onError = function( code ) {
            if ( code == "Q_EXCEED_NUM_LIMIT" ) {
                alert( "最多传5张照片" );
            } else if ( code == "F_DUPLICATE" ) {
                alert( "文件已存在队列中" );
            } else if ( code == "Q_TYPE_DENIED" ) {
                alert( "文件类型不满足" );
            } else if ( code == "F_EXCEED_SIZE" ) {
                alert( "文件大小不能超过" + fileSingleSizeLimit / (1024 * 1024) + " M" );
            } else if ( code == "Q_EXCEED_SIZE_LIMIT") {
                alert("添加的文件总大小不能超过 " + fileSizeLimit / (1024 * 1024) + " M");
            }
        };

        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            //alert( 'todo' );
            //执行忽略动作，重置
            uploader.reset();
        } );

        // 添加Reset监听。by tanzx 2017-08-01
        // TODO reset不知道有没有问题。是否选择用destroy？
        uploader.reset = function() {
            fileCount = 0;
            fileSize = 0;
            setState('pedding');
            // 移除页面上的图片。
            $.each(uploader.getFiles(), function(index, value) {
                removeFile( value )
            });
            updateTotalProgress();
        };

        // 统计队列内未完成上传的数量
        function getQueueCount() {
            var file, i, status, readyFile = 0, files = uploader.getFiles();

            for (i = 0; file = files[i++]; ) {
                status = file.getStatus();
                if (status == 'inited' || status == 'queued' || status == 'uploading' || status == 'progress') readyFile++;
            }

            return readyFile;
        }

        $upload.addClass( 'state-' + state );
        updateTotalProgress();

        // WebUploader实例
        return uploader;
    };

    window.CommonWebUploader = this;

})(window);