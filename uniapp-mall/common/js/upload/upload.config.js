//aliyun OSS config
const config = {
  // #ifdef H5
  uploadImageUrl: '*********', // 默认存在根目录，可根据需求改
  // #endif
  // #ifndef H5
  uploadImageUrl: '*****************', // 默认存在根目录，可根据需求改
  // #endif
  AccessKeySecret: '****************',        // AccessKeySecret 去你的阿里云上控制台上找
  OSSAccessKeyId: '********************',         // AccessKeyId 去你的阿里云上控制台上找
  timeout: 2000 //这个是上传文件时Policy的失效时间
};

module.exports = config