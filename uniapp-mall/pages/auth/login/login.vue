<!-- 账号密码登录及微信一键登录 -->
<template>
	<view class="login">
		<navigator hover-class="none" open-type="navigateBack" :delta="1" class="back-icon text-white" :style="[{ top: StatusBar + 'px' }]">
			<text class="icon iconfont icon-tubiaozhizuo-"></text>
		</navigator>
		<view class="u-f-ac box">
			<view class="login-box bg-white shadow-blur">
				<view class="logo-img"><image src="../../../static/logo.jpg" mode="aspectFill"></image></view>
				<form class="login-form" @submit="save">
					<view class="u-f-ac set-li account">
						<view class="prefix">账号</view>
						<input type="text" name="account" placeholder="请输入登录账号" placeholder-style="color:#aaa" />
					</view>
					<view class="u-f-ac set-li">
						<view class="prefix">密码</view>
						<input type="password" name="password" placeholder="请输入密码" placeholder-style="color:#aaa" />
					</view>
					<view class="u-f-jsb text-size26 bottom-text">
						<navigator hover-class="none" url="../forget/forget?name=找回密码&type=1">忘记密码？点击找回您的密码</navigator>
						<navigator hover-class="none" url="../forget/forget?name=注册新用户&type=2">注册</navigator>
					</view>
					<button class="loginWeChatBtn pw-btn u-f-jsb" form-type="submit">
						<image src="../../../static/auth/pwLogin.png" mode="aspectFill"></image>
						<text class="flex1">登录</text>
					</button>
					<button class="loginWeChatBtn wx-btn u-f-jsb" @tap="wechatAuth" v-if="!res">
						<image src="../../../static/auth/wxLogin.png" mode="widthFix"></image>
						<text class="flex1">微信快捷登录</text>
					</button>
					<button v-else class="loginWeChatBtn wx-btn u-f-ac" open-type="getPhoneNumber" @getphonenumber="wechatLogin">
						<image src="../../../static/auth/wxLogin.png" mode="widthFix"></image>
						<text class="flex1">微信快捷登录</text>
					</button>
				</form>
			</view>
		</view>
	</view>
</template>

<script>
import valid from '@/common/js/utils/formValidate.js';

export default {
	data() {
		return {
			StatusBar: this.StatusBar, //状态栏高度
			flag: true,
			res: {},
			code:''
		};
	},
	onLoad() {
		this.res = uni.getStorageSync('wechatAuth') || '';
		let that = this
		uni.login({
			provider: 'weixin',
			success({ errMsg, code }) {
				if (errMsg == 'login:ok') {
					that.code = code
					console.log(code)
				}
			}
		});
	},
	methods: {
		save: function(e) {
			if (!this.flag) return this.$tip.toast('手速太快了,悠着点~');
			//定义表单验证规则
			let rule = [
				{ name: 'account', checkType: 'phoneno', checkRule: '', errorMsg: '您的账号不存在' },
				{ name: 'password', checkType: 'notnull', checkRule: '', errorMsg: '请输入密码' }
			];
			//获取表单数据
			let formdata = e.detail.value;
			//表单验证
			let checkRes = valid.check(formdata, rule);
			if (checkRes) {
				this.flag = false;
				//发送请求
				this.$http
					.post('/api/member/login', formdata, { header: { authType: 'false' } })
					.then(res => {
						this.flag = true;
						this.$tip.successBack(res, 1500, 1);
						this.$store.commit('getUserInfo', res.data);
					})
					.catch(err => {
						this.flag = true;
					});
			} else {
				uni.showToast({ title: valid.error, icon: 'none' });
			}
		},
		// 小程序登录
		wechatLogin(e) {
			var _this = this;
			if (e.detail.errMsg == 'getPhoneNumber:ok') {
				wx.checkSession({
				     success(res) {
						let formdata = {
							code: _this.code,
							iv: e.detail.iv,
							encryptedData: e.detail.encryptedData,
							avatarUrl: _this.res.userInfo.avatarUrl,
							gender: _this.res.userInfo.gender,
							nickname: _this.res.userInfo.nickName
						};
						// 弹出正在登录的弹框，登录请求
						uni.showLoading({ title: '正在登录' });
						//请求后台登录
						_this.$http.post('/api/member/appletLogin', formdata, { header: { authType: 'false' } }).then(res => {
							uni.hideLoading();
							_this.$tip.successBack(res, 1500, 1);
							_this.$store.commit('getUserInfo', res.data);
							// uni.setStorageSync('userInfo', res.data);
						});
					},fail(err) {
					// session_key 已经失效，需要重新执行登录流程
					uni.login({
						provider: 'weixin',
						success({ errMsg, code }) {
							if (errMsg == 'login:ok') {
								that.code = code
							}
						}
					});
					}
				})
			} else {
				this.Route({ type: 'back', duration: 1000 });
			}
		},
		//微信授权获取用户信息
		wechatAuth() {
			uni.getUserProfile({
				desc: '授权登录', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
				success: res => {
					this.res = res;
					uni.setStorageSync('wechatAuth', res);
				}
			});
		}
	}
};
</script>

<style scoped lang="scss">
.login {
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
.box {
	height: calc(100vh - 90rpx);
}
.back-icon {
	width: 120rpx;
	height: 90rpx;
	line-height: 90rpx;
	text-align: center;
	position: relative;
	.icon {
		font-size: 42rpx;
	}
}
.login-box {
	flex: 1;
	margin: 0 60rpx;
	border-radius: 48rpx;
	padding: 48rpx 60rpx;
	box-shadow: 0 39rpx 68rpx rgba(14, 57, 101, 0.32);
	.logo-img {
		height: 240rpx;
		width: 100%;
		text-align: center;
	}
	image {
		margin-top: 30rpx;
		width: 150rpx;
		height: 150rpx;
	}
}
.login-form {
}
.set-li {
	padding: 28rpx 0;
	box-sizing: border-box;
}
.set-li,
.set-li input,
.set-li text {
	font-size: 24rpx;
}
.set-li text {
	width: 1em !important;
}
.prefix {
	padding-right: 28rpx;
	margin-right: 28rpx;
}
.bottom-text {
	padding: 28rpx 0;
	color: $main-color;
}
.loginWeChatBtn {
	width: 100%;
	height: 80rpx;
	font-size: 30rpx;
	border: none;
	color: #fff;
	margin-bottom: 40rpx;
	margin-top: 40rpx;
	border-radius: 60rpx;
	image {
		margin: auto 0;
		width: 46rpx;
		height: 46rpx;
	}
	.flex1 {
		flex: 1;
		font-weight: bold;
	}
}
.pw-btn {
	background-image: linear-gradient(to right, rgb(40, 109, 249), rgb(40, 165, 249), rgb(40, 165, 249));
}
.wx-btn {
	background-image: linear-gradient(to right, rgb(72, 177, 30), rgb(93, 218, 44));
}
</style>
