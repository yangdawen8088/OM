<template>
	<view class="wapper">
		<view class="top">
			<image src="https://sbb-sbb.oss-cn-shenzhen.aliyuncs.com/static/images/my-img/wate.jpg" mode="widthFix" class="order-bg"></image>
			<view class="text-white text-center wait" v-if="orderState == 0">
				<view class="text-xl">等待买家付款</view>
				<view class="text-sm">23:00自动关闭</view>
			</view>
			<view class="text-white text-center wait" v-if="orderState == 6">
				<view class="text-xl">退款审核中</view>
				<view class="text-sm" v-if="orderDetail.applyTime">申请时间：{{orderDetail.applyTime | dateFormat}}</view>
			</view>
			<view class="text-white text-center wait" v-if="orderState == 7">
				<view class="text-xl">退款成功</view>
				<view class="text-sm" v-if="orderDetail.approveTime">审核成功时间：{{orderDetail.approveTime | dateFormat}}</view>
			</view>
			<view class="text-white text-center wait" v-if="orderState == 8">
				<view class="text-xl">退款审核失败</view>
				<view class="text-sm" v-if="orderDetail.approveTime">审核失败时间：{{orderDetail.approveTime | dateFormat}}</view>
			</view>
			<view class="text-white text-center order-top">
				<view class="text-xl" v-if="orderState == 1">待发货</view>
				<view class="text-xl" v-if="orderState == 2">待收货</view>
				<view class="text-xl" v-if="orderState == 3">订单已完成</view>
				<view class="text-xl" v-if="orderState == 4">订单已关闭</view>
				<view class="text-xl" v-if="orderState == 5">订单已取消</view>
				<view class="text-xl" v-if="orderState == 9">交易已关闭</view>
				<view class="text-xl" v-if="orderState == 11">已撤销退款申请</view>
			</view>
		</view>
		<!-- 地址时间 -->
		<view class="addresss bg-white padding">
			<view class="getTitle">收货地址:</view>
			<view class="u-f-ac align-center">
				<text class="icon iconfont icon-dizhi"></text>
				<view class="add">
					<text>广东省广州市白云区富丽大厦</text>
					<view class="text-bold">门牌703 李哈哈 13602464662</view>
				</view>
			</view>
		</view>
		<!-- 商品内容 -->
		<view class="contaner bg-white padding-lr" :class="true ? 'ovh' :'auto'">
			<view class="list" :class="{active:flag}">
				<view class="con-item u-f-ac"v-for="(item,index) in 5" :key="index">
					<image class="pic" src="../../../static/goods/5.jpg" mode="widthFix"></image>
					<view class="info-right">
						<view class="name">新鲜牛肉肉肉肉肉肉肉肉肉肉肉肉肉肉肉肉肉肉肉肉肉肉</view>
						<view class="Spec guige text-sm">
							<text style="margin-right: 10rpx;">重量：1kg</text>
						</view>
						<text class="text-gray margin-bottom-sm text-sm">原价:199.00</text>
						<view class="u-f-jsb del">
							<view class="text-red">￥99.00</view>
							<view class="text-red"></view>
							<view class="">X3</view>
						</view>
						<view class="u-f-jse" v-if="orderState == 1 || orderState == 2">
							<view v-if="item.orderItemState == 0" class="base-btn gray-btn" @tap="gorefundDetail(orderDetail.id,item.skuId,0)">退款中</view>
							<view v-if="item.orderItemState == 1" class="base-btn gray-btn" @tap="gorefundDetail(orderDetail.id,item.skuId,1)">退款成功</view>
							<view v-if="item.orderItemState == 2" class="base-btn gray-btn" @tap="gorefundDetail(orderDetail.id,item.skuId,2)">已拒绝</view>
							<view v-if="item.orderItemState == 3" class="base-btn gray-btn" @tap="gorefundDetail(orderDetail.id,item.skuId,3)">已撤销申请</view>
							<!-- 审核通过或拒绝或撤销，但是退换的数量 小于 购买的总数量，可以继续退换 -->
							<!-- 未发货 -->
							<view v-if="item.orderItemState !=-1 && item.orderItemState !=0 && item.applyCount < item.count " class="base-btn gray-btn" @tap="gorefund(orderDetail.id,item)">继续退换</view>
						</view>
						<!-- 未发货的时候，申请退款 -->
						<view class="u-f-jse" v-if="orderState == 1 && item.orderItemState == -1">
							<view class="base-btn gray-btn" @tap="gorefund(orderDetail.id,item)">申请退款</view>
						</view>
					</view>
					
				</view>
			</view>
			<view class="padding-tb u-fc more" @click="conmore">
				{{flag?'收起':'展开更多'}}
				<text class="icon iconfont" :class="flag?'icon-icon-arrow-top2':'icon-icon-arrow-bottom2'"></text>
			</view>
		</view>
		<!-- 订单信息 -->
		<view class="infoxq bg-white">
			<view class="info-top u-f-jsb">
				<view class="title">订单信息</view>
				<view class="more" v-if="!showMore" @tap="infomore">查看更多<text class="icon iconfont icon-xia"></text></view>
			</view>
			<view class="info-list u-f-ac">
				<text>订单备注：</text>
				<text>测试测试</text>
			</view>
			<view class="info-list u-f-ac">
				<text>积分：</text>
				<text>获得66积分</text>
			</view>
			<view class="info-list u-f-ac">
				<text>赠送金券：</text>
				<text>获得22金券</text>
			</view>
			<view class="info-list u-f-jsb">
				<view class="u-f-ac spec">
					<text>订单编号：</text>
					<text>23344433333</text>
				</view>
				<view class="text-red" @tap="clipboard(orderDetail.outTradeNo)">复制</view>
			</view>
			<view class="info-list u-f-ac">
				<text>支付方式：</text>
				<text>微信支付</text>
			</view>
			<view v-if="showMore">
				<view class="info-list u-f-ac">
					<text>创建时间：</text>
					<text>2021-12-30 12:30:09</text>
				</view>
				<view class="info-list u-f-ac">
					<text>付款时间：</text>
					<text>2021-12-30 12:30:09</text>
				</view>
				<view class="info-list u-f-ac">
					<text>申请退款：</text>
					<text>2021-12-30 12:30:09</text>
				</view>
				<view class="info-list u-f-ac">
					<text>退款原因：</text>
					<text>个人原因</text>
				</view>
				<view class="info-list u-f-ac">
					<text>审核时间：</text>
					<text>2021-12-30 12:30:09</text>
				</view>
				<view class="info-list u-f-ac">
					<text>完成时间：</text>
					<text>2021-12-30 12:30:09</text>
				</view>
			</view>
			<view style="clear: both;"></view>
		</view>
		<!-- 未付款 -->
		<view class="u-f-jse btn-box" v-if="orderState == 0">
			<view class="base-btn gray-btn" @tap="cancel()">取消订单</view>
			<view class="base-btn red-btn" @tap="pay(orderDetail.id)">付款</view>
		</view>
		<!-- 订单已取消或者订单已关闭 -->
		<view class="u-f-jse btn-box" v-if="orderState == 4 || orderState == 5">
			<view class="base-btn red-btn" @tap="deleteOrder(orderDetail.id)">删除订单</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				orderState:0,
				flag: false,
				showMore: false
			};
		},
		methods:{
			infomore(){
				this.showMore = true
			},
			conmore(){
				this.flag =!this.flag
			}
		}
	}
</script>

<style lang="scss">
	.wait {
		position: absolute;
		width: 100%;
		top: 0;
		padding-top: 30rpx;
	}
	.getTitle {
		margin-bottom: 10rpx;color: #000000;font-size: 30rpx;color: #f22222;font-weight: bold;
	}
	.wapper {
		padding-bottom: 96rpx;
	}
	.wapper .top {
		font-size: 0;
		.order-bg {
			margin-bottom: 0;
		}
	}
	.wapper .btn-box {
		border-radius: 0;
		margin-bottom: 0;
	}
	.wapper > view{
		margin-bottom: 20upx;
		border-radius: 20upx;
	}
	.btn-white{
		border-radius: 20upx !important;
	}
	// 取货码
	.head{
		// border-top-left-radius: 0 !important;
		// border-top-right-radius: 0 !important;
		align-items: center;
		& > view{
			padding: 20upx 0;
		}
		.text{
			font-size: 24upx;
		}
		.code{
			font-size: 42upx;
			font-weight: bold;
			color: $black-color;
		}
	}
	// 地址
	.addresss{
		.add{
			color: $black-color;
		}
		.icon-dizhi {
			font-size: 40rpx!important;
			margin-right: 10rpx;
		}
	}
	// 商品内容
	.ovh .list{
		height: 470upx;
		overflow: hidden;
	}
	.auto .list{
		overflow: visible !important;
		height: auto !important;
	}
	.active{
		overflow: visible !important;
		height: auto !important;
	}
	.contaner{
		.more{
			align-items: center;
			.icon{
				font-size: 34upx;
			}
		}
		.con-item:not(:first-child){
			border-top: 2upx solid #f2f2f2;
		}
		.con-item{
			width: 100%;
			box-sizing: border-box;
			padding: 30upx 0;
			.pic{
				margin-left: 5upx;
				width: 176upx;
				height: 176upx;
				display: block;
				box-shadow: 0 0 16upx #CCCCCC;
				border-radius: 10upx;
				flex-shrink: 0;
			}
			.info-right{
				justify-content: space-between;
				width: 100%;
				margin-left: 20upx;
				.name{
					font-size: 28upx;
					color: $black-color;
					word-break: break-all;
					overflow: hidden;
					text-overflow: ellipsis;
					display: -webkit-box;
					-webkit-box-orient: vertical;
					-webkit-line-clamp: 2;
				}
				.Spec{
					font-size: 24upx;
					margin: 10upx 0;
					width: 400upx;
					word-break: break-all;
					overflow: hidden;
					text-overflow: ellipsis;
					display: -webkit-box;
					-webkit-box-orient: vertical;
					-webkit-line-clamp: 1;
				}
				.del{
					color: $black-color;
					.text-red{
						font-size: 30upx;
						font-weight: bold;
					}
				}
			}
		}
	}
	// 订单信息
	.infoxq{
		padding-top: 30upx;
		& > view:not(:last-child){
			padding-left: 28upx;
			padding-right: 28upx;
		}
		.info-top{
			margin: 20upx 0 40upx;
			.title{
				font-size: 30upx;
				font-weight: bold;
				color: $black-color;
			}
			.more{
				font-size: 26upx;
				.icon{
					margin-left: 10upx;
					font-size: 34upx;
				}
			}
		}
		.info-list{
			padding-bottom: 30upx;
			font-size: 26upx;
			color: $black-color;
			& > text:first-child{
				width: 166upx;
				color: #666666;
			}
			.spec{
				& > text:first-child{
					width: 166upx;
					color: #666666;
				}
			}
		}
		.phone{
			font-size: 30upx;
			border-top: 2upx solid #f2f2f2;
			padding: 30upx 0;
		}
		.hover-bg{
			background-color: #F2F2F2;
		}
	}
	.dpay{
		justify-content: flex-end;
		padding-bottom: 10rpx;
		image{
			margin-right: 20rpx;
			width: 50rpx;
			height: 50rpx;
		}
	}
</style>