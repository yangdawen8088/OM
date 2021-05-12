$(function(){
	var show_count2 = 20;
	var count2 = 1;
	$(".addtr2").click(function () {
		var length = $(this).parent('.btn_a1').prev('.neijian').children('.tbody2 tr').length;
		if (length < show_count2) {
			var idVal = "saleAreaName" + count2;
			var str = '<tr class="alonTr2">'+
						'<td><input type="text" name="productPrice"/></td>'+
						'<td><input type="text" name="marketPrice"/></td>'+
						'<td><input type="text" name="supplyPrice"/></td>'+
						'<td><input type="text" name="productStock"/></td>'+
						'<td><input type="text" name="saleAmount"/></td>'+
						'<td><span class="u-input"><input type="text" name="saleAreaName" id="'+idVal+'"/></span></td>'+
			            '<td><a class="text_a" href="javascript:" onClick="deltr3(this)">删除</a></td></tr>';
			//$(".model2 tbody tr").clone().appendTo($(this).parent('.btn_a1').prev('.neijian').children('.tbody2'));
			$(this).parent('.btn_a1').prev('.neijian').children('.tbody2').append(str);
			zero2one.request("/remote/goodSaleArea/tree",{}, function (data) {
				$('#'+idVal).zdCascader({
					data: data.data,
					onChange: function(data1, data2,data3) {
						$("#"+idVal).val(data3[2].name + "" + data3[1].name+"" + data3[0].name)
					}
				});
			});
			count2++;
		}
	});

});

function deltr2(opp) {
	var length = $(this).parent('.btn_a1').prev('.neijian').children('.tbody2 tr').length;
	if (length <= 1) {
		alert("至少保留一行");
	} else {
		$(opp).parent().parent().remove();//移除当前行

	}
}

function deltr3(opp) {
	var length = $('.neijian .tbody2 tr').length;
	if (length <= 1) {
		alert("至少保留一行");
	} else {
		$(opp).parent().parent().remove();//移除当前行
	}
}