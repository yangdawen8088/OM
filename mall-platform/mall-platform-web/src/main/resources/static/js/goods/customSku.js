var customPropId = -1;
var customPropValId = -1;
$(function () {
    $(document).on("click", ".cloneSku", function () {
        var cloneSource = $("#skuCloneModel");
        var cloneNode = cloneSource.clone(true);
        cloneNode.css("display", "block");
        cloneNode.removeAttr("id");
        customPropId--;
        $(cloneNode).find(".cusSkuTypeInput").parent().attr("propid", customPropId);
        $(cloneNode).find(".cusSkuValInput").prev().attr("propid", customPropId);
        customPropValId--;
        $(cloneNode).find(".cusSkuValInput").prev().attr("propvalid", customPropValId);
        $(this).before(cloneNode);
    });
    $(document).on("click", ".cloneSkuVal", function () {
        var cloneSource = $("#onlySkuValCloneModel");
        var cloneNode = cloneSource.clone(true);
        cloneNode.removeAttr("id");
        var propid = $(this).parents("ul").prev().find("li").attr("propid");
        $(cloneNode).find(".cusSkuValInput").prev().attr("propid", propid);
        customPropValId--;
        $(cloneNode).find(".cusSkuValInput").prev().attr("propvalid", customPropValId);
        cloneNode.css("display", "block");
        $(this).before(cloneNode);
    });
    $(document).on("change", ".cusSkuTypeInput", function () {
        var isHaveThisSkuType = false;
        var customSkuTypeName = $(this).val();
        if (customSkuTypeName) {
            $("ul[class*='SKU_TYPE']").find("li").each(function () {
                var currSkuTypeName = $(this).attr("sku-type-name");
                if (currSkuTypeName == customSkuTypeName) {
                    isHaveThisSkuType = true;
                }
            });
        }
        if (isHaveThisSkuType) {
            layer.alert("该SKU类型已经存在!");
            $(this).val("");
        }
        $(this).parent().attr("sku-type-name", $(this).val());
        if (!$(this).val() || isHaveThisSkuType) {
            $(this).parent().parent().next().find("input[type='checkbox'][class*='sku_value']").each(function () {
                $(this).attr("checked", false);
                $(this).removeClass("sku_value");
            });
        }
        $(".model_sku_val").trigger("change");
    });
    $(document).on("change", ".cusSkuValInput", function () {
        var isHaveSkuVal = false;
        var thisSkuVal = $(this).val();
        $(".model_sku_val,.sku_value").each(function () {
            var customSkuVal = $(this).val();
            if (thisSkuVal == customSkuVal) {
                isHaveSkuVal = true;
            }
        });
        if (isHaveSkuVal) {
            layer.alert("该SKU类型已经存在!");
            $(this).val("");
        }
        $("input[type*='checkbox'][class*='']");
        var cusSkuVal = $(this).val();
        if (!cusSkuVal || isHaveSkuVal) {
            $(this).prev().removeClass("sku_value");
            if ($(this).prev().is(":checked")) {
                $(this).prev().attr("checked", false);
            }
        }
        $(this).prev().val(cusSkuVal);
        $(".model_sku_val").trigger("change");
    });
    $(document).on("change", ".model_sku_val", function () {
        var skuTypeVal = $(this).parent().parent().prev().find("li").attr("sku-type-name");
        if (skuTypeVal && $(this).val()) {
            $(this).addClass("sku_value");
        }
        $("input[type='checkbox']").first().trigger("change");
    });
    $(document).on("click", ".delCusSkuType", function () {
        $(this).parent().parent().parent().remove();
        $("input[type='checkbox']").first().trigger("change");
    });
    $(document).on("click", ".delCusSkuVal", function () {
        $(this).parent().remove();
        $("input[type='checkbox']").first().trigger("change");
    });

    init();
});

//编辑时再调用,初始化商品sku
function init() {
    var skuTypeVal = $(this).parent().parent().prev().find("li").attr("sku-type-name");
    if (skuTypeVal && $(this).val()) {
        $(this).addClass("sku_value");
    }
    $("input[type='checkbox']").first().trigger("change");
}

//初始化sku
function cloneSku(last, start, arr){
    var cloneSource = $("#skuCloneModel");
    var cloneNode = cloneSource.clone(true);
    cloneNode.css("display", "block");
    cloneNode.removeAttr("id");
    customPropId--;
    $(cloneNode).find(".cusSkuTypeInput").parent().attr("propid", customPropId);
    $(cloneNode).find(".cusSkuValInput").prev().attr("propid", customPropId);
    $(cloneNode).find(".cusSkuValInput").prev().attr("value", start);
    $(cloneNode).find(".cusSkuValInput").attr("value", start);
    $(cloneNode).find(".cusSkuValInput").prev().attr("class", "model_sku_val sku_value");
    $(cloneNode).find(".cusSkuValInput").prev().attr("checked", true);
    customPropValId--;
    $(cloneNode).find(".cusSkuValInput").prev().attr("propvalid", customPropValId);
    $(cloneNode).find(".cusSkuTypeInput").parent().attr("sku-type-name", last);
    $(cloneNode).find(".cusSkuTypeInput").attr("value", last);
    $(".cloneSku").before(cloneNode);
    $.each(arr, function (i, v) {
       cloneSkuVal(cloneNode, v, customPropId);
    })
}

//初始化sku属性值
function cloneSkuVal(node, v, customPropId) {
    var obj = $(node).find(".cloneSkuVal");
    var cloneSource = $("#onlySkuValCloneModel");
    var cloneNode = cloneSource.clone(true);
    cloneNode.removeAttr("id");
    $(cloneNode).find(".cusSkuValInput").prev().attr("propid", customPropId);
    $(cloneNode).find(".cusSkuValInput").prev().attr("class", "model_sku_val sku_value");
    $(cloneNode).find(".cusSkuValInput").prev().attr("checked", true);
    $(cloneNode).find(".cusSkuValInput").prev().attr("value", v);
    $(cloneNode).find(".cusSkuValInput").attr("value", v);
    customPropValId--;
    $(cloneNode).find(".cusSkuValInput").prev().attr("propvalid", customPropValId);
    cloneNode.css("display", "block");
    $(obj).before(cloneNode);
}

//初始化sku列表
function initDataGrid(data) {
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
                '<img src="'+data.data[i].product_picture+'" placeholder="点击上传" width="30" height="30" style="cursor: pointer">' +
                '<input type="hidden" name="productPicture" value="'+data.data[i].product_picture+'"></td>' +
                '<td>' +
                '<input type="hidden" value="'+propvalnameArr.join("-")+'" name="skuName">' +
                '<input type="hidden" value="'+data.data[i].id+'" name="skuId">' +
                '<input type="text" name="productStock"  class="setting_sku_stock" value="'+data.data[i].product_stock+'"/></td>' +
                '<td><input type="text" name="supplyPrice"   class="setting_sku_stock" value="'+data.data[i].supply_price+'"/></td>' +
                '<td><input type="text" name="marketPrice"   class="setting_sku_stock" value="'+data.data[i].market_price+'"/></td>' +
                '<td><input type="text" name="productPrice"  class="setting_sku_stock" value="'+data.data[i].product_price+'"/></td>' +
                '<td><input type="text" name="productWeight" class="setting_sku_stock" value="'+data.data[i].product_weight+'"/></td>' +
                '<td><input type="text" name="productVolume" class="setting_sku_stock" value="'+data.data[i].product_volume+'"/></td>' +
                '<td><button onclick="watchPicture(this)" class="btn btn-info btn_query btn-round" type="button">查看图片</button></td>' +
                '</tr>';
        }
        SKUTableDom += "</table>";
        $("#batchEditSkuTable").show();
    }
    $("#skuTable").html(SKUTableDom);
}