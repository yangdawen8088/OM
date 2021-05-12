<!-- ¸öÈËÊÕ²ØÉÌÆ· -->
<template>
	<view>
		<view class="collection">
			<navigator hover-class="none" url="../../common/goodsDetail/goodsDetail" class="u-f-jsb order-goods baseW" v-for="(item, index) in 4" :key="index" >
				<view class="order-img-box"><image src="../../../static/goods/3.jpg"></image></view>
				<view class="order-goods-text2">
					<view class="order-goods-name">ÐÂÏÊ²ÝÝ®²ÝÝ®ÐÂÏÊ²ÝÝ®²ÝÝ®ÐÂÏÊ²ÝÝ®²ÝÝ®ÐÂÏÊ²ÝÝ®²ÝÝ®ÐÂÏÊ²ÝÝ®²ÝÝ®</view>
					<view class="u-f-jsb">
						<view class="order-goods-price text-red text-bold text-sm">
							£¤<text>9.9</text>
						</view>
						<view @tap.stop="deleteC(item)" class="cancel"><text class="icon iconfont icon-shanchuhuishouzhanlajitongqingchu"></text></view>
					</view>
				</view>
			</navigator>
		</view>
	</view>
</template>

<script></script>

<style lang="scss" scoped>
.cancel {
	padding: 0 30rpx 30rpx;
}
.collection {
	padding: 30rpx 28rpx 20rpx;
	.order-goods {
		padding: 20rpx;
		position: relative;
		.order-img-box {
			box-shadow: none;
			width: 220rpx;
			height: 220rpx;
			image {
				width: 100%;
				height: 100%;
			}
		}

		.order-goods-text2 {
			width: 410rpx;
			padding-top: 16rpx;
			.order-goods-name {
				margin-bottom: 40rpx;
			}
			.guige {
				margin-bottom: 14rpx;
			}
			.guige > text:first-child {
				padding-right: 20rpx;
			}
			.guige > text:last-child {
				padding-left: 20rpx;
			}
			.shixiao {
				height: 34rpx;
				line-height: 34rpx;
				width: 80rpx;
				text-align: center;
				background-color: #ccc;
				color: #fff;
				font-size: 24rpx;
				border-radius: 6rpx;
			}
		}
		.icon-gouwuche {
			width: 50rpx;
			height: 50rpx;
			line-height: 50rpx;
			border-radius: 100%;
			text-align: center;
			background-color: #eee;
			position: absolute;
			right: 30rpx;
			bottom: 30rpx;
		}
	}
}
</style>
