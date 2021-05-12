<template>
	<view class="comtainer">
		<view class="bill">
			<view class="sticky-item">
				<view class="moth" @tap.stop='showpicker'>{{year}}年{{month}}月<text class="icon iconfont icon-icon-arrow-bottom2"></text></view>
				<view class="amount">
					<view>支出 ￥1999</view>
					<view>收入 ￥29999</view>
				</view>
			</view>
			<view class="baseW" style="padding-top: 104rpx;">
				<view class="bill-list" v-for="(item,index) in 12" :key="index">
					<view class="u-f-jsb">
						<text class="text-cut">购买商品</text>
						<text class="text-red">-9.9</text>
					</view>
					<view class="u-f-jsb">
						<view class="time">2021-09-12 09:09:09</view>
					</view>
				</view>
			</view>
		<!--内容 end-->
		</view>
		<view class="date-time-picker" v-if="visible">
			<view class="date-time-mask" @tap.stop="hide"></view>
			<view class="pickerTit u-f-ac u-f-jsb">
				<view class="text-gray" @tap.stop="hide">取消</view>
				<view>请选择时间</view>
				<view class="text-blue" @tap.stop='confirm'>确定</view>
			</view>
			<picker-view class="picker-view" :indicator-style="indicatorStyle" :value="value" @change="bindChange">
				<picker-view-column>
					<view class="item" v-for="(item,index) in years" :key="index">{{item}}年</view>
				</picker-view-column>
				<picker-view-column>
					<view class="item" v-for="(item,index) in months" :key="index">{{item}}月</view>
				</picker-view-column>
			</picker-view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			const date = new Date()
			const years = []
			const year = date.getFullYear()
			const months = []
			const month = (date.getMonth() + 1 + '').padStart(2, '0')
			const days = []
			const day = date.getDate()
			this.data = `${year}-${month}`
			for (let i = 2019; i <= date.getFullYear(); i++) {
				years.push(i)
			}
			for (let i = 1; i <= 12; i++) {
				const m = ''
				if(i<10){
					m = '0'+i
				}else{
					m = i
				}
				months.push(m)
			}
			for (let i = 1; i <= 31; i++) {
				days.push(i)
			}
			return {
				// recalc: 1,
				scrollTop: 0,
				years,
				year,
				months,
				month,
				value: [9999, month - 1, day - 1],
				visible: false,
				indicatorStyle: `height: ${Math.round(uni.getSystemInfoSync().screenWidth/(750/100))}px;`,
				total: 0,
				bill: [],
				currentPage: 1,
				noMoreFlag1: false,
				noDataFlag1: false,
				loadMore: true,
				type: 0
			}
		},
		methods: {
			//页面滚动执行方式
			onPageScroll(e) {
				this.scrollTop = e.scrollTop
			},
			bindDateChange(e){
			},
			bindChange: function (e) {
				const val = e.detail.value
				this.year = this.years[val[0]]
				this.month = this.months[val[1]]
			},
			confirm(){
				this.data = `${this.year}-${this.month}`
				this.visible = false
				this.total = 0
				this.bill=[]
				this.currentPage = 1
				this.noMoreFlag1 = false
				this.noDataFlag1 = false
				this.loadMore = true
				if(this.type == 3){
					this.sharingCommission()
				}else{
					this.wallerMoreLog()
					this.walleGroupByLog()
				}
			},
			hide(){
				this.visible = false
			},
			showpicker(){
				this.visible = true
			}
		},
	}
</script>

<style scoped lang="less">
/* sticky 容器  */
.sticky-item {
	position: fixed;
	width: 100%;
	height: 104rpx;
	padding: 20rpx 30rpx;
	background-color: #f2f2f2;
	display: flex;
	align-items: center;
	justify-content: space-between;
	box-sizing: border-box;
}
.amount {
	text-align: right;
	color: #7A7A7A;
	font-size: 24rpx;
}

.bill {
	.moth {
		font-weight: bold;
		padding: 8rpx 30rpx;
		border-radius: 30rpx;
		text{
			margin: auto 8rpx;
		}
		.iconfont{
			font-size: 24rpx;
		}
	}
	.bill-list {
		font-size: 26rpx;
		padding: 28rpx 0;
		border-bottom: 2rpx solid #eee;
		.text-cut {
			width: 444rpx;
		}
		.time {
			color: #666;
			margin-top: 10rpx;
			font-size: 24rpx;
		}
	}
}
	.date-time-picker {
		.date-time-mask {
			position: fixed;
			top: 0;
			bottom: 0;
			left: 0;
			right: 0;
			background-color: rgba(#000000, 0.5);
			z-index: 998;
		}
		.pickerTit{
			background-color: #F1F1F1;
			position: fixed;
			height: 80rpx;
			z-index: 9999;
			bottom: 500rpx;
			width: 100%;
			padding: 0 20rpx;
		}
		.picker-view {
			position: fixed;
			height: 500rpx;
			bottom: 0;
			width: 100%;
			flex: 1;
			z-index: 9999;
			background-color: #ffffff;
			.item {
				font-size: 34rpx;
				display: flex;
				align-items: center;
				justify-content: center;
			}
		}
	}
</style>
