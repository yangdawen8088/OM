/**
 * 格式化时间戳 Y-m-d H:i:s
 * @param {String} format Y-m-d H:i:s
 * @param {Number} timestamp 时间戳   
 * @return {String}
 */

export const date = (format, timeStamp) => {
	if ('' + timeStamp.length <= 10) {
		timeStamp = +timeStamp * 1000;
	} else {
		timeStamp = +timeStamp;
	}
	
	let _date = new Date(timeStamp),
		Y = _date.getFullYear(),
		m = ( _date.getMonth() + 1).padStart(2,'0'),
		d =( _date.getDate() + 1).padStart(2,'0'),
		H = ( _date.getHours() + 1).padStart(2,'0'),
		i = ( _date.getMinutes() + 1).padStart(2,'0'),
		s = ( _date.getSeconds() + 1).padStart(2,'0');
	return format.replace(/[YmdHis]/g, key => {
		return {Y,m,d,H,i,s} [key];
	});
};

/* 
 * 获取当前日期
 * 
 */
export function getDate(type) {
	const date = new Date();
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	if (type === 'start') {
		year = year - 60;
	} else if (type === 'end') {
		year = year + 2;
	}
	month = month > 9 ? month : '0' + month;;
	day = day > 9 ? day : '0' + day;
	return `${year}-${month}-${day}`;
}


export const getymd = (format, timeStamp) => {
	var data = timeStamp.substr(0, 19);
	var dd = new Date(data.replace(/T/g, ' ').replace(/-/g, '/'));
	var dt=new Date(dd.getTime()+(8 * 60 * 60 * 1000))
	let _date= new Date(dt)
	 let	Y = _date.getFullYear(),
		m = ( _date.getMonth() + 1),
		d =( _date.getDate() + 1),
		H = ( _date.getHours() + 1),
		i = ( _date.getMinutes() + 1),
		s = ( _date.getSeconds() + 1);
	return format.replace(/[YmdHis]/g, key => {
		return {Y,m,d,H,i,s} [key];
	});
};