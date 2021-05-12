<!-- 商品分类 -->
<template>
	<view class="sort">
		<!-- 头部 -->
		<nav-bar type="fixed" :backState="2000" :bgColor="['#fff', '#fff']" :titleCenter="false" title="Open-Mall">
			<view class="Title">分类</view>
			<navigator class="search_box u-f-ac" hover-class="none" url="../common/search/search">
				<text class="iconR iconfont icon-sousuo"></text>
				<text class="prompt padding-xs">搜索</text>
			</navigator>
		</nav-bar>
		<view class="main" :style="{paddingTop: Custom + 'px'}">
			<scroll-view class="tab-view" scroll-y scroll-with-animation 
			 :scroll-top="verticalNavTop">
				<view class="tab-bar-item u-f-ac" v-for="(item, index) in 4" :key="index" :class="[curIndex == index ? 'active' : '']" 
				:data-id="index" @tap.stop="TabSelect(index)">
					<text class="text-cut">新鲜水果</text>
				</view>
			</scroll-view>
			<scroll-view scroll-y class="right-box" scroll-y scroll-with-animation 
			  @scrolltolower="getMore">
				<view class="page-view" v-if="show">
						<view class="g-container">
							<view class="g-item u-f" v-for="(i,d) in 8" >
								<image class="g-img" :src="'../../static/goods/'+(d+1)+'.jpg'" mode="widthFix"></image>
								<view class="g-content u-fc ">
									<view class="g-name">新鲜海鲜包邮88888元</view>
									<view class="u-f-jsb u-f-ac">
										<view class="g-price text-red text-xl text-bold">￥9.9</view>
										<view class="cart-btn round">加入购物车</view>
									</view>
								</view>
							</view>
						</view>
				</view>
				<view>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				Custom: this.CustomBar || 0,
				show: true,
				curIndex:0,
				verticalNavTop: 0,
				
			};
		},
		methods:{
			TabSelect(index){
				this.show = false
				this.curIndex = index
				uni.showLoading({
					title: '正在加载'
				})
				setTimeout(()=>{
					this.show = true
					uni.hideLoading()
				},1000)
			}
		}
	}
</script>

<style scoped lang="scss">
/* #ifdef APP-PLUS-NVUE */
	@function unit($num) {
		@return $num + 0rpx;
	}
/* #endif */

/* #ifndef APP-PLUS-NVUE */
	@function unit($num) {
		@return $num + 0upx;
	}
/* #endif */
// 顶部
.Title {
	margin-right: 20rpx;
}
.search_box {
	margin-right: 30upx;
	width: 450upx;
	height: 64upx;
	background-color: #f5f5f5;
	border-radius: 32upx;
	padding: 0upx 40upx;
	.prompt,.iconfont{
		font-size: 28upx;
		color: #cccccc!important;
	}
}
/* 左侧导航布局 start*/
	.main {
		height: 100%;
		width: 100%;
		position: fixed;
		top: 0;
		padding-bottom:0;
	}
	/* 隐藏scroll-view滚动条*/
	
	::-webkit-scrollbar {
		width: 0;
		height: 0;
		color: transparent;
	}
	.tab-view {
		width: 160rpx;
		background-color: #f6f6f6;
		height: 100%;
		float: left;
		//#ifdef H5
		padding-top: unit(88);
		//#endif
	}
	
	.tab-bar-item {
		width: 160rpx;
		height: 50px;
		background: #f6f6f6;
		box-sizing: border-box;
		justify-content: center;
		font-size: 26rpx;
		color: #444;
		font-weight: 400;
	}
	
	.active {
		position: relative;
		color: #000;
		font-size: 30rpx;
		font-weight: 600;
		background: #ffffff;
	}
	
	.active::before{
		content: '';
		position: absolute;
		border-left: 8rpx solid #e41f19;
		height: 30rpx;
		left: 0;
	}
	
	/* 左侧导航布局 end*/
	// 右侧商品
	.right-box {
		min-width: 100%;
		padding-left: 180rpx;
		padding-right:20rpx ;
		box-sizing: border-box;
		height: 100%;
		background-color: #fff;
		//#ifdef H5
		padding-top: unit(95);
		//#endif
	}
	.page-view {
		width: 100%;
		overflow: hidden;
		// padding-top: 30rpx;
		box-sizing: border-box;
		padding-bottom: 200rpx;
	}
	.g-item{
		margin-bottom: 20rpx;
		min-height: 200rpx;
		.g-img{
			width: 200rpx;
			align-self: center;
		}
		.g-content{
			justify-content: space-between;
			padding: 20rpx 0;
			flex: 1;
		}
	}
	.cart-btn{
		background-color: #f22222;
		color: #ffffff;
		width: 135upx;
		height: 45upx;
		line-height: 45upx;
		text-align: center;
		font-size: 22upx;
		margin-top: 10rpx;
	}
	// 右侧商品
</style>
