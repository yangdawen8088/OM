
/**
 * 算精準度
 */
export default class compute {
  
  constructor() {}
  // 減法計算精準度
  static accSub(num1, num2) {  
    let r1;
    let r2;
    try {
      r1 = num1.toString().split('.')[1].length;
    } catch (e) {
      r1 = 0;
    }
    try {
      r2 = num2.toString().split('.')[1].length;
    } catch (e) {
      r2 = 0;
    }
    const m = Math.pow(10, Math.max(r1, r2));
    const n = (r1 >= r2) ? r1 : r2;
    return (Math.round(num1 * m - num2 * m) / m).toFixed(n);
}

}
