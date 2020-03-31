<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/23 0023
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<html>
<head>
    <title><fmt:message key="BooksManager" bundle="${bundle}"/></title>
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
    <h3><fmt:message key="BooksManager" bundle="${bundle}"/></h3>
</div>
<table align='center' border='1' width='800px'>
    <tr>
        <th><a href='${pageContext.request.contextPath}/books?sort=bookId'><fmt:message key="ID"
                                                                                        bundle="${bundle}"/></a></th>
        <th><a href='${pageContext.request.contextPath}/books?sort=bookName'><fmt:message key="bookName"
                                                                                          bundle="${bundle}"/></a></th>
        <th><a href='${pageContext.request.contextPath}/books?sort=bookAuthor'><fmt:message key="author"
                                                                                            bundle="${bundle}"/></a>
        </th>
        <th><a href='${pageContext.request.contextPath}/books?sort=bookPublisher'><fmt:message key="publisher"
                                                                                               bundle="${bundle}"/></a>
        </th>
        <th><a href='${pageContext.request.contextPath}/books?sort=bookPrice'><fmt:message key="price"
                                                                                           bundle="${bundle}"/></a></th>
        <th><a href='${pageContext.request.contextPath}/books?sort=bookNum'><fmt:message key="bookNum"
                                                                                         bundle="${bundle}"/></a></th>
        <th><a href='${pageContext.request.contextPath}/books?sort=bookPublishDate'><fmt:message key="publishDate"
                                                                                                 bundle="${bundle}"/></a>
        </th>
        <th><fmt:message key="option" bundle="${bundle}"/></th>
    </tr>
    <c:if test="${not empty booksPb.pageData}">
        <c:forEach items="${booksPb.pageData}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.bookName}</td>
                <td>${book.bookAuthor}</td>
                <td>${book.publisher}</td>
                <td>${book.price}</td>
                <td>${book.bookNum}</td>
                <td>${book.publishDate}</td>
                <td><a href='${pageContext.request.contextPath}/queryBook?id=${book.id}'><fmt:message key="update"
                                                                                                      bundle="${bundle}"/></a>&nbsp;<a
                        href='${pageContext.request.contextPath}/deleteBook?id=${book.id}'><fmt:message key="delete"
                                                                                                        bundle="${bundle}"/></a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <tr>
        <td colspan='8' align='center'><a href='${pageContext.request.contextPath}/queryBook'><fmt:message key="addBook"
                                                                                                           bundle="${bundle}"/></a>
        </td>
    </tr>
    <tr>
        <td colspan="8" align="center">
            <fmt:message key="pageCount" bundle="${bundle}"/><input type="text" style="width: 20px"
                                                                    value="${booksPb.pageCount}"
                                                                    onkeyup="this.value=this.value.replace(/\D/g,'')"
                                                                    onblur='checkPageCount()'
                                                                    id='pageCount'/>&nbsp;&nbsp;
            <fmt:message key="current" bundle="${bundle}"/><input type="text" style="width: 20px"
                                                                  value="${booksPb.currPage}"
                                                                  onkeyup="this.value=this.value.replace(/\D/g,'')"
                                                                  onblur='checkPageCount()'
                                                                  id='currPage'/>/${booksPb.totalPage}<fmt:message
                key="page" bundle="${bundle}"/>&nbsp;&nbsp;
            <c:choose>
                <c:when test="${booksPb.currPage  == 1 && booksPb.currPage != booksPb.totalPage}">
                    <fmt:message key="first" bundle="${bundle}"/>&nbsp;&nbsp;<fmt:message key="previous"
                                                                                          bundle="${bundle}"/>
                    <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.currPage+1}&pageCount=${booksPb.pageCount}"><fmt:message
                            key="next" bundle="${bundle}"/> </a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.totalPage}&pageCount=${booksPb.pageCount}"><fmt:message
                            key="last" bundle="${bundle}"/></a>
                </c:when>
                <c:when test="${booksPb.currPage == booksPb.totalPage && booksPb.currPage  != 1 }">
                    <a href="${pageContext.request.contextPath}/books?currPage=1&pageCount=${booksPb.pageCount}"><fmt:message
                            key="first" bundle="${bundle}"/></a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.currPage-1}&pageCount=${booksPb.pageCount}"><fmt:message
                            key="previous" bundle="${bundle}"/> </a>&nbsp;&nbsp;
                    <fmt:message key="next" bundle="${bundle}"/>&nbsp;&nbsp;<fmt:message key="last" bundle="${bundle}"/>
                </c:when>
                <c:when test="${booksPb.currPage == booksPb.totalPage && booksPb.currPage  == 1 }">
                    <fmt:message key="first" bundle="${bundle}"/>&nbsp;&nbsp;<fmt:message key="previous"
                                                                                          bundle="${bundle}"/>&nbsp;&nbsp;
                    <fmt:message key="next" bundle="${bundle}"/>&nbsp;&nbsp;<fmt:message key="last" bundle="${bundle}"/>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/books?currPage=1&pageCount=${booksPb.pageCount}"><fmt:message
                            key="first" bundle="${bundle}"/></a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.currPage-1}&pageCount=${booksPb.pageCount}"><fmt:message
                            key="previous" bundle="${bundle}"/> </a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.currPage+1}&pageCount=${booksPb.pageCount}"><fmt:message
                            key="next" bundle="${bundle}"/> </a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.totalPage}&pageCount=${booksPb.pageCount}"><fmt:message
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
        window.location.href = "${pageContext.request.contextPath}/books?currPage=" + currPage + "&pageCount=" + pageCount;
    }
</script>
</html>
