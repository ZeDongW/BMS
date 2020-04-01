<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/24 0024
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title><s:text name="passWordUpdate"/></title>
</head>
<body>
<jsp:include page="../pub/head.jsp"></jsp:include>
<div style="text-align: center">
    <h3><s:text name="passWordUpdate"/></h3>
</div>
<s:form action='users_update' method='post' onsubmit='return checkForm()'>
    <s:hidden name='id'/>
    <table align='center' border='1' width='300px'>
        <tr>
            <th><s:text name="newPassWord"/></th>
            <td><s:password name='passWord' onblur='checkPass()' id='passWord'/><span id='passWordCheck'></span></td>
        </tr>
        <tr>
            <th><s:text name="confirmPassword"/></th>
            <td><s:password name='confirmPassWord' onblur='confirmPass()' id='confirmPassWord'/><span id='confirmPassWordId'></span></td>
        </tr>
        <tr>
            <td colspan='2' align='center'>
                <input type='submit' value='<s:text name="save"/>'/>&nbsp;
                <input type='reset' value='<s:text name="reset"/>'/></td>
        </tr>
    </table>
</s:form>
<div style="text-align: center">
    <s:a href='users_index'><s:text name="Home"/></s:a>&nbsp;&nbsp;&nbsp;
    <s:a href='users_logOut'><s:text name="logout"/></s:a>
</div>
</body>
<script type='text/javascript' src="${pageContext.request.contextPath}/js/checkPass.js"></script>
</html>
