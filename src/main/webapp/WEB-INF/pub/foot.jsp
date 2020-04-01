<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 7/4/2019
  Time: 7:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div style="text-align: center">
    <s:a href='users_index'><s:text name="Home"/></s:a>&nbsp;&nbsp;&nbsp;
    <s:a href='users_query?id=%{#session.loginUser.id}'><s:text name="passWordUpdate"/></s:a>&nbsp;&nbsp;&nbsp;
    <s:a href='users_logOut'><s:text name="logout"/></s:a></center>
</div>
