<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/6/25 0025
  Time: 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<html>
<head>
    <title><fmt:message key="NotFound" bundle="${bundle}"/></title>
</head>
<body>
<div style="text-align: center"><fmt:message key="NotFoundMessage" bundle="${bundle}"/><a
        href="${pageContext.request.contextPath}/index"><fmt:message key="Index" bundle="${bundle}"/></a></div>
</body>
</html>
