<template>
	<view class="product-box">
		<view class="food u-f" v-for="(food, index) in goods" :key="index" @click="Route({url:`../common/goodsDetail/goodsDetail?detail=${encodeURIComponent(JSON.stringify(food))}&relations=${encodeURIComponent(JSON.stringify(food.relation))}`})">
			<image :src="food.mainPicture" :class="food.productStock == 0 ? 'mask' : ''" class="pic radius " mode="widthFix"></image>
			<view class="pro-info ">
				<view>
					<text class="title texts-cut">{{ food.productName }}</text>
					<text class="del">
						<text>{{food.relation.saleAmount}}人已下单</text>
						<text class="productStock" v-if="food.relation.productStock > 0 && food.relation.productStock < 11">仅剩{{ food.relation.productStock }}件</text>
						<text class="productStock" v-if="food.relation.productStock < 1">暂时缺货</text>
					</text>
				</view>
				
				<!-- 加减 -->
				<view class="flexend tui-por-del">
					<view class="del-item del-item-red">
						已省{{$compute.accSub(food.relation.marketPrice,food.relation.productPrice)}}元
					</view>
					<view class="del-item del-item-blue" v-if="food.buyLimit">
						限购{{food.buyLimit}} 份
					</view>
					<view class="food-btm">
						<view class="spec">
							<text class="food-price">￥{{food.relation.productPrice}}</text>
							<text class="line-through">￥{{food.relation.marketPrice}}</text>
						</view>
						<cart-control :food="food" :carList='carList' @cartChange='handelCart'></cart-control>
					</view>
					
				</view>
			</view>
		</view>
		<view>
			<!-- <tui-loadmore v-show="loadMore"></tui-loadmore> -->
			<our-loading  :active='loadMore' :translateY='90' text="loading..."  />
			<om-nomore :visible="noMoreFlag" bgcolor="#fff"></om-nomore>
			<tui-no-data imgUrl="https://zj.gdwrh.com/fnyg/static/images/my-img/img_nodata.png" v-if="noData1" :fixed="false">暂无内容</tui-no-data>
		</view>
		<shop-cart v-if="carList.length" :goods="carList" @delAll="delAll" @change='handelCart'></shop-cart>
	</view>
</template>

<script>
import shopCart from '../shop-cart/shopcart.vue'
import Vue from 'vue';
import accSub from "@/common/js/utils/calculation.js"
export default {
	components:{
		shopCart
	},
	name: 'goodsContainer',
	data() {
		return {
			goods: [],        //商品列表
			currentPage: 0,
			total: 0,
			loadMore: false,
			noMoreFlag: false,
			noData1: false,
			carList:this.$store.state.carList || []
		};
	},
	props: {
		car: {
			type: Array | String,
			default: function() {
				return '';
			}
		},
		typeId: {
			type: Number,
			default: 3
		},
		scrollBottom: {
			type: Number,
			default: 0
		}
	},
	watch:{
		// 监听index页面购物车变化
		car: {
			immediate: true, // 这句重要
			deep: true,
			handler(nval,oval) {
				this.carList = this.$store.state.carList
				this.goods.forEach(item =>{
					// #ifdef MP-WEIXIN
					item.count = 0
					// #endif
					if(this.carList.length == 0 || !this.carList){
						item.count = 0
					}else{
						nval.forEach(pro => {
							if (item.id == pro.id) {
								item.count = pro.count;
							}
						});
					}
					
				})
			}
		},
		scrollBottom() {
			if(this.scrollBottom != 0) {
				//通知触底了
				this.setReachBottom();
			}
		},
		typeId(){
			this.clear()
			this.getProducts()
		}
	},
	created() {
		this.getProducts()
	},
	methods:{
		// 獲取首頁商品
		getProducts(){
			this.loadMore = true
			this.$http.post('/api/search/products/pageByType', {typeId: this.typeId,currentPage:this.currentPage}, { header: { authType: 'false'}}).then(res => {
				this.loadMore = false
				this.total = res.data.total
				if(this.total == 0) return this.noData1 = true
				res.data.rows.forEach(item =>{
				Vue.set(item, 'count', 0);
				if(!this.carList) return
				// 遍历购物车给商品列表赋值
				this.carList.forEach(car => {
					if (item.id == car.id) {
						item.count += car.count;
					}
				});
				})
				this.goods = [...this.goods,...res.data.rows]
			})
		},
		clear(){
			this.noData1 = false
			this.noMoreFlag = false
			this.currentPage = 0
			this.goods = []
			this.total = 0
		},
		// 触底事件
		setReachBottom() {
			if(this.total == this.goods.length) return this.noMoreFlag = true
			this.currentPage++
			this.getProducts()
		},
		// 购物车
		handelCart(item){
			this.carList = this.$store.state.carList
			this.goods.forEach(pro=>{
				if(pro.id == item.id){
					pro.count = item.count
				}
			})
		},
		// 清空购物车
		delAll(){
			this.carList = []
			this.goods.forEach(item=>{
				item.count = 0
			})
		}
	}
};
</script>

<style lang="scss" scoped>

// 商品列表
.food {
	margin-bottom: 15rpx;
	padding: 10rpx 20rpx;
	.pic {
		position: relative;
		width: 280rpx;
		height: auto;
		display: block;
		flex-shrink: 0;
		align-self: center;
	}
	.mask::after {
		content: '该商品已售罄';
		position: absolute;
		top: 0;
		left: 0;
		display: flex;
		align-items: center;
		width: 100%;
		height: 100%;
		text-indent: 60rpx;
		background-color: rgba($color: #a0a0a0, $alpha: 0.1);
		color: #8799a3;
	}
	.mask {
		.pro-info .title {
			color: #8799a3;
		}
	}
}
.food::after {
	content: '';
	background-color: #f4f2f5;
	width: 100%;
	height: 2rpx;
	position: absolute;
	left: 0;
	bottom: -10rpx;
}
.flexend {
	align-items: flex-end;
}
.food,
.food-btm,
.content {
	display: flex;
	flex-direction: row;
}

.food-btm {
	position: relative;
}

.count {
	position: absolute;
	height: 40rpx;
	background-color: #f22222;
	color: #fff;
	min-width: 40rpx;
	line-height: 40rpx;
	text-align: center;
	border-radius: 40rpx;
	right: 0;
	bottom: 42rpx;
}
.pro-info {
	margin-left: 20rpx;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	flex: 2;
	min-height: 240rpx;
	right: 20rpx;
	.title {
		font-size: 30rpx;
	}

	.sub {
		font-size: 24upx;
	}

	.del {
		font-size: 22upx;
		color: #888888;
		margin: 12rpx 0;
	}
}

.food-btm {
	font-size: 20upx;
	display: flex;
	justify-content: space-between;
	width: 100%;
	.spec {
		& > text {
			margin-right: 10upx;
		}
	}

	.text-cut {
		background-color: #f22222;
		width: 120upx;
		height: 50upx;
		line-height: 50upx;
		text-align: center;
		font-size: 24upx;
		color: #fff;
	}
}

.food-price {
	color: #f22222;
	font-size: 36upx;
	font-weight: bold;
}

.productStock {
	color: #f22222;
	font-size: 26rpx;
	margin-left: 30rpx;
}
.tui-por-del {
	box-sizing: border-box;
	display: flex;
	align-items: center;
	flex-wrap: wrap;
	margin-top: 5upx;
	.del-item:last-child {
		margin-right: 0;
	}
}

</style>
