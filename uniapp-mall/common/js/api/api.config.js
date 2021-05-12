 /**
  * 服务器请求地址配置
  */
let BASE_URL = '';

if (process.env.NODE_ENV == 'development') {
    BASE_URL = 'http://192.168.2.46:8888' // 开发环境
} else {
	BASE_URL = 'http://www.xxx.org:8888' // 生产环境
}

const base = { url: BASE_URL };

export default base