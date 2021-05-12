<template>
	<view>
		<!-- 搜索框 -->
		<view class="topnav fixed">
			<view class="cu-bar search bg-white">
				<navigator hover-class="none" url="search" class="search-form round">
					<text class="cuIcon-search icon iconfont icon-sousuo"></text>
					<input type="text" placeholder="搜索z驿站" confirm-type="search"></input>
				</navigator>
			</view>
		</view>
		<view class="head-title u-f-ac">附近驿站</view>
		<view class="add-info">
			<view class="itme bg-white u-f" v-for="(item,index) in 12" :key="index">
				<image src="../../../static/auth/loginBg2.png" mode="aspectFill" class="post-img"></image>
				<view @tap="Route({type:'back'})" class="u-fc flex1">
					<view class="u-f-jsb" style="align-items: flex-start;">
						<view class="u-fc title">
							<view>驿站名字驿站名字驿站名字驿站名字</view>
							<view class="tui-por-del">
								<view class="del-item del-item-blue">支持冷藏</view>
								<view class="del-item del-item-red">优质服务</view>
							</view>
						</view>
						<view class="round bg-blue" v-if="true">选择</view>
						<view class="round bg-gray" v-else>暂停接单</view>
					</view>
					
					<view class="distance">距您：100km</view>
					<view class="det text-m">广东省广州市白云区岗贝路富丽大厦</view>
					<view class="u-f-jsb">
						<view class="u-f-ajc time"><text class="icon iconfont icon-shijian"></text>营业时间：09:00 - 23:00</view>
						<!-- <view class="u-f-ajc cont" @tap="call(item.phone)"><text class="icon iconfont icon-dianhua"></text>联系商家</view> -->
					</view>
				</view>
				
			</view>
			<!-- <tui-no-data imgUrl="https://zj.gdwrh.com/fnyg/static/images/my-img/img_nodata.png" v-if="noDataFlag">
				<view>该区域暂未开放，敬请期待</view>
				<view>前往烽鸟全国包邮，挑选更多商品!</view>
				<tui-button margin="20rpx auto" type="danger" shape="circle"  width="260rpx" height="60rpx" :size="24" @click="toindex">去逛逛</tui-button>
			</tui-no-data> -->
		</view>
	</view>
</template>
<script>
	// import gcoord from 'gcoord'
	export default {
		data() {
			return {
				searchText:''
			};
		},
		// onLoad() {
		// 	// 获取本地经纬度
		// 	uni.getLocation({
		// 		type: 'wgs84',
		// 		geocode: true,
		// 		success(e) {
		// 			// 经纬度处理
		// 			var result = gcoord.transform(
		// 			  [e.longitude, e.latitude],    // 经纬度坐标
		// 			  gcoord.WGS84,               // 当前坐标系
		// 			  gcoord.BD09 
				
		// 			);
		// 			that.longitude = result[0]
		// 			that.latitude = result[1]
		// 			// 业务处理
		// 		},
		// 		fail(e) {
		// 			that.tui.toast("定位失败")
		// 		}
		// 	})
		// }
	}
</script>

<style lang="scss" scoped>
	.topnav {
		top: 0;
		// #ifdef H5
		top: 44px;
		// #endif
	}
	.icon-cuo {
		position: relative;
		right: 20rpx;
	}
	.head-title{
		position: fixed;
		font-size: 30upx;
		font-weight:bold;
		padding: 130upx 28upx 30upx;
		background-color: #F2F2F2;
		top: 0;
		// #ifdef H5
		top: 44px;
		// #endif
		width: 100%;
		z-index: 9;
	}
	.head-title:before{
		content: '';
		width: 6upx;
		height: 28upx;
		display: inline-block;
		background-color: $main-color;
		margin-right: 20upx;
	}
	.add-info{
		width: 100%;
		padding-top: 200rpx;
		& > scroll-view{
			height: 500upx;
		}
		.itme{
			align-items: flex-start;
			border-radius: 20upx;
			padding: 30upx 28upx;
			margin-bottom: 20upx;
			.flex1{
				flex: 1;
			}
			.title{
				font-size: 30upx;
				font-weight: bold;
				color: #080808;
				.icon{
					color: $main-color;
				}
			}
			.post-img{
				border-radius: 18rpx;
				width: 166rpx;
				height: 170rpx;
				flex-shrink: 0;
				display: block;
				margin-right: 18rpx;
			}
			.distance{
				font-size: 24upx;
			}
			.det{
				// width: 430upx;
				margin: 10upx 0;
			}
			.round{
				flex-shrink: 0;
				padding: 10rpx 20rpx;
				margin-left: 18rpx;
			}
			.time{
				.icon{
					color: #080808;
					font-size: 40upx;
				}
			}
			.cont{
				color: $main-color;
				.icon{
					font-size: 40upx;
				}
			}
		}
	}

</style>
