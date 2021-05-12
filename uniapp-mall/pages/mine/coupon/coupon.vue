<!-- 我的优惠券 -->
<template>
	<view class="coupon-list">
		<view class="baseW coupon-item" v-for="item in 3">
			<view class="top u-f-jsb">
				<view class="text-red">
					<text>￥</text><text class="price">9.9</text>
					<view>优惠券</view>
				</view>
				<view>
					全场满99元通用
					<view class="time">2010-12-03 12:59:59到期</view>
				</view>
				<navigator hover-class="none" open-type="switchTab" url="../../index/index" class="btn u-f-ajc">立即使用</navigator>
			</view>
			<view class="bottom">活动规则：请在指定日期内使用</view>
		</view>
		<!-- 不可使用的优惠券 -->
		<view class="disable-tit">无效优惠券</view>
		<view class="baseW coupon-item disable" v-for="item in 2">
			<view class="top u-f-jsb">
				<view class="text-red">
					<text>￥</text><text class="price">9.9</text>
					<view>优惠券</view>
				</view>
				<view>
					全场满99元通用
					<view class="time">2010-12-03 12:59:59到期</view>
				</view>
			</view>
			<view class="bottom">活动规则：请在指定日期内使用</view>
		</view>
	</view>
</template>

<script>
</script>

<style scoped lang="scss">
	.coupon-list{
		margin: 20upx 0;
	}
	.coupon-item::before{
		content: "";
		width: 30upx;
		height: 30upx;
		border-radius: 50%;
		background-color: #f2f2f2;
		position: absolute;
		left: -10upx;
		top: 60%;
	}
	.coupon-item::after{
		content: "";
		width: 30upx;
		height: 30upx;
		border-radius: 50%;
		background-color: #f2f2f2;
		position: absolute;
		right: -10upx;
		top: 60%;
	}
	.coupon-item{
		margin: 0 20upx 20upx;
		padding: 38upx 30upx 25upx;
		position: relative;
		.top{
			align-items: flex-start;
			border-bottom: 2px dashed #cccccc;
			padding-bottom: 20upx;
			margin-bottom: 20upx;
		}
		.text-red{
			text-align: center;
			.price{
				font-size: 60upx;
				font-weight: bold;
			}
		}
		.time{
			margin-top: 5upx;
		}
		.btn{
			color: #fff;
			// padding: 0 10upx;
			width: 170upx;
			height: 70upx;
			border-radius: 60upx;
			background-image: linear-gradient(90deg,#ff5d04,#f22222) !important;
		}
		
	}
	.disable-tit{
		padding: 38upx 30upx 25upx;
		font-size: 32rpx;
		font-weight: bold;
	}
	.disable{
		background-color: rgba($color: #666, $alpha: 0.05);
		color: #ccc;
		.bottom{
			color: #ccc;
		}
	}
	.disable .text-red{
		color: #ccc;
	}
	.bottom{
		color: #666666;
	}
</style>