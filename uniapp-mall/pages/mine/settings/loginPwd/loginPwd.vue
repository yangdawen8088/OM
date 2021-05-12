<!-- 修改登录密码 -->
<template>
	<view class="changePhone">
		<form @submit="save">
			<view class="u-f-ac set-li">
				<text>原密码</text>
				<input type="password" placeholder="请输入原密码" name="oldPassword" placeholder-style="color:#ccc"> </view>
			<view class="u-f-ac set-li">
				<text>新密码</text>
				<input type="password" placeholder="请输入8~20位字符" name="newPassword" placeholder-style="color:#ccc">
			</view>
			<view class="u-f-ac set-li">
				<text>确认密码</text>
				<input type="password" placeholder="请输入8~20位字符" name="rePassword" placeholder-style="color:#ccc">
			</view>
			<view class="tips">密码由8~20位字母、数字组合，不能有特殊字符，不能是纯数字/字母，字母区分大小写</view>
			<button type="primary"  form-type="submit" class="submitBtn">完成</button>
		</form>
		<tui-modal :show="modal" @cancel="hide" :custom="true" width="510rpx" :maskClosable="false" padding="60rpx 0">
			<view class="tui-modal-custom">
				<image src="https://zj.gdwrh.com/fnyg/static/images/my-img/fkyj_cg_x3.png" class="tui-tips-img"></image>
				<view class="tui-modal-custom-text">修改登录密码成功</view>
				<tui-button type="danger" width="400rpx" margin="0 55rpx" :shadow="true" shape="circle" :size="30" @click="handleClick">确定</tui-button>
			</view>
		</tui-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				modal: false,
				flag:true
			};
		},
		methods:{
			save(e){
				if(!this.flag) return this.$tip.toast('手速太快了,悠着点~')
				let rule = [
					{name:"account", checkType : "phoneno", checkRule:"",  errorMsg:"您的账号不存在"},
					{name:"password", checkType : "notnull", checkRule:"",  errorMsg:"请输入密码"}
				];
				//获取表单数据
				let formdata = e.detail.value;
				//表单验证
				let checkRes = valid.check(formdata, rule);
				if(checkRes){
					this.flag = false
				   //发送请求
				   this.$http.post('/api/member/auth/editOrForget', formdata).then(res => {
					   this.flag = true
				   }).catch(err => {
						this.flag = true
				   })
				}else{
				   uni.showToast({ title: valid.error, icon: "none" })
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
.changePhone {
	padding: 60rpx 50rpx;
}
.set-li {
	padding: 19rpx 0;
	margin-bottom: 40rpx;
}
.set-li,
.set-li input,
.set-li text {
	font-size: 24rpx;
}
.tui-modal-custom {
	text-align: center;
	font-size: 30rpx;
	.tui-tips-img {
		width: 130rpx;
		height: 130rpx;
		margin-bottom: 30rpx;
	}
	.tui-modal-custom-text {
		margin-bottom: 48rpx;
	}
}
.tips {
	color: #aaa;
	font-size: 24rpx;
	margin-bottom: 96rpx;
}
</style>
