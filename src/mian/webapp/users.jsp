<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/23 0023
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" import="cn.zedongw.bms.entity.*" %>
<html>
<head>
    <title>用户管理</title>
    <style type='text/css'>
        table td{
            text-align:center;
        }
        table{
           border-collapse:collapse;
        }
    </style>
</head>
<body>
    <center>欢迎登陆， <font size='+2' color='green'>${user.userName}</font><br />
        <h3>用户管理</h3>
    </center>
    <table align='center' border='1' width='800px'>
        <tr>
            <th><a href='${pageContext.request.contextPath}/users?sort=userId'>编号</a></th>
            <th><a href='${pageContext.request.contextPath}/users?sort=userName'>用户名</a></th>
        <c:choose>
            <c:when test="${'admin' eq user.userName}">
                    <th>操作</th>
                </tr>
                <c:forEach items="${list}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td><a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'>修改</a>&nbsp;<a href='${pageContext.request.contextPath}/deleteUser?id=${user.id}'>删除</a></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                </tr>
                <c:forEach items="${list}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
    <center>
        <a href='${pageContext.request.contextPath}/index'>返回主页</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'>用户修改</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/logOut'>安全退出</a>
    </center>
</body>
</html>
