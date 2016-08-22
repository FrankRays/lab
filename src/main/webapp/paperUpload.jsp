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
    <title>发布论文登记</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap3-typeahead.js"></script>

    <script>

        function getFileName(path){
            var pos1 = path.lastIndexOf('/');
            var pos2 = path.lastIndexOf('\\');
            var pos  = Math.max(pos1, pos2);
            if( pos<0 )
                return path;
            else
                return path.substring(pos+1);
        }
        var getName = function() {

            if (!$("#file1").val()){
                alert("请选择文件")
            } else {
                console.log($("#file1").val());
                var fileName = getFileName($("#file1").val());
                console.log(fileName)
            }
        };

        $(function(){
            $("#add").click(function () {
                var value = $("#inp").val();
                console.log(value)

                var line2 = "<tr>"
                line2 +="<td><input type='text' name='author' value='"+value+"' readonly class='form-control'></td>" +
                        "<td><input type='checkbox' name='isCorr' value='"+value+"'></td>" +
                        "<<td><input type='checkbox' name='isExtra' value='"+value+"'></td></td>" +
                        "<td><input type='button' value='删除' class='del btn btn-default'></td>";
                line2 += "</tr>";

                $("#show2").append(line2);
            });

            $("#addField").click(function(){
                var field = $("#inpField").val();
                var appendLine = "<input type='text' name='fields' value='"+field+"' readonly class='form-control del'>"
                $("#field").append(appendLine);
            });

            $("#field").on("click", ".del", function(){
                $(this).remove();
            });

            $("#show2").on("click", ".del", function(){
                $(this).parent().parent().remove();
                count--;
            });

            $.post("/lab/comp_field",
                    {},
                    function(data){
                        $('#inpField').typeahead({source: data.list})
                    }
            )
        });

        var prePost = function(){
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

            var fieldsElem = document.getElementsByName("fields");
            var fields = [];
            console.log(fields);
            for (var j = 0; j < fieldsElem.length; j++){
                fields.push(fieldsElem[j].value);
            }
            sendInfo.fields = fields;
            var str2 = JSON.stringify(sendInfo);
            console.log(str2);
            $("form").append("<input type='hidden' name='authors' value='"+str2+"'>");
            //return true;
            $("form").submit();
        }
    </script>
</head>
<body>
<div class="container well" style="background: #70cca8; min-height: 300px;">
    <hr>
    <h1 class="text-center">论文登记上传</h1>
    <hr>
    <div class="">
        <form action="/lab/paper_addPaper" method="post" enctype="multipart/form-data">
            <%--<label>点击选框选择上传文件</label>--%>
            <input type="file" name="file" class="form-control">
            <hr>
            <div class="col-md-6">
                <label for="title">论文标题</label>
                <input type="text" name="title" id="title" class="form-control"><br>
                <label for="type">论文类型（会议论文/期刊论文）</label>
                <input type="text" name="type" id="type" class="form-control"><br>
                <label for="category">论文类别（SEI/EI/核心期刊/普通）</label>
                <input type="text" name="category" id="category" class="form-control"><br>
                <label for="ccfStatus">CCF等级（A/B/C/无【不填写】）</label>
                <input type="text" name="ccfStatus" id="ccfStatus" class="form-control"><br>
                <label for="postYear">发表年份</label>
                <input type="text" name="postYear" id="postYear" class="form-control"><br>
            </div>
            <div class="col-md-6">
                <label for="periodical">期刊或会议名称</label>
                <input type="text" name="periodical" id="periodical" class="form-control"><br>
                <label for="volNum">论文卷号</label>
                <input type="text" name="volNum" id="volNum" class="form-control"><br>
                <label for="issueNum">论文期号</label>
                <input type="text" name="issueNum" id="issueNum" class="form-control"><br>
                <label for="startPage">论文起始页</label>
                <input type="text" name="startPage" id="startPage" class="form-control"><br>
                <label for="endPage">论文结束页</label>
                <input type="text" name="endPage" id="endPage" class="form-control"><br>
            </div>
            <hr>
            <%--<input type="submit" value="提交论文" onclick="prePost()" class="btn btn-success">--%>
        </form>
    </div>
    <div style="clear: both;"></div>
    <div style="min-height: 400px;" class="panel">
        <div class=" col-md-7">
            <h4>作者列表(包含作者顺序)</h4>
            <table id="show2" class="table table-hover">
                <thead><td>作者姓名</td><td>是否通讯作者</td><td>非本数据库用户</td><td>操作</td></thead>
            </table>
            <input type="text" id="inp" class="form-control">
            <input type="button" value="添加作者" id="add" class="btn btn-success">
            <hr>
        </div>
        <div class="col-md-1"></div>
        <div class=" col-md-4">
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
    <button onclick="prePost()" class="btn btn-warning btn-lg">提交</button>
</div>

</body>
</html>