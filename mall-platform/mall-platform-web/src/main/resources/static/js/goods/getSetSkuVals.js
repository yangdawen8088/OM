$(".getSetSkuVal").on("click", function () {
    $("tr[class*='sku_table_tr']").each(function () {
        var propids = $(this).attr("propids");
        var propvalids = $(this).attr("propvalids");
    });
});

//批量设置
$("#batchSet").click(function () {

    batchSet('product_stock', 'productStock');
    batchSet('supply_price', 'supplyPrice');
    batchSet('market_price', 'marketPrice');
    batchSet('product_price', 'productPrice');
    batchSet('product_weight', 'productWeight');
    batchSet('product_volume', 'productVolume');

});

function batchSet(name1, name2) {
    var objVal = $("input[name="+name1+"]").val();
    if(objVal.length > 0){
        for (let i = 0; i < $("input[name="+name2+"]").length; i++) {
            $("input[name="+name2+"]")[i].value = objVal;
        }
    }
}