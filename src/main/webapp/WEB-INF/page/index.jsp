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
    <jsp:include page="../pub/head.jsp"></jsp:include>
    <div style="text-align: center">
        <a href='${pageContext.request.contextPath}/users'>用户管理</a> <a href='${pageContext.request.contextPath}/books'>书本管理</a> <br />
        <a href='${pageContext.request.contextPath}/queryUser?id=${loginUser.id}'>用户修改</a> <a href='${pageContext.request.contextPath}/logOut'>安全退出</a>
    </div>
</body>
</html>
