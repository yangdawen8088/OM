<template>
	<view class="setPayPw2">
		<view class="text-center mb30">{{tipsText}}</view>
		<view @tap="showM">
			<tui-keyboard-input :inputvalue="valArr"></tui-keyboard-input>
		</view>
		
		<view class="tip text-center text-sm">不要是登录密码或连续相同数字</view>
		<tui-keyboard :show="keyboardShow" :radius="true" @click="keyboardClick" @complete="handelComplete" @close="handalClose">
			<view class="tui-digital-box">
				<view class="tui-keyboard-tips">请输入密码</view>
			</view>
		</tui-keyboard>
		
		<!-- 密码设置成功弹框 -->
		<tui-modal :show="modalShow" :custom="true" class="finish-modal" @click='hindeModal' @handleClickCancel='hindeModal'>
			<text class="icon iconfont icon-cuo" @tap='hindeModal'></text>
			<view class="feedback-finish text-center">
				<image src="https://zj.gdwrh.com/fnyg/static/images/my-img/fkyj_cg_x3.png"></image>
				<view class="thanks">恭喜您！修改支付密码成功</view>
				<tui-button width="80%" type="danger" :shadow="true" margin="0 50rpx" shape="circle" :size="30" @tap="hindeModal">完成</tui-button>
			</view>
		</tui-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				modalShow:false,
				valArr :['', '', '', '', '', ''],
				keyboardShow:false,
				payPw:'', //第一次密码
				rePayPw:'', //再次密码
				tipsText: '请输入支付密码，用于支付'
			}
		},
		onLoad(op) {
			this.formdata = JSON.parse(decodeURIComponent(op.formdata))
			console.log(this.formdata)
		},
		methods:{
			showM(){
				this.keyboardShow=true
			},
			// 监听键盘数字输入
			keyboardClick(e) {
				this.valArr = e;
			},
			handalClose() {
				this.keyboardShow = false;
				this.valArr = ['', '', '', '', '', '']
			},
			// 监听键盘完成指定长度数字输入
			handelComplete(e) {
				if(!this.payPw){
					this.payPw = e.join('')
					this.tipsText = '请再次输入，以确认密码'
					return
				}else{
					this.rePayPw = e.join('')
					//  两次密码不一致
					if(this.payPw !== this.rePayPw){
						this.$tip.toast('两次输入密码不一致')
						this.handalClose()
					}else{
						// 发送请求
						this.formdata.type = 2
						this.formdata.payPwd = e.join('')
						// 请求
						this.$http.post('/api/auth/member/pwd/setPayPwd', this.formdata).then(res => {
							if(res.success){
								this.modalShow = true
								let userInfo =	this.$store.state.userInfo
								userInfo.payPwd = 2
								this.$store.commit('getUserInfo',userInfo)
							}else{
								this.$tip.toast(res.msg)
							}
						});
					}
				}
			},
			hindeModal(){
				this.Route({type:'back',delta:2,duration:1500})
			}
		}
	}
</script>

<style>
page {
	background-color: #fff;
}
</style>
<style scoped lang="scss">
.setPayPw2 {
	font-size: 30rpx;
	padding: 60rpx 28rpx 0;
}
.mb30 {
	margin-bottom: 30rpx;
}
.tip {
	margin: 20rpx 0 60rpx;
}
.tui-keyboard-tips {
	width: 100%;
	height: 120rpx;
	line-height: 1;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 30rpx;
	background: #fff;
	position: relative;
	color: #333;
	border: 0;
}
//修改成功弹出框
 .finish-modal{
	 .icon{
		position: absolute;
		right: 60rpx;
		top: 40rpx;
	 }
 }
.feedback-finish {
	padding-top: 60rpx;
	font-size: 30rpx;
}
image {
	width: 130rpx;
	height: 130rpx;
	margin-bottom: 30rpx;
}
.thanks {
	margin-bottom: 98rpx;
}
</style>
