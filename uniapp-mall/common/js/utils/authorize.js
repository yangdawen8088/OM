/**
 * 授权工具类
 */

/**
 * 获取地址授权
 * fn 回调函数
 * @param {Object} fn
 */
export function locationAuth(fn) {
	uni.showModal({
		title: '请求授权当前位置',
		content: '需要获取您的地理位置，请确认授权',
		success: (res) => {
			if (res.confirm) {
				// 获取地址接口
				uni.chooseLocation({
					type: 'wgs84',
					geocode: true,
					success: function(response) {
						fn(response);
					}
				});

			} else if (res.cancel) {
				uni.showToast({
					title: '你拒绝了授权，无法获得周边信息',
					icon: 'none',
					duration: 1000
				})
			}
		}
	})
}

