<!-- 我的粉丝 -->
<template>
	<view>
		<!-- tab标签栏 -->
		<u-subsection mode="button" bgColor='#2ba4f7' activeColor='#2ba4f7' inactiveColor='#fff' :list="['一级粉丝', '二级粉丝']" @change="tabChange"></u-subsection>
		<view class="myFans text-center">
			<view class="fans-list-box">
				<view class="fans-list-title u-f-jsb">
					<text style="width: 15%;">头像</text>
					<text style="width: 45%;">粉丝名称</text>
					<text style="width: 40%;">邀请时间</text>
				</view>
				<scroll-view scroll-y @scrolltolower="getMore">
					<view class="u-f-jsb fans-list" v-for="(item,index) in 8" :key="index">
						<!-- <view style="width: 13%;"> -->
							<view class="avater round">
								<!-- 有头像，则显示头像，没头像，则用默认头像 -->
								<image src="../../../static/logo.jpg" class="round" mode="aspectFill"></image>
							</view>
						<!-- </view> -->
						<text style="width: 45%;" class="text-cut padding-left-xs">十月</text>
						<text style="width: 7%;">{{index}}级</text>
						<text style="width: 40%;" class="text-cut">2021-12-12 09:20:22</text>
					</view>
				</scroll-view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
			};
		},
		methods:{
			tabChange(){}
		}
	}
</script>

<style scoped lang="scss">
page{
    width: 100%;
    height: 100%;
}
.myFans{
    width: 100%;
    height: calc(100vh - 78rpx);
	background: linear-gradient(to bottom,$main-color,#8cd8fe);
	background-size: 100% 100%;
	padding: 40rpx 28rpx 20rpx;
	.fans-list-box {
		background-color: #fff;
		border-radius: 36rpx;
		padding: 30rpx 30rpx 20rpx 30rpx;
		max-height: 100%;
		min-height: 80%;
		.fans-list-title {
			border-bottom: 2rpx solid #eee;
			padding-bottom: 20rpx;
			margin-bottom: 10rpx;
			font-size: 28rpx;
			text-align: center;
		}
		.fans-list{
			line-height: 100rpx;
			height: 100rpx;
			font-size: 24rpx;
		}
	}
}
.avater {
	
	image{
		width: 80rpx;
		height: 80rpx;
	}
}
</style>
