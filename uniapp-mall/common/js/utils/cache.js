/**  
 * 缓存数据优化  
 * import cache from '@/common/js/utils/cache'  
 * 使用方法 【  
 *     一、设置缓存  
 *         string    cache.put('k', 'string你好啊');  
 *         json      cache.put('k', { "b": "3" }, 2);  
 *         array     cache.put('k', [1, 2, 3]);  
 *         boolean   cache.put('k', true);  
 *     二、读取缓存  
 *         默认值    cache.get('k')  
 *         string    cache.get('k', '你好')  
 *         json      cache.get('k', { "a": "1" })  
 *     三、移除/清理    
 *         移除: cache.remove('k');  
 *         清理：cache.clear();   
 * 】  
 * @type {String}  
 */  
export default class cache {
  
  constructor() {
	  // 缓存前缀 
	  // 这里在构造函数中进行this绑定
	  this.postfix = '_om'
  }
/**  
 * 设置缓存   
 * @param  {[type]} k [键名]  
 * @param  {[type]} v [键值]  
 * @param  {[type]} t [时间、单位秒]  
 */  
static put(k, v, t) {  
    uni.setStorageSync(k, v);
    let seconds = parseInt(t);  
    if (seconds > 0) {  
        let timestamp = Date.parse(new Date());  
        timestamp = timestamp / 1000 + seconds;  
        uni.setStorageSync(k + this.postfix, timestamp + "")  
    } else {  
        uni.removeStorageSync(k + this.postfix)  
    }  
}  

/**  
 * 获取缓存   
 * @param  {[type]} k   [键名]  
 * @param  {[type]} def [获取为空时默认]  
 */  
static get(k, def) {  
    let deadtime = parseInt(uni.getStorageSync(k + this.postfix));
    if (deadtime) {  
        if (parseInt(deadtime) < Date.parse(new Date()) / 1000) {  
            if (def) {  
                return def;  
            } else {  
                return false;  
            }  
        }  
    }  
    let res = uni.getStorageSync(k);  
    if (res) {  
        return res;  
    } else {  
        if (def == undefined  || def == "") {  
            def = false;   
        }  
        return def;  
    }  
}  

/**
 * 根据key删除缓存
 * @param {Object} k [键名]  
 */
static remove(k) {  
    uni.removeStorageSync(k);  
    uni.removeStorageSync(k + this.postfix);  
}  

}