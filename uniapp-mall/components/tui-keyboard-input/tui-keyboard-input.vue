<template>
	<view class="tui-keyboard-input tui-pwd-box" :style="{backgroundColor:backgroundColor}">
		<view class="tui-inner-box" :class="inputtype=='code' ? 'code-box' : 'password-box'">
			<view class="tui-input" :class="[inputvalue.length===4?'tui-margin-right':'']" :style="{fontSize:size+'rpx',color:color,width:(inputvalue.length===4?90:115)+'rpx' }"
			 v-for="(item,index) in inputvalue" :key="index">{{item}}</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "tuiKeyboardInput",
		props: {
			//背景颜色
			backgroundColor: {
				type: String,
				default: "#fff"
			},
			size: {
				type: Number,
				default: 28
			},
			color: {
				type: String,
				default: "#333"
			},
			//输入框的值：数组格式，长度即为输入框个数
			inputvalue: {
				type: Array,
				default: ["", "", "", "", "", ""] //密码圆点 ●
			},
			inputtype: {
				type: String,
				default: "Password"
			}
		},
		data() {
			return {

			};
		}
	}
</script>

<style scoped lang="scss">
	.tui-pwd-box {
		display: flex;
		align-items: center;
		justify-content: center;
		box-sizing: border-box;
		vertical-align: top;
	}

	.tui-inner-box {
		display: flex;
		align-items: center;
		justify-content: center;
		border-radius: 10rpx;
	}
	.password-box{
		border: 2rpx solid $main-color;
		.tui-input {
			position: relative;
			display: flex;
			align-items: center;
			justify-content: center;
			border-right: 2rpx solid $main-color;
			height: 84rpx;
			line-height: 84rpx;
		}
	}
	.code-box .tui-input{
		position: relative;
		display: flex;
		align-items: center;
		justify-content: center;
		border-bottom: 2px solid #666666;
		margin: 0 10rpx;
		height: 84rpx;
		width: 90rpx!important;
		line-height: 84rpx;
	}
	.password-box .tui-input:last-child {
		border: none;
	}

	.tui-margin-right {
		margin-right: 30rpx;
	}

	.tui-input:last-child {
		margin-right: 0 !important;
	}
</style>


	.tui-margin-right {
		margin-right: 30rpx;
	}

	.tui-input:last-child {
		margin-right: 0 !important;
	}
</style>
