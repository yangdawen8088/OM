<!-- 我的订单 -->
<template>
	<view class="order">
		<!-- 搜索框 -->
		<view class="topnav fixed">
			<view class="cu-bar search bg-white">
				<view class="search-form round">
					<text class="cuIcon-search icon iconfont icon-sousuo"></text>
					<input type="text" placeholder="搜索订单" confirm-type="search" @tap="searchOrder" disabled></input>
				</view>
			</view>
		</view>
		<!-- tab标签栏 -->
		<view class="fixed tab">
			<tui-tabs :tabs="tabs" :currentTab="currentTab" @change="change" itemWidth="20%" :top="0"></tui-tabs>
		</view>
		<!-- 内容 -->
		<view class="uni-tab-bar">
			<swiper class="swiper-box" :current="currentTab">
				<!-- 全部 -->
				<swiper-item v-for="i in tabs">
					<scroll-view scroll-y>
						<view class="baseW" v-for="(item,index) in 5" :key="index">
							<view class="order-shop u-f-jsb">
								<view class="u-f-ac">
									<text class="icon iconfont icon-dianpu"></text>
									<text class="order-shop-name text-bold text-cut">店铺名称M家购</text>
									<text class="icon iconfont icon-right"></text>
								</view>
								<view class="text-red text-sm" v-if="item.orderState == 0">等待买家付款</view>
								<view class="text-red text-sm" v-if="item.orderState == 1">买家已付款</view>
								<view class="text-red text-sm" v-if="item.orderState == 2">待收货</view>
								<view class="text-red text-sm" v-if="item.orderState == 3">交易成功</view>
								<view class="text-red text-sm" v-if="item.orderState == 4">订单已关闭</view>
								<view class="text-red text-sm" v-if="item.orderState == 5">交易已取消</view>
								<view class="text-red text-sm" v-if="item.orderState == 6">退款中</view>
								<view class="text-red text-sm" v-if="item.orderState == 9">交易关闭</view>
							</view>
							<navigator hover-class="none" url="./detail" class="u-f-jsb u-f-ac order-goods" v-for="(itemg,indexg) in 2" :key="indexg">
								<view class="order-img-box"><image src="../../../static/goods/6.jpg"></image></view>
								<view class="order-goods-text">
									<view class="order-goods-name">新鲜草莓新鲜草莓新鲜草莓新鲜草莓新鲜草莓</view>
									<view class="guige text-sm">
										<text style="margin-right: 10rpx;">重量：1kg</text>
									</view>
									<view class="u-f-jsb">
										<view class="order-goods-price text-red text-bold text-sm">￥<text>99</text></view>
										<view class="text-sm order-num">共2件</view>
									</view>
								</view>
							</navigator>
							<view class="u-f-jse" v-if="item.orderState == 0">
								<view class="base-btn gray-btn" @tap="cancel(item.id)">取消订单</view>
								<view class="base-btn gray-btn" @tap="changeAddress(item.id,item.address)">修改地址</view>
								<view class="base-btn red-btn" @tap="pay(item.id)">付款</view>
							</view>
							<view class="u-f-jse" v-if="item.orderState == 1">
								<!-- <view class="base-btn gray-btn" @tap="refund(item.id)">退款</view> -->
								<view class="base-btn gray-btn" @tap="changeAddress(item.id, item.address)">修改地址</view>
								<view class="base-btn red-btn" @tap="urge(item.storeId,item.id)">催发货</view>
							</view>
							<view class="u-f-jse" v-if="item.orderState == 2">
								<!-- <view class="base-btn gray-btn" @tap="logistics(item.id)">查看物流</view> -->
								<view class="base-btn red-btn" @tap="confirmReceipt(item.id)">确认收货</view>
							</view>
							<view class="u-f-jse" v-if="item.orderState == 3">
								<view class="base-btn red-btn" @tap="addCart(item.id)">加入购物车</view>
							</view>
							<view class="u-f-jse" v-if="item.orderState == 4 || item.orderState == 5">
								<view class="base-btn red-btn" @tap="deleteOrder(item.id)">删除订单</view>
							</view>
						</view>
						<view class="no-order" v-if="noDataFlag1">
							<image src="https://zj.gdwrh.com/fnyg/static/images/my-img/no_order.png"></image>
							<view>您还没有相关的订单哦！</view>
							<view>可以去看看那些想买的</view>
						</view>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				noDataFlag1: false,
				currentTab:0,
				tabs: [ 
					{name: "全部",num:"0"}, 
					{name: "待付款",num:"0"}, 
					{name: "待收货",num:"0"},
					{name: "已完成",num:"0"},
					{name: "退款/售后",num:"0"}
				],
			}
		},
		methods:{
			change(e){
				this.currentTab = e.index
			},
		}
	}
</script>
<style lang="scss">

</style>
<style scoped lang="scss">
.order {
	position: relative;
}
.topnav {
	top: 0;
	// #ifdef H5
	top: 44px;
	// #endif
}
.tab {
	top: 0;
	// #ifdef H5
	top: 44px;
	// #endif
	padding-top: 100rpx;
	width: 100%;
}
tui-tabs {
	margin-top: 100px;
}
.uni-tab-bar {
	padding-top: 192rpx;
	swiper-item {
		scroll-view {
			padding: 20rpx 28rpx 20rpx;
			height: 100%;
			.baseW {
				padding: 30rpx;	
			}
		}
	}
}
.swiper-box{
	height: calc(100vh - 212rpx);
}
// 退款售后
.reorder-num {
	font-weight: bold;
	color: #080808;
}
.price {
	line-height: 80rpx;
	border-radius: 10rpx;
	background-color: #F2F2F2;
	padding: 0 28rpx;
	font-size: 26rpx;
	margin: 30rpx 0;
}
.price view:first-child {
	font-size: 30rpx;
	font-weight: bold;
	margin-right: 20rpx;
}
.order-shop .u-f-ac{
	width: 480rpx;
}
</style>

