<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/24 0024
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<fmt:setBundle basename="msg" var="bundle"/>
<fmt:setLocale value="${pageContext.request.locale}"/>
<html>
<head>
    <c:choose>
        <c:when test="${empty book.id}">
            <title><fmt:message key="addBook" bundle="${bundle}"/></title>
        </c:when>
        <c:otherwise>
            <title><fmt:message key="bookUpdate" bundle="${bundle}"/></title>
        </c:otherwise>
    </c:choose>

</head>
<body>
    <jsp:include page="../pub/head.jsp"></jsp:include>
    <c:choose>
        <c:when test="${empty book.id}">
            <div style="text-align: center">
                <h3><fmt:message key="addBook" bundle="${bundle}"/></h3>
            </div>
            <form action='${pageContext.request.contextPath}/addBook' method='post' onsubmit='return checkForm()'>
                <table align='center' border='1' width='300px'>
                    <tr>
                        <th><fmt:message key="bookName" bundle="${bundle}"/></th>
                        <td><input type='text' name='bookName'/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="author" bundle="${bundle}"/></th>
                        <td><input type='text' name='bookAuthor'/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="publisher" bundle="${bundle}"/></th>
                        <td><input type='text' name='publisher'/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="price" bundle="${bundle}"/></th>
                        <td><input type='text' name='price' onblur='checkPrice()' id='price'/><span
                                id='priceCheck'></span></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="bookNum" bundle="${bundle}"/></th>
                        <td><input type='text' name='bookNum' onblur='checkBookNum()' id='bookNum'/><span
                                id='bookNumCheck'></span></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="publishDate" bundle="${bundle}"/></th>
                        <td><input type='date' dataformatas="yyyy-MM-dd" name='publishDate' onblur='checkPublishDate()'
                                   id='publishDate'/><span id='publishDateCheck'></span></td>
                    </tr>
                    <tr>
                        <td colspan='2' align='center'>
                            <input type='submit' value='<fmt:message key="save" bundle="${bundle}"/>'/>&nbsp;
                            <input type='reset' value='<fmt:message key="reset" bundle="${bundle}"/>'/></td>
                    </tr>
                </table>
            </form>
        </c:when>
        <c:otherwise>
            <div style="text-align: center">
                <h3><fmt:message key="bookUpdate" bundle="${bundle}"/></h3>
            </div>
            <form action='${pageContext.request.contextPath}/updateBook' method='post' onsubmit='return checkForm()'>
                <input type='hidden' name='id' value='${book.id}'/>
                <table align='center' border='1' width='300px'>
                    <tr>
                        <th><fmt:message key="bookName" bundle="${bundle}"/></th>
                        <td><input type='text' name='bookName' value="${book.bookName}"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="author" bundle="${bundle}"/></th>
                        <td><input type='text' name='bookAuthor' value="${book.bookAuthor}"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="publisher" bundle="${bundle}"/></th>
                        <td><input type='text' name='publisher' value="${book.publisher}"/></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="price" bundle="${bundle}"/></th>
                        <td><input type='text' name='price' value="${book.price}" onblur='checkPrice()'
                                   id='price'/><span id='priceCheck'></span></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="bookNum" bundle="${bundle}"/></th>
                        <td><input type='text' name='bookNum' value="${book.bookNum}" onblur='checkBookNum()'
                                   id='bookNum'/><span id='bookNumCheck'></span></td>
                    </tr>
                    <tr>
                        <th><fmt:message key="publishDate" bundle="${bundle}"/></th>
                        <td><input type='text' name='publishDate' value="${book.publishDate}"
                                   onblur='checkPublishDate()' id='publishDate'/><span id='publishDateCheck'></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2' align='center'>
                            <input type='submit' value='<fmt:message key="save" bundle="${bundle}"/>'/>&nbsp;
                            <input type='reset' value='<fmt:message key="reset" bundle="${bundle}"/>'/></td>
                    </tr>
                </table>
            </form>
        </c:otherwise>
    </c:choose>
    <jsp:include page="../pub/foot.jsp"></jsp:include>
</body>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/checkBook.js' ></script>
</html>
