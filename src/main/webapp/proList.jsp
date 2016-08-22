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
    <style type="text/css">
        span{
            margin:0 5px 0 0;
        }
        .divide{
            margin: 3px;
        }
        .against-left{
            margin-left: 3px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            var page = $("#pid").text();
            $.post("/lab/disp_proList", {"page":page}, function(data){

                $('#wait').modal('toggle');
                console.log(data);
                var pros = data.rtn.pros;
                var papers = data.rtn.papers;
                console.log(pros);
                for(var i = 0; i < pros.length; i++){
                    var str = pros[i].extraDirectors;
                    str = str.replace("\\", "");
                    var json = JSON.parse(str);
                    var authors = json.list;
                    var fields = json.fields;

                    var append = "<h4 class='against-left'>";
                    for (var m = 0; m < authors.length; m++){
                        append += "<spaan>"+authors[m].name+", </span>";
                    }
                    append += pros[i].proName + ", ";
                    append += pros[i].startDate.substring(0,10) + " ~ " + pros[i].endDate.substring(0,10);
                    append += " <a href='/lab/view?page=proDetail&id="+pros[i].proId+"'>查看详细</a>";
                    append += "</h4>";
                    for (var n = 0; n < fields.length; n++){
                        append += "<button class='btn btn-sm against-left'>"+fields[n]+", </button>"
                    }
                    append += "<hr>";

                    $("#pros").append(append);
                }
            });

            $('#wait').modal('toggle');

            $.post("log_currentUser", {}, function (data) {
                var username = data.username;
                username = username.replace("\\", "");
                $("#username").html(username);
            });

            $("body").on("click", "#showLogin", function(){
                $("#login").modal('toggle');
            });

            $("#submit").click(function(){
                var username = document.getElementsByName("username")[0].value;
                var password = document.getElementsByName("password")[0].value;
                console.log(username + "--" + password);
                $.post("/lab/log_login", {"username":username, "password":password}, function(data){
                    if (data.status) {
                        var username = data.username;
                        username = username.replace("\\", "");
                        $("#username").html(username);
                    } else {
                        alert("用户名或密码错误，请重新登录");
                    }
                    $("#login").modal('toggle');
                });
            });
        });
    </script>

</head>
<body>
<div class="container well">

    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">上海大学科研团队在线管理系统(SHU-RTMS)</a>
        </div>
        <div>
            <ul class="nav navbar-nav pull-right" style="margin-left: 20px;" id="username">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        UserName
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">个人中心</a></li>
                        <li class="divider"></li>
                        <li><a href="#">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>

    <hr>
    <div id="pid" class="hidden">${id}</div>

    <div class="panel" style="min-height: 300px;">
        <h3>&nbsp;&nbsp;最新登记项目</h3>
        <hr>
        <div id="pros"></div>
        <hr>
        <%--<button class="btn btn-success against-left">More...</button>--%>
        <div class="text-center">
            <ul class="pagination">
                <li class="disabled"><a href="#">&laquo;</a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </div>
    </div>

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="wait" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 435px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        数据正在加载中...
                    </h4>
                </div>
                <div class="modal-body">
                    <img src="images/loading.gif" alt="loading">
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal dialog-->
    </div><!-- /.modal-->

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="login" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 435px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close"
                            data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="">
                        用户登录
                    </h4>
                </div>
                <div class="modal-body">
                    <hr>
                    <label>用户名</label>
                    <input type="text" name="username" class="form-control"><br>
                    <label for="password">密码</label>
                    <input type="password" name="password" id="password" class="form-control"><br>
                    <hr>
                    <button type="button" class="btn btn-success" id="submit">登录</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal dialog-->
    </div><!-- /.modal-->
</div>
<hr>
<hr>
<s:debug/>
</body>


</html>
