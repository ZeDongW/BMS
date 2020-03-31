<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 7/4/2019
  Time: 7:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<div style="text-align: center">
    <a href='${pageContext.request.contextPath}/index'><fmt:message key="Home" bundle="${bundle}"/></a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/queryUser?id=${loginUser.id}'><fmt:message key="passWordUpdate"
                                                                                           bundle="${bundle}"/></a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/logOut'><fmt:message key="logout" bundle="${bundle}"/></a></center>
</div>
