<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 7/4/2019
  Time: 7:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div style="text-align: center">
    <a href='${pageContext.request.contextPath}/index'>返回主页</a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/queryUser?id=${loginUser.id}'>修改密码</a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/logOut'>安全退出</a></center>
</div>
