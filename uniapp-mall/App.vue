<script>
	import Vue from 'vue'
	export default {
		onLaunch: function() {
			console.log(process.env.NODE_ENV);
			var that = this;
			uni.getSystemInfo({
				success: function(e) {
					// 状态栏高度
					Vue.prototype.StatusBar = e.statusBarHeight;
					// #ifdef MP-WEIXIN
					let custom = wx.getMenuButtonBoundingClientRect();
					Vue.prototype.Custom = custom;
					Vue.prototype.CustomBar = custom.bottom + custom.top - e.statusBarHeight;
					// #endif
				}
			});
			console.log('App Launch'+ process.env.NODE_ENV)
		},
		onShow: function() {
			console.log('App Shows')
			// 小程序自动更新版本
			// #ifdef MP-WEIXIN
			const updateManager = uni.getUpdateManager(); // 获取更新管理器对象
			updateManager.onCheckForUpdate(function(res) {
				if (res.hasUpdate) {
					// 新版本下载完成
					updateManager.onUpdateReady(function() {
						uni.showModal({
							title: '更新提示',
							content: '检查到新版本，点击确定重新启动',
							showCancel: false,
							success: res => {
								if (res.confirm) {
									// 新版本下载完成，调用该方法会强制当前小程序应用上新版本并重启
									updateManager.applyUpdate();
								}
							}
						});
					});
					// 新版本下载失败
					updateManager.onUpdateFailed(function() {
						uni.showModal({
							title: '已经有新版本了哟~',
							content: '新版本已经上线啦~，请您删除当前小程序，重新搜索打开哟~',
							showCancel: false
						});
					});
				}
			});
			// #endif
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	@import "common/css/main.css";
	@import "common/css/base.css";
	@import "common/css/mybase.css";
	@import "common/css/omtIcons.css";
	/* 动画库css */
	@import "common/css/zwyCss.css";
	@import "common/css/animation.css";
</style>
