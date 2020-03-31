<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/23 0023
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<html>
<head>
    <title><fmt:message key="loginSuccess" bundle="${bundle}"/></title>
</head>
<body>
<jsp:include page="../pub/head.jsp"></jsp:include>
<div style="text-align: center">
    <a href='${pageContext.request.contextPath}/users'><fmt:message key="userManage" bundle="${bundle}"/></a> <a
        href='${pageContext.request.contextPath}/books'><fmt:message key="BooksManager" bundle="${bundle}"/></a> <br/>
    <a href='${pageContext.request.contextPath}/queryUser?id=${loginUser.id}'><fmt:message key="passWordUpdate"
                                                                                           bundle="${bundle}"/></a> <a
        href='${pageContext.request.contextPath}/logOut'><fmt:message key="logout" bundle="${bundle}"/></a>
</div>
</body>
</html>
