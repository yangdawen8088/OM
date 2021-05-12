<!-- 按钮一键登录 -->
<template>
	<view class="login">
		<navigator open-type="navigateBack" hover-class="none" :delta="1" class="back-icon text-white" :style="[{top:StatusBar + 'px'}]">
			<text class="icon iconfont icon-tubiaozhizuo-"></text>
		</navigator>
		<view class="u-fc box">
			<view class="logo-img u-f-ac">
				<image src="../../../static/auth/mall-logo.png" mode="heightFix"></image>
			</view>
			<view class="login-box u-fc u-f-jsb">
				<view class="agree">点击即代表同意OPEN-MALL<text class="text-blue">《法律声明及隐私政策》</text></view>
				<button class="loginWeChatBtn wx-btn u-f-ac" open-type="getUserInfo" @getuserinfo="wechatAuth" v-if="show">
					<image src="../../../static/auth/wxLogin.png"></image><text>微信授权登录</text>
				</button>
				<button class="loginWeChatBtn wx-btn u-f-ac" open-type="getPhoneNumber" @getphonenumber="wechatLogin" v-else>
					<image src="../../../static/auth/wxLogin.png"></image><text>微信快捷登录</text>
				</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				StatusBar: this.StatusBar,    //状态栏高度
				show:false,
			}
		},
		onLoad() {
			let that = this
			// 判断是否授权
			uni.getSetting({
				success(res) {
					this.show = !res.authSetting['scope.userInfo']? true :false
				}
			})
		},
		methods: {
			// 小程序点击授权
			wechatAuth(e) {
				var that=this
				if(e.detail.errMsg=="getUserInfo:ok") {
					that.show = false
				} else {
					this.Route({type:'back',duration:1000})
				}
			},
			// 小程序登录
			wechatLogin(e) {
				var _this = this
				console.log(e,'===========')
				if(e.detail.errMsg=="getPhoneNumber:ok"){
					uni.login({
					   	provider: 'weixin',
					   	success({errMsg,code}) {
					   		if (errMsg == "login:ok") {
								uni.getUserInfo({
								    provider: 'weixin',
								    success: function (infoRes) {
									//请求后台登录
									
									// 弹出正在登录的弹框，登录请求
									uni.showLoading({title: '正在登录'})
								  }
								});
								
					   		}
					   	}
					});
				}else{
				    this.Route({type:'back',duration:1000})
				}
			}
		}
	}
</script>
<style>
	page {
		background-color: #fff;
	}
</style>
<style scoped lang="scss">
.login{
	position: relative;
	/* #ifdef H5 */
	background-image: url(/ossadvert/1619604787085108.png);
	/* #endif */
	/* #ifdef MP-WEIXIN */
	background-image: url(https://sbb-osss.oss-cn-beijing.aliyuncs.com/advert/1619604787085108.png);
	/* #endif */
	height: 100vh;
	width: 100vw;
	min-height: 100vh;
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
}
.back-icon{
	width: 120rpx;
	height: 90rpx;
	line-height: 90rpx;
	text-align: center;
	position: relative;
	.icon{
		font-size: 42rpx;
	}
}
.box{
	justify-content: space-between;
	height: calc(100vh - 90rpx);
	.logo-img{
		flex: 1;
	}
	.logo-img image{
		display: block;
		height: 266rpx;
		margin: 0 auto;
	}
}
.login-box{
	background-color: rgba(#fff,0.8);
	margin: 0 20rpx;
	border-radius: 28rpx;
	padding: 48rpx 28rpx;
	margin-bottom: 120rpx;
	.agree{
		font-size: 26rpx;
	}
}
.loginWeChatBtn {
	width: 100%;
	height: 90rpx;
	font-size: 30rpx;
	border: none;
	color: #fff;
	margin-bottom: 40rpx;
	margin-top: 40rpx;
	border-radius: 60rpx;
	justify-content: center;
	box-shadow: 0 6rpx 20rpx rgba(69,171,46,0.5);
	image{
		width: 48rpx;
		height: 40rpx;
		margin-right: 10rpx;
	}
}
.wx-btn{
	background-color: rgb(69,171,46);
}
</style>

