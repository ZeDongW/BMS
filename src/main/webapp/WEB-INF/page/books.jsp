<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <jsp:include page="../pub/head.jsp"></jsp:include>
    <div style="text-align: center">
        <h3>书本管理</h3>
    </div>
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
                    <td><a href='${pageContext.request.contextPath}/queryBook?id=${book.id}'>修改</a>&nbsp;<a
                            href='${pageContext.request.contextPath}/deleteBook?id=${book.id}'>删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <tr>
            <td colspan='8' align='center'><a href='${pageContext.request.contextPath}/queryBook'>添加书本</a></td>
        </tr>
        <tr>
            <td colspan="8" align="center">
                每页显示行数<input type="text" style="width: 20px" value="${booksPb.pageCount}"
                             onkeyup="this.value=this.value.replace(/\D/g,'')" onblur='checkPageCount()'
                             id='pageCount'/>&nbsp;&nbsp;
                当前<input type="text" style="width: 20px" value="${booksPb.currPage}"
                         onkeyup="this.value=this.value.replace(/\D/g,'')" onblur='checkPageCount()'
                         id='currPage'/>/${booksPb.totalPage}页&nbsp;&nbsp;
                <c:choose>
                    <c:when test="${booksPb.currPage  == 1 && booksPb.currPage != booksPb.totalPage}">
                        首页&nbsp;&nbsp;上一页
                        <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.currPage+1}&pageCount=${booksPb.pageCount}">下一页 </a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.totalPage}&pageCount=${booksPb.pageCount}">末页</a>
                    </c:when>
                    <c:when test="${booksPb.currPage == booksPb.totalPage && booksPb.currPage  != 1 }">
                        <a href="${pageContext.request.contextPath}/books?currPage=1&pageCount=${booksPb.pageCount}">首页</a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.currPage-1}&pageCount=${booksPb.pageCount}">上一页 </a>&nbsp;&nbsp;
                        下一页&nbsp;&nbsp;末页
                    </c:when>
                    <c:when test="${booksPb.currPage == booksPb.totalPage && booksPb.currPage  == 1 }">
                        首页&nbsp;&nbsp;上一页&nbsp;&nbsp;
                        下一页&nbsp;&nbsp;末页
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/books?currPage=1&pageCount=${booksPb.pageCount}">首页</a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.currPage-1}&pageCount=${booksPb.pageCount}">上一页 </a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.currPage+1}&pageCount=${booksPb.pageCount}">下一页 </a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/books?currPage=${booksPb.totalPage}&pageCount=${booksPb.pageCount}">末页</a>
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
