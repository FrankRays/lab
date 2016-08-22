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
    <title>论文信息</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap3-typeahead.js"></script>

    <script type="text/javascript" src="js/pdfobject.js"></script>

    <script>

        $(function(){

            $.post("log_currentUser", {}, function (data) {
                var username = data.username;
                username = username.replace("\\", "");
                $("#username").html(username);
            });

            $("#add").click(function () {
                var value = $("#inp").val();
                console.log(value);

                var line2 = "<tr>";
                line2 +="<td><input type='text' name='author' value='"+value+"' readonly class='form-control'></td>" +
                        "<td><input type='checkbox' name='isCorr' value='"+value+"'></td>" +
                        "<<td><input type='checkbox' name='isExtra' value='"+value+"'></td></td>" +
                        "<td><input type='button' value='删除' class='del btn btn-default'></td>";
                line2 +="</tr>";

                $("#show2").append(line2);
            });

            $("#addField").click(function(){
                var field = $("#inpField").val();
                var appendLine = "<input type='text' name='fields' value='"+field+"' readonly class='form-control del'>";
                $("#field").append(appendLine);
            });


            var pid = $("#pid").text();
            console.log(pid);

            $('#wait').modal('toggle');

            $.post("/lab/disp_paper",{"paperId":pid}, function(data){

                $('#wait').modal('toggle');

                /** add paperId into the form ,so that backend can identify it is modify a paper */
                $("form").append("<input type='hidden' name='paperId' value='"+data.rtn.paper.paperId+"'>");
                $("[name='title']").val(data.rtn.paper.title);
                $("[name='category']").val(data.rtn.paper.category);
                $("[name='ccfStatus']").val(data.rtn.paper.ccfStatus);
                $("[name='endPage']").val(data.rtn.paper.endPage);
                $("[name='issueNum']").val(data.rtn.paper.issueNum);
                $("[name='periodical']").val(data.rtn.paper.periodical);
                $("[name='postYear']").val(data.rtn.paper.postYear);
                $("[name='startPage']").val(data.rtn.paper.startPage);
                $("[name='type']").val(data.rtn.paper.type);
                $("[name='volNum']").val(data.rtn.paper.volNum);


                var authors = data.rtn.authors;
                var fields = data.rtn.fields;

                /** show authors for this paper */
                var line = "";
                for (var i = 0; i < authors.length; i++){
                    line = "<tr>";
                    if (authors[i].isCorr==1 && authors[i].isExtra==1){
                        line +="<td><input type='text' name='author' value='"+authors[i].name+"' readonly class='form-control'></td>" +
                                "<td><input type='checkbox' name='isCorr' checked='checked' readonly></td>" +
                                "<<td><input type='checkbox' name='isExtra' checked='checked' readonly></td></td>";
                    } else if (authors[i].isCorr==1 && authors[i].isExtra==0) {
                        line +="<td><input type='text' name='author' value='"+authors[i].name+"' readonly class='form-control'></td>" +
                                "<td><input type='checkbox' name='isCorr' checked='checked' readonly></td>" +
                                "<<td><input type='checkbox' name='isExtra' readonly></td></td>";
                    } else if(authors[i].isCorr==0 && authors[i].isExtra==1) {
                        line +="<td><input type='text' name='author' value='"+authors[i].name+"' readonly class='form-control'></td>" +
                                "<td><input type='checkbox' name='isCorr' readonly></td>" +
                                "<<td><input type='checkbox' name='isExtra' checked='checked' readonly></td></td>";
                    } else {
                        line +="<td><input type='text' name='author' value='"+authors[i].name+"' readonly class='form-control'></td>" +
                                "<td><input type='checkbox' name='isCorr' readonly></td>" +
                                "<<td><input type='checkbox' name='isExtra' readonly></td></td>";
                    }
                    line +="</tr>";
                    $("#show2").append(line);
                }

                for(var j = 0; j < fields.length; j ++){
                    var appendLine2 = "<input type='text' name='fields' value='"+fields[j]+"' readonly class='form-control del'>";
                    $("#field").append(appendLine2);
                }
                var sourceUrl = data.rtn.paper.sourceUrl;
                var paperId = data.rtn.paper.paperId;
                $.post("comp_basePath", {}, function (data) {
                    console.log(data.basePath);
                    PDFObject.embed(data.basePath+"/"+sourceUrl, "#example1");
                    var path = data.basePath+"/"+sourceUrl;
                    $("#file").append("<h4>已上传附件:<a href='"+path+"'>查看附件</a> </h4>");
                    //$("#file").append("fhskjdhsjkdshk");
                });

                $("#modify").append("<a href='/lab/view?page=paperModify&id="+paperId+"'><button class='btn btn-warning btn-lg'>修改信息</button></a>");
            });
        });
    </script>
</head>
<body>
<div class="container well" style="background: #70cca8; min-height: 300px;">
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
    <h1 class="text-center">论文信息</h1>
    <hr>
    <div id="pid" class="hidden">${id}</div>
    <div class="">
        <form action="/lab/paper_addPaper" method="post" enctype="multipart/form-data">

            <%--<input type="file" name="file" class="form-control">--%>
            <div class="panel" id="file"></div>
            <hr>
            <div class="col-md-6 panel">
                <label for="title">论文标题</label>
                <input type="text" name="title" id="title" class="form-control" readonly><br>
                <label for="type">论文类型（会议论文/期刊论文）</label>
                <input type="text" name="type" id="type" class="form-control" readonly><br>
                <label for="category">论文类别（SEI/EI/核心期刊/普通）</label>
                <input type="text" name="category" id="category" class="form-control" readonly><br>
                <label for="ccfStatus">CCF等级（A/B/C/无【不填写】）</label>
                <input type="text" name="ccfStatus" id="ccfStatus" class="form-control" readonly><br>
                <label for="postYear">发表年份</label>
                <input type="text" name="postYear" id="postYear" class="form-control" readonly><br>
            </div>
            <%--<div class="col-md-2"></div>--%>
            <div class="col-md-6 panel">
                <label for="periodical">期刊或会议名称</label>
                <input type="text" name="periodical" id="periodical" class="form-control" readonly><br>
                <label for="volNum">论文卷号</label>
                <input type="text" name="volNum" id="volNum" class="form-control" readonly><br>
                <label for="issueNum">论文期号</label>
                <input type="text" name="issueNum" id="issueNum" class="form-control" readonly><br>
                <label for="startPage">论文起始页</label>
                <input type="text" name="startPage" id="startPage" class="form-control" readonly><br>
                <label for="endPage">论文结束页</label>
                <input type="text" name="endPage" id="endPage" class="form-control" readonly><br>
            </div>
            <hr>
            <%--<input type="submit" value="提交论文" onclick="prePost()" class="btn btn-success">--%>
        </form>
    </div>
    <div style="clear: both;"></div>
    <hr>
    <div style="min-height: 400px;" class="panel">
        <div class=" col-md-7">
            <h4>作者列表</h4>
            <table id="show2" class="table table-hover">
                <thead><th>作者姓名</th><th>是否通讯作者</th><th>非本数据库用户</th></thead>
            </table>
        </div>
        <div class="col-md-1"></div>
        <div class=" col-md-4">
            <h4>论文涉及领域</h4>
            <hr>
            <div id="field"></div>
            <hr>
        </div>
    </div>
    <div style="clear: both;"></div>
    <hr>
    <div id="modify"></div>
    <div id="example1"></div>
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