<%--
  Created by IntelliJ IDEA.
  User: Jimmy
  Date: 2016/8/22
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Homepage</title>
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
            var uid = $("#id").text();
            $.post("/lab/disp_user",{"paperId":uid}, function(data){
                /** loading info succeed, hide loading view */
                $('#wait').modal('toggle');

            });

            /** when dom loading succeed, show waiting gif */
            $('#wait').modal('toggle');
        });

    </script>
</head>
<body>
    <h1>用户个人主页</h1>
    <div class="hidden" id="id">${id}</div>

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
