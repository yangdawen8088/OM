<!-- 个人信息 -->
<template>
	<view class="setting">
	  <form @submit="save">
		<view class="setting-top bg-white">
			<view class="u-f-jsb u-f-ac avatar-box" @tap="changeAvater">
				<view class="u-f-ac">
					<view class="avatar round">
						<image :src="userInfo.memberAvatar ? userInfo.memberAvatar : '../../../../static/logo.jpg'"  class="round" name="memberAvatar"></image></view>
				</view>
				<view>
					<text class="tip-text">修改头像</text>
					<text class="icon iconfont icon-right"></text>
				</view>
			</view>
		</view>
		
			<view class="radius20 bg-white padding">
				<view class="u-f-jsb u-f-ac set-li">
					<text>登录账号</text>
					<view>
						<text class="mr8">{{ userInfo.memberAccount }}</text>
					</view>
				</view>
				<view class="u-f-jsb u-f-ac set-li">
					<text class="flex1">昵称</text>
					<input type="text" align='right' placeholder="请输入昵称" maxlength="25" name="nickName" placeholder-style="color:#aaa" v-model="userInfo.nickName"/>
				</view>
				<view class="u-f-jsb u-f-ac set-li">
					<text>邮箱</text>
					<input type="text" align='right' placeholder="请输入个人邮箱" maxlength="25" name="memberEmail" placeholder-style="color:#aaa;"  v-model="userInfo.memberEmail"/>
				</view>
				<view class="u-f-jsb u-f-ac set-li">
					<text>性别</text>
					<radio-group class="radio-group" name='sex'>
						<label class="tui-radio">
							<radio value="1" color="#2ba4f7" :checked="userInfo.sex == 1"/>男
						</label>

						<label class="tui-radio">
							<radio value="2" color="#2ba4f7" :checked="userInfo.sex == 2"/>女
						</label>
					</radio-group>
				</view>
				<picker mode="date" :value="userInfo.birthday" :start="startDate" :end="endDate" name='birthday' @change="bindDateChange">
					<view class="u-f-jsb u-f-ac set-li">
						<text>生日</text>
						<view>
							<text class="tip-text" v-if="userInfo.birthday == null">请设置</text>
							<text v-else>{{ userInfo.birthday }}</text>
							<text class="icon iconfont icon-right"></text>
						</view>
					</view>
				</picker>
				<view class="u-f-jsb set-li justify-end" @tap="addrmap" name="address">
					<text class="set-tit">常住地</text>
					<view>
						<text :class="userInfo.address ? '' : 'tip-text'">{{ userInfo.address ? userInfo.address : '请设置' }}</text>
						<text class="icon iconfont icon-right"></text>
					</view>
				</view>
				<button type="primary" form-type="submit" class="submitBtn">确定</button>
			</view>
		</form>
	</view>
</template>

<script>
import chooseImg from '@/common/js/upload/uploadFile.js'
import { locationAuth } from '@/common/js/utils/authorize.js';
import { getDate } from '@/common/js/utils/date.js';
import valid from '@/common/js/utils/formValidate.js';
export default {
	data() {
		return {
			userInfo: this.$store.state.userInfo,
			startDate: getDate('start'),
			endDate: getDate('end'),
			flag: true
		};
	},
	methods: {
		// 更改头像
		changeAvater() {
			let that = this
			// 本地图片上传到阿里云
			chooseImg(1,res=>{
				that.userInfo.memberAvatar = res
			})
		},
		bindDateChange(e){
			this.userInfo.birthday = e.detail.value
		},
		// 设置居住地
		addrmap() {
			let that = this;
			locationAuth(function(res) {
				that.userInfo.address = res.address;
			});
		},
		//提交
		save(e){
			if (!this.flag) return this.$tip.toast('手速太快了,悠着点~');
			//获取表单数据
			let formdata = e.detail.value;
			formdata.memberId = this.userInfo.memberId
			formdata.memberAvatar = this.userInfo.memberAvatar
			formdata.address = this.userInfo.address
			//表单验证
			let rule = [
				{ name: 'nickName', checkType: 'notnull', checkRule: '', errorMsg: '请输入昵称' },
				{ name: 'memberEmail', checkType: 'email', checkRule: '', errorMsg: '请输入正确邮箱' }
			];
			let checkRes = valid.check(formdata, rule);
			if (checkRes) {
				this.flag = false;//防止重复提交标识
				//发送请求
				this.$http.post('/api/auth/member/info/edit',formdata).then(res => {
						this.flag = true
						this.$tip.successBack(res, 1500, 1);
						this.$store.commit('getUserInfo',res.data)
					}).catch(err => {
						this.flag = true
					});
			} else {
				this.$tip.toast(valid.error)
			}
			
		}
	}
};
</script>

<style lang="scss" scoped>
.setting-top .avatar-box {
	padding: 30rpx 28rpx;
}
.mr8 {
	margin-right: 16rpx;
}
.set-li{
	padding:28rpx 0 !important;
}
.set-li .icon {
	display: inline !important;
	width: 1em !important;
}
.set-li text {
	width: 150rpx;
}
.tip-text {
	color: #aaa;
}
.icon-right {
	margin-left: 20rpx;
}
.set-li input{
	flex: 1;
}
.set-tit{
	min-width: 4rem;
}
.address{
	align-self: flex-start;
}
</style>
