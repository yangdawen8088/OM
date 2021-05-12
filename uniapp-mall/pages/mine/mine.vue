 <!-- 我的主页 -->
 <template>  
 	<view class="mine">
 		<!-- 用户信息 -->
 			<!-- 这里是内容头像内容 -->
 			<view class="my-top padding">
 				<!-- 已登录 -->
 				<view class="u-f-ac avater-box" v-if="userInfo">
 					<view class="avater round">
 						<!-- 有头像，则显示头像，没头像，则用默认头像 -->
 						<image :src="userInfo.memberAvatar?userInfo.memberAvatar:'../../static/logo.jpg'"></image>
 					</view>
 					<view class="avatar-text">
 						<view class="u-f-ac">
 							<!-- 有昵称显示昵称，无昵称，显示手机号码 -->
 							<text class="name text-cut">{{userInfo.nickName || userInfo.memberAccount}}</text>
 						</view>
						<navigator url="settings/setting" hover-class="none" class="group">账户设置</navigator>
 					</view>
 				</view>
 				<!-- 未登录 -->
 				<navigator hover-class="none" url="../auth/login/login" class="u-f-ac avater-box" v-else>
 					<view class="avater round">
 						<image src="../../static/logo.jpg"></image>
 					</view>
 					<view class="avatar-text">
 						<view class="u-f-ac">
 							<text style="text-decoration: underline;">点击登录</text>
 						</view>
 					</view>
 				</navigator>
 			</view>
 			<!-- 我的订单 -->
 			<view class="order-box margin">
 				<view class="order-title u-f-jsb">
 					<view class="text-bold">我的订单</view>
 					<view @tap="navTo('../mine/order/order')">查看全部订单<text class="icon iconfont icon-right"></text></view>
 				</view>
 				<view class="order-type u-f text-center">
 					<view @tap="navTo('../mine/order/order')">
 						<view class="icon iconfont icon-daifukuan"></view>
 						<text>待付款</text>
 						<view class="badge" v-if="orderNum.waitPay">{{orderNum.waitPay}}</view>
 					</view>
 					<view @tap="navTo('../mine/order/order')">
 						<view class="icon iconfont icon-daishouhuo"></view>
 						<text>待收货</text>
 						<view class="badge" v-if="orderNum.waitReceive">{{orderNum.waitReceive}}</view>
 					</view>
 					<view @tap="navTo('../mine/order/order')">
 						<view class="icon iconfont icon-daipingjia"></view>
 						<text>已完成</text>
 					</view>
 					<view @tap="navTo('../mine/order/order')">
 						<view class="icon iconfont icon-tuikuan"></view>
 						<text>退款/售后</text>
 					</view>
 				</view>
 			</view>
 			<view class="menu-list margin">
				<view class="item" @tap="navTo('../mine/coupon/coupon')">
					<view class="left"><text class="icon iconfont iconfensix"></text>优惠卷</view>
					<view class="icon iconfont icon-right"></view>	
				</view>
				<view class="item" @tap="navTo('../mine/collect/collect')">
					<view class="left"><text class="icon iconfont iconfensix"></text>我的收藏</view>
					<view class="icon iconfont icon-right"></view>	
				</view>
 				<view class="item" @tap="navTo('../mine/fans/fans')">
 					<view class="left"><text class="icon iconfont iconfensix"></text>我的团队</view>
 					<view class="icon iconfont icon-right"></view>	
 				</view>
 				<view class="item" @tap="navTo('../mine/wallet/wallet')">
 					<view class="left"><text class="icon iconfont iconerweima"></text>我的钱包</view>
 					<view class="icon iconfont icon-right"></view>	
 				</view>
 				<view class="item" @tap="navTo('../mine/settings/setting')">
 					<view class="left"><text class="icon iconfont iconshezhi"></text>账户设置</view>
 					<view class="icon iconfont icon-right"></view>	
 				</view>
 			</view>
 	</view>
 </template>
 
 <script>
 	export default {
 		data() {
 			return {
 				userInfo: '',
 				// 各订单数量
 				orderNum: {
 					waitPay: 3,
 					waitDeliver: 2,
 					waitReceive: 0
 				}
 			}
 		},
 		onShow() {
 			this.userInfo = this.$store.state.userInfo
 		},
 		methods: {
 			// 订单页
 			order(e) {
 				this.navTo("../my/YJGorder/order?tab="+e)
 			},
 			navTo(url){
 				// 路由跳转 intercept：true 路由拦截
 				this.Route({url,intercept:true})  
 			},
 		}
 	}
 </script>
 
 <style scoped lang="scss">
//
.mine{
	height: 276rpx;
	background-color: $main-color;
}
 // menu-list列表
 .menu-list{
 	background-color: #fff;
 	border-radius: 20rpx;
 	.item{
 		display: flex;
 		align-items: center;
 		justify-content: space-between;
 		padding: 30rpx 0;
 		margin: 0 20rpx;
 		.left{
 			.iconfont{
 				font-size: 38rpx;
 				padding-right: 10rpx;
 				color: #487556;
 			}
 		}
 		.iconfont{
 			font-size: 32rpx;
 		}
 	}
 	.item:not(:last-child){
 		border-bottom: 1px solid #f1f3ee;
 	}
 }
 .page {
 	height: 100vh;
 }
 // 头像部分
 .my-top {
 	color: #fff;
 	padding-bottom: 20rpx;
 }
 .top-right-btns view {
 	margin-left: 30rpx;
 	.icon {
 		margin-right: 4rpx;
 	}
 }
 .avater-box {
 	margin-bottom: 28rpx;
 	.avater {
 		width: 120rpx;
 		height: 120rpx;
 		padding: 10rpx;
 		background-color: rgba(242,242,242,0.1);
 		image {
 			width: 100rpx;
 			height: 100rpx;
			border-radius: 10rpx;
 		}
 	}
 	.avatar-text {
 		margin-left: 20rpx;
 		flex: 1;
 		text {
 			margin-right: 10rpx;
 		}
 		// .name{
 		// 	max-width: 35%;
 		// }
 		image {
 			width: 30rpx;
 			height: 20rpx;
 		}
 		.group {
 			font-size: 24rpx;
 			padding: 0 20rpx;
 			background-color: rgba(163,52,24,0.3);
 			display: inline-block;
 			line-height: 38rpx;
 			border-radius: 38rpx;
			margin-top: 10rpx;
 		}
 		.memberAcc{
 			margin-top: 10rpx;
 			line-height: 1;
 		}
 	}
 }
 // 我的订单部分
 .order-box {
 	background-color: #fff;
 	border-radius: 20rpx;
 	margin-top: 0;
 	margin-bottom: 20rpx;
 	.order-title {
 		padding: 0 20rpx;
 		line-height: 78rpx;
 		color: #080808;
 		border-bottom: 2rpx solid #eee;
 		:last-child {
 			color: #666;
 			font-size: 26rpx;
 			.icon {
 				margin-left: 20rpx;
 			}
 		}
 	}
 	.order-type {
 		padding: 30rpx 0;
 		font-size: 22rpx;
 		color: #080808;
 		> view {
 			width: 33.33%;
 			position: relative;
 		}
 		.icon {
 			font-size: 62rpx;
 			margin-bottom: 20rpx;
 			background-image: linear-gradient(129deg, #007aff 0%, #2ba4f7 100%);
 			-webkit-background-clip:text; 
 			-webkit-text-fill-color:transparent; 
 		}
 		.badge {
 			min-width: 40rpx;
 			height: 40rpx;
 			line-height: 40rpx;
 			border: 1px solid #F22222;
 			text-align: center;
 			color: red;
 			background-color: #fff;
 			border-radius: 5000upx;
 			font-size: 24rpx;
 			position: absolute;
 			left: 50%;
 			margin-left: 20rpx;
 			top: -10rpx;
 		}
 	}
 }
 </style>
 