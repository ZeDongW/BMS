<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
            <title><s:text name="addBook"/></title>
        </c:when>
        <c:otherwise>
            <title><s:text name="bookUpdate"/></title>
        </c:otherwise>
    </c:choose>

</head>
<body>
    <jsp:include page="../pub/head.jsp"></jsp:include>
    <c:choose>
        <c:when test="${empty book.id}">
            <div style="text-align: center">
                <h3><s:text name="addBook"/></h3>
            </div>
            <s:form action='books_add' method='post' onsubmit='return checkForm()'>
                <table align='center' border='1' width='300px'>
                    <tr>
                        <th><s:text name="bookName"/></th>
                        <td><s:textfield name='bookName'/></td>
                    </tr>
                    <tr>
                        <th><s:text name="author"/></th>
                        <td><s:textfield name='bookAuthor'/></td>
                    </tr>
                    <tr>
                        <th><s:text name="publisher"/></th>
                        <td><s:textfield name='publisher'/></td>
                    </tr>
                    <tr>
                        <th><s:text name="price"/></th>
                        <td><s:textfield name='price' onblur='checkPrice()' id='price'/><span id='priceCheck'></span></td>
                    </tr>
                    <tr>
                        <th><s:text name="bookNum"/></th>
                        <td><s:textfield name='bookNum' onblur='checkBookNum()' id='bookNum'/><span id='bookNumCheck'></span></td>
                    </tr>
                    <tr>
                        <th><s:text name="publishDate"/></th>
                        <td><input type='date' format="yyyy-MM-dd" name='publishDate' onblur='checkPublishDate()' id='publishDate'/><span id='publishDateCheck'></span></td>
                    </tr>
                    <tr>
                        <td colspan='2' align='center'>
                            <input type='submit' value='<s:text name="save"/>'/>&nbsp;
                            <input type='reset' value='<s:text name="reset"/>'/></td>
                    </tr>
                </table>
            </s:form>
        </c:when>
        <c:otherwise>
            <div style="text-align: center">
                <h3><s:text name="bookUpdate"/></h3>
            </div>
            <s:form action='books_update' method='post' onsubmit='return checkForm()'>
                <s:hidden name='id'/>
                <table align='center' border='1' width='300px'>
                    <tr>
                        <th><s:text name="bookName"/></th>
                        <td><s:textfield name='bookName'/></td>
                    </tr>
                    <tr>
                        <th><s:text name="author"/></th>
                        <td><s:textfield name='bookAuthor'/></td>
                    </tr>
                    <tr>
                        <th><s:text name="publisher"/></th>
                        <td><s:textfield name='publisher'/></td>
                    </tr>
                    <tr>
                        <th><s:text name="price"/></th>
                        <td><s:textfield name='price'  onblur='checkPrice()' id='price'/><span id='priceCheck'></span></td>
                    </tr>
                    <tr>
                        <th><s:text name="bookNum"/></th>
                        <td><s:textfield name='bookNum' onblur='checkBookNum()' id='bookNum'/><span id='bookNumCheck'></span></td>
                    </tr>
                    <tr>
                        <th><s:text name="publishDate"/></th>
                        <td><input type='date' name='publishDate' value='<s:date name="publishDate" format="yyyy-MM-dd"/>' onblur='checkPublishDate()' id='publishDate'/><span id='publishDateCheck'></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan='2' align='center'>
                            <input type='submit' value='<s:text name="save"/>'/>&nbsp;
                            <input type='reset' value='<s:text name="reset"/>'/></td>
                    </tr>
                </table>
            </s:form>
        </c:otherwise>
    </c:choose>
    <jsp:include page="../pub/foot.jsp"></jsp:include>
</body>
<script type='text/javascript' src='${pageContext.request.contextPath}/js/checkBook.js' ></script>
</html>
