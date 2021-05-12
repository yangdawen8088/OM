<template>
	<!-- 多图上传 -->
	<!-- 引入方式<uploadimg  @myEvens="getImage"></uploadimg> -->
	<view>
		<view class="box-pic">
			<view v-for="(item,index) in imgs" :key="index" class="img-box">
				<image src="https://zj.gdwrh.com/fnyg/static/images/my-img/cuo.png" class="cuo" @tap="remove(index)"></image>
				<image class="pic" :src="item" @click="prevImg(item)"></image>
			</view>
			
			<view class="pre-btn u-fc" @tap="shang" v-if="show">
				<image src="https://zj.gdwrh.com/fnyg/static/images/my-img/ico_fkyj_x3.png"></image>
				<view>上传截图</view>
				<view>（最多{{imgCount}}张）</view>
			</view>
		</view>
	</view>
</template>

<script>
	import chooseImg from '@/common/js/upload/uploadFile.js'
	export default {
		name:'uploadImage',
		watch: {
			startList: {
				immediate: true, // 这句重要
				handler(val) {
					this.imgs = val
				}
			}
		},
		props: {
			startList: {
				type: Array
			},
			imgCount: {
				type: Number,
				default: 3
			}
		},
		data() {
			return {
				imgArr: [],
				imgs: [],
				count: 3,
				show:true,
				changeList: []
			}
		},
		methods: {
			remove(index) {
				this.imgs.splice(index,1)
				this.sendNum()
			},
			sendNum(){
				if(this.imgs.length >= 3) {
					this.show = false
				} else {
					this.count = 3 - this.imgs.length
					this.show = true
				}
				this.$emit('myEvens',this.imgs)
			},
			shang() {
				var that = this;
				chooseImg(this.count,res=>{
					that.imgs.push(res)
					that.sendNum()
				})
			},
			prevImg(current) {
				uni.previewImage({
					current,
					urls: this.imgs,
					loop: true,
					indicator: 'number'
				})
			}
		
	}}
</script>

<style scoped lang="scss">
	.box-pic{
		display: flex;
		flex-direction: row;
		flex-wrap: wrap;
	}
	.box-pic .pic{
		width: 160rpx;
		height: 160rpx;
		border-radius: 10rpx;
		margin-right: 18rpx;
		margin-bottom: 18rpx;
	}
	.pre-btn{
		font-size: 20rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		width: 160rpx;
		height: 160rpx;
		border-radius: 10rpx;
		background-color: rgba(242,34,34,0.08);
		color: #F22222;
		line-height: 32rpx;	
		margin-bottom: 20rpx;
	}
	image {
		width: 46rpx;
		height: 40rpx;
		margin-bottom: 10rpx;
	}
	.img-box {
		position: relative;
	}
	.cuo {
		width: 38rpx;
		height: 38rpx;
		position: absolute;
		top: -20rpx;
		right: 0;
		z-index: 9;
	}
</style>
