<template>
	<view :style="{'background-color':bgColor}">
		<view class="tui-searchbox">
			<view class="tui-search-input">
				<icon type="search" :size='13' color='#333'></icon>
				<input  placeholder-class="tui-input-plholder" class="tui-input" confirm-type="search" :adjust-position="true" placeholder="搜索驿站" v-model="keyword" @confirm="doSearch(keyword)" />
				<icon type="clear" :size='13' color='#bcbcbc' @click.stop="clear" v-show="keyword"></icon>
			</view>
			<view class="tui-cancle" @tap="doSearch(keyword)">搜索</view>
		</view>
		<view class="search-order">
			<view v-if="isShowgoods" class="lists">
				<view class="add-info">
					<view class="itme bg-white u-fc" v-for="(item,index) in list1" :key="index" @tap="toPost(item)">
						<view class="u-f-jsb">
							<view class="u-f-ajc title"><text class="icon iconfont icon-dizhi"></text>{{item.stationName}}</view>
							<view class="distance">{{item.distance | dist}}</view>
						</view>
						<view class="det">{{item.address}}</view>
						<view class="u-f-jsb">
							<view class="u-f-ajc time"><text class="icon iconfont icon-shijian"></text>营业时间：{{item.businessTime}}</view>
							<view class="u-f-ajc cont" @tap="call(item.phone)"><text class="icon iconfont icon-dianhua"></text>联系商家</view>
						</view>
					</view>
				</view>
				<view>
					<loadmore :visible="loadMore"></loadmore>
					<tui-nomore :visible="noMoreFlag"></tui-nomore>
					<tui-no-data imgUrl="https://zj.gdwrh.com/fnyg/static/images/my-img/img_nodata.png" v-if="noData" :fixed="true">该区域尚未开发，敬请期待</tui-no-data>
				</view>
			</view>
			<view v-else class="record">
				<view class="margin-lr" v-if="oldKeywordList.length>0">
					<view class="tui-history-header">
						<view class="tui-search-title">历史搜索</view>
						<view class="tui-icon-delete" @tap="oldDelete()">删除</view>
					</view>
					<view class="tui-history-content">
						<view v-for="(keyword,index) in oldKeywordList" @tap="doSearch(keyword)" :key="index" class="tui-circle round">{{keyword}}</view>
					</view>
				</view>
				<!-- <view class="margin-lr" v-if="historyShop.length>0">
					<view class="tui-history-header">
						<view class="tui-search-title">浏览驿站记录</view>
						<view class="tui-icon-delete" @tap="oldDelete2()">删除</view>
					</view>
					<view class="tui-history-content">
						<view v-for="(keyword,index) in historyShop" @tap="doSearch(keyword)" :key="index" class="tui-circle round">{{keyword}}</view>
					</view>
				</view> -->
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isShowgoods: false,
				oldKeywordList:[
					'M家购'
				],
				bgColor: '#fff',
				keyword:''
			}
		},
	}
</script>

<style lang="scss">
	page {
		color: #333;
		background: #FFFFFF;
	}
	.search-order {
		padding-top: 44px;
	}
	.lists {
		padding: 20rpx;
	}
	.baseW {
		// border-bottom: 1px solid #ccc;
		padding-top: 20rpx;
		padding-bottom: 20rpx;
	}
	.tui-searchbox {
		padding: 10rpx 28rpx;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		background-color: #FFFFFF;
		position: fixed;
		width: 100%;
		z-index: 999;
	}
	
	.tui-search-input {
		width: 100%;
		height: 66rpx;
		border-radius: 35rpx;
		padding: 0 30rpx;
		box-sizing: border-box;
		background: #f2f2f2;
		display: flex;
		align-items: center;
		flex-wrap: nowrap;
	}
	
	.tui-input {
		flex: 1;
		color: #333;
		padding: 0 16rpx;
		font-size: 28rpx;
	}
	
	.tui-input-plholder {
		font-size: 28rpx;
		color: #b2b2b2;
	}
	
	.tui-cancle {
		color: #888;
		font-size: 28rpx;
		padding-left: 30rpx;
		flex-shrink: 0;
	}
	/* 历史搜索 */
	.tui-history-header {
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 30rpx 0;
	}
	.tui-search-title {
		font-size: 28rpx;
		font-weight: bold;
	}
	.tui-icon-delete {
		padding: 10rpx;
	}
	.tui-history-content{
		display: flex;
		align-items: center;
		flex-wrap: wrap;
	}
	.tui-circle{
		background-color: #f2f2f2;
		padding: 14upx 20upx;
		margin: 0 20upx 20upx 0;
	}
	.keyword-entry-tap {
		background-color:#F1F1F1;
	}
	// 列表
	.add-info{
		width: 100%;
		& > scroll-view{
			height: 500upx;
		}
		.itme{
			border-radius: 20upx;
			padding: 30upx 28upx;
			margin-bottom: 20upx;
			.title{
				font-size: 30upx;
				font-weight: bold;
				color: #080808;
				.icon{
					color: $main-color;
				}
			}
			.distance{
				font-size: 24upx;
			}
			.det{
				// width: 430upx;
				margin: 20upx 0;
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
