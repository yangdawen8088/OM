/**
 * 路由跳转方法,
 * params 传递参数
 * 带有路由拦截功能
 */
class Router {
	constructor() {
		// 因为route方法是需要对外赋值给另外的对象使用，同时route内部有使用this，会导致route失去上下文
		// 这里在构造函数中进行this绑定
		this.route = this.route.bind(this)
	}
	// 执行路由跳转
	route({
		type = 'navigateTo',
		url,
		delta = 1, // navigateBack页面后退时,回退的层数
		animationType = 'pop-in', // 窗口动画,只在APP有效
		animationDuration = 300, // 窗口动画持续时间,单位毫秒,只在APP有效
		intercept = false, // 是否需要拦截,默认false 不拦截
		duration = 0, //页面停留时间
		params= '' ,// 页面跳转携带参数object
	}) {
		// 判断是否需要拦截
		if(!uni.getStorageSync('userInfo') && intercept){
			uni.showToast({ title: '您还没登录，请先登录！',icon:'none'})
			setTimeout(() => {
				uni.navigateTo({
					url: '/pages/auth/login/login'
				})
			}, 2000)
			return
		}else{
			url = params ? this.mixinParam(url, params) : url
			switch(type){
				case 'navigateTo': case'to':
				setTimeout(() => {
					uni.navigateTo({
						url,
						animationType,
						animationDuration
					});
				}, duration)
				break
				case 'redirectTo': case'redirect':
				setTimeout(() => {
					uni.redirectTo({
						url
					});
				}, duration)
				break
				case 'switchTab': case'tab':
				setTimeout(() => {
					uni.switchTab({
						url
					});
				}, duration)
				break
				case 'reLaunch': case'launch':
				setTimeout(() => {
					uni.reLaunch({
						url
					});
				}, duration)
				break
				case 'navigateBack': case'back':
				setTimeout(() => {
					uni.navigateBack({
						delta
					});
				}, duration)
				break
			}
		}
	}
	// 整合路由参数
	mixinParam(url, params) {
		if (!url) return ''
		let queryArr = [];
		for (const key in params) {
			if (params.hasOwnProperty(key)) {
				queryArr.push(`${key}=${params[key]}`)
			}
		}
		if(url.indexOf('?') !== -1) {
			url =`${url}&${queryArr.join('&')}`
		} else {
			url =`${url}?${queryArr.join('&')}`
		}
		return url
	}
}

export default (new Router()).route
