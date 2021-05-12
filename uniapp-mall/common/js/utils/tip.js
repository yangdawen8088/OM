/**
 * 提示与加载工具类
 */
export default class Tips {
  
  constructor() {}
  
  /**
   * 请求成功，跳转
   * res : 对象
   * duration ：延迟时间（单位毫秒）
   * url：跳转路径
   */
  static success(res, duration, url) {
       res.msg ? uni.showToast({ title: res.msg, icon: "none"}): ''
	   if(!res.success) return
	   if(res.success && url){
		   setTimeout(()=>{
			   uni.navigateTo({url})
		   },duration)
	   }
  }
  
  /**
   * 请求成功，返回上某层
   * delta：返回的页面数
   */
  static successBack(res, duration, delta) {
	   uni.showToast({ title: res.msg, icon: "none"})
	   if(!res.success) return
	   if(res.success){
	   		   setTimeout(()=>{
	   			   uni.navigateBack({
	   			   	delta
	   			   })
	   		   },duration)
	   }
  }
  
  
  /**
   * 请求成功，重定向到某个页面
   * msg：自定义提示语
   * fn: 回调函数
   */
  static successRedirect({res, duration, url,msg,fn}) {
	  if(!res.success) return uni.showToast({ title: res.msg, icon: "none"})
	   msg ? uni.showToast({ title: msg, icon: "none"}): uni.showToast({ title: res.msg, icon: "none"})
	   fn && fn()
	   if(url){
		   setTimeout(()=>{
				uni.redirectTo({url})
		   },duration)
	   }
  }
  
  /**
    * 普通提示
    * icon: 参数有 'loading', 'none','success'
    */
   static toast(title,icon='none') {
  		uni.showToast({
  		  title: title,
  		  icon,
  		  mask: true,
  		  duration:1000
  		});
   }
   
}
