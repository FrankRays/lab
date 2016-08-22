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
            $.post("/lab/disp_index", {}, function(data){

                console.log(data);

                $('#wait').modal('toggle');
                
                var pros = data.rtn.pros;
                var papers = data.rtn.papers;

                for (var j = 0; j < papers.length; j++){
                    var str2 = papers[j].extraAuthor;
                    str2 = str2.replace("\\", "");

                    var json2 = JSON.parse(str2);
                    var authors2 = json2.list;
                    var fields2 = json2.fields;

                    var append2 = "<h4 class='against-left'>";
                    for (var k = 0; k < authors2.length; k++){
                        append2 += "<span>"+authors2[k].name+", </span>"
                    }
                    append2 += papers[j].title + ", ";
                    append2 += papers[j].postYear + ", ";
                    append2 += papers[j].issueNum + "(" + papers[j].volNum + "):";
                    append2 += papers[j].startPage + "-" + papers[j].endPage + "页";
                    append2 += " <a href='/lab/view?page=paperDetail&id="+papers[j].paperId+"'>查看详细</a>";
                    append2 += "</h4>";
                    for (var p = 0; p < fields2.length; p++){
                        append2 += "<button class='btn btn-sm against-left'>"+fields2[p]+" </button>"
                    }
                    append2 += "<hr>";
                    $("#papers").append(append2);
                }

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
            <a class="navbar-brand" href="/lab/">上海大学科研团队在线管理系统(SHU-RTMS)</a>
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

    <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="images/1.jpg" alt="First slide">
                <div class="carousel-caption">标题 1</div>
            </div>
            <div class="item">
                <img src="images/1.jpg" alt="Second slide">
                <div class="carousel-caption">标题 2</div>
            </div>
            <div class="item">
                <img src="images/1.jpg" alt="Third slide">
                <div class="carousel-caption">标题 3</div>
            </div>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
    <hr>
    <div class="panel" style="min-height: 300px;">
        <h3>&nbsp;&nbsp;最新发布论文</h3>
        <hr>
        <div id="papers"></div>
        <hr>
        <a href="/lab/view?page=paperList&id=1"><button class="btn btn-success against-left">More...</button></a>
    </div>

    <div class="panel" style="min-height: 300px;">
        <h3>&nbsp;&nbsp;最新登记项目</h3>
        <hr>
        <div id="pros"></div>
        <hr>
        <a href="/lab/view?page=proList&id=1"><button class="btn btn-success against-left">More...</button></a>
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
