<template>
	<view>
		<view class="code-btn" @tap="getCode()">{{ msgVerCode }}</view>
	</view>
</template>

<script>
	export default {
		props:['phone','type'],
		data() {
			return {
				countDown: 60,
				isLock: true,
				msgVerCode: '获取验证码'
			};
		},
		onLoad() {
			// 进入页面清除定时器
			this.timer && this.clearTimer();
		},
		methods: {
			//校验手机格式
			checkMobile(mobile) {
				return RegExp(/^1[345789]\d{9}$/).test(mobile);
			},
			async getCode() {
				if(!this.isLock) return
				const phoneFlag = this.checkMobile(this.phone);
				if (!phoneFlag) {
					return uni.showToast({ title: "请输入正确的手机号码", icon: "none"})
				} else {
					this.msgVerCode = this.countDown + ' 秒';
					this.timer = setInterval(() => {
						if (this.countDown === 1) {
							this.reset();
							this.timer && this.clearTimer();
						} else {
							this.loading();
						}
					}, 1000);
					let op = {
						phone: this.phone
					}
					let url = this.type == 'setPayPw' ? '/api/sms/msg/sendPwd' : '/api/sms/msg/send'
					// 请求接口获取验证码
					await this.$http.post(url, op, {header:{authType:'false'}}).then(res => {
						// 请求成功提示语
					    if(res.success == true) {
					    	uni.showToast({title: '发送成功'});
					    } else {
					    	uni.showToast({title:res.msg,icon:'none'})
					    	this.reset()
					    }
					})
					return 
				}
			},
			loading() {
				this.countDown -= 1;
				this.msgVerCode = this.countDown + ' 秒';
				this.isLock = false;
			},
			reset() {
				console.log('--------')
				this.countDown = 60;
				this.isLock = true;
				this.msgVerCode = '获取验证码';
			},
			clearTimer() {
				clearInterval(this.timer);
				this.timer = null;
			},
		}
	}
</script>

<style scoped lang="scss">
.code-btn {
	width: 180rpx;
	height: 60rpx;
	line-height: 60rpx;
	text-align: center;
	background-color: rgba(34, 213, 242, 0.08);
	color: $main-color;
	border-radius: 60rpx;
}
</style>
