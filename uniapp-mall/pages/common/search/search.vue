<!-- 商品搜索 -->
<template>
	<view class="container">
		<view class="tui-searchbox margin-lr">
			<view class="tui-search-input">
				<icon type="search" :size="13" color="#333"></icon>
				<input placeholder-class="tui-input-plholder" class="tui-input" confirm-type="search"
					:adjust-position="true" placeholder="请输入" @input="inputChange" />
				<icon type="clear" :size="13" color="#bcbcbc" @click.stop="clear"></icon>
			</view>
			<view class="tui-cancle" @tap="doSearch(keyword)">搜索</view>
		</view>

		<!--  -->
		<view class="search-keyword">
			<!-- 历史搜索 -->
			<scroll-view scroll-y>
				<view class="margin-lr">
					<view class="tui-history-header">
						<view class="tui-search-title">历史搜索</view>
						<view class="tui-icon-delete" @tap="oldDelete">删除</view>
					</view>
					<view class="tui-history-content">
						<view v-for="(keyword, index) in 5" @tap="doSearch(keyword)" :key="index"
							class="tui-circle round">水果</view>
					</view>
				</view>
			</scroll-view>
		</view>

		<!--  @scroll="scroll" 商品列表 -->
		<view>
			<scroll-view scroll-y class="goodscom" :style="{ height: 500 + 'px' }">
				<view ref="foodsWrapper" class="padding-bottom">
					<view class="food u-f-ajc" v-for="(food, index) in 10" :key="index">
						<image src="../../../static/goods/4.jpg" class="pic radius" mode="aspectFill"></image>
						<view class="food-info u-fc u-f-jsb">
							<text class="title">产品名称</text>
							

							<!-- 加减 -->
							<view class="food-btm">
								<view class="spec">
									<text class="food-price">￥8</text>
									<text class="line-through">￥9</text>
								</view>
								<view>
									<!-- <cartcontrol :food="food.map" @add="addCart"
										@dec="decreaseCart"></cartcontrol> -->
									<view class="text-cut round">选规格</view>
									<view  class="count">
										{{index+1}}</view>
								</view>
							</view>
							<text class="del">
								<text>销量99+</text>
								<text class="productStock" v-if="1 == 1">仅剩55件</text>
								<text class="productStock" v-if="1 != 1">暂时缺货</text>
							</text>
						</view>
					</view>
				</view>
			</scroll-view>
			<tui-no-data imgUrl="https://sbb-sbb.oss-cn-shenzhen.aliyuncs.com/static/images/my-img/img_nodata.png" v-if="false" :fixed="true">暂无内容</tui-no-data>
			<!-- <shopcart :goods="carList" :discount="mydiscount" @goorder="getorder" @goorderqr="geqrdd" @add="addCart" @dec="decreaseCart" @delAll="delAll" :rightshow="false"></shopcart> -->
		</view>
	</view>
</template>

<script>
	
</script>

<style lang="scss">
page {
	background-color: #ffffff;
}
.container {
	padding-bottom: 100upx;
	// background-color: #FFFFFF;
	min-height: 100%;
}
uni-page {
	background-color: #ffffff;
}
// 商品
.food {
	margin: 28upx 50upx;
	.pic {
		width: 200upx;
		height: 200upx;
		display: block;
		flex-shrink: 0;
	}
	.food-info {
		margin-left: 20upx;
		flex: 2;
		.title {
			font-size: 30upx;
			font-weight: bold;
			word-break: break-all;
			overflow: hidden;
			text-overflow: ellipsis;
			display: -webkit-box;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 2;
		}
		.sub {
			font-size: 24upx;
		}
		.del {
			font-size: 20upx;
			color: #888888;
			margin: 12rpx 0;
		}
		.food-btm {
			position: relative;
			font-size: 20upx;
			display: flex;
			justify-content: space-between;
			.spec {
				& > text {
					margin-right: 10upx;
				}
			}
			.text-cut {
				background-color: $main-color;
				color: #ffffff;
				width: 120upx;
				height: 50upx;
				line-height: 50upx;
				text-align: center;
				font-size: 20upx;
			}
			.count {
				position: absolute;
				height: 40rpx;
				background-color: $main-color;
				color: #fff;
				min-width: 40rpx;
				line-height: 40rpx;
				text-align: center;
				border-radius: 40rpx;
				right: 0;
				bottom: 36rpx;
			}
		}
	}
}
.food-price {
	color: #F22222;
	font-size: 36upx;
	font-weight: bold;
}
.tui-searchbox {
	padding: 30upx 0;
	box-sizing: border-box;
	display: flex;
	align-items: center;
}

.tui-search-input {
	width: 100%;
	height: 66upx;
	border-radius: 35rpx;
	padding: 0 30rpx;
	box-sizing: border-box;
	background: #f2f2f2;
	display: flex;
	align-items: center;
	flex-wrap: nowrap;
}

.tui-input {
	flex: 1;
	color: #333;
	padding: 0 16rpx;
	font-size: 28rpx;
}

.tui-input-plholder {
	font-size: 28rpx;
	color: #b2b2b2;
}

.tui-cancle {
	color: #888;
	font-size: 28rpx;
	padding-left: 30rpx;
	flex-shrink: 0;
}
/* 历史搜索 */
.tui-history-header {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 30rpx 0;
}
.tui-search-title {
	font-size: 28rpx;
	font-weight: bold;
}
.tui-icon-delete {
	padding: 10rpx;
}
.tui-history-content {
	display: flex;
	align-items: center;
	flex-wrap: wrap;
}
.tui-circle {
	background-color: #f2f2f2;
	padding: 14upx 20upx;
	margin: 0 20upx 20upx 0;
}
.productStock {
	color: #f22222;
	font-size: 24rpx;
	margin-left: 30rpx;
}
</style>
