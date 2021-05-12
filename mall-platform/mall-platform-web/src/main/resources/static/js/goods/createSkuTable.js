$(function () {
    $(document).on("change", '.sku_value', function () {
        var b = true;
        var skuTypeArr = [];
        var totalRow = 1;
        $(".SKU_TYPE").each(function () {
            var skuTypeNode = $(this).children("li");
            var skuTypeObj = {};
            skuTypeObj.skuTypeTitle = $(skuTypeNode).attr("sku-type-name");
            var propid = $(skuTypeNode).attr("propid");
            skuTypeObj.skuTypeKey = propid;
            var is_required = $(skuTypeNode).attr("is_required");
            skuValueArr = [];
            var skuValNode = $(this).next();
            var skuValCheckBoxs = $(skuValNode).find("input[type='checkbox'][class*='sku_value']");
            var checkedNodeLen = 0;
            $(skuValCheckBoxs).each(function () {
                if ($(this).is(":checked")) {
                    var skuValObj = {};
                    skuValObj.skuValueTitle = $(this).val();
                    skuValObj.skuValueId = $(this).attr("propvalid");
                    skuValObj.skuPropId = $(this).attr("propid");
                    skuValueArr.push(skuValObj);
                    checkedNodeLen++;
                }
            });
            if (is_required && "1" == is_required) {
                if (checkedNodeLen <= 0) {
                    b = false;
                    return false;
                }
            }
            if (skuValueArr && skuValueArr.length > 0) {
                totalRow = totalRow * skuValueArr.length;
                skuTypeObj.skuValues = skuValueArr;
                skuTypeObj.skuValueLen = skuValueArr.length;
                skuTypeArr.push(skuTypeObj);
            }
        });
        var SKUTableDom = "";
        if (b) {
            SKUTableDom += "<table class='skuTable'><caption style=\"text-align: left;font-size: large\">商品属性：</caption><tr>";
            for (var t = 0; t < skuTypeArr.length; t++) {
                SKUTableDom += '<th>' + skuTypeArr[t].skuTypeTitle + '</th>';
            }
            SKUTableDom += '<th>商品图片</th><th>商品库存</th><th>供货价</th><th>市场售价</th><th>商品价格</th><th>商品重量(KG)</th><th>商品体积(m³)</th><th>操作</th>';
            SKUTableDom += "</tr>";
            for (var i = 0; i < totalRow; i++) {
                var currRowDoms = "";
                var rowCount = 1;
                var propvalidArr = [];
                var propIdArr = [];
                var propvalnameArr = [];
                var propNameArr = [];
                for (var j = 0; j < skuTypeArr.length; j++) {
                    var skuValues = skuTypeArr[j].skuValues;
                    var skuValueLen = skuValues.length;
                    rowCount = (rowCount * skuValueLen);
                    var anInterBankNum = (totalRow / rowCount);
                    var point = ((i / anInterBankNum) % skuValueLen);
                    propNameArr.push(skuTypeArr[j].skuTypeTitle);
                    if (0 == (i % anInterBankNum)) {
                        currRowDoms += '<td rowspan=' + anInterBankNum + '>' + skuValues[point].skuValueTitle + '</td>';
                        propvalidArr.push(skuValues[point].skuValueId);
                        propIdArr.push(skuValues[point].skuPropId);
                        propvalnameArr.push(skuValues[point].skuValueTitle);
                    } else {
                        propvalidArr.push(skuValues[parseInt(point)].skuValueId);
                        propIdArr.push(skuValues[parseInt(point)].skuPropId);
                        propvalnameArr.push(skuValues[parseInt(point)].skuValueTitle);
                    }
                }
                var propvalids = propvalidArr.toString();
                SKUTableDom += '<tr propvalids=\'' + propvalids + '\' propids=\'' + propIdArr.toString() + '\' propvalnames=\'' + propvalnameArr.join(";") + '\'  propnames=\'' + propNameArr.join(";") + '\' class="sku_table_tr">'
                    + currRowDoms + '<td id="goodsPicture" onclick="uploadPicture(this)">' +
                    '<img src="images/sc.png" placeholder="点击上传" width="30" height="30" style="cursor: pointer">' +
                    '<input type="hidden" value="" name="productPicture"></td>' +
                    '<td><input type="hidden" value="'+propvalnameArr.join("-")+'" name="skuName">' +
                    '<input type="text" name="productStock"  class="setting_sku_stock"/></td>' +
                    '<td><input type="text" name="supplyPrice"   class="setting_sku_stock"/></td>' +
                    '<td><input type="text" name="marketPrice"   class="setting_sku_stock"/></td>' +
                    '<td><input type="text" name="productPrice"  class="setting_sku_stock"/></td>' +
                    '<td><input type="text" name="productWeight" class="setting_sku_stock"/></td>' +
                    '<td><input type="text" name="productVolume" class="setting_sku_stock"/></td>' +
                    '<td><button onclick="watchPicture(this)" class="btn btn-info btn_query btn-round" type="button">查看图片</button></td>' +
                    '</tr>';
            }
            SKUTableDom += "</table>";
            $("#batchEditSkuTable").show();
        }
        $("#skuTable").html(SKUTableDom);
    });
});

var currentObj;
function uploadPicture(param) {
    $("#fileInput").click();
    currentObj = param;
}

$("#fileInput").change(function () {
    //判断是否有图片
    if($(this).val()){
        //上传图片
        $("#fileForm").ajaxSubmit(function (data) {
            $(currentObj).children("img").prop("src", data.data.imgUrl);
            $(currentObj).children("input").prop("value", data.data.imgUrl);
            $("#fileForm input[type=file]").val(null);
        })
    }
});

//查看图片
function watchPicture(param) {
    window.open().document.write("<img src="+$(param).parent("td").siblings("#goodsPicture").children("img").attr("src")+" />");
}