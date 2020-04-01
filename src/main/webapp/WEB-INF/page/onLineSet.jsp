<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/7/14
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="OnLineList"/></title>
</head>
<body>
<jsp:include page="../pub/head.jsp"></jsp:include>
<div style="text-align: center">
    <h3><s:text name="OnLineList"/></h3>
</div>
<table align="center" cellpadding="0" cellspacing="1" width="80%" border="1">
    <tr>
        <td><s:text name="ID"/></td>
        <td><s:text name="userName"/></td>
    </tr>
    <s:if test="%{#application.onLineSet.size() != 0}">
        <s:iterator var="user" value="%{#application.onLineSet}" status="st">
            <tr>
                <td><s:property value="#st.count"/></td>
                <td><s:property value="#user.userName"/></td>
            </tr>
        </s:iterator>
    </s:if>
</table>

<div style="text-align: center">
    <s:a href='users_index'><s:text name="Home"/></s:a>&nbsp;&nbsp;&nbsp;
    <s:a href='users_query?id=%{#session.loginUser.id}'><s:text name="passWordUpdate"/></s:a>&nbsp;&nbsp;&nbsp;
    <s:a href='users_logOut'><s:text name="logout"/></s:a></center>
</div>
</body>
</html>
