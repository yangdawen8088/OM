<!-- 忘记修改登录密码 或注册新用户 -->
<template>
	<view class="login-box">
		<view class="u-f-ac u-fc">
			<view class="mylogin-img u-f-ac">
				<image src="../../../static/logo.jpg"></image>
			</view>
			<form @submit="save">
				<view class="setPayPw">
					<view class="u-f-ac set-li">
						<view class="prefix">+86</view>
						<input type="number" maxlength="11" name="phone" v-model="phone" placeholder="请输入手机号" placeholder-style="color:#aaa"/>
					</view>
					<view class="u-f-jsb u-f-ac set-li ">
						<input type="text" maxlength="6" placeholder="请输入验证码" name="code" placeholder-style="color:#aaaaaa">
						<countdown :phone="phone" type="register"></countdown>
					</view>
					<view class="u-f-jsb u-f-ac set-li">
						<view class="set-name">密码</view>
						<input type="password" placeholder="请输入密码" name="password" placeholder-style="color:#aaa">
					</view>
					<view class="u-f-jsb u-f-ac set-li">
						<view class="set-name">确认密码</view>
						<input type="password" placeholder="请确认您输入的密码" name="comfirePwd" placeholder-style="color:#aaa">
					</view>
					<view class="tips">密码由8~20位字母、数字组合，不能含有特殊字符，不能含有中文</view>
					<button type="primary"  form-type="submit" class="submitBtn">确定</button>
				</view>
			</form>
		</view>
	</view>
</template>

<script>
import valid from '@/common/js/utils/formValidate.js'
export default{
	data(){
		return{
			phone: this.$store.state.userInfo.memberPhone || '',
			type:1,
			flag: true,
		}
	},
	onLoad(option) {
		this.type = option.type
		uni.setNavigationBarTitle({
		    title: option.name
		});
	},
	methods:{
		save(e){
			if(!this.flag) return this.$tip.toast('手速太快了,悠着点~')
			//获取表单数据
			let formdata = e.detail.value;
			//定义表单验证规则
			let rule = [
				{name:"phone", checkType : "phoneno", checkRule:"",  errorMsg:"请输入正确的手机号"},
				{name:"code", checkType : "zipcode", checkRule:"",  errorMsg:"请输入正确的验证码"},
				{name:"password", checkType : "checkPwd", checkRule:"",  errorMsg:"请输入8~20位字母或者数字的密码"},
				{name:"comfirePwd", checkType : "same", checkRule: formdata.password,  errorMsg:"两次输入密码不一致"},
			];
			//表单验证
			let checkRes = valid.check(formdata, rule);
			if(checkRes){
				this.flag = false
				// 判断忘记密码还是注册
				let url = this.type == 2 ? '/api/member/register' : '/api/member/editOrForget'
				//发送请求
				this.$http.post(url, formdata, {header:{authType:'false'}}).then(res => {
					this.flag = true
					if(this.type == 3){
						// 修改密码
						this.$tip.successRedirect({res,msg:'修改密码成功，请重新登录'})
						if(res.success){this.loginOut()}
					}else{
						// 请求成功提示语
						this.$tip.successBack(res,1500,1)
					}
				}).catch(e=>{
					this.flag = true
				})
			}else{
			   this.$tip.toast(valid.error)
			}
		},
		loginOut(){
			this.$http.post('/api/member/logout',this.$store.state.userInfo.memberId, {header:{authType:'false'}}).then(res => {
				this.$store.commit('removeUserInfo')
				this.Route({url: '../login/login',type:'redirectTo',intercept:false,duration:1500})
			})
		}
	}
}
</script>
<style scoped lang="scss">
page{
	height: 100vh;
	overflow: hidden;
}
.login-box{
	height: 100vh;
	box-sizing: border-box;
	background-color: #ffffff;
	image{
		width: 280rpx;
		height: 280rpx;
	}
}
// 
.setPayPw{
	width: 100vw;
	padding: 0 60rpx;
	margin-top: 40rpx;
}
.set-li{
	box-sizing: border-box;
	.set-name{
		font-size: 24rpx;
		width: 140rpx;
	}
	input{
		flex: 1;
	}
}
.set-li,
.set-li input,
.set-li text {
	font-size: 24rpx;
}

.set-li .prefix {
	border-right: 2rpx solid #eee;
	padding-right: 28rpx;
	margin-right: 28rpx;
}

.mylogin-img{
	margin-top: 40rpx;
}
.tips {
	color: #aaa;
	font-size: 24rpx;
	margin-top: 20rpx;
	margin-bottom: 96rpx;
}
</style>

