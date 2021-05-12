import Request from '@/common/js/luch-request/index.js'
import base from '@/common/js/api/api.config.js'
import store from '@/store/index.js'
import { USER_INFO } from '@/common/js/utils/constants.js'

//获取缓存中的token
const getTokenStorage = () => {
	let token = '';
	try {
		token = store.state.userInfo? store.state.userInfo.token :''
	} catch (e) {
		//TODO handle the exception
		console.log("getTokenStorage",token)
	}
	return token
};

// 创建请求实例
const http = new Request();

/**
 * 设置全局配置：
 * baseURL：请求服务地址
 * header：请求头	   
 */
http.setConfig((config) => { 
	config.baseURL = base.url;
	config.header = {
	  ...config.header,
	  token: store.state.userInfo? store.state.userInfo.token :'',
	  authType: "true"
	};
	config.custom = {
	  auth: false, // 是否传token
	    // loading: false // 是否使用loading
	};
	return config
});

/**
 * 请求之前拦截器。可以使用async await 做异步操作
 */
http.interceptors.request.use((config) => {
	//请求之前业务处理 
	/* if(config.custom.auth){
		config.header.token = '';
	} */
	config.header.token = store.state.userInfo? store.state.userInfo.token :''
	return config
}, (config) => {
	return Promise.reject(config)
});

/**
 * 请求响应拦截器。必须使用异步函数，注意
 */
http.interceptors.response.use(async (response) => { 
	// 请求响应业务处理
	if (response.data.code == -1) { // 登录已过期
	  store.commit('removeUserInfo')
	  setTimeout(function() {
	  	uni.navigateTo({
	  		url: "/pages/auth/login/login.vue",
	  	})
	  }, 1000);
	  return uni.showToast({
	  	title: response.data.msg,
	  	icon: 'none'
	  })
	}
	return response.data
}, (response) => { 
	// 请求异常时做点什么。可以使用async await 做异步操作
	console.log(response)
	return Promise.reject(response.data)
});

// 可以自定义局部变量，在自定义变量里面增加拦截业务处理，如日志 权限 通知等
export { http }