<!-- 设置页面 -->
<template>
	<view class="setting">
		<view class="setting-top">
			<view class="u-f-jsb u-f-ac avatar-box" @tap="navTo('info/info')">
				<view class="u-f-ac">
					<view class="avatar round">
						<!-- 有头像，则显示头像，没头像，则用默认头像 -->
						<image :src="userInfo.memberAvatar?userInfo.memberAvatar:'../../../static/logo.jpg'" class="round"></image>
					</view>
					<!-- 有昵称显示昵称，无昵称，显示手机号码 -->
					<text v-if="userInfo">{{userInfo.nickName || userInfo.memberAccount}}</text>
				</view>
				<view class="icon iconfont icon-right"></view>
			</view>
			<view class="u-f-jsb u-f-ac set-li" @tap="navTo('address/address')">
				<text>地址管理</text>
				<view class="icon iconfont icon-right"></view>
			</view>
		</view>
		<view class="radius20">
			<view class="u-f-jsb u-f-ac set-li" @tap="navTo('../../auth/forget/forget?name=修改登录密码&type=3')">
				<text>修改登录密码</text>
				<view class="icon iconfont icon-right"></view>
			</view>
			<view class="u-f-jsb u-f-ac set-li" @tap="navTo('payPwd/setPayPwd?memberPhone='+userInfo.memberPhone)" v-if="!userInfo.payPwd">
				<text>设置支付密码</text>
				<view class="icon iconfont icon-right"></view>
			</view>
			<view class="u-f-jsb u-f-ac set-li" @tap="navTo('payPwd/changePayPwd')" v-else>
				<text>修改支付密码</text>
				<view class="icon iconfont icon-right"></view>
			</view>
			<view class="u-f-jsb u-f-ac set-li" @tap="navTo('feedback/feedback')">
				<text>意见反馈</text>
				<view class="icon iconfont icon-right"></view>
			</view>
			<navigator hover-class="none" url="about/about" class="u-f-jsb u-f-ac set-li">
				<text style="flex: 1;">关于M家购</text>
				<view style="color: #666666;">版本号 1.0.0</view>
				<view class="icon iconfont icon-right"></view>
			</navigator>
		</view>
		<tui-modal :show="modal" @click="handleClick" @cancel="hide" title="提示" content="确定退出登录吗？"></tui-modal>
		<view class="loginOut" @tap="outlogin">退出账号</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo:'',
				modal:false
			}
		},
		onLoad() {
			this.userInfo =  this.$store.state.userInfo
		},
		methods: {
			navTo(url){
				// 路由跳转 intercept：true 路由拦截
				this.Route({url,intercept:true})  
			},
			// 退出登录
			outlogin(){
				this.modal = true
			},
			hide(){
				this.modal = false
			},
			handleClick(e){
				this.hide()
				if(e.index == 1){
					this.$http.post('/api/member/logout',this.userInfo.memberId, {header:{authType:'false'}}).then(res => {
						this.$tip.successBack(res, 1500, 1);
						this.$store.commit('removeUserInfo')
					})
					
				}
				
			}
		}
	}
</script>

<style scoped lang="scss">
.loginOut {
	border-radius: 20rpx;
	background-color: #fff;
	line-height: 102rpx;
	position: absolute;
	text-align: center;
	bottom: 60rpx;
	width: 100%;
}	
</style>
