<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/23 0023
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
        <c:if test="${not empty usersPb.pageData}">
            <c:forEach items="${usersPb.pageData}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td><a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'>修改</a>&nbsp;<a
                            href='${pageContext.request.contextPath}/deleteUser?id=${user.id}'>删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
        </c:when>
        <c:otherwise>
            </tr>
            <c:if test="${not empty usersPb.pageData}">
                <c:forEach items="${usersPb.pageData}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </c:otherwise>
        </c:choose>
        <tr>
            <td colspan="3" align="center">
                每页显示行数<input type="text" style="width: 20px" value="${usersPb.pageCount}"
                             onkeyup="this.value=this.value.replace(/\D/g,'')" onblur='checkPageCount()'
                             id='pageCount'/>&nbsp;&nbsp;
                当前<input type="text" style="width: 20px" value="${usersPb.currPage}"
                         onkeyup="this.value=this.value.replace(/\D/g,'')" onblur='checkPageCount()'
                         id='currPage'/>/${usersPb.totalPage}页&nbsp;&nbsp;
                <c:choose>
                    <c:when test="${usersPb.currPage  == 1 && usersPb.currPage != usersPb.totalPage}">
                        首页&nbsp;&nbsp;上一页
                        <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.currPage+1}&pageCount=${usersPb.pageCount}">下一页 </a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.totalPage}&pageCount=${usersPb.pageCount}">末页</a>
                    </c:when>
                    <c:when test="${usersPb.currPage == usersPb.totalPage && usersPb.currPage  != 1}">
                        <a href="${pageContext.request.contextPath}/users?currPage=1&pageCount=${usersPb.pageCount}">首页</a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.currPage-1}&pageCount=${usersPb.pageCount}">上一页 </a>&nbsp;&nbsp;
                        下一页&nbsp;&nbsp;末页
                    </c:when>
                    <c:when test="${usersPb.currPage == usersPb.totalPage && usersPb.currPage  == 1}">
                        首页&nbsp;&nbsp;上一页&nbsp;&nbsp;
                        下一页&nbsp;&nbsp;末页
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/users?currPage=1&pageCount=${usersPb.pageCount}">首页</a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.currPage-1}&pageCount=${usersPb.pageCount}">上一页 </a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.currPage+1}&pageCount=${usersPb.pageCount}">下一页 </a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.totalPage}&pageCount=${usersPb.pageCount}">末页</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
    <jsp:include page="../pub/foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
    function checkPageCount() {
        const currPage = document.getElementById("currPage").value;
        const pageCount = document.getElementById("pageCount").value;
        window.location.href = "${pageContext.request.contextPath}/users?currPage=" + currPage + "&pageCount=" + pageCount;
    }
</script>
</html>
