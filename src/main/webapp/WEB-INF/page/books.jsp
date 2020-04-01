<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/23 0023
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <title><s:text name="BooksManager"/></title>
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
    <h3><s:text name="BooksManager"/></h3>
</div>
<table align='center' border='1' width='800px'>
    <tr>
        <th><s:a href='books_list?sort=bookId'><s:text name="ID"/></s:a></th>
        <th><s:a href='books_list?sort=bookName'><s:text name="bookName"/></s:a></th>
        <th><s:a href='books_list?sort=bookAuthor'><s:text name="author"/></s:a></th>
        <th><s:a href='books_list?sort=bookPublisher'><s:text name="publisher" /></s:a></th>
        <th><s:a href='books_list?sort=bookPrice'><s:text name="price"/></s:a></th>
        <th><s:a href='books_list?sort=bookNum'><s:text name="bookNum"/></s:a></th>
        <th><s:a href='books_list?sort=bookPublishDate'><s:text name="publishDate"/></s:a></th>
        <th><s:text name="option"/></th>
    </tr>
    <s:if test="%{#request.booksPb.pageData.size()!=0}">
        <s:iterator value="%{#request.booksPb.pageData}" var="book">
            <tr>
                <td><s:property value="#book.id"/></td>
                <td><s:property value="#book.bookName"/></td>
                <td><s:property value="#book.bookAuthor"/></td>
                <td><s:property value="#book.publisher"/></td>
                <td><s:property value="#book.price"/></td>
                <td><s:property value="#book.bookNum"/></td>
                <td><s:date format="yyyy-MM-dd" name="%{#book.publishDate}"/></td>
                <td><s:a href='books_query?id=%{#book.id}'><s:text name="update"/></s:a>&nbsp;
                    <s:a href='books_delete?id=%{#book.id}'><s:text name="delete"/></s:a></td>
            </tr>
        </s:iterator>
    </s:if>
    <tr>
        <td colspan='8' align='center'><s:a href='books_query'><s:text name="addBook"/></s:a>
        </td>
    </tr>
    <tr>
        <td colspan="8" align="center">
            <s:text name="pageCount"/>
            <s:textfield style="width: 20px" value='%{#request.booksPb.pageCount}' onkeyup="this.value=this.value.replace(/\D/g,'')" onblur='checkPageCount()' id='pageCount'/>&nbsp;&nbsp;
            <s:text name="current"/>
            <s:textfield style="width: 20px" value='%{#request.booksPb.currPage}' onkeyup="this.value=this.value.replace(/\D/g,'')" onblur='checkPageCount()' id='currPage'/>
            /<s:property value="#request.booksPb.totalPage"/>
            <s:text name="page"/>&nbsp;&nbsp;
            <c:choose>
                <c:when test="${booksPb.currPage  == 1 && booksPb.currPage != booksPb.totalPage}">
                    <s:text name="first"/>&nbsp;&nbsp;<s:text name="previous"/>
                    <s:a href="books_list?currPage=%{#request.booksPb.currPage+1}&pageCount=%{#request.booksPb.pageCount}">
                        <s:text name="next"/> </s:a>&nbsp;&nbsp;
                    <s:a href="books_list?currPage=%{#request.booksPb.totalPage}&pageCount=%{#request.booksPb.pageCount}">
                        <s:text name="last"/></s:a>
                </c:when>
                <c:when test="${booksPb.currPage == booksPb.totalPage && booksPb.currPage  != 1 }">
                    <s:a href="books_list?currPage=1&pageCount=%{#request.booksPb.pageCount}"><s:text name="first"/></s:a>&nbsp;&nbsp;
                    <s:a href="books_list?currPage=%{#request.booksPb.currPage-1}&pageCount=%{#request.booksPb.pageCount}"><s:text name="previous"/> </s:a>&nbsp;&nbsp;
                    <s:text name="next"/>&nbsp;&nbsp;<s:text name="last"/>
                </c:when>
                <c:when test="${booksPb.currPage == booksPb.totalPage && booksPb.currPage  == 1 }">
                    <s:text name="first"/>&nbsp;&nbsp;<s:text name="previous"/>&nbsp;&nbsp;
                    <s:text name="next"/>&nbsp;&nbsp;<s:text name="last"/>
                </c:when>
                <c:otherwise>
                    <s:a href="books_list?currPage=1&pageCount=%{#request.booksPb.pageCount}"><s:text name="first"/></s:a>&nbsp;&nbsp;
                    <s:a href="books_list?currPage=%{#request.booksPb.currPage-1}&pageCount=%{#request.booksPb.pageCount}"><s:text name="previous"/> </s:a>&nbsp;&nbsp;
                    <s:a href="books_list?currPage=%{#request.booksPb.currPage+1}&pageCount=%{#request.booksPb.pageCount}"><s:text name="next"/> </s:a>&nbsp;&nbsp;
                    <s:a href="books_list?currPage=%{#request.booksPb.totalPage}&pageCount=%{#request.booksPb.pageCount}"><s:text name="last"/></s:a>
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
        window.location.href = "books_list?currPage=" + currPage + "&pageCount=" + pageCount;
    }
</script>
</html>
