<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/24 0024
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<html>
<head>
    <title><fmt:message key="passWordUpdate" bundle="${bundle}"/></title>
</head>
<body>
<jsp:include page="../pub/head.jsp"></jsp:include>
<div style="text-align: center">
    <h3><fmt:message key="passWordUpdate" bundle="${bundle}"/></h3>
</div>
<form action='${pageContext.request.contextPath}/updateUser' method='post' onsubmit='return checkForm()'>
    <input type='hidden' name='id' value='${user.id}'/>
    <table align='center' border='1' width='300px'>
        <tr>
            <th><fmt:message key="newPassWord" bundle="${bundle}"/></th>
            <td><input type='password' name='passWord' value='${user.passWord}' onblur='checkPass()'
                       id='passWord'/><span id='passWordCheck'></span></td>
        </tr>
        <tr>
            <th><fmt:message key="confirmPassword" bundle="${bundle}"/></th>
            <td><input type='password' name='confirmPassWord' onblur='confirmPass()' id='confirmPassWord'/><span
                    id='confirmPassWordId'></span></td>
        </tr>
        <tr>
            <td colspan='2' align='center'>
                <input type='submit' value='<fmt:message key="save" bundle="${bundle}"/>'/>&nbsp;
                <input type='reset' value='<fmt:message key="reset" bundle="${bundle}"/>'/></td>
        </tr>
    </table>
</form>
<div style="text-align: center">
    <a href='${pageContext.request.contextPath}/index'><fmt:message key="Home" bundle="${bundle}"/></a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/logOut'><fmt:message key="logout" bundle="${bundle}"/></a>
</div>
</body>
<script type='text/javascript' src="${pageContext.request.contextPath}/js/checkPass.js"></script>
</html>
