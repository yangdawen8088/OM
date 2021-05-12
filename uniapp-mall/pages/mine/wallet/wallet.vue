<!-- 我的钱包 -->
<template>
	<view class="wallet">
		<view class="wallet-box1 u-f-jsb text-center">
			<view :class="curTab == 0? 'titactive': ''">
				<view class="text-sm">金币</view>
				<view class="text-df">0</view>
			</view>
			<view :class="curTab == 3?'titactive': ''">
				<view class="text-sm">佣金</view>
				<view class="text-df">0</view>
			</view>
		</view>
		<view class="xpadding list">
			
			<view class="wallet-box2 u-f-ajc text-center text-sm">
				<view @tap="recharge">
					<view class="icon iconfont icon-chongzhi"></view>
					<view>金币充值</view>
				</view>
				<view @tap="transfer">
					<view class="icon iconfont icon-danbizhuanzhang"></view>
					<view>金币转账</view>
				</view>
				<view @tap='withdra'>
					<view class="icon iconfont icon-tixian"></view>
					<view>佣金提现</view>
				</view>
			</view>
			<view class="wallet-box3">
				<view class="u-f-jsb u-f-ac margin-bottom-xs">
					<text class="text-df text-bold">账单</text>
					<view class="text-sm more u-f-jse" @tap="more">查看更多<text class="icon iconfont icon-right"></text></view>
				</view>
				<scroll-view scroll-y class="bill-box">
					<view class="bill-list"  v-for="(item,index) in 10" :key="index">
						<view class="u-f-jsb">
							<text class="text-cut">转账转账转账转账转账转账</text>
							<text v-if="item.sign === '+'"  class="text-red">+33</text>
							<text v-else>12</text>
						</view>
						<view class="u-f-jsb">
							<view class="time">2021-12-03 09:08:50</view>
						</view>
					</view>
				</scroll-view>
			</view>
		</view>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				curTab:0,
			}
		},
		onLoad() {
		}
	}
</script>

<style scoped lang="scss">
.list {
	position: relative;
	top: -60rpx;
}
.wallet-box1 {
	background: #2ba4f7;
	color: #fff;
	padding: 28rpx 28rpx 88rpx;
	> view {
		width: 50%;
	}
	.text-sm {
		margin-bottom: 18rpx;
	}
}
.wallet-box2 {
	> view {
		width: 33.3%;
	}
	background-color: #fff;
	border-radius: 10rpx;
	padding: 36rpx 0;
	margin-bottom: 20rpx;
	.icon {
		font-size: 68rpx;
		background-image: linear-gradient(129deg, #f22222 0%, #ff8549 100%);
		-webkit-background-clip:text; 
		-webkit-text-fill-color:transparent; 
		margin-bottom: 14rpx;
	}
}
.wallet-box3 {
	background-color: #fff;
	border-radius: 10rpx;
	padding: 38rpx 28rpx;
	.more {
		color: $main-color;
		.icon {
			color: $main-color;
			margin-left: 10rpx;
		}
	}
	.bill-box{
		height: calc(100vh - 570rpx);
	}
	.bill-list {
		font-size: 26rpx;
		padding: 28rpx 0;
		border-bottom: 2rpx solid #eee;
		.text-cut {
			width: 444rpx;
		}
		.time {
			color: #666;
			margin-top: 10rpx;
			font-size: 24rpx;
		}
	}
}
.titactive{
	// color: #000000;
	font-weight: bolder;
	.text-sm{
		font-size: 30upx;
	}
	.text-df{
		color: #FFFFFF;
		background: rgba($color: #fff, $alpha: 0.3);
		margin: 0 40rpx;
		padding:8rpx 18rpx;
		border-radius: 10rpx;
		font-size: 24upx;
		box-sizing: border-box;
	}
}
</style>
