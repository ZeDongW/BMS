<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/7/14
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>在线用户列表</title>
</head>
<body>
<jsp:include page="../pub/head.jsp"></jsp:include>
<div style="text-align: center">
    <h3>在线列表展示</h3>
</div>
<table align="center" cellpadding="0" cellspacing="1" width="80%" border="1">
    <tr>
        <td>编号</td>
        <td>姓名</td>
    </tr>
    <c:if test="${not empty onLineSet}">
        <c:forEach var="user" items="${onLineSet}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${user.userName}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>

<div style="text-align: center">
    <a href='${pageContext.request.contextPath}/index'>返回主页</a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'>修改密码</a>&nbsp;&nbsp;&nbsp;
    <a href='${pageContext.request.contextPath}/logOut'>安全退出</a></center>
</div>
</body>
</html>
