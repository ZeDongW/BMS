<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/23 0023
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" import="cn.zedongw.bms.entity.*" %>
<html>
<head>
    <title>书本管理</title>
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
    <center>
        欢迎登陆， <font size='+2' color='green'>${user.userName}</font><br />
        <h3>书本管理</h3>
    </center>
    <table align='center' border='1' width='800px'>
        <tr>
            <th><a href='${pageContext.request.contextPath}/books?sort=bookId'>编号</a></th>
            <th><a href='${pageContext.request.contextPath}/books?sort=bookName'>书名</a></th>
            <th><a href='${pageContext.request.contextPath}/books?sort=bookAuthor'>作者</a></th>
            <th><a href='${pageContext.request.contextPath}/books?sort=bookPublisher'>出版社</a></th>
            <th><a href='${pageContext.request.contextPath}/books?sort=bookPrice'>价格</a></th>
            <th><a href='${pageContext.request.contextPath}/books?sort=bookNum'>书号</a></th>
            <th><a href='${pageContext.request.contextPath}/books?sort=bookPublishDate'>出版日期</a></th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.bookName}</td>
                <td>${book.bookAuthor}</td>
                <td>${book.publisher}</td>
                <td>${book.price}</td>
                <td>${book.bookNum}</td>
                <td>${book.publishDate}</td>
                <td><a href='${pageContext.request.contextPath}/queryBook?id=${book.id}'>修改</a>&nbsp;<a href='${pageContext.request.contextPath}/deleteBook?id=${book.id}'>删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan='8' align='center'><a href='${pageContext.request.contextPath}/queryBook'>添加书本</a></td>
        </tr>
    </table>
    <center><a href='${pageContext.request.contextPath}/index'>返回主页</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'>用户修改</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/logOut'>安全退出</a></center>
</body>
</html>
