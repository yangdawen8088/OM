<!-- 地址管理 -->
<template>
	<view class="address">
		<view class="address-list" v-for="(item,index) in addressList">
			<view class=" u-f-jsb u-f-ac">
				<text class="icon iconfont icon--"></text>
				<view class="list-left">
					<view class="u-f-ac">
						<text class="name">{{item.receiverName}}</text>
						<text class="text-gray">{{item.receivePhone}}</text>
					</view>
					<view class="address-detail">{{item.address}} {{item.streetAddr}}</view>
				</view>
				<view @tap.stop="Route({url:'editAddress',params:item})" class="edit-btn">编辑</view>
				
			</view>
			<view class="u-f-jsb text-sm padding-top margin-top address-button">
				<view class="u-f" @tap.stop='setDefault(item.id)'>
					<view class="choose-box round u-f-ac" :class="item.isDefault==1 ? 'active':''">
						<text class="icon iconfont icon-dui"></text>
					</view>设为默认地址
				</view>
				<view @tap.stop="deleteAdree(item.id)">删除</view>
			</view>
		</view>
		<tui-no-data imgUrl="https://zj.gdwrh.com/fnyg/static/images/my-img/img_nodata.png" v-if="noDataFlag">暂无收货地址</tui-no-data>
		<tui-button class="btn-fixed" width="650rpx" type="primary" :shadow="true" shape="circle" :size="30" @click="Route({url:'addAddress'})">添加新地址</tui-button>
		<tui-modal :show="modal" @click="handleClick" @cancel="hide" title="提示" content="确定要删除该地址吗？"></tui-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				flag: true,
				addressList: [],		//地址列表
				total:0,
				noDataFlag:false,
				currentPage:1,
				modal:false,
				addressId: ''		//地址id
			};
		},
		onShow() {
			this.addressList = []
			this.getAddressList()
		},
		methods:{
			getAddressList(){
				if(!this.flag) return
				let formdata = {
					pageSize:15,
					currentPage:this.currentPage,
					memberId: this.$store.state.userInfo.memberId
				}
				this.$http.post('/api/auth/member/address/list', formdata).then(res => {
					this.flag = true
					if(!res.success) return this.$tip.toast(res.msg);
					if(res.data.total == 0) return this.noDataFlag = true
					this.total = res.data.total
					this.addressList = [...this.addressList,...res.data.rows]
				})
			},
			// 删除地址
			deleteAdree(id){
				this.modal = true
				this.addressId = id
			},
			hide(){
				this.modal = false
			},
			handleClick(e){
				this.hide()
				if(e.index==1){
					this.$http.post('/api/auth/member/address/delete', {id:this.addressId}).then(res => {
						this.clear(res)
					})
				}
			},
			// 设置默认地址
			setDefault(id){
				let op = {id,memberId:this.$store.state.userInfo.memberId}
				this.$http.post('/api/auth/member/address/setDefault', op).then(res => {
					this.clear(res)
				})
			},
			clear(res){
				if(!res.success) return this.$tip.toast(res.msg)
				this.flag = false
				uni.showLoading({
					title:'正在加载'
				})
				this.addressList = []
				this.total = 0
				this.currentPage = 1
				setTimeout(()=>{
					this.getAddressList()
					uni.hideLoading()
				},500)
			},
		},
		onReachBottom() {
			if(this.total == this.addressList.length) return
			this.currentPage++
			this.getAddressList()
		}
	}
</script>

<style scoped lang="scss">
	.address-list{
		.icon--{
			font-size: 74rpx;
			margin-right: 20rpx;
			background-image: linear-gradient(129deg, #007aff 0%, #2ba4f7 100%);
			-webkit-background-clip:text;
			-webkit-text-fill-color:transparent; 
		}
	}
	.address-button{
		border-top: 1rpx #f2f2f2 solid;
	}
	.choose-box {
		width: 35rpx;
		height: 35rpx;
		margin-right: 18rpx;
		border: 1rpx $uni-border-color solid;
		box-sizing: border-box;
		justify-content: center;
		.icon{
			font-size: 12px;
			color: #fff;
		}
	}
	.active{
		background-color: $main-color;
	}
</style>
