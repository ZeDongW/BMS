<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <title><s:text name="loginSuccess"/></title>
</head>
<body>
<jsp:include page="../pub/head.jsp"></jsp:include>
<div style="text-align: center">
    <s:a href='users_list'><s:text name="userManage"/></s:a>
    <s:a href='books_list'><s:text name="BooksManager"/></s:a> <br/>
    <s:a href='users_query?id=%{#session.loginUser.id}'><s:text name="passWordUpdate" /></s:a>
    <s:a href='users_logOut'><s:text name="logout"/></s:a>
</div>
</body>
</html>
