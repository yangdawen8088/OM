<template>
	<view>
		<scroll-view scroll-x class="nav"  scroll-with-animation  :scroll-left="verticalNavLeft">
			<view class="cu-item"  v-for="(item,index) in typeList"  @tap="TabSelect(index)">
				<view class="item-name">{{item.time}}</view>
				<view class="bor-bottom" :class="index==currentIndex?' cur':''">已开抢</view>
			</view>
		</scroll-view>
		<!-- 商品 -->
		<scroll-view class="goods-box" scroll-y scroll-with-animation :scroll-into-view="'main-'+mainCur" @scroll="VerticalMain">
			<view class="kill-item" v-for="(j,index) in goods" :id="'main-'+index">
				<view class="kill-tit u-f-jsb" :class="index == 0? 'gold':'nomal'">
					<view class=" name">{{j.type}}</view>
					<view class="u-f-ac countdown">
						<uni-countdown  color="#fff" background-color="#ff3d27" :show-day="false" :hour="12" :minute="12" :second="12"></uni-countdown>
						后结束
					</view>
				</view>
				<view class="bg-white">
					<view class="goods-item u-f" v-for="i in j.list">
						<image src="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3918045283,989435347&fm=26&gp=0.jpg" mode="widthFix"></image>
						<view class="good-info">
							<view class="good-name texts-cut">{{i.name}}</view>
							<view class="flexend">
								<view class="ms-box u-f-ac" >
									<view class="tit">秒杀价</view>
									<view class="colorr">限2份</view>
								</view>
								<view class="food-btm u-f-jsb">
									<view class="spec">
										<view class="food-price">￥9.9</view>
										<view class="line-through">￥18.9</view>
									</view>
									<view>
										<!-- <cartcontrol :food="food.map" @add="addCart" @dec="decreaseCart"></cartcontrol> -->
										<view class="addcart round" @click.stop="addCart(food.map)">
											<text>加入购物车</text>
										</view>
									</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</scroll-view>
		<!-- 购物车 -->
		<shop-cart :goods="carList" @change='handelCart' @delAll="delAll" bottom='0px'></shop-cart>
	</view>
</template>

<script>
	import shopCart from '@/components/shop-cart/shopcart.vue'
	export default{
		components:{
			shopCart
		},
		data(){
			return{
				load:true,
				currentIndex:0,
				scrollLeft:0,
				verticalNavLeft: 0,
				mainCur:0,
				typeList:[
					{
						time:'万人团'
					},
					{
						time:'10:00'
					},
					{
						time:'12:00'
					},
					{
						time:'14:00'
					},
					{
						time:'万人团'
					},
					{
						time:'10:00'
					},
					{
						time:'12:00'
					},
					{
						time:'14:00'
					}
				],
				goods: [
					{
						type: '万人团',
						list:[
							{
								name: '草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓'
							},
							{
								name: '苹果'
							}
						]
					},
					{
						type: '10:00',
						list:[
							{
								name: '草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓'
							},
							{
								name: '苹果'
							}
						]
					},
					{
						type: '12:00',
						list:[
							{
								name: '草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓'
							},
							{
								name: '苹果'
							}
						]
					},
					{
						type: '14:00',
						list:[
							{
								name: '草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓'
							},
							{
								name: '苹果'
							}
						]
					},
					{
						type: '万人团',
						list:[
							{
								name: '草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓'
							},
							{
								name: '苹果'
							}
						]
					},
					{
						type: '10:00',
						list:[
							{
								name: '草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓'
							},
							{
								name: '苹果'
							}
						]
					},
					{
						type: '12:00',
						list:[
							{
								name: '草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓'
							},
							{
								name: '苹果'
							}
						]
					},
					{
						type: '14:00',
						list:[
							{
								name: '草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓草莓'
							},
							{
								name: '苹果'
							}
						]
					},
				],
				carList:[],
				userInfo:{}
			}
		},
		onShow() {
		},
		methods:{
			TabSelect(index){
				this.currentIndex = this.mainCur = index;
				this.verticalNavLeft = (index - 1) * 50
			},
			VerticalMain(e) {
				// #ifdef MP-ALIPAY
				return false //支付宝小程序暂时不支持双向联动 
				// #endif
				let tabHeight = 0;
				if (this.load) {
					for (let i = 0; i < this.goods.length; i++) {
						let view = uni.createSelectorQuery().select("#main-" + i);
						view.fields({
							size: true
						}, data => {
							this.goods[i].top = tabHeight;
							tabHeight = tabHeight + data.height;
							this.goods[i].bottom = tabHeight;
						}).exec();
					}
					this.load = false
				}
				let scrollTop = e.detail.scrollTop  - 20;
				for (let i = 0; i < this.goods.length; i++) {
					if (scrollTop > this.goods[i].top && scrollTop < this.goods[i].bottom) {
						this.currentIndex = i
						this.verticalNavLeft = (i - 1) * 50
						return false
					}
				}
			},
		}
	}
</script>

<style lang="scss" scoped>
page{
	background-color: #ff4000;
}
// 头部
.nav{
	background-color: #ff4000;
	min-height: 100rpx;
	padding: 10rpx 0;
	position: fixed;
	z-index: 99;
	/* #ifdef H5 */
	top: 44px;
	/* #endif */
	.cu-item{
		display: inline-block;
		font-size: 28rpx;
		color: #fff;
		font-weight: 900;
		text-align: center;
		height: 106rpx;
		line-height: 50rpx;
		.item-name{
			font-size: 36rpx;
		}
		
	}
	.bor-bottom.cur{
		background-color: #FFFFFF;
		color: #ff4000;
		padding: 0 10rpx;
		border-radius: 26rpx;
	}
}
// 商品部分
.goods-box{
	height: 100vh;
	padding-top: 130rpx;
	/* #ifdef MP-WEIXIN */
	padding-bottom: 120rpx;
	/* #endif */
}
.kill-item{
	margin: 20rpx 20rpx 0 ;
	border-radius: 20rpx;
	overflow: hidden;
}
.gold{
	background: linear-gradient(to right,#ffdf59,#fffefb);
	.name{
		color: #602202;
	}
}
.nomal{
	background: linear-gradient(to right,#ffe1e1,#fffdfd);
}
.kill-tit{
	padding:10rpx 20rpx;
	.name{
		font-size: 36rpx;
		font-weight: 900;
	}
	.countdown{
		color: #ff4000;
	}
}
.bg-white{
	padding: 20rpx;
}
.goods-item{
	.good-info{
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		margin-left: 20rpx;
		flex: 1;
	}
	image{
		display: inline-block;
		width: 300rpx;
	}
	.food-price{
		color: #F22222;
		font-size: 34rpx;
		font-weight: 700;
	}
	.line-through{
		font-size: 24rpx;
	}
}
.goods-item:not(last-child){
	margin-bottom: 20rpx;
}
.ms-box{
	position: relative;
	margin: 25rpx 0 12rpx;
	font-size: 24rpx;
	view{
		text-align: center;
		box-sizing: border-box;
	}
	.tit{
		padding: 5rpx 8rpx;
		background-color: #ff1216;
		color: #fff;
	}
	.colorr{
		padding: 3rpx 8rpx;
		border: 2rpx solid #ff1216;
		color: #ff1216;
		// flex: 1;
	}
}
.ms-box::before{
	content: '';
	position: absolute;
	bottom: -14rpx;
	left: 16rpx;
	width: 0;
	height: 0;
	border-left: 8rpx solid transparent;
	border-right: 8rpx solid transparent;
	border-top: 14rpx solid #ff1216;
	z-index: 1;
}
.food-btm{
	.addcart{
		background-color: #F22222;
		color: #FFFFFF;
		width: 150upx;
		height: 50upx;
		line-height: 50upx;
		text-align: center;
		font-size: 24upx;
		margin-top: 10rpx;
	}
}
.uni-countdown__number{
	font-weight: 800;
}
</style>
