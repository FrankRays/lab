<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <%--Addv bootstrap Typeahead 输入框提示--%>
    <script type="text/javascript" src="js/bootstrap3-typeahead.js"></script>

    <%--convert to josn string--%>
    <script type="text/javascript" src="js/json2.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            /*
            var list = [];
            var cont = {};
            var conn = {};
            conn.name = "jimmy";
            conn.order = 2;
            conn.isCorr = 1;
            var conn2 = {}
            conn2.name = "jimmy";
            conn2.order = 1;
            conn2.isCorr = 1;
            list.push(conn);
            list.push(conn2);
            cont.list = list;
            var str = JSON.stringify(cont);
            console.log(str);
            $("button").click(function(){
                $.post("/lab/ajax_",
                {
                    authors: str
                },
                function(data,status){
                    alert("数据: \n" + data + "\n状态: " + status);
                });
            });
*/
            var count = 1

            $("#add").click(function () {
                var value = $("#inp").val()
                console.log(value)

                var line2 = "<tr>"
                line2 += "<td>"+count+"</td>" +
                        "<td><input type='text' name='authors' value='"+value+"'></td>" +
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

            $("#send").click(function(){
                var authors = document.getElementsByName("authors");
                var isCorrs = document.getElementsByName("isCorr");
                var isExtras = document.getElementsByName("isExtra");
                var list = [];
                for (var i = 0; i < authors.length; i++){
                    var author = {}
                    /*if ($(isCorrs[i]).is(':checked')){
                        author.isCorr = 1;
                    } else {
                        author.isCorr = 0;
                    }*/
                    author.isCorr = $(isCorrs[i]).is(':checked') ? 1:0;
                    author.isExtra = $(isExtras[i]).is(':checked') ? 1:0;
                    author.order = i+1;
                    author.name = authors[i].value;
                    list.push(author);
                }
                var sendInfo = {}
                sendInfo.list = list;


                var str2 = JSON.stringify(sendInfo)
                $.post("/lab/ajax_",
                    {
                        authors:str2
                    },
                    function(data, status){

                })
            })
            var subjects = $("#users").val();
            console.log(subjects)
            $('#inp').typeahead({source: subjects})


        });
    </script>

</head>
<body>
<h1></h1>
<h2>Hello World!</h2>

<%--<button type="button">提交</button>--%>
<div class="col-md-2 panel panel-primary">

    <input type="text" id="inp" class="">
    <input type="button" value="添加" id="add">
</div>

<div class="panel panel-danger col-md-5">
    <table id="show2" class="table table-hover">
        <thead><td>序号</td><td>作者姓名</td><td>是否通讯作者</td><td>非本数据库用户</td><td>操作</td></thead>
    </table>
</div>

<input type="button" value="提交" id="send">
<hr>
<hr>
<input type="hidden" value="${users}" id="users">
<s:debug/>
</body>


</html>
