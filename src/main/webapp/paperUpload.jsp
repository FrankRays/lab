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
    <link rel="stylesheet" href="css/jquery-ui.css">
    <link rel="stylesheet" href="css/jquery-ui.theme.css">
    <%--<link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-theme.css">--%>
    <script src="js/jquery.js"></script>
    <%--<script src="js/bootstrap.js"></script>--%>
    <script src="js/jquery-ui.js"></script>
    <style>
        #sortable1, #sortable2 { list-style-type: none; margin: 0; padding: 0 0 2.5em; float: left; margin-right: 10px; }
        #sortable1 li, #sortable2 li { margin: 0 5px 5px 5px; padding: 5px; font-size: 1.2em; width: 120px; }
    </style>
    <script>
        $(function() {
            $( "#sortable1, #sortable2" ).sortable({
                connectWith: ".connectedSortable"
            }).disableSelection();
        });
    </script>
</head>
<body>
<div class="container">
    <div class=""></div>
    <form action="paperUpload.action" method="post" enctype="multipart/form-data">

        <ul id="sortable1" class="connectedSortable">
            <li>
                <input type="file" name="file" id="file">
            </li>
            <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
                <input type="checkbox" name="authors" value="1">AAA
            </li>
            <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
                <input type="checkbox" name="authors" value="2">BBB
            </li>
            <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
                <input type="checkbox" name="authors" value="3">CCC
            </li>
            <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
                <input type="checkbox" name="authors" value="44">DDD
            </li>
            <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
                <input type="checkbox" name="authors" value="555">EEE
            </li>
            <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
                <input type="checkbox" name="authors" value="666">FFF
            </li>
            <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
                <input type="checkbox" name="authors" value="777">GGG
            </li>
            <li>
                <input type="submit" name="submit" id="submit" value="上传">
            </li>

        </ul>
    </form>
    <ul id="sortable2" class="connectedSortable">
        <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
            <input type="checkbox" name="authors" value="121">QQQ
        </li>
        <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
            <input type="checkbox" name="authors" value="122">WWW
        </li>
        <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
            <input type="checkbox" name="authors" value="123">SSS
        </li>
        <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
            <input type="checkbox" name="authors" value="124">HHH
        </li>
        <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
            <input type="checkbox" name="authors" value="125">JJJ
        </li>
        <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
            <input type="checkbox" name="authors" value="126">ZZZ
        </li>
        <li class="ui-state-default"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>
            <input type="checkbox" name="authors" value="127">XXX
        </li>
    </ul>
</div>
<div style="clear: both;"></div>
<div class="" style="height: 200px; background: #6ce26c">
哈哈哈哈哈哈哈哈啊哈哈哈哈哈哈哈哈哈哈
</div>

<%--
<div class="container" style="margin-top: 100px">
    <div id="filter" style="margin-left: 30px">
        <div><div data-force="5" class="layer title title_xl">Editable list</div></div>

        <div style="margin-top: -8px; margin-left: 10px" class="block__list block__list_words">
            <ul id="editable">
                <li draggable="false" class="">Владимир<i class="js-remove">✖</i></li><li>Оля<i class="js-remove">✖</i></li>

                <li>Алина<i class="js-remove">✖</i></li>
            </ul>

            <button id="addUser">Add</button>
        </div>
    </div>
</div>
--%>

</body>
</html>