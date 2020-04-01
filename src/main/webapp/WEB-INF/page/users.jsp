<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <title><s:text name="userManage"/></title>
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
    <h3><s:text name="userManage"/></h3>
</div>
<table align='center' border='1' width='800px'>
    <tr>
        <th><s:a href='users_list?sort=userId'><s:text name="ID" /></s:a></th>
        <th><s:a href='users_list?sort=userName'><s:text name="userName"/></s:a></th>
        <c:choose>
            <c:when test="${'admin' eq loginUser.userName}">
                <th><s:text name="option"/></th>
                </tr>
                <s:if test='%{#request.usersPb.pageData.size() != 0}'>
                    <s:iterator value="%{#request.usersPb.pageData}" var="user">
                        <tr>
                            <td><s:property value="#user.id"/></td>
                            <td><s:property value="#user.userName"/></td>
                            <td><s:a href='users_query?id=%{#user.id}'><s:text name="passWordUpdate"/></s:a>&nbsp;
                                <s:a href='users_delete?id=%{#user.id}'><s:text name="delete"/></s:a></td>
                        </tr>
                    </s:iterator>
                </s:if>
            </c:when>
            <c:otherwise>
                </tr>
                <s:if test="%{#request.usersPb.pageData.size() != 0}">
                    <s:iterator value="%{#request.usersPb.pageData}" var="user">
                        <tr>
                            <td><s:property value="#user.id"/></td>
                            <td><s:property value="#user.userName"/></td>
                        </tr>
                    </s:iterator>
                </s:if>
            </c:otherwise>
        </c:choose>
    <tr>
        <td colspan="3" align="center">
            <s:text name="pageCount"/><s:textfield type="text" style="width: 20px" value='%{#request.usersPb.pageCount}' onkeyup="this.value=this.value.replace(/\D/g,'')" onblur='checkPageCount()' id='pageCount'/>&nbsp;&nbsp;
            <s:text name="current"/>
            <s:textfield style="width: 20px" value='%{#request.usersPb.currPage}' onkeyup="this.value=this.value.replace(/\D/g,'')" onblur='checkPageCount()' id='currPage'/>
            /<s:property value="%{#request.usersPb.totalPage}"/><s:text name="page"/>&nbsp;&nbsp;
            <c:choose>
                <c:when test="${usersPb.currPage  == 1 && usersPb.currPage != usersPb.totalPage}">
                    <s:text name="first"/>&nbsp;&nbsp;<s:text name="previous"/>
                    <s:a href="users_list?currPage=%{#request.usersPb.currPage+1}&pageCount=%{#request.usersPb.pageCount}"><s:text name="next"/> </s:a>&nbsp;&nbsp;
                    <s:a href="users_list?currPage=%{#request.usersPb.totalPage}&pageCount=%{#request.usersPb.pageCount}"><s:text name="last"/></s:a>
                </c:when>
                <c:when test="${usersPb.currPage == usersPb.totalPage && usersPb.currPage  != 1}">
                    <s:a href="users_list?currPage=1&pageCount=%{#request.usersPb.pageCount}"><s:text name="first"/></s:a>&nbsp;&nbsp;
                    <s:a href="users_list?currPage=%{#request.usersPb.currPage-1}&pageCount=%{#request.usersPb.pageCount}"><s:text name="previous"/> </s:a>&nbsp;&nbsp;
                    <s:text name="next"/>&nbsp;&nbsp;<s:text name="last"/>
                </c:when>
                <c:when test="${usersPb.currPage == usersPb.totalPage && usersPb.currPage  == 1}">
                    <s:text name="first"/>&nbsp;&nbsp;<s:text name="previous"/>&nbsp;&nbsp;
                    <s:text name="next"/>&nbsp;&nbsp;<s:text name="last"/>
                </c:when>
                <c:otherwise>
                    <s:a href="users_list?currPage=1&pageCount=%{#request.usersPb.pageCount}"><s:text name="first"/></s:a>&nbsp;&nbsp;
                    <s:a href="users_list?currPage=%{#request.usersPb.currPage-1}&pageCount=%{#request.usersPb.pageCount}"><s:text name="previous"/> </s:a>&nbsp;&nbsp;
                    <s:a href="users_list?currPage=%{#request.usersPb.currPage+1}&pageCount=%{#request.usersPb.pageCount}"><s:text name="next"/> </s:a>&nbsp;&nbsp;
                    <s:a href="users_list?currPage=%{#request.usersPb.totalPage}&pageCount=%{#request.usersPb.pageCount}"><s:text name="last"/></s:a>
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
        window.location.href = "users_list?currPage=" + currPage + "&pageCount=" + pageCount;
    }
</script>
</html>
