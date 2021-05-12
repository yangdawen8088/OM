/**
 * jquery.sort.js
 * 商品发布-选择分类
 * author: 锐不可挡
 **/
//一二级分类
var province = [];
//三级分类
var city = [];
//四级分类
var district = [];
var  one,two,three,four,expressD;
var  oneid,twoid,threeid,fourid;
var  oneName,twoName,threeName,fourName;
var flag=false;
var arrow = " <font>&gt;</font> ";

/*初始化12级目录*/
function intProvince() {
    one = "";

    $.post("/remote/cate/listLevel", { }, function(data){
        var data=data.data;
        //遍历一级的节点
        $.each(data,function(index,value){
            one='<div id=ul'+index+' class="first">' +
                '<div class="h-w-p-title" onclick="showClass('+index+','+value.id+',\''+value.name+'\')" style="cursor: pointer">'+
                    '<a href="javascript:void(0)" style="color: #3eb5b8">'+value.name+'</a>' +
                '</div>' +
                '<ul style="margin: 0;position: relative;left: -10px;border: none;width: 288px;"></ul>';
                '</div>';
            $("#sort1").append(one);
            //如果一级节点有子节点就遍历
            if (data[index].children!=null){
                //遍历二级节点
                $.each(data[index].children,function(index2,value2){
                    two= '<li onClick="selectP(' + value2.id+','+value2.level+',\''+value2.name+'\');"><a href="javascript:void(0)">' + value2.name + '</a></li>';
                    $("#ul"+index + " ul").append(two);
                });
            }
        });

    }, "json");

}
intProvince();

/*选择3级目录*/
function selectP(p,pl,name) {
    three = "";
    $.post("/remote/cate/listByLevel", {"id":p,"level":pl}, function(data){
        var data=data.data;
        for (var j=0; j<data.length; j++) {
            three+= '<li onClick="selectC(' + data[j].id + ',' + data[j].level + ',\''+data[j].name+'\');"><a href="javascript:void(0)">' + data[j].name + '</a></li>';
        }
        $("#sort2").append(three);
    }, "json");
    flag=false;
    twoid=p;
    twoName=name;
    expressE = oneName+"_"+twoName;
    $("#selectedSort").html(expressE);
    $("#sort2").html(three).show();
    $("#sort3").hide();
}

/*选择4级目录*/
function selectC(p,c,name) {
    four="";
    $.post("/remote/cate/listByLevel", {"id":p,"level":c}, function(data){
        var data=data.data;
        for (var l=0; l<data.length; l++) {
            four+= '<li onClick="selectD(' + data[l].id + ',' + data[l].level + ',\''+data[l].name+'\');"><a href="javascript:void(0)">' + data[l].name + '</a></li>';
        }
        $("#sort3").append(four);
        //如果没有四级
        if(data.length==0){
            classId = oneid+"_"+twoid+"_"+threeid+"_"+threeid;
            $("#selectedSort").val(classId);
            flag=true;
        }else {
            classId = oneid+"_"+twoid+"_"+threeid;
            $("#selectedSort").val(classId);
            flag=false;
        }
    }, "json");
    threeid=p;
    threeName=name;
    //获取名字
    expressE = oneName+"_"+twoName+"_"+threeName;
    $("#productTypeName").val(expressE);
    //获取id

    //显示页面
    $("#selectedSort").html(expressE);
    $("#sort3").html(four).show();
}

/*合并目录*/
function selectD(p,c,name) {
    flag=true;
    classId = oneid+"_"+twoid+"_"+threeid+"_"+p;
    $("#selectedSort").val(classId);
    expressE = oneName+"_"+twoName+"_"+threeName+"_"+name;
    $("#selectedSort").html(expressE);
    $("#productTypeName").val(expressE);

}

function showClass(e,id,name) {
    $('.first ul').hide();
    $('#ul'+e +' ul').show();
    $("#sort2").hide();
    oneid=id;
    oneName=name;
    flag=false;
    $("#selectedSort").html(name);

}
/*
/!*点击下一步*!/
$("#releaseBtn").click(function() {
    var releaseS = $(this).prop("disabled");
    if (releaseS == false) {//未被禁用
        //location.href = "商品发布-详细信息.html";//跳转到下一页
    }
});
*/
