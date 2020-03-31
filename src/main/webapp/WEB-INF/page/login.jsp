<%@page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<html>
<head>
    <title><fmt:message key="BMS" bundle="${bundle}"/></title>
</head>
<body>
<div style="text-align: center">
    <h2><fmt:message key="welcome" bundle="${bundle}"/></h2>
    <form action="" method="post" name="form" onsubmit="return checkForm()">
        <fmt:message key="userName" bundle="${bundle}"/>：<input type="text" name="userName" onblur="checkUserName()"
                                                                id="userName"/><span
            id="userNameCheck"></span><br>
        <div style="align-content: center; color: red">
            <c:choose>
                <c:when test='${"1" eq message}'>
                    <fmt:message key="useNameNotExist" bundle="${bundle}"/>
                </c:when>
                <c:when test='${"2" eq message}'>
                    <fmt:message key="useNameNull" bundle="${bundle}"/>
                </c:when>
                <c:when test='${"3" eq message}'>
                    <fmt:message key="passWordNull" bundle="${bundle}"/>
                </c:when>
                <c:when test='${"4" eq message}'>
                    <fmt:message key="passWordError" bundle="${bundle}"/>
                </c:when>
                <c:when test='${"5" eq message}'>
                    <fmt:message key="userNameUsed" bundle="${bundle}"/>
                </c:when>
            </c:choose>
        </div>
        <fmt:message key="passWord" bundle="${bundle}"/>：<input type="password" name="passWord" onblur="checkPassWord()"
                                                                id="passWord"/><span
            id="passWordCheck"></span><br>
        <div style="align-content: center; color: green">
            <c:if test="${not empty success}">
                <fmt:message key="registSuccess" bundle="${bundle}"/>
            </c:if>
        </div>
        <input type="submit" onclick='form.action="${pageContext.request.contextPath}/regist"'
               value='<fmt:message key="regist" bundle="${bundle}"/>'/>
        <input type="submit" onclick='form.action="${pageContext.request.contextPath}/login"'
               value='<fmt:message key="login" bundle="${bundle}"/>'/>
    </form>
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkUser.js"></script>
</html>
