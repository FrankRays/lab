<%--
  Created by IntelliJ IDEA.
  User: Jimmy
  Date: 2016/7/30
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Project Upload</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/bootstrap-theme.css">
<script src="js/jquery.js"></script>
<script src="js/bootstrap.js"></script>
<%--typeahead plugin--%>
<script type="text/javascript" src="js/bootstrap3-typeahead.js"></script>

<script>
    $(function(){
        $("#add").click(function () {
            var value = $("#inp").val();
            console.log(value);

            var line2 = "<tr>";
            line2 +="<td><input type='text' name='author' value='"+value+"'readonly class='form-control'></td>" +
                    "<td><input type='checkbox' name='isCorr'></td>" +
                    "<<td><input type='checkbox' name='isExtra'></td></td>" +
                    "<td><input type='button' value='删除' class='del btn btn-default'></td>";
            line2 += "</tr>";

            $("#show2").append(line2);
        });

        $("#show2").on("click", ".del", function(){
            $(this).parent().parent().remove();
        });

        /** add a field to project */
        $("#addField").click(function(){
            var field = $("#inpField").val();
            var appendLine = "<input type='text' name='fields' value='"+field+"' readonly class='form-control del'>";
            $("#field").append(appendLine);
        });
        /** delete a field from project */
        $("#field").on("click", ".del", function(){
            $(this).remove();
        });

        /** get fields prompt info from DB*/
        $.post("/lab/comp_field", {}, function(data){
            $('#inpField').typeahead({source: data.list})
        });

        $.post("/lab/comp_user", {}, function(data){
            $('#inp').typeahead({source: data.list})
        });
    });

    var prePost = function(){
        /** add authors and relative info */
        var authors = document.getElementsByName("author");
        var isCorrs = document.getElementsByName("isCorr");
        var isExtras = document.getElementsByName("isExtra");
        var list = [];
        for (var i = 0; i < authors.length; i++){
            var author = {};

            author.isCorr = $(isCorrs[i]).is(':checked') ? 1:0;
            author.isExtra = $(isExtras[i]).is(':checked') ? 1:0;
            author.order = i+1;
            author.name = authors[i].value;
            list.push(author);
        }
        var sendInfo = {};
        sendInfo.list = list;
        /** add fields info */
        var fieldsElem = document.getElementsByName("fields");
        var fields = [];
        console.log(fields);
        for (var j = 0; j < fieldsElem.length; j++){
            fields.push(fieldsElem[j].value);
        }
        sendInfo.fields = fields;

        var str2 = JSON.stringify(sendInfo);
        console.log(str2);
        $("form").append("<input type='hidden' name='directors' value='"+str2+"'>");
        //return true;
        $("form").submit();
    }
</script>
</head>
<body>
<div class="container well" style="background: #70cca8; min-height: 300px;">

    <hr>
        <h1 class="text-center">项目登记</h1>
    <hr>
    <div class="">
        <form action="/lab/pro_addPro" method="post" enctype="multipart/form-data">
            <div class="col-md-6">
                <label for="proName">项目名称</label>
                <input type="text" name="proName" id="proName" class="form-control">
                <label for="startTime">起始时间</label>
                <input type="text" name="startTime" id="startTime" class="form-control">
                <label for="endTime">结束时间</label>
                <input type="text" name="endTime" id="endTime" class="form-control">
            </div>
            <div class="col-md-6">
                <label for="proFee">项目经费</label>
                <input type="text" name="proFee" id="proFee" class="form-control">
                <label for="proType">项目类型（国家型/企业型）</label>
                <input type="text" name="proType" id="proType" class="form-control">
                <label for="proLevel">项目级别</label>
                <input type="text" name="proLevel" id="proLevel" class="form-control">

            </div>
            <div style="clear: both;"></div>

        </form>
    </div>
    <hr>
    <div style="clear: both;"></div>
    <div class="panel" style="min-height: 400px;">
        <div class="col-md-7">
            <h4>参与人员列表(第一位为项目负责人，其余无顺序先后)</h4>
            <table id="show2" class="table table-hover">
                <thead><td>人员姓名</td><td>是否通讯作者</td><td>非本数据库用户</td><td>操作</td></thead>
            </table>
            <input type="text" id="inp" class="form-control">
            <br>
            <input type="button" value="添加参与者" id="add" class="btn btn-success">
        </div>
        <div class="col-md-1"></div>
        <div class="col-lg-4">
            <h4>论文涉及领域</h4>
            <hr>
            <div id="field"></div>
            <hr>
            <input type="text" id="inpField" class="form-control">
            <br>
            <input type="button" value="添加领域" id="addField" class="btn btn-success">
            <br>
        </div>
    </div>
    <hr>
    <button onclick="prePost()" class="btn btn-warning btn-lg">提交项目</button>
</div>

</body>
</html>