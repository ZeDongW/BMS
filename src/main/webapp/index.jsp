<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/23 0023
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
    <center>
        欢迎登陆， <font size='+2' color='green'>${user.userName}</font><br />
        <a href='${pageContext.request.contextPath}/users'>用户管理</a> <a href='${pageContext.request.contextPath}/books'>书本管理</a> <br />
        <a href='${pageContext.request.contextPath}/queryUser?id="+ user.getId()+"'>用户修改</a> <a href='${pageContext.request.contextPath}/logOut'>安全退出</a>
    </center>
</body>
</html>
