<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/9/7
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="r" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message}<br>



<r:hasPermission name="bookmanager:book:query">
        查询
</r:hasPermission>

<r:hasPermission name="bookmanager:book:add">
    新增
</r:hasPermission>

<a href="/shiro02/user/goDel">删除用户</a>
<a href="/shiro02/user/goUp">修改用户</a>

<form action="/shiro02/user/add" method="post">
    账号:<input type="text" name="username"><br>
    密码:<input type="password" name="password"><br>
    <input type="submit">

</form>


</body>
</html>
