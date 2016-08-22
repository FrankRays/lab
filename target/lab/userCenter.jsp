<%--
  Created by IntelliJ IDEA.
  User: Jimmy
  Date: 2016/8/19
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Center</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <%--typeahead plugin--%>
    <script type="text/javascript" src="js/bootstrap3-typeahead.js"></script>
    <%-- parseJSON plugin --%>
    <script type="text/javascript" src="js/json2.js"></script>

    <style type="text/css">
        .del:hover{
            background: #ffffff;
        }
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

            $.post("/lab/comp_group",
                    {},
                    function (data){
                        console.log(data.list);
                        $('#group').typeahead({source: data.list})
                    });

            $.post("/lab/comp_field",
                    {},
                    function (data2){
                        console.log(data2.list);
                        $('#field').typeahead({source: data2.list})
                    });

            $("#addField").click(function(){
                var val = $("#field").val();
                var appe = "<input type='text' name='fieldUsers' readonly='readonly' class='del form-control' style='text-align: center;' value='"+val+"'><br>";
                $("#displayField").append(appe)
            });

            $.post("log_currentUser", {}, function (data) {
                var username = data.username;
                username = username.replace("\\", "");
                $("#username").html(username);
            });

            $("#addGroup").click(function(){
                var val = $("#group").val();
                var appe2 = "<input type='text' name='groupUsers' readonly='readonly' class='del form-control' style='text-align: center;' value='"+val+"'><br>";
                $("#displayGroup").append(appe2);
            });

            $("body").on("click", ".del", function(){
                $(this).remove();
            });

            $("#submit").click(function(){
                var groupElem = document.getElementsByName("groups");
                var fieldsElem = document.getElementsByName("fields");

                var groups = [];
                var fields = [];
                for (var i = 0; i < groupElem.length; i++){
                    groups.push(groupElem[i].value)
                }
                for (var j = 0; j < fieldsElem.length; j++){
                    fields.push(fieldsElem[j].value)
                }
                //var groups = $("[name='groups']").val();
                //var fields = $("*[name='fields']").val();
                var username = document.getElementsByName("username");
                var password = document.getElementsByName("password");
                var rePassword = document.getElementsByName("rePassword");
                var engName = document.getElementsByName("engName");
                var userType = document.getElementsByName("userType");
                var userIntro = $("#userIntro").attr("value");
                var sendObj = {}
                sendObj.groups = groups;
                sendObj.fields = fields;
                sendObj.username = username[0].value;
                sendObj.password = password[0].value;
                sendObj.rePassword = rePassword[0].value;
                sendObj.engName = engName[0].value;
                sendObj.userType = userType[0].value;
                sendObj.userIntro = userIntro;
                console.log(JSON.stringify(sendObj));
                $.post("")
            });

            $('#wait').modal('toggle');

            $.post("/lab/disp_user", {}, function(data){
                $('#wait').modal('toggle');

                $("[name='username']").val(data.rtn.user.username);
                $("[name='password']").val(data.rtn.user.password);
                $("[name='rePassword']").val(data.rtn.user.password);
                $("[name='engName']").val(data.rtn.user.engName);
                $("[name='userType']").val(data.rtn.user.userType);
                $("[name='userIntro']").val(data.rtn.user.userIntro);
                $("form").append("<input type='hidden' name='userId' value='"+data.rtn.user.userId+"'>");
                var fields = data.rtn.fields;
                var pros = data.rtn.pros;
                var papers = data.rtn.papers;
                var groups = data.rtn.groups;
                /** show fields */
                var append = "";
                for (var i = 0; i < fields.length; i++){
                    append += "<input type='text' name='fieldUsers' readonly='readonly' class='del form-control' style='text-align: center;' value='"+fields[i]+"'><br>";
                }
                $("#displayField").append(append);

                /** show groups */
                var append2 = "";
                for (j = 0; j < groups.length; j++){
                    append2 += "<input type='text' name='groupUsers' readonly='readonly' class='del form-control' style='text-align: center;' value='"+groups[j]+"'><br>";
                }
                $("#displayGroup").append(append2);

                /** show papers */
                for (var j = 0; j < papers.length; j++){
                    var str2 = papers[j].extraAuthor;
                    str2 = str2.replace("\\", "");

                    var json2 = JSON.parse(str2);
                    var authors2 = json2.list;
                    var fields2 = json2.fields;

                    append2 = "<h4 class='against-left'>";
                    for (var k = 0; k < authors2.length; k++){
                        append2 += "<span>"+authors2[k].name+", </span>"
                    }
                    append2 += papers[j].title + ", ";
                    append2 += papers[j].postYear + ", ";
                    append2 += papers[j].issueNum + "(" + papers[j].volNum + "):";
                    append2 += papers[j].startPage + "-" + papers[j].endPage + "页";
                    append2 += "</h4>";
                    for (var p = 0; p < fields2.length; p++){
                        append2 += "<button class='btn btn-sm against-left'>"+fields2[p]+" </button>"
                    }
                    append2 += "<hr>";
                    $("#papers").append(append2);
                }

                for(i = 0; i < pros.length; i++){
                    var str = pros[i].extraDirectors;
                    str = str.replace("\\", "");
                    var json = JSON.parse(str);
                    var authors = json.list;
                    fields = json.fields;

                    append = "<h4 class='against-left'>";
                    for (var m = 0; m < authors.length; m++){
                        append += "<spaan>"+authors[m].name+", </span>";
                    }
                    append += pros[i].proName + ", ";
                    append += pros[i].startDate.substring(0,10) + "~" + pros[i].endDate.substring(0,10);
                    append += "</h4>";
                    for (var n = 0; n < fields.length; n++){
                        append += "<button class='btn btn-sm against-left'>"+fields[n]+", </button>"
                    }
                    append += "<hr>";

                    $("#pros").append(append);
                }

            })
        })

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

    <h1>用户中心</h1>
    <hr>
    <h3>用户资料</h3>

    <form action="/lab/user_addUser" method="post">
        <div class="col-md-3 panel">
            <label for="username">用户名</label>
            <input type="text" name="username" id="usernam" class="form-control"><br>
            <label for="password">登录密码</label>
            <input type="password" name="password" id="password" class="form-control"><br>
            <label for="rePassword">再次输入密码</label>
            <input type="password" name="rePassword" id="rePassword" class="form-control"><br>
            <label for="engName">英文名</label>
            <input type="text" name="engName" id="engName" class="form-control"><br>
            <label for="userType">用户类型（本科生、研究生、助教、教授）</label>
            <input type="text" name="userType" id="userType" class="form-control">
            <br>
            <button class="btn btn-success" id="submit">提交</button>
        </div>

        <div class="col-md-3 panel">
            <label for="field">研究领域</label>
            <input type="text" id="field" class="form-control">
            <br>
            <input type="button" id="addField" value="添加研究领域" class="btn btn-success">
            <hr>
            <div id="displayField"></div>
        </div>

        <div class="col-md-3 panel">
            <label for="group">所在科研小组</label>
            <input type="text" id="group" class="form-control"  data-provide="typeahead">
            <br>
            <input type="button" id="addGroup" value="添加所在科研小组" class="btn btn-success">
            <hr>
            <div id="displayGroup"></div>
        </div>

        <div class="col-md-3 panel">
            <label for="userIntro">个人介绍信息</label>
            <br><br>
            <textarea name="userIntro" id="userIntro" cols="30" rows="10" placeholder="输入个人介绍"></textarea>
        </div>
    </form>
</div>

<div style="clear: both;"></div>
<div class="well container">
    <div class="panel">
        <h3>参与的论文</h3>
        <div id="papers"></div>
    </div>
    <div class="panel">
        <h3>参与的项目</h3>
        <div id="pros"></div>
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

</body>
</html>
