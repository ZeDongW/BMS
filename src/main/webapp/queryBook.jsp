<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/24 0024
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <c:choose>
        <c:when test="${empty book.id}">
            <title>添加书本</title>
        </c:when>
        <c:otherwise>
            <title>修改书本</title>
        </c:otherwise>
    </c:choose>

</head>
<body>
    <center>
        欢迎登陆， <font size='+2' color='green'>${user.userName}</font><br />
        <c:choose>
            <c:when test="${empty book.id}">
                <h3>添加书本</h3>
                </center>
                <form action='${pageContext.request.contextPath}/addBook' method='post' onsubmit='return checkForm()'>
                    <table align='center' border='1' width='300px'>
                        <tr>
                            <th>书名</th>
                            <td><input type='text' name='bookName'/></td>
                        </tr>
                        <tr>
                            <th>作者</th>
                            <td><input type='text' name='bookAuthor'/></td>
                        </tr>
                        <tr>
                            <th>出版社</th>
                            <td><input type='text' name='publisher'/></td>
                        </tr>
                        <tr>
                            <th>价格</th>
                            <td><input type='text' name='price' onblur='checkPrice()' id='price'/><span id='priceCheck'></span></td>
                        </tr>
                        <tr>
                            <th>书号</th>
                            <td><input type='text' name='bookNum' onblur='checkBookNum()' id='bookNum'/><span id='bookNumCheck'></span></td>
                        </tr>
                        <tr>
                            <th>出版日期</th>
                            <td><input type='date' dataformatas="yyyy-MM-dd" name='publishDate' onblur='checkPublishDate()' id='publishDate'/><span id='publishDateCheck'></span></td>
                        </tr>
                        <tr>
                            <td colspan='2' align='center'>
                                <input type='submit' value='保存'/>&nbsp;
                                <input type='reset' value='重置'/></td>
                        </tr>
                    </table>
                </form>
            </c:when>
            <c:otherwise>
                <h3>修改书本</h3>
                </center>
                <form action='${pageContext.request.contextPath}/updateBook' method='post' onsubmit='return checkForm()'>
                    <input type='hidden' name='id' value='${book.id}'/>
                    <table align='center' border='1' width='300px'>
                        <tr>
                            <th>书名</th>
                            <td><input type='text' name='bookName' value="${book.bookName}"/></td>
                        </tr>
                        <tr>
                            <th>作者</th>
                            <td><input type='text' name='bookAuthor' value="${book.bookAuthor}"/></td>
                        </tr>
                        <tr>
                            <th>出版社</th>
                            <td><input type='text' name='publisher' value="${book.publisher}"/></td>
                        </tr>
                        <tr>
                            <th>价格</th>
                            <td><input type='text' name='price' value="${book.price}" onblur='checkPrice()' id='price'/><span id='priceCheck'></span></td>
                        </tr>
                        <tr>
                            <th>书号</th>
                            <td><input type='text' name='bookNum' value="${book.bookNum}" onblur='checkBookNum()' id='bookNum'/><span id='bookNumCheck'></span></td>
                        </tr>
                        <tr>
                            <th>出版日期</th>
                            <td><input type='text' name='publishDate' value="${book.publishDate}" onblur='checkPublishDate()' id='publishDate'/><span id='publishDateCheck'></span></td>
                        </tr>
                        <tr>
                            <td colspan='2' align='center'>
                                <input type='submit' value='保存'/>&nbsp;
                                <input type='reset' value='重置'/></td>
                        </tr>
                    </table>
                </form>
            </c:otherwise>
        </c:choose>
    <center>
        <a href='${pageContext.request.contextPath}/index'>返回主页</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/queryUser?id=${user.id}'>用户修改</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/logOut'>安全退出</a>
    </center>
</body>
<script type='text/javascript' src='js/checkBook.js' ></script>
</html>
