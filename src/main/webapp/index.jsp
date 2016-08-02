<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<body>
<h2>Hello World!</h2>

</body>
    <%--<h4 class="title"></h4>
    <ul class="case1">
        <li class="aa1"></li>
        <li class="aa2"></li>
        <li class="aa3"></li>
        <li class="aa4"></li>
    </ul>
    <ul class="case2">
        <li class="bb1"></li>
        <li class="bb2"></li>
        <li class="bb3"></li>
        <li class="bb4"></li>
        <li class="bb5"></li>
    </ul>--%>
<h1>文件上传</h1>
<form action="paperUpload.action" method="post" enctype="multipart/form-data">
    <label for="title">论文标题</label>
    <input type="text" name="title" id="title">
    <br>
    <br>
    <label for="authors">作者列表</label>
    <br>
    <input type="checkbox" name="authors" value="sss" id="authors">sss
    <br>
    <input type="checkbox" name="authors" value="aaa">aaa
    <br>
    <input type="checkbox" name="authors" value="ttt">ttt
    <br>
    <input type="checkbox" name="authors" value="www">www
    <br>
    <br>
    <label for="corrAuthors">通讯作者列表</label>
    <input type="checkbox" name="corrAuthors" id="corrAuthors">
    <input type="checkbox" name="corrAuthors">
    <input type="checkbox" name="corrAuthors">
    <input type="checkbox" name="corrAuthors">

    <input type="file" name="file" id="file">
    <input type="submit" name="sub" id="sub" value="上传">
</form>

<s:debug/>
</html>
