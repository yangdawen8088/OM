<template>
	<view>
		<view class="u-f-ajc" v-if="food.count">
			<view class="u-f-ajc">
				<view class="cont" :style="{'margin-top':marginTop + 'rpx'}" @click.stop="decreaseCart(food)">
					<image src="https://zj.gdwrh.com/fnyg/static/images/post-station/des.png" style="width: 46rpx;height: 46rpx;" mode="widthFix"></image>
				</view>
				<text style="padding: 0 25upx;font-size: 16px;">{{food.count}}</text> </view>
			<view :style="{'margin-top':marginTop + 'rpx'}" class="cont"  @click.stop="addCart(food)">
				<image src="https://zj.gdwrh.com/fnyg/static/images/post-station/add2.png" style="width: 46rpx;height: 46rpx;color: #CCCCCC;" mode="widthFix"></image>
			</view>
		</view>
		<view class="addCartBtn round u-f-ac" v-else  @click.stop="addCart(food)">
			<text>加入购物车</text>
		</view>
	</view>
</template>

<script>
	import Vue from 'vue'
	export default {
		props:{
		    // 购物车
		 //    carList: {
			// 	type: Array | String
			// },
			// 商品
			food: {type: Object,
			},
			marginTop:{
				type: Number,
				default: 2,
			}
		},
		data() {
			return {
			};
		},
		methods: {
			addCart: function(item) {
				let carList = this.$store.state.carList || []
				if(item.relation.productStock < 1) {
					this.$tip.toast('对不起，库存不足')
					return
				}
				if(item.count<=0) {
					if(item.count >= item.buyLimit & item.buyLimit !=0) return this.$tip.toast("抱歉，该商品每人限购"+item.buyLimit+"件")
					Vue.set(item, 'count', 1)
					// Vue.set(item, 'productName', item.productName)
					// Vue.set(item, 'marketPrice', item.marketPrice)
					// Vue.set(item, 'buyLimit', item.buyLimit)
					carList.push(item)
					this.$store.commit('setCar',carList)
					// this.carList.push(item);
				}else if(item.count >= 1 && item.count < item.relation.productStock) {
					if(item.count >= item.buyLimit & item.buyLimit !=0) return this.$tip.toast("抱歉，该商品每人限购"+item.buyLimit+"件")
					item.count++
					carList = carList
					carList.forEach(good =>{
						if(good.id == item.id){
							good.count = item.count
						}
					})
					this.$store.commit('setCar',carList)
				} else if(item.count >= item.productStock){
					this.$tip.tui.toast("已达到最大库存")
					item.count = item.count - item.count
					item.count = item.productStock
					item.count = item.count + item.count
					return
				}
				this.$emit('cartChange',item)
			},
			decreaseCart(item) {
				let carList = this.$store.state.carList
				if (item.count) {
					item.count--
					// 遍历购物车
					carList = carList || []
					carList.forEach(good =>{
						if(good.id == item.id){
							good.count = item.count
						}
					})
					this.$store.commit('setCar',carList)
					this.$emit('cartChange',item)
				if (item.count == 0) {
					item.count = 0
					carList = carList || []
					carList.forEach((good, index) => {
						if (good.id == item.id) {
							carList.splice(index, 1);
							this.$store.commit('setCar',carList)
							return;
						}
					})
				}
				}
			}
		}
	}
</script>

<style scoped>
	.cont {
		width: 50upx;
		height: 50upx;
		box-sizing: border-box;
		border-radius: 50%;
		text-align: center;
	}
	.addCartBtn{
		display: inline-block;
		background-color: #2ba4f7;
		width: 150upx;
		line-height: 1;
		text-align: center;
		color: #ffffff;
		padding:14rpx 12rpx;
		margin: 0 auto;
		font-size: 12px;
	}
</style>
