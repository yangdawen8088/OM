<template>
	<view class="container">
		<!--header-->
		<view class="tui-header-box" :style="{ height: CustomBar + 'px', background: 'rgba(255,255,255,' + opcity + ')' }">
			<!-- <view class="tui-header" :style="{ paddingTop: StatusBar + 'px', opacity: opcity }">商品详情</view> -->
			<view class="tui-header-icon" :style="{ marginTop: StatusBar + 'px' }">
				<view class="tui-icon-box" :style="{ backgroundColor: 'rgba(0, 0, 0,' + iconOpcity + ')' }" @tap="Back" v-if="back">
					<view class="icon iconfont iconmore" :class=" opcity >= 1 ? 'text-black' : 'text-white'"></view>
				</view>
				<view class="tui-icon-box" :style="{ backgroundColor: 'rgba(0, 0, 0,' + iconOpcity + ')' }" @tap="goindex" v-else>
					<view class="icon iconfont iconshouye" :class=" opcity >= 1 ? 'text-black' : 'text-white'"></view>
				</view>
			</view>
			<!-- #ifdef H5 || APP-PLUS -->
			<view class="tui-header-right" :style="{ marginTop: StatusBar + 'px' }">
				<view class="tui-icon-box tui-icon-ml" :style="{backgroundColor: 'rgba(0, 0, 0,' + iconOpcity + ')'}" @tap.stop="popup">
					<view class="icon iconfont iconfenxiang" :class=" opcity >= 1 ? 'text-black' : 'text-white'"></view>
				</view>
			</view>
			<!-- #endif -->
		</view>
		<!--header-->
		<!--banner-->
		<view class="tui-skeleton">
		<view class="tui-banner-swiper tui-skeleton-fillet">
			<swiper :duration="150" class="swiperheight"
			 @change="bannerChange">
				<block v-for="(item, index) in goodDetail.whitePicture" :key="index">
					<swiper-item :data-index="index" @tap.stop="previewImage">
						<image :src="item" class="tui-slide-image swiperheight" />
					</swiper-item>
				</block>
			</swiper>
			<view class="tui-banner-tag">
				<view class="banner-tag-in">
					<view class="banner-tag round">
						{{ bannerIndex + 1 }}/{{ goodDetail.whitePicture.length }}
					</view>
				</view>
			</view>
			<!-- 视频播放 -->
			<view v-if="goodDetail.videoSrc != ''">
				<view class="vedio-box" v-show="vedioShow">
					<video id="myVideo" :src="goodDetail.videoSrc" controls :enable-progress-gesture="false"></video>
				</view>
				<view @click="playRadio" class="playBtn u-f-ac" v-show="!vedioShow"><view class="round"><image src="https://sbb-sbb.oss-cn-shenzhen.aliyuncs.com/static/playbtn.png"></image></view><view>播放</view></view>
				<view @click="outRadio" class="outBtn" v-show="vedioShow">退出播放</view>
			</view>
		</view>
		<!--banner-->
		
		<view class="pro-detail">
			<!-- 价格模块 1-->
			<view class="banner-pricebox u-f-ac tui-skeleton-fillet">
				<tpl-price :price="goodDetail.relation.productPrice" :component="'color: #ffffff'" :integerStyle="'font-size: 42rpx;transform: translateY(5rpx);'"></tpl-price>
				<view class="line-through">
					￥{{goodDetail.relation.productPrice}}
				</view>
			</view>
			<view class="por-list">
				<view class="pro-titbox u-f-ajc">
					<view class="pro-title tui-skeleton-rect">{{goodDetail.productName}}</view>
					<button class="pro-button tui-skeleton-rect" open-type="share">
						分享
					</button>
				</view>
				<!-- 价格模块 2-->
				<view class="pro-pricebox u-f-jsb">
					<view class="tui-por-del">
						<view class="del-item del-item-red">赠送8积分</view>
					</view>
					<view class="u-f-jse food-btm">
						<cart-control :food="goodDetail" :carList='carList' @cartChange='handelCart'></cart-control>
					</view>
				</view>
				
			</view>
			<view class="por-list">
				<view class="u-f-jsb">
					<view class="u-f-ac">
						<view class="por-specs tui-skeleton-rect">
							<text>发货</text>
							<text>{{goodDetail.relation.saleAreaName}}</text>
						</view>
					</view>
					<view class="text-gray tui-skeleton-rect">
						销量 {{goodDetail.relation.saleAmount}}
					</view>
				</view>
			
			</view>
		</view>
		<!-- 详情strat -->
		<view class="product-details tui-skeleton-fillet bg-white">
			<view class="product-sku">
				<view class="details-title  u-f-ajc">
					<view></view>
					<text>商品规格</text>
					<view></view>
				</view>
				<view class="margin bg-white sku-box">
					<view class="u-f sku">
						<text class="bg-gray">产地</text>
						<text class="sku-detail">{{goodDetail.productPlace}}</text>
					</view>
					<view class="u-f sku">
						<text class="bg-gray">规格</text>
						<text class="sku-detail">{{goodDetail.skuName}}</text>
					</view>
					<view class="u-f sku">
						<text class="bg-gray">生产日期</text>
						<text class="sku-detail">{{goodDetail.produceTime | friendDate}}</text>
					</view>
					<view class="u-f sku">
						<text class="bg-gray">保质期</text>
						<text class="sku-detail">{{goodDetail.lifeTime}}{{goodDetail.lifeTimeUnit}}</text>
					</view>
					<view class="u-f sku">
						<text class="bg-gray">储存方式</text>
						<text class="sku-detail">{{goodDetail.storeCondition}}</text>
					</view>
				</view>
			</view>
			<view>
				<view class="details-title bg-white u-f-ajc">
					<view></view>
					<text>商品详情</text>
					<view></view>
				</view>
				<image v-for="item in goodDetail.detailPicture" :src="item" mode="widthFix"></image>
				<view></view>
			</view>
			
		</view>
		</view>
		<shop-cart v-if="carList.length" :goods="carList" @change='handelCart' @delAll="delAll" bottom='0px'></shop-cart>
		<!-- <shopcart v-if="carList.length" :discount="mydiscount" :goods="carList" @goorder="getorder" @goorderqr="geqrdd" @add="addCart" @dec="decreaseCart" @delAll="delAll"></shopcart> -->
		
	</view>
</template>

<script>
	import shopCart from '@/components/shop-cart/shopcart.vue'
	import Vue from 'vue'
	export default {
		components :{
			shopCart
		},
		data() {
			return {
				StatusBar: this.StatusBar,
				CustomBar: this.CustomBar,
				height: 64, //header高度
				top: 26, //标题图标距离顶部距离
				scrollH: 0, //滚动总高度
				iconOpcity: 0.5,
				goodDetail: {},
				bannerIndex: 0,
				value: 1,
				banner: [],
				skeletonShow: true,
				// 控制视频是否播放
				vedioShow: false,
				opcity:0,
				back:false,
				carList:this.$store.state.carList
			}
		},
		// watch:{
		// 	carList() {
				
		// 		this.carList.forEach(car =>{
		// 			if(car.id == this.goodDetail.id){
		// 				this.goodDetail.count = car.count
		// 			}
		// 		})
		// 	},
		// },
		async onLoad(options) {
			this.goodDetail =  JSON.parse(decodeURIComponent(options.detail))
			this.goodDetail.relation = JSON.parse(decodeURIComponent(options.relations))
			this.goodDetail.detailPicture = this.goodDetail.detailPicture.split(',')
			this.goodDetail.whitePicture = this.goodDetail.whitePicture.split(',')
			// #ifdef MP-WEIXIN
			setTimeout(()=>{
				wx.showShareMenu({
					withShareTicket:false,
					//设置下方的Menus菜单，才能够让发送给朋友与分享到朋友圈两个按钮可以点击
					menus:["shareAppMessage"]
				})
			},1000)
			// #endif
		},
		//发送给朋友
		onShareAppMessage(res) {
			return {
				title: "秒杀价:" + this.goodDetail.productPrice + '元购' + this.goodDetail.productName,
				type: 0,
				path: 'pages/component/goods-details/goods-details?productId='+this.productId+'&type='+'share',
				imageUrl: this.banner[0],
			}
		},
		methods: {
			// 购物车数据处理
			handelCart(item){
				this.carList = this.$store.state.carList
				if(this.goodDetail.id = item.id){
					this.goodDetail.count = item.count
				}
			},
			// 清空购物车
			delAll(){
				this.carList = []
				this.goodDetail.count = 0
			},
			bannerChange: function(e) {
				this.bannerIndex = e.detail.current;
			},
			previewImage: function(e) {
				let index = e.currentTarget.dataset.index;
				uni.previewImage({
					current: this.banner[index],
					urls: this.banner
				});
			},
			// 视频播放
			playRadio() {
				this.vedioShow = true
				let vedioContext = uni.createVideoContext("myVideo",this)
				vedioContext.play() // 开始播放
			},
			// 退出/暂停视频
			outRadio() {
				this.vedioShow = false
				let vedioContext = uni.createVideoContext("myVideo",this)
				vedioContext.pause() // 停止播放
			},
		},
		onPageScroll(e) {
			let scroll = e.scrollTop <= 0 ? 0 : e.scrollTop;
			let opcity = scroll / this.scrollH;
			if (this.opcity >= 1 && opcity >= 1) {
				return;
			}
			this.opcity = opcity;
			this.iconOpcity = 0.5 * (1 - opcity < 0 ? 0 : 1 - opcity);
		}
	}
</script>


<style scoped lang="scss">
.vedio-box {
	width: 750rpx;
	height: 750rpx;
	background-color: #000;
	position: absolute;
	top: 0;
}
video {
		width: 750rpx;
		height: 640rpx;
	}
.playBtn,.outBtn {
	width: 180rpx;
	line-height: 60rpx;
	height: 60rpx;
	border-radius: 60rpx;
	background-color: rgba(255,255,255,0.5);
	text-align: center;
	position: absolute;
	top: 660rpx;
	left: 50%;
	margin-left: -100rpx;
}
.playBtn {
	.round {
		margin-right: 30rpx;
		margin-left: 8rpx;
		width: 50rpx;
		height: 50rpx;
		background-color: #F22222;
		image {
			width: 100%;
			height: 100%;
		}
	}
}
	.tui-opcity .tui-menu-text,
	.tui-opcity .tui-badge-box {
		opacity: 0.5;
		transition: opacity 0.2s ease-in-out;
	}
	.tui-padding {
		padding: 0 30rpx;
		box-sizing: border-box;
	}
	.tui-ptop {
		padding-top: 20px;
	}
	/* #ifdef H5 */
	.tui-ptop {
		padding-top: 44px;
	}
	
	/* #endif */
	
	.container {
		padding-bottom: 110rpx;
	}
	/* nav导航 */
	.tui-header-box {
		width: 100%;
		position: fixed;
		left: 0;
		top: 0;
		z-index: 995;
	}
	.tui-header {
		width: 100%;
		font-size: 18px;
		line-height: 88px;
		font-weight: 500;
		height: 32px;
		margin-top: 10px;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.tui-header-icon {
		position: fixed;
		top: 0;
		left: 10px;
		display: flex;
		align-items: flex-start;
		justify-content: space-between;
		height: 32px;
		transform: translateZ(0);
		z-index: 9999;
	}
	.tui-header-icon .tui-badge {
		background: #e41f19 !important;
		position: absolute;
		right: -4px;
	}
	.tui-header-right{
		position: fixed;
		top: 0;
		right: 10px;
		display: flex;
		align-items: flex-start;
		justify-content: space-between;
		height: 32px;
		transform: translateZ(0);
		z-index: 9999;
	}
	.tui-icon-ml {
		margin-left: 20rpx;
	}
	.tui-icon-box {
		position: relative;
		height: 30px !important;
		width: 30px !important;
		padding: 6px !important;
	
		border-radius: 50%;
		display: flex;
		align-items: center;
		justify-content: center;
		.icon{
			font-size: 20px;
		}
		
	}
	/* banner */
	.tui-banner-swiper {
		position: relative;
	}
	
	.tui-banner-tag {
		position: absolute;
		color: #fff;
		bottom: 0;
		width: 100%;
		.banner-tag-in{
			display: flex;
			justify-content: flex-end;
			margin: 0 30upx 30upx 0;
			.banner-tag{
				background-color: rgba($color: #000000, $alpha: 0.5);
				padding: 20upx 0;
				width: 100upx;
				text-align: center;
			}
		}
	}
	.tui-slide-image {
		width: 100%;
		display: block;
	}
	
	// 标题内容模块
	.pro-detail{
		.banner-pricebox{
			background-image: url(https://sbb-sbb.oss-cn-shenzhen.aliyuncs.com/static/index/YJG_pro_details_x3.png);
			background-repeat: no-repeat;
			background-position: top center;
			background-size: cover;
			width: 100%;
			height: 130upx;
			padding: 0 24upx;
			color: #FFFFFF;
			.line-through{
				margin-top: 15rpx;
				margin-left: 20rpx;
			}
		}
		.por-list{
			padding: 40upx 28upx;
			margin-bottom: 20upx;
			background-color: #FFFFFF;
			.pro-pricebox{
				.original-price{
					margin-right: 10upx;
					color: #F22222;
					& > text{
						font-size: 20upx;
					}
					.tui-price{
						font-size: 42upx !important;
						font-weight: bold;
					}
				}
				.pro-people{
					color: $main-color;
				}
			}
			.pro-titbox{
				display: flex;
				flex-direction: row;
				justify-content: space-between;
				box-sizing: border-box;
				.pro-title{
					color: #080808;
					font-size: 30upx;
					font-weight: bold;
					word-break: break-all;
					overflow: hidden;
					text-overflow: ellipsis;
					display: -webkit-box;
					-webkit-box-orient: vertical;
					-webkit-line-clamp: 2;
					width: 80%;
				}
				button.pro-button{
					width: 95upx;
					padding:9upx  0;
					background-color: #fee700;
					color: $main-color;
					border-top-left-radius:50upx ;
					border-bottom-left-radius:50upx ;
					text-align: center;
					position: absolute;
					right: 0;
					font-size: 28rpx;
					line-height: 1;
				}
				button.pro-button::after{
					border: 0  none !important;
					box-shadow: none !important;
				}
			}
			.iconfanhui-right{
				color:$black-color;
				font-size: 35upx;
				padding-left: 5upx;
			}
			.title{
				font-size: 34upx;
				font-weight: bold;
			}
			.pro-vip-del{
				color: $main-color;
				.icon{
					color: $main-color;
				}
			}
		}
		.por-specs{
			padding-right: 42upx;
			& > text:first-child{
				padding-right: 20upx;
				color: #aaaaaa;
			}
		}
		
	}
	.por-list >view:not(:last-child){
		margin-bottom: 27upx;
	}
	/* 详情 */
	.product-details{
		.details-title{
			padding: 30upx 0;
			& > text{
				font-size: 30upx;
				margin: 0 40upx;
			}
			& > view{
				width: 32%;
				height: 2upx;
				background-color: #EEEEEE;
			} 
		}
		image{
			width: 100vw;
			display: block;
		}
	}
	// 加入购物车
	.food-btm {
		flex: 1;
		position: relative;
		.round{
			text-align: center;
			line-height: 1;
			background-color: #f22222;
			color: #fff;
			padding: 15rpx 20rpx;
		}
	}
	
	.count {
		position: absolute;
		height: 40rpx;
		color: #fff;
		background-color: #f22222;
		min-width: 40rpx;
		line-height: 40rpx;
		text-align: center;
		border-radius: 40rpx;
		right: 0;
		bottom: 50rpx;
	
	}
	.swiperheight{
		height: 100vw;
	}
	.sku-box{
		border-radius: 10rpx;
		overflow: hidden;
	}
	.sku{
		height: 90rpx;
		border-bottom: 1px solid #fff;
		text{
			padding: 0 18rpx;
			line-height: 90rpx;
		}
		.bg-gray{
			width: 20%;
		}
	}
	.sku:last-child{
		border: none;
	}
	.sku-detail{
		flex: 1;
		background-color: #f8f8f8;
		
	}
</style>
