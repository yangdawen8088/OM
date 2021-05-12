<template>
	<view class="addAddress">
		<form @submit="save">
			<view class="baseW">
				<view class="u-f u-f-ac set-li">
					<text>收货地址</text>
					<view class="tui-input" :class="chooseAddress ? '' : 'text-grey'"  @click="addrmap"><text class="icon iconfont icon-dizhi"></text>{{chooseAddress.address || '点击选择您的收货地址'}}</view>
				</view>
				<view class="u-f u-f-ac set-li">
					<text>收货人</text>
					<input type="text" placeholder="请输入收货人名称" maxlength="25" name="receiverName" placeholder-style="color:#ccc">
				</view>
				<view class="u-f u-f-ac set-li">
					<text>手机号码</text>
					<input type="number" placeholder="请输入您的手机号码"  maxlength="11" name="receivePhone" placeholder-style="color:#ccc">
				</view>
			</view>
			<view class="baseW">
				<view class="u-f-jsb u-f-ac set-li address-detail">
					<text>详细地址</text>
					<textarea placeholder="如街道、门牌号、小区、楼栋号、单元、室等" name="streetAddr" placeholder-style="color:#ccc"></textarea>
				</view>
			</view>
			<button type="primary"  form-type="submit" class="submitBtn">确定</button>
		</form>
	</view>
</template>
<script>
	import valid from '@/common/js/utils/formValidate.js';
	import {locationAuth} from '@/common/js/utils/authorize.js'
	export default {
		data() {
			return {
				chooseAddress:'',
				flag: true
			};
		},
		methods:{
			save(e){
				if (!this.flag) return this.$tip.toast('手速太快了,悠着点~');
				//获取表单数据
				let formdata = e.detail.value;
				formdata = {...formdata,...this.chooseAddress}
				let rule = [
					{ name: 'address', checkType: 'notnull', checkRule: '', errorMsg: '请选择收货地址' },
					{ name: 'receiverName', checkType: 'notnull', checkRule: '', errorMsg: '请输入收货人名称' },
					{ name: 'receivePhone', checkType: 'phoneno', checkRule: '', errorMsg: '请输入正确的手机号' },
					{ name: 'streetAddr', checkType: 'notnull', checkRule: '', errorMsg: '请输入详细收货地址' },
				];
				formdata.memberId = this.$store.state.userInfo.memberId
				formdata.nickName = this.$store.state.userInfo.nickName
				formdata.id = ''
				//表单验证
				let checkRes = valid.check(formdata, rule);
				if (checkRes) {
					this.flag = false;
					//发送请求
					this.$http.post('/api/auth/member/address/addOrEdit',formdata).then(res => {
							this.flag = true
							this.$tip.successBack(res, 1500, 1);
						}).catch(err => {
							this.flag = true
						});
				} else {
					this.$tip.toast(valid.error)
				}
			},
			addrmap(){
				let that = this
				locationAuth(function(res){
					that.chooseAddress = res
				})
			},
		}
	}
</script>

<style scoped lang="scss">
.addAddress {
	padding-top: 20rpx;
}
.baseW {
	padding: 0;
}
.address-detail {
	align-items: flex-start;
}
.tui-header {
	width: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 20rpx;
	box-sizing: border-box;
	background-color: #fff;
	border-top-left-radius: 20rpx;
	border-top-right-radius: 20rpx;
	overflow: hidden;
	position: relative;
}
.tui-icon-close {
	position: absolute;
	right: 30rpx;
	top: 50%;
	transform: translateY(-50%);
}
.text-grey{
	color:#ccc ;
}
</style>
