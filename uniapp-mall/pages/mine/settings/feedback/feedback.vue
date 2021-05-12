<!-- 意见反馈 -->
<template>
	<view class="xpadding">
		<view class="baseW feedback-text">
			<textarea maxlength="300" class="comment-info" placeholder="请填写反馈内容..." v-model="feedBack" @input="conInput" />
			<view class="text-num">{{num}} / 300</view>
		</view>
		<view class="baseW feedback-img">
			<view class="pre-title u-f-ac">
				<view>上传截图</view>
				<view>（最多上传3张）</view>
			</view>
			<view>
				<upload-image @myEvens="getImages" :startList="listImg"></upload-image>
			</view>
		</view>
		<button type="primary" form-type="submit" class="submitBtn" @click="save">确定</button>
		<tui-modal :show="modalShow" :custom="true" class="finish-modal">
			<text class="icon iconfont icon-cuo" @click='hindeModal'></text>
			<view class="feedback-finish text-center">
				<image src="https://zj.gdwrh.com/fnyg/static/images/my-img/fkyj_cg_x3.png"></image>
				<view class="thanks">感谢您的反馈，我们会尽快处理</view>
				<tui-button width="80%" type="danger" :shadow="true" margin="0 50rpx" shape="circle" :size="30" @click="hindeModal">完成</tui-button>
			</view>
		</tui-modal>
	</view>
</template>

<script>
	import uploadImage from '@/components/upload-image/upload-image.vue'
	import valid from '@/common/js/utils/formValidate.js';
	export default {
		components: {
			uploadImage
		},
		data() {
			return {
				modalShow: false, //反馈成功后展示
				num: 0, //记录字数
				flag: true,
				feedBack: '',
				listImg: ''
			}
		},
		methods: {
			//字数变动
			conInput() {
				this.num = this.feedBack.trim().length
			},
			//选取图片
			getImages(imgs) {
				this.listImg = imgs
			},
			//提交
			save(e) {
				if (!this.flag) return this.$tip.toast('手速太快了,悠着点~');
				//获取数据
				let formdata = {
					feedBack: this.feedBack,
					memberId: this.$store.state.userInfo.memberId,
					memberName: this.$store.state.userInfo.nickName,
					photoUrl: this.listImg.toString()
				}
				//表单验证
				let rule = [{
					name: 'feedBack',
					checkType: 'notnull',
					checkRule: '',
					errorMsg: '请输入反馈内容'
				}];
				let checkRes = valid.check(formdata, rule);
				if (checkRes) {
					this.flag = false;
					//发送请求
					this.$http.post('/api/auth/member/feedBack/add', formdata).then(res => {
						this.flag = true
						this.modalShow = true
					}).catch(err => {
						this.flag = true
					});
				} else {
					this.$tip.toast(valid.error)
				}
			},
			//提交完成后的弹出框处理
			hindeModal() {
				this.modalShow = false
				this.Route({type: 'back'})
			}
		},


	}
</script>

<style scoped lang="scss">
	.feedback-text {
		padding-top: 20rpx;
		padding-bottom: 20rpx;
	}

	.comment-info {
		font-size: 24rpx;
		width: 100%;
		height: 320rpx;
	}

	.text-num {
		text-align: right;
		color: #ccc;
		font-size: 24rpx;
	}

	.feedback-img {
		padding-bottom: 20rpx;
	}

	.pre-title {
		padding: 30upx 0;
	}

	.pre-title>view:last-child {
		font-size: 24rpx;
		color: #ccc;
	}

	//意见反馈完成弹出框
	.finish-modal {
		.icon {
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
