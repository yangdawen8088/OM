import cache from '@/common/js/utils/cache.js'
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

/**
 * context : 上下文 ---> 可以获取state中的参数
 * 如：context.state.token
 */
const store = new Vuex.Store({
	state: {
	  token: 'sss',
	  userInfo: uni.getStorageSync('userInfo'),
	  carList: cache.get('carList') || []
	},
	mutations: {
		// 登录成功，把userinfo存到仓库
	  getUserInfo(state,params){
		  state.userInfo = params
		  uni.setStorageSync('userInfo',params)
	  },
	  // 退出登录，把userinfo存到仓库
	  removeUserInfo(state){
		  state.userInfo = ''
		  uni.removeStorage({key:'userInfo'})
	  },
	  // 把购物车存到仓库 时间3天
	  setCar(state,params){
	  		  state.carList = params
	  		  cache.put('carList',params,259200)
	  },
	  // 把清除购物车仓库
	  removeCar(state){
	  		  state.carList = []
	  		  cache.remove('carList')
	  },
	},
	actions: {
		getCity(context){
			
		}
	},
	getters:{

	}
})

export default store