import Vue from 'vue'
import App from './App'
import store from './store'
import base from '@/common/js/api/api.config.js'
import tip from '@/common/js/utils/tip.js'
import compute from '@/common/js/utils/calculation.js'
import {http} from '@/common/js/api/api.js'
import router from  '@/common/js/utils/route.js'
import  '@/common/filter/filter.js'

Vue.config.productionTip = false;
Vue.prototype.$store = store;
Vue.prototype.$config=base;
Vue.prototype.$http = http;
// 提示語
Vue.prototype.$tip = tip;
// 計算精準度
Vue.prototype.$compute = compute;
Vue.prototype.Route = router;
App.mpType = 'app';

const app = new Vue({
    ...App
});
app.$mount();
