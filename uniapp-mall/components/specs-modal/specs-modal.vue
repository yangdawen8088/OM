<!-- 商品规格选择 -->
<template>
	<view>
		<tui-modal :show="showt" @cancel="hideSpec" :custom="true" padding="20upx 0 0">
			<view class="tui-modal-spec">
				<view class="u-f-ajc title">请选择商品规格</view>
				<view class="spec"><image class="specImg" src="../../static/goods/3.jpg"></image></view>
				<view class="spec">
					<view class="sub">长度</view>
					<view class="u-f-ac">
						<view class="spec-item" v-for="(item, index) in 3" :key="index" @tap="clickone(index, item)">12cm</view>
					</view>
				</view>
				<view class="foot-btm u-f-jsb">
					<view class="foot-left u-f-ac">
						<text class="price">￥99.9</text>
						<text class="productStock ml0">（仅剩3件）</text>
						<!-- <text class="productStock ml0" v-if="checkSku.productStock < 1">暂时缺货</text> -->
					</view>
					<!-- <cartcontrol :food="checkSku" @add="addCart" @dec="decreaseCart" v-if="checkSku.count"></cartcontrol> -->
					<view class="btn round" @click="addCart(checkSku)">加入购物车</view>
				</view>
			</view>
			<view class="close-btn u-f-ajc" @click="hideSpec"><text class="icon iconfont icon-cuo"></text></view>
		</tui-modal>
	</view>
</template>

<script>
	export default {
		name:"specs-modal",
		props:{
			// 是否显示
			showt:{
				type:Boolean,
				default:false
			}
		},
		data() {
			return {
				
			};
		},
		methods:{
			hideSpec(){
				this.$emit('close');
			}
		}
	}
</script>

<style lang="scss" scoped>
.tui-modal-spec {
	.title {
		font-size: 34upx;
		color: #9a9a9a;
	}

	.spec {
		padding-left: 28upx;
		.specImg {
			margin-top: 20rpx;
			width: 150rpx;
			height: 150rpx;
			border-radius: 10rpx;
		}
		.sub {
			margin: 30upx 0 0upx;
		}
		.u-f-ac {
			flex-wrap: wrap;
		}
		.spec-item {
			border: 1px solid #cccccc;
			// width: 120upx;
			font-size: 23upx;
			text-align: center;
			padding: 6upx 6upx;
			margin-right: 30upx;
			margin-top: 20rpx;
		}

		.active {
			border-color: $main-color;
			color: $main-color;
		}
	}

	.foot-btm {
		margin-top: 40upx;
		padding: 30upx 20upx;
		background-color: #f6f6f6;
		width: 100%;
		border-bottom-left-radius: 24rpx;
		border-bottom-right-radius: 24rpx;

		.foot-left {
			.price {
				font-size: 36upx;
				font-weight: bold;
				color: $main-color;
				padding-right: 10upx;
			}

			.del {
				font-size: 22upx;
			}
		}

		.btn {
			background-color: #f22222;
			color: #fff;
			// color: #FFFFFF;
			padding: 8upx 20upx;
		}
	}
}
.close-btn {
	width: 80upx;
	height: 80upx;
	background-color: #000000;
	border-radius: 50%;
	position: absolute;
	left: 280upx;
	right: 0;
	bottom: -150upx;
	opacity: 0.5;

	.icon {
		color: #ffffff;
		font-size: 60upx;
	}
}
</style>
