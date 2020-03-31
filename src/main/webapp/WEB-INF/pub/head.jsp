<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 7/4/2019
  Time: 7:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<div style="text-align: center;">
    <fmt:message key="welcome" bundle="${bundle}"/> <span
        style="font-size: 15px; color: green">${loginUser.userName}</span>&nbsp;&nbsp;&nbsp;<a
        href='${pageContext.request.contextPath}/onLineSet'><fmt:message key="OnLineList" bundle="${bundle}"/></a>
</div>
