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
    <jsp:include page="../pub/head.jsp"></jsp:include>
    <div style="text-align: center">
        <h3>用户管理</h3>
    </div>
    <table align='center' border='1' width='800px'>
        <tr>
            <th><a href='${pageContext.request.contextPath}/users?sort=userId'>编号</a></th>
            <th><a href='${pageContext.request.contextPath}/users?sort=userName'>用户名</a></th>
        <c:choose>
            <c:when test="${'admin' eq loginUser.userName}">
                    <th>操作</th>
                </tr>
                <c:forEach items="${usersList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td><a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'>修改</a>&nbsp;<a href='${pageContext.request.contextPath}/deleteUser?id=${user.id}'>删除</a></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                </tr>
                <c:forEach items="${usersList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
    <jsp:include page="../pub/foot.jsp"></jsp:include>
</body>
</html>
