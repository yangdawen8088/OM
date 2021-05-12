<template>
	<view>
		<view class="item">30天无忧退货</view>
		<view class="item">48小时快速退款</view>
		<view class="item">满88元免邮费</view>
		<button @click="login">测试</button>
	</view>
</template>

<script>
	import Request from '@/common/luch-request/index.js'
	const http = new Request();
	
	export default {
		data() {
			return {
				
			};
		},
		methods:{
			login(){
				console.log("12222");
				
				this.$store.dispatch("token");
				
				http.get('http://localhost:9300/api/user/login', {params: {userName: 'name', password: '123456'}}).then(res => {
					
					console.log(res)
				    
				}).catch(err => {
				    
				})
			}
		}
	}
</script>

<style lang="scss">

</style>
