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
    <title>Authority Center</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap-theme.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <%--typeahead plugin--%>
    <script type="text/javascript" src="js/bootstrap3-typeahead.js"></script>

    <style type="text/css">
        .del:hover{
            background: #ffffff;
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
                var username = document.getElementsByName("username");
                var password = document.getElementsByName("password");
                var rePassword = document.getElementsByName("rePassword");
                var engName = document.getElementsByName("engName");
                var userType = document.getElementsByName("userType");
                var userIntro = $("#userIntro").attr("value");
                var sendObj = {};
                sendObj.groups = groups;
                sendObj.fields = fields;
                sendObj.username = username[0].value;
                sendObj.password = password[0].value;
                sendObj.rePassword = rePassword[0].value;
                sendObj.engName = engName[0].value;
                sendObj.userType = userType[0].value;
                sendObj.userIntro = userIntro;
                console.log(JSON.stringify(sendObj));
                $.post("");
            })
        })

    </script>
</head>
<body>
<h1>Authority Center</h1>
<h2>Add User</h2>

<div class="well">
    <form action="/lab/user_addUser" method="post">
        <div class="col-md-3 well">
            <label for="username">用户名</label>
            <input type="text" name="username" id="username" class="form-control"><br>
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

        <div class="col-md-3 well">
            <label for="field">研究领域</label>
            <input type="text" id="field" class="form-control">
            <br>
            <input type="button" id="addField" value="添加研究领域" class="btn btn-success">
            <hr>
            <div id="displayField"></div>

        </div>

        <div class="col-md-3 well">
            <label for="group">所在科研小组</label>
            <input type="text" id="group" class="form-control"  data-provide="typeahead">
            <br>
            <input type="button" id="addGroup" value="添加所在科研小组" class="btn btn-success">
            <hr>
            <div id="displayGroup"></div>

        </div>

        <div class="col-md-3 well">
            <label for="userIntro">个人介绍信息</label>
            <br><br>
            <textarea name="userIntro" id="userIntro" cols="40" rows="10" placeholder="输入个人介绍"></textarea>
        </div>
    </form>
</div>


</body>
</html>
