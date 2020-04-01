<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 7/4/2019
  Time: 7:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div style="text-align: center;">
    <s:text name="welcome"/> <span
        style="font-size: 15px; color: green"><s:property value="%{#session.loginUser.userName}"/></span>&nbsp;&nbsp;&nbsp;<s:a
        href='users_onLineSet'><s:text name="OnLineList"/></s:a>
</div>
