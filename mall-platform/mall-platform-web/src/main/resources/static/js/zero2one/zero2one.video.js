/**
 * 视频播放
 */
zero2one.video = function(videoId,aliVideo){
    $("#aliVideo").append('<div id="J_prismPlayer" class="prism-player"></div>');
    $.get("/remote/culture/getVideoPlayAuth/"+videoId,function(data){
        new Aliplayer({
            id: 'J_prismPlayer',
            width: '50%',
            height: '200px',
            autoplay: false,
            //支持播放地址播放,此播放优先级最高
            // source : '播放url',
            //播放方式二：点播用户推荐
            vid: videoId, // 视频id
            playauth: data.data, // 播放凭证
            mediaType: 'video', // 返回音频还是视频
            cover: 'http://liveroom-img.oss-cn-qingdao.aliyuncs.com/logo.png',
            preload:true,//播放器自动加载
            useH5Prism: true, // 播放器类型：html5
            // encryptType:1, //当播放私有加密流时需要设置。
        },function(player){
        });
    });



};