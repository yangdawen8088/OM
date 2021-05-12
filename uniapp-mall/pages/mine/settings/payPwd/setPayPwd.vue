<template>
	<view class="setPayPw">
		<view class="u-f-ac set-li">
			<view class="prefix">+86</view>
			<view>{{userInfo.memberPhone}}</view>
		</view>
		<form @submit="save">
			<view class="u-f-jsb u-f-ac set-li">
				<input type="text" placeholder="请输入验证码" name="code" placeholder-style="color:#aaa">
				<countdown :phone="userInfo.memberPhone" type="setPayPw"></countdown>
			</view>
			<button type="primary"  form-type="submit" class="submitBtn">下一步</button>
		</form>
	</view>
</template>

<script>
export default{
	data(){
		return{
			userInfo:''
		}
	},
	onLoad(e) {
		this.userInfo = this.$store.state.userInfo
	},
	methods:{
		save(e){
			let formdata = e.detail.value;
			formdata.memberPhone = this.userInfo.memberPhone
			formdata.memberAccount = this.userInfo.memberPhone
			formdata.type = 1
			// 请求
			this.$http.post('/api/auth/member/pwd/setPayPwd',formdata).then(res => {
				this.$tip.success(res,0,'setPayPwd2?formdata='+ encodeURIComponent(JSON.stringify(formdata)))
			});
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
.setPayPw {
	padding: 60rpx 50rpx;
}
.set-li {
	padding: 19rpx 0;
	margin-bottom: 40rpx;
}
.mb100 {
	margin-bottom: 100rpx;
}
.set-li,
.set-li input,
.set-li text {
	font-size: 24rpx;
}
.prefix {
	border-right: 2rpx solid #eee;
	padding-right: 28rpx;
	margin-right: 28rpx;
}
.flex1{
	flex: 1;
}
</style>
