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
    <title>Project Modify</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <%--typeahead plugin--%>
    <script type="text/javascript" src="js/bootstrap3-typeahead.js"></script>

    <script>
        $(function(){

            $.post("log_currentUser", {}, function (data) {
                var username = data.username;
                username = username.replace("\\", "");
                $("#username").html(username);
            });

            var pid = $("#pid").text();
            $('#wait').modal('toggle');

            $.post("disp_project", {"paperId":pid}, function(data){

                $('#wait').modal('toggle');

                $("form").append("<input type='hidden' name='proId' value='"+data.rtn.project.proId+"'>");
                $("[name='endTime']").val(data.rtn.project.endDate.substring(0,10));
                $("[name='proFee']").val(data.rtn.project.proFee);
                $("[name='proLevel']").val(data.rtn.project.proLevel);
                $("[name='proName']").val(data.rtn.project.proName);
                $("[name='proType']").val(data.rtn.project.proType);
                $("[name='startTime']").val(data.rtn.project.startDate.substring(0,10));

                var fields = data.rtn.fields;
                var authors = data.rtn.authors;
                for (var i = 0; i < fields.length; i++){
                    var appendLine2 = "<input type='text' name='fields' value='"+fields[i]+"' readonly class='form-control del'>";
                    $("#field").append(appendLine2);
                }
                var line = "";
                for (var j = 0; j < authors.length; j++){
                    line = "<tr>";
                    if (authors[j].isExtra == 1){
                        line +="<td><input type='text' name='author' value='"+authors[j].name+"'readonly class='form-control'></td>" +
                                "<td><input type='checkbox' name='isCorr' readonly></td>" +
                                "<<td><input type='checkbox' name='isExtra' checked='checked' readonly></td></td>";
                    } else{
                        line +="<td><input type='text' name='author' value='"+authors[j].name+"'readonly class='form-control'></td>" +
                                "<td><input type='checkbox' name='isCorr' readonly></td>" +
                                "<<td><input type='checkbox' name='isExtra' readonly></td></td>";
                    }
                    line += "</tr>";
                    $("#show2").append(line);
                }
                $("#submit").append("<a href='/lab/view?page=proModify&id="+pid+"'> <button class='btn btn-warning btn-lg'>修改项目信息</button> </a>")
            });

        });
    </script>
</head>
<body>
<div class="container well" style="background: #70cca8; min-height: 300px;">
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
    <h1 class="text-center">项目信息变更</h1>
    <hr>
    <div class="hidden" id="pid">${id}</div>
    <div class="">
        <form action="/lab/pro_addPro" method="post" enctype="multipart/form-data">
            <div class="col-md-6">
                <label for="proName">项目名称</label>
                <input type="text" name="proName" id="proName" class="form-control" readonly><br>
                <label for="startTime">起始时间</label>
                <input type="text" name="startTime" id="startTime" class="form-control" readonly><br>
                <label for="endTime">结束时间</label>
                <input type="text" name="endTime" id="endTime" class="form-control" readonly><br>
            </div>
            <div class="col-md-6">
                <label for="proFee">项目经费</label>
                <input type="text" name="proFee" id="proFee" class="form-control" readonly><br>
                <label for="proType">项目类型（国家型/企业型）</label>
                <input type="text" name="proType" id="proType" class="form-control" readonly><br>
                <label for="proLevel">项目级别</label>
                <input type="text" name="proLevel" id="proLevel" class="form-control" readonly><br>

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
        </div>
        <div class="col-md-1"></div>
        <div class="col-lg-4">
            <h4>论文涉及领域</h4>
            <hr>
            <div id="field"></div>
            <hr>
        </div>
    </div>
    <hr>
    <div id="submit"></div>
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