<%@page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="BMS"/></title>
</head>
<body>
<div style="text-align: center">
    <h2><s:text name="welcome"/></h2>
    <s:form action="users_login" method="post" name="form" onsubmit="return checkForm()">
        <s:text name="userName"/>：<s:textfield name="userName" onblur="checkUserName()" id="userName"/><span id="userNameCheck"></span><br>
        <div style="align-content: center; color: red">
            <c:choose>
                <c:when test='${"1" eq message}'>
                    <s:text name="useNameNotExist"/>
                </c:when>
                <c:when test='${"2" eq message}'>
                    <s:text name="useNameNull"/>
                </c:when>
                <c:when test='${"3" eq message}'>
                    <s:text name="passWordNull"/>
                </c:when>
                <c:when test='${"4" eq message}'>
                    <s:text name="passWordError"/>
                </c:when>
                <c:when test='${"5" eq message}'>
                    <s:text name="userNameUsed"/>
                </c:when>
            </c:choose>
            <s:fielderror fieldName="userName"/><s:fielderror fieldName="passWord"/>
        </div>
        <s:text name="passWord"/>：<s:password name="passWord" onblur="checkPassWord()" id="passWord"/><span id="passWordCheck"></span><br>
        <div style="align-content: center; color: green">
            <c:if test="${not empty success}">
                <s:text name="registSuccess"/>
            </c:if>
        </div>
        <input type="submit" onclick='form.action="users_regist"' value='<s:text name="regist"/>'/>
        <input type="submit" onclick='form.action="users_login"' value='<s:text name="login"/>'/>
    </s:form>
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkUser.js"></script>
</html>
