<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/7/14
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<html>
<head>
    <title><fmt:message key="OnLineList" bundle="${bundle}"/></title>
</head>
<body>
<jsp:include page="../pub/head.jsp"></jsp:include>
<div style="text-align: center">
    <h3><fmt:message key="OnLineList" bundle="${bundle}"/></h3>
</div>
<table align="center" cellpadding="0" cellspacing="1" width="80%" border="1">
    <tr>
        <td><fmt:message key="ID" bundle="${bundle}"/></td>
        <td><fmt:message key="userName" bundle="${bundle}"/></td>
    </tr>
    <c:if test="${not empty onLineSet}">
        <c:forEach var="user" items="${onLineSet}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${user.userName}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>

<div style="text-align: center">
    <a href='${pageContext.request.contextPath}/index'><fmt:message key="Home" bundle="${bundle}"/></a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'><fmt:message key="passWordUpdate"
                                                                                      bundle="${bundle}"/></a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/logOut'><fmt:message key="logout" bundle="${bundle}"/></a></center>
</div>
</body>
</html>
