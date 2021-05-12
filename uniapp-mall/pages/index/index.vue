<!-- 商城启动页 -->
<template>
	<view class="index-box">
		<nav-bar type="fixed" :backState="2000" :bgColor="['#2ba4f7', '#8bd8fe']" :titleCenter="false" title="Open-Mall">
			<view class="Title">M家购</view>
			<navigator class="search_box u-f-ac" hover-class="none" url="../common/search/search">
				<text class="iconR iconfont icon-sousuo"></text>
				<text class="prompt padding-xs">搜索</text>
			</navigator>
		</nav-bar>
		<view class="nav-tab">
			<view class="top u-f-jsb">
				<view v-for="i in navList">
					<text class="icon iconfont" :class="i.icon"></text>
					{{i.name}}
				</view>
			</view>
			<view class="choose-post u-f-jsb u-f-ac">
				<navigator hover-class="none" url="../common/postStation/postStation" class="text-cut post u-f-jsb">
					自提点：OM自营店
					<view class="padding-left">切换<text class="icon iconfont icon-right text-white"></text></view>
				</navigator>
				<button class="pro-button" open-type="share">
					<text class="icon iconfont icon-fenxiang3 "></text>分享
				</button>
			</view>
			<!-- 消息轮动 -->
			<!-- <swiper
				:autoplay="true"
				:interval="6000"
				:duration="150"
				class="news-box"
				:vertical='true'
				:circular="true"
			>
			<swiper-item class="u-f-ac swiper-new text-sm" v-for="i in 3">
				<image src="../../static/logo.jpg" class="round" mode="aspectFill"></image>
				<view class="text-cut">李**19分钟前下单</view>
			</swiper-item>
			</swiper> -->
			<!-- 轮播图 -->
			<swiper
				:indicator-dots="true"
				:autoplay="true"
				:interval="5000"
				:duration="150"
				class="tui-banner-swiper square-dot"
				:circular="true"
				indicator-color="rgba(255, 255, 255, 0.8)"
				indicator-active-color="#2ba4f7"
			>
				<swiper-item v-for="(item, index) in advertList" @click="Route({url:item.advertUrl})">
					<image :src="item.picUrl" class="tui-slide-image" mode="scaleToFill"/>
				</swiper-item>
			</swiper>
		</view>
		<!-- 滑动菜单 -->
		<view class="unicorn-header2">
			<swiper
				class="screen-swiper2 square-dot"
				indicator-color="#f22222"
				:indicator-active-color="tab.length>5?'#f22222':'#fff'"
				:indicator-dots="tab.length>5"
				:circular="false"
				:autoplay="false"
				interval="5000"
				duration="500"
				style="background: transparent !important;"
			>
				<swiper-item style="background: transparent !important;" >
					<view class="menu">
						<block v-for="(item, index) in tab" :key="item.id">
							<view class="menu-in">
								<image :src="'../../static/icon/'+ item.img" class="menu-img" mode="scaleToFill"></image>
								<view class="menu-name text-cut">{{ item.name }}</view>
							</view>
						</block>
					</view>
				</swiper-item>
			</swiper>
		</view>
		<!-- 秒杀商品 -->
		<view class="bg-white seckill-box">
			<view class="u-f-jsb margin-bottom">
				<view class="u-f-ajc text-bold u-f-ac ">
					<image src="../../static/icon/xsqg_title.png" class="seckill-tit" mode="widthFix" :lazy-load="true"></image>
				</view>
				<navigator hover-class="none" url="../home/seckill/seckill" class="text-gray text-sm u-f-ac">{{total}}个商品即将抢光<text class="icon iconfont icon-right "></text></navigator>
			</view>
			<scroll-view scroll-x class="scroll-box" scroll-with-animation>
				<view class="seckill-item" v-for="(item,index) in seckillList">
					<image :src="item.mainPicture" mode="heightFix"></image>
					<view class="price text-red text-bold text-df">
							<text class="text-xs">￥</text>
							{{item.relation.productPrice}}
					</view>
					<cart-control  style="margin-top: 10rpx;" :food="item" :carList='carList' @cartChange='handelCart'></cart-control>
				</view>
			</scroll-view>
		</view>
		<!-- 商品分类 -->
		<!-- #ifdef MP-WEIXIN -->
		<scroll-view scroll-x class="nav" scroll-with-animation :scroll-left="scrollLeft" :style="{top: Custom + 'px'}">
		<!-- #endif -->
		<!-- #ifndef MP-WEIXIN -->
		<scroll-view scroll-x class="nav" scroll-with-animation :scroll-left="scrollLeft">
		<!-- #endif -->
			<view class="cu-item" :class="index == currentIndex ? 'active' : ''" v-for="(item, index) in 8" @tap="TabSelect(index,item.id)">
				<view class="cu-pic"><image src="../../static/goods/2.jpg"></image></view>
				<view>新鲜水果</view>
				<view class="bor-bottom round" :class="index == currentIndex ? ' cur' : ''"></view>
			</view>
		</scroll-view>
		<!-- 商品列表 -->
		<view class="foods-wrapper bg-white">
			<goods-container id="goods-container" :scrollBottom='scrollBottom' :typeId='typeId' :car='carList'></goods-container>
		</view>
	</view>
</template>

<script>
	import Request from '@/common/js/luch-request/index.js'
	import goodsContainer from '@/components/goods-container/goods-container.vue'
	const http = new Request();
	import Vue from 'vue';
	export default {
		components:{
			goodsContainer
		},
		data() {
			return {
				// #ifdef MP-WEIXIN
				Custom: this.CustomBar,
				// #endif
				StatusBar: this.StatusBar,
				navTop:0,		//商品列表位置
				navList:[
					{
						name:'优选好货',
						icon: 'icon-yezi'
					},
					{
						name:'次日16点自提',
						icon: 'icon-shuiguo'
					},
					{
						name:'售后无忧',
						icon: 'icon-zhiliangguanli'
					}
				],
				tab: [
					{
						id: 1,
						img: 'discount.png',
						name: '今日特惠'
					},
					{
						id: 3,
						img: 'luck.png',
						name: '天天抽奖'
					},
					{
						id: 4,
						img: 'jf.png',
						name: '积分兑换'
					},
					{
						id: 2,
						img: 'th.png',
						name: '特卖专区'
					},
					{
						id: 5,
						img: 'chief.png',
						name: '成为团长'
					}
				],
				currentIndex:0,		//当前分类
				scrollLeft:0,
				typeId:3,			//商品分類id
				advertList: [], 	//轮播图
				scrollBottom:0,
				carList: [],
				seckillList:[],    //秒杀商品
				total:0,
			};
		},
		onReady() {
			this.getInfo()
		},
		onLoad() {
			this.getAdvertList()
			this.getKill()
		},
		onShow() {
			this.carList = this.$store.state.carList
		},
		methods:{
			// 获取首页轮播图
			getAdvertList(){
				this.$http.post('/api/advert/load', {type:"1"}, { header: { authType: 'false'}}).then(res => {
					this.advertList = res.data || []
				})
			},
			// 获取秒杀商品
			getKill(){
				this.$http.post('/api/search/products/secKillPage', {moudleId:"1",currentPage:0,pageSize:6}, { header: { authType: 'false'}}).then(res => {
					this.total = res.data.total
					if(this.total == 0) return 
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
					this.seckillList = [...this.seckillList,...res.data.rows]
				})
			},
			// 购物车
			handelCart(item){
				this.seckillList.forEach(pro=>{
					if(pro.id == item.id){
						pro.count = item.count 
					}
				})
			},
			TabSelect(index,id){
				this.currentIndex = index
				this.typeId = id + 1
				this.scrollBottom = 0
				this.scrollLeft = (index - 1) * 80;
				uni.pageScrollTo({
					scrollTop: this.navTop,
					duration: 0
				});
			},
			// 获取盒子在页面滚动的位置
			getInfo() {
				   let _this=this
				   let height=""
				   const query = uni.createSelectorQuery()
				   query.select('.nav').boundingClientRect()
				   query.selectViewport().scrollOffset()
				   query.exec(function(res){
					// debugger
					res[0].top       // #the-id节点的上边界坐标
					res[1].scrollTop // 显示区域的竖直滚动位置
					height=uni.getSystemInfoSync().screenHeight
					// #ifdef H5
					_this.navTop=height-res[0].height - 54
					// #endif
					// #ifdef MP-WEIXIN
					_this.navTop=height-res[0].height - uni.upx2px( 20 + 2*_this.StatusBar - _this.Custom)
					// #endif
					// _this.navTop = uni.upx2px(res[1].scrollHeight - (res[0].top + res[1].scrollTop))
				   })
			},
		},
		onReachBottom() {
			let timestamp = new Date().getTime();
			this.scrollBottom = timestamp;
		}
	}
</script>

<style lang="scss" scoped>
/* #ifdef APP-PLUS-NVUE */
	@function unit($num) {
		@return $num + 0rpx;
	}
	/* #endif */
	/* #ifndef APP-PLUS-NVUE */
	@function unit($num) {
		@return $num + 0upx;
	}
// 顶部
.Title {
	margin-right: 20rpx;
	color: #ffffff;
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
.nav-tab {
	position: relative;
	background: linear-gradient(to right,#2ba4f7,#8cd8fe);
	border-bottom-left-radius: 280rpx;
	padding: 0 28rpx;
	z-index: 9;
	color: #fff;
	.top {
		font-size: 24rpx;
		.icon {
			margin-right: 10rpx;
			font-weight: bold;
			font-size: 26rpx;
			color: #fff;
		}
	}

	.choose-post {
		background-color: rgba($color: #fff, $alpha: 0.2);
		border-radius: 10rpx;
		margin-top: 20rpx;
		padding: 18rpx;
		color: #fff;
		.post {
			flex: 1;
			border-right: 1rpx solid #fff;
		}
		.icon {
			padding:10rpx;
			color: #FFFFFF;
			font-size: 14px;
		}
	}
	button.pro-button {
		padding: 0;
		background-color: rgba($color: #fff, $alpha: 0);
		color: #fff;
		text-align: center;
		font-size: 26rpx;
		line-height: 1;
		margin: 0!important;
		.icon{
			font-size: 30rpx;
		}
	}
	
}
// 轮播图
.tui-banner-swiper {
	margin: 20rpx 0;
	height: 280upx;
	border-radius: 12rpx;
	overflow: hidden;
	transform: translateY(0);
	z-index: 88;
}
.tui-slide-image {
	width: 100%;
	height: 280upx;
	border-radius: 20upx;
	display: block;
}
// 金刚区
.unicorn-header2 {
	margin: 20upx;
	background-color: #ffffff;
	border-radius: 20upx;
	background-size: 100% 100%;
	-moz-background-size: 100% 100%;
}

.menu {
	box-sizing: border-box;
	display: flex;
	align-items: center;
	flex-wrap: wrap;
	font-size: 24upx;
	color: #555;
	margin-bottom: 20upx;
	padding-top: 20upx;

	.menu-in {
		width: 20%;
		display: flex;
		align-items: center;
		justify-content: space-between;
		flex-direction: column;
		padding-top: 15upx;
		.menu-img {
			height: 75upx;
			width: 75upx;
			display: block;
			flex-shrink: 0;
			background-color: #ffffff;
		}

		.menu-name {
			transform: scale(0.9);
			line-height: 50upx;
		}
	}
}
// 分类
.nav {
	position: sticky;
	/* #ifdef H5 APP-NVUE */
	top: unit(88);
	/* #endif */
	background-color: #fff;
	z-index: 9;
	padding-top: 20rpx;
	min-height: 100rpx;
	.cu-item {
		height: 170upx;
		line-height: 44upx;
		font-size: 28rpx;
		text-align: center;
		padding: 0;
		margin: 0 18rpx;
		image {
			width: 80rpx;
			height: 80rpx;
			border-radius: 32rpx;
		}
	}
	.active {
		font-size: 30rpx;
		font-weight: bold;
		color: #f22222;
	}
	.bor-bottom.cur {
		height: 10rpx;
		width: 70%;
		margin: auto;
		margin-top: 6rpx;
		border-radius: 5rpx;
		background-image: linear-gradient(to right, #f22222, #ffe7df);
		animation-name: myfade;
		animation-duration: 0.2s;
		animation-timing-function: linear;
	}
}
@keyframes myfade {
	0% {
		width: 0;
		opacity: 0;
	}

	100% {
		width: 70%;
		opacity: 1;
	}
}
// 秒杀专区
.seckill-box {
	padding: 20rpx 25rpx;
	margin-bottom: 10px;
	height: 360rpx;
	border-radius: 20rpx;
	.seckill-tit{
		width: 120rpx;
		display: block;
		flex-shrink: 0;
	}
	.icon{
		font-size: 24rpx;
		padding-top: 10rpx;
	}
}
.scroll-box {
	white-space: nowrap;
}
.seckill-item {
	position: relative;
	width: 25%;
	text-align: center;
	display: inline-block;
	overflow: hidden;
	margin-right: 25rpx;
	image {
		height: 150rpx;
		border-radius: 10rpx;
	}
	.price {
		text-align: center;
	}
	.text-del {
		text-decoration: line-through;
	}
	.countCont {
		height: 42upx;
		line-height: 42upx;
		margin-top: 13rpx;
	}
	.cont {
		margin-top: 0 !important;
	}
	.count {
		bottom: 36rpx;
	}
}
// 加入购物车按钮
.count{
	position: absolute;
	background-color: #F22222;
	color: #fff;
	border-radius: 100%;
	width: 40rpx;
	height: 40rpx;
	right: 3px;
}
.screen-swiper2{
	height: 190rpx !important;
}
// 商品列表
.foods-wrapper {
	position: relative;
	margin-top: 10px;
	// overflow: hidden;
	padding: 20rpx 0 100rpx;
	min-height: 400rpx;
}
// 消息滚动区
.news-box{
	position: absolute;
	width: 80%;
	top: 140rpx;
	height: 54rpx;
	transform: translateY(0);
	z-index: 88;
}
.swiper-new{
	background: rgba($color: #000000, $alpha: 0.3);
	box-sizing: border-box;
	width: auto!important;
	border-radius: 28rpx;
	padding: 0 18rpx;
	image{
		width: 40rpx;
		height: 40rpx;
		display: block;
		flex-shrink: 0;
	}
}
</style>
