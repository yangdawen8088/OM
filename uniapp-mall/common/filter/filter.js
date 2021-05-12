import Vue from 'vue'
import { getymd } from '@/common/js/utils/date.js'


Vue.filter('date', (timeStamp, format='Y-m-d H:i')=>{
	if(!timeStamp) return '';
	return getymd(format, timeStamp);
});

Vue.filter('friendDate', (timeStamp, format='Y-m-d')=>{
	if(!timeStamp) return '';
	return getymd(format, timeStamp);
});

Vue.filter('price', (price, fixed=0)=>{
	if(!price || isNaN(price)){
		return 0
	}
	return fixed > 0 ? parseFloat(price).toFixed(fixed) : + parseFloat(price).toFixed(2);
});