<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/23 0023
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<html>
<head>
    <title><fmt:message key="userManage" bundle="${bundle}"/></title>
    <style type='text/css'>
        table td {
            text-align: center;
        }

        table {
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<jsp:include page="../pub/head.jsp"></jsp:include>
<div style="text-align: center">
    <h3><fmt:message key="userManage" bundle="${bundle}"/></h3>
</div>
<table align='center' border='1' width='800px'>
    <tr>
        <th><a href='${pageContext.request.contextPath}/users?sort=userId'><fmt:message key="ID"
                                                                                        bundle="${bundle}"/></a></th>
        <th><a href='${pageContext.request.contextPath}/users?sort=userName'><fmt:message key="userName"
                                                                                          bundle="${bundle}"/></a></th>
        <c:choose>
        <c:when test="${'admin' eq loginUser.userName}">
        <th><fmt:message key="option" bundle="${bundle}"/></th>
    </tr>
    <c:if test="${not empty usersPb.pageData}">
        <c:forEach items="${usersPb.pageData}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td><a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'><fmt:message key="update"
                                                                                                      bundle="${bundle}"/></a>&nbsp;<a
                        href='${pageContext.request.contextPath}/deleteUser?id=${user.id}'><fmt:message key="delete"
                                                                                                        bundle="${bundle}"/></a>
                </td>
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
            <fmt:message key="pageCount" bundle="${bundle}"/><input type="text" style="width: 20px"
                                                                    value="${usersPb.pageCount}"
                                                                    onkeyup="this.value=this.value.replace(/\D/g,'')"
                                                                    onblur='checkPageCount()'
                                                                    id='pageCount'/>&nbsp;&nbsp;
            <fmt:message key="current" bundle="${bundle}"/><input type="text" style="width: 20px"
                                                                  value="${usersPb.currPage}"
                                                                  onkeyup="this.value=this.value.replace(/\D/g,'')"
                                                                  onblur='checkPageCount()'
                                                                  id='currPage'/>/${usersPb.totalPage}<fmt:message
                key="page" bundle="${bundle}"/>&nbsp;&nbsp;
            <c:choose>
                <c:when test="${usersPb.currPage  == 1 && usersPb.currPage != usersPb.totalPage}">
                    <fmt:message key="first" bundle="${bundle}"/>&nbsp;&nbsp;<fmt:message key="previous"
                                                                                          bundle="${bundle}"/>
                    <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.currPage+1}&pageCount=${usersPb.pageCount}"><fmt:message
                            key="next" bundle="${bundle}"/> </a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.totalPage}&pageCount=${usersPb.pageCount}"><fmt:message
                            key="last" bundle="${bundle}"/></a>
                </c:when>
                <c:when test="${usersPb.currPage == usersPb.totalPage && usersPb.currPage  != 1}">
                    <a href="${pageContext.request.contextPath}/users?currPage=1&pageCount=${usersPb.pageCount}"><fmt:message
                            key="first" bundle="${bundle}"/></a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.currPage-1}&pageCount=${usersPb.pageCount}"><fmt:message
                            key="previous" bundle="${bundle}"/> </a>&nbsp;&nbsp;
                    <fmt:message key="next" bundle="${bundle}"/>&nbsp;&nbsp;<fmt:message key="last" bundle="${bundle}"/>
                </c:when>
                <c:when test="${usersPb.currPage == usersPb.totalPage && usersPb.currPage  == 1}">
                    <fmt:message key="first" bundle="${bundle}"/>&nbsp;&nbsp;<fmt:message key="previous"
                                                                                          bundle="${bundle}"/>&nbsp;&nbsp;
                    <fmt:message key="next" bundle="${bundle}"/>&nbsp;&nbsp;<fmt:message key="last" bundle="${bundle}"/>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/users?currPage=1&pageCount=${usersPb.pageCount}"><fmt:message
                            key="first" bundle="${bundle}"/></a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.currPage-1}&pageCount=${usersPb.pageCount}"><fmt:message
                            key="previous" bundle="${bundle}"/> </a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.currPage+1}&pageCount=${usersPb.pageCount}"><fmt:message
                            key="next" bundle="${bundle}"/> </a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/users?currPage=${usersPb.totalPage}&pageCount=${usersPb.pageCount}"><fmt:message
                            key="last" bundle="${bundle}"/></a>
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
