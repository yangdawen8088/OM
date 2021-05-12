<template>
	<view class="shopcart">
		<!-- @click="toggleList" -->
		<view class="cartBottom u-f-jsb u-f-ajc" :style={bottom:bottom}>
			<view class="BtnLeft round u-f-jsb" :class="getAllCount ? 'active' : '' ">
				<view class="u-f-ac"  @click="toggleList">
					<view class="iconFont">
						<view class="amount round u-f-ajc"  v-if="getAllCount">{{getAllCount}}</view>
						<image class=" ico " :class="isadd?'add': 'noadd zwyShake zwyHover1'"  hover-class="" :hover-stay-time="300" style="transform-origin: center bottom;--count:1;" src="https://zj.gdwrh.com/fnyg/static/images/post-station/post-shopping_x3.png" mode="widthFix"></image>
					</view>
					<view class="u-f-ac price-text">
						<view class="price">
							总计:￥<text>{{getAllPrice}}</text>
						</view>
						<text class="line-through">￥{{getAllmarkePrice}}</text>
					</view>
				</view>
				<view class="Button u-f-ajc" @click="Route({url:'/pages/mine/order/order',intercept:true})">结算</view>
			</view>
			<!-- <view class="BtnRight u-fc" @click="$emit('goorder')" v-show="rightshow">
				<text class="icon iconfont icon-icon"></text>
				<text class="text-cut">查看订单</text>
			</view> -->
		</view>
		<!-- 选择的商品 -->
		<view class="cartList" v-show="isShowList && goods.length">
			<view class="title-box">
				<view class="title u-f-jsb">
					<text class="text-bold">已购商品</text>
					<view class="clear" @click="delShopcart">
						<text class="icon iconfont icon-shanchuhuishouzhanlajitongqingchu"></text>清空购物车
					</view>
				</view>
				<view class="tip">
					注: 无需自提点，满39元配送到家(仅限赤坎、霞山)
				</view>
			</view>
			<scroll-view scroll-y style="max-height: 400px;">
				<view class="list" :style="{paddingBottom:bottom}">
					<view class="list-text u-f-ac" v-for="(item,index) in goods" :key="index">
						<image :src="item.mainPicture" class="pic" mode="widthFix"></image>
						<view class="u-fc text-info">
							<view class="title-sub">{{item.productName}}</view>
							<!-- 规格 -->
							<view class="company">
								<text v-if="item.skuName">{{item.skuName}}</text>
								<text class="productStock" v-if="item.relation.productStock > 0 && item.relation.productStock < 11">( 仅剩{{item.relation.productStock}}件 )</text>
								<text class="productStock" v-if="item.relation.productStock < 1">暂时缺货</text>
							</view>
							
							<view class="price-sub u-f-jsb">
								<view class="sub">
									￥<text>{{item.relation.productPrice}}</text>
								</view>
								<cartcontrol :food="item" :carList='goods' @cartChange='handelCart'></cartcontrol>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
		<view class="listMask" v-show="isShowList && goods.length" @click="toggleList"></view>
	</view>
</template>

<script>
	import cartcontrol from "../cart-control/cart-control.vue"
	// import {mul} from '@/utils/lib'
	export default {
		props: {
			goods: {
				type: Array
			},
			rightshow:{
				type: Boolean,
				default:true
			},
			// #ifndef MP-WEIXIN
			bottom:{
				type: String,
				default:'50px'
			}
			// #endif
			// #ifdef MP-WEIXIN
			bottom:{
				type: String,
				default:'0rpx'
			}
			// #endif
		},
		data() {
			return {
				isShowList: false,
				isadd: false
			};
		},
		components: {
			cartcontrol
		},
		watch:{
			goods:{
				immediate: true, // 这句重要
				deep: true,
				handler(nval, oval) {
					if (nval) {
						this.isadd = true
					}
				}
			}
			// goods(nval, oval) {
			// 	if (nval) {
			// 		this.isadd = true
			// 	}
			// }
		},
		computed: {
			// 获得购物车所有商品数量
			getAllCount() {
				let result = 0;
				this.goods.forEach((food) => {
					result += food.count
				})
				this.isadd = false
				return result
			},
			// 总价
			getAllPrice() {
				let result = 0;
				let result1 = 0;
				this.goods.forEach((good) => {
						result1 += this.accMul(good.count, good.fnPrice)
						result = result1.toFixed(2);
					
				})
				return result
			},
			// 市场价格
			getAllmarkePrice() {
				let result = 0;
				let result1 = 0;
				this.goods.forEach((good) => {
						result1 += this.accMul(good.count, good.marketPrice)
						result = result1.toFixed(2);
				})
				return result
			}
		},
		methods: {
			// 解决浮点数 运算出现多位小数
			accMul(arg1, arg2) {
				let m = 0,
					s1 = '',
					s2 = '';
				if (arg1 && arg1 != null)
					s1 = arg1.toString();
				if (arg2 && arg2 != null)
					s2 = arg2.toString();
				try {
					m += s1.split('.')[1].length
				} catch (e) {}
				try {
					m += s2.split('.')[1].length
				} catch (e) {}

				return Number(s1.replace('.', '')) * Number(s2.replace('.', '')) / Math.pow(10, m);
			},
			toggleList() {
				if (this.goods.length) {
					this.isShowList = !this.isShowList;
				}
			},
			delShopcart() {
				// 删除本地购物车缓存
				this.$store.commit('removeCar')
				this.$emit('delAll');
			},
			handelCart(item){
				this.$emit('change', item)
			},
		},
	}
</script>

<style scoped lang="scss">
	.shopcart .cartBottom {
		position: fixed;
		// bottom: 0;
		left: 0;
		right: 0;
		height: 54px;
		z-index: 199;
		display: flex;
		background-color: #FFFFFF;
		padding: 0 28upx;
	}
	.BtnLeft.active{
		opacity: 1;
	}
	.BtnLeft{
		opacity: 0.5;
		width: 100%;
		height: 92upx;
		color: #FFFFFF;
		background-color: #080808;
		align-items: center;
		position: relative;
		.iconFont{
			position: relative;
			padding-left: 30upx;
			.ico{
				width: 62upx;
				height: 62upx;
				display: block;
			}
			.amount{
				position: absolute;
				left: -10upx;
				top: -30upx;
				width: 50upx;
				height: 50upx;
				background-color: $main-color;
				z-index: 999;
			}
		}
		.price-text{
			align-items: flex-end;
			padding-left: 20upx;
			font-size: 26upx;
			.price{
				& > text{
					font-size: 34upx;
					font-weight: bold;
				}
			}
			.line-through{
				font-size: 22upx;
				padding-left: 10upx;
			}
		}
		.Button{
			background-color: $main-color;
			width: 140upx;
			height: 100%;
			border-top-right-radius: 100upx;
			border-bottom-right-radius: 100upx;
		}
	}
	.BtnRight {
		align-items: center;
		margin-left: 20upx;
		.icon{
			font-size: 42upx;
			color: $main-color;
			margin-bottom: 8upx;
		}
		.text-cut{
			font-size: 22upx;
			color: #000000;
		}
	}
	.list-text {
		display: flex;
		flex-direction: row;
	}

	

	.cartList {
		position: fixed;
		bottom: 54px;
		left: 0;
		right: 0;
		z-index: 190;
		padding: 0 28upx;
		background-color: #FFFFFF;
	}

	.cartList .title,
	.cartList .list-text {
		display: flex;
		flex-direction: row;
		font-size: 24upx;
		.icon{
			font-size: 26upx;
			margin-right: 10upx;
		}
	}
	.tip{
		color: $main-color;
		font-size: 24rpx;
		margin-top: 12rpx;
	}
	.cartList .title-box {
		border-bottom: 2upx solid #CCCCCC;
		justify-content: space-between;
		padding: 30upx 28upx;
	}
	.cartList .title-box{
		padding-bottom: 10rpx;
	}
	.cartList .list-text {
		padding: 20upx 30upx;
		.pic{
			width: 120upx;
			height: 120upx;
			display: block;
			flex-shrink: 0;
		}
		.text-info{
			margin-left: 20upx;
			width: 100%;
			.title-sub{
				font-size: 28upx;
				word-break: break-all;
				overflow: hidden;
				text-overflow: ellipsis;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 2;
			}
			.company{
				font-size: 24upx;
				color: #666;
				margin: 10rpx 0;
			}
			.price-sub{
				color: $main-color;
				.sub{
					& > text{
						font-size: 30upx;
						font-weight: bold;
					}
				}
			}
		}
	}

	.list {
		padding-bottom: 10px;
	}


	/* 遮布 */
	.listMask {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		z-index: 110;
		opacity: 0.5;
		background: #6a7076;
	}
	.productStock {
		color: #F22222;
	}
	.add{
		width: 80px;
		height: 80px;
	}
	.noadd{
		width: 40px;
		height: 40px;
	}
</style>
