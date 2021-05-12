function bu(txtObj) {
    txtObj.value = Number(txtObj.value).toFixed(2);
}

//消除数字和小数点以外的字符，保留两位小数
function clearNoNum(obj) {
    obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数

}



//消除数字以外的字符
function zhzs(value){
    //只可输入数字
    value.value = value.value.replace(/[^\d]/g,'');
    if(''!=value.value){
        value = parseInt(value.value);
    }
    return value.value;
}
