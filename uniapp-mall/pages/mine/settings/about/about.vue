<!-- 关于版本升级信息 -->
<template>
	<view class="about bg-white">
		<view class="text-center">
			<image src="../../../../static/logo.jpg"></image>
		</view>
		<view class="rich">
			<rich-text class="radius20" :nodes="content">
				
			</rich-text>
		</view>
		<view class="text-center">
			<view>版本号1.0.0</view>
			<view>COPYRIGHT@2</view>
			<view>M家购.COM版权所有</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				content: 'OM凭借创新的S2B2C+O2M商业模式，深度整合供应链资源，搭建数据中心，物流配送，运营管理，营销策划，智能客服的综合体系；以源头好产品进行研选，赋能企业，打造一个丰衣足食的生活购物平台，全面覆盖家庭日常生活所需。S2b2c+O2M商业模式，是对传统工业时代B2C模式的最根本颠覆，将会重构整个商务的全链路，商业网络从传统的供应链走向网络协同的商业革命。一个强大的供应链平台（S），与千万个直接服务客户的商家（b），结合人的创造性和系统网络的创造力培育出一个全新的赋能平台，O2M是“线下实体店+线上电商+移动终端”的组合式运营模式'
			};
		}
	}
</script>

<style scoped lang="scss">
.about {
	height: 100vh;
	padding: 60rpx 28rpx 40rpx;
	font-size: 24rpx;
	color: #000;
}
image {
	width: 252rpx;
	height: 252rpx;
	margin-bottom: 40rpx;
}
.rich {
	background-color: #f2f2f2;
	padding: 38rpx 30rpx;
	width: 100%;
	margin-bottom: 38rpx;
	font-size: 24rpx;
	color: #666;
}
</style>