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
    <title>jQuery UI 排序（Sortable） - 连接列表</title>
    <%--<link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/jquery-ui.theme.css">--%>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <%--<script src="js/jquery-ui.js"></script>--%>

    <!-- 引用ajaxfileupload.js -->
    <script src="js/ajaxfileupload.js"></script>
    <style>

    </style>
    <script>

        function getFileName(path){
            var pos1 = path.lastIndexOf('/');
            var pos2 = path.lastIndexOf('\\');
            var pos  = Math.max(pos1, pos2)
            if( pos<0 )
                return path;
            else
                return path.substring(pos+1);
        }
        var getName = function() {

            if (!$("#file1").val()){
                alert("请选择文件")
            } else {

                console.log($("#file1").val())
                var fileName = getFileName($("#file1").val())
                console.log(fileName)
            }
        }

        $(function(){
            //点击打开文件选择器
            $("#upload").on('click', function() {
                $('#fileToUpload').click();
            });

            //选择文件之后执行上传
            /*$('#fileToUpload').on('change', function() {


                var authors = document.getElementsByName("authors");
                var isCorrs = document.getElementsByName("isCorr");
                var isExtras = document.getElementsByName("isExtra");
                var list = [];
                for (var i = 0; i < authors.length; i++){
                    var author = {}

                    author.isCorr = $(isCorrs[i]).is(':checked') ? 1:0;
                    author.isExtra = $(isExtras[i]).is(':checked') ? 1:0;
                    author.order = i+1;
                    author.name = authors[i].value;
                    list.push(author);
                }
                var sendInfo = {}
                sendInfo.list = list;


                var str2 = JSON.stringify(sendInfo)
                console.log(str2)

                $.post({
                    url:"/lab/ajax_",
                    type:'post',
                    //secureuri:true,
                    fileElementId:"fileToUpload",//file标签的id
                    dataType: "json",//返回数据的类型
                    data:{
                        authors:str2,
                        title:"logan",
                        type :"期刊论文",
                        category:'SCI',
                        ccfStatus:'A',
                        postYear:'2016',
                        periodical:'生物自然杂志',
                        volNum:'第6卷',
                        issueNum:'第8期',
                        startPage:'27',
                        endPage:'66'

                    },//一同上传的数据
                    success: function (data, status) {
                        //把图片替换
                        /!*var obj = jQuery.parseJSON(data);
                        $("#upload").attr("src", "../image/"+obj.fileName);

                        if(typeof(data.error) != 'undefined') {
                            if(data.error != '') {
                                alert(data.error);
                            } else {
                                alert(data.msg);
                            }
                        }*!/

                        console.log(data + "," + status)

                    },
                    error: function (data, status, e) {
                        alert(e);
                    }
                });
            });*/



            var count = 1

            $("#add").click(function () {
                var value = $("#inp").val()
                console.log(value)

                var line2 = "<tr>"
                line2 += "<td>"+count+"</td>" +
                        "<td><input type='text' name='author' value='"+value+"'  readonly></td>" +
                        "<td><input type='checkbox' name='isCorr' value='"+value+"'></td>" +
                        "<<td><input type='checkbox' name='isExtra' value='"+value+"'></td></td>" +
                        "<td><input type='button' value='删除' class='del btn btn-default'></td>"
                line2 += "</tr>"

                count++;


                $("#show2").append(line2)
                //var authors = document.getElementsByName("authors")
                //alert(authors[0].value)
            })

            $("#show2").on("click", ".del", function(){
                $(this).parent().parent().remove();
                count--;
            })



        });

        var prePost = function(){
            var authors = document.getElementsByName("author");
            var isCorrs = document.getElementsByName("isCorr");
            var isExtras = document.getElementsByName("isExtra");
            var list = [];
            for (var i = 0; i < authors.length; i++){
                var author = {}

                author.isCorr = $(isCorrs[i]).is(':checked') ? 1:0;
                author.isExtra = $(isExtras[i]).is(':checked') ? 1:0;
                author.order = i+1;
                author.name = authors[i].value;
                list.push(author);
            }
            var sendInfo = {}
            sendInfo.list = list;
            var str2 = JSON.stringify(sendInfo)
            console.log(str2)
            $("form").append("<input type='hidden' name='authors' value='"+str2+"'>")
            return true;
        }
    </script>
</head>
<body>
<div class="container" style="background: #70cca8; min-height: 300px;">
    <%--<input type="file" id="file1">
    <input type="button" value="显示名字" onclick="getName()">--%>

    <%--
    <input id="upload" type="button" value="上传文件" class="btn btn-danger">
    <!-- 隐藏file标签 -->
    <input id="fileToUpload" style="display: none" type="file" name="file"><br/>
--%>
    <hr>
    <hr>
    <div class="panel">
        <form action="/lab/paperUpload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" class="form-control">
            <hr>
            <input type="submit" value="提交论文" onclick="prePost()" class="btn btn-success">
        </form>
    </div>
    <hr>
    <div class="panel">

        <input type="text" id="inp" class="form-control">
        <input type="button" value="添加作者" id="add" class="btn btn-success">
    </div>
    <hr>
    <div class="panel">
        <h4>作者列表</h4>
        <table id="show2" class="table table-hover">
            <thead><td>序号</td><td>作者姓名</td><td>是否通讯作者</td><td>非本数据库用户</td><td>操作</td></thead>
        </table>
    </div>
    <hr>
</div>


</body>
</html>