<%--
  Created by IntelliJ IDEA.
  User: ZeDongW
  Date: 2019/5/24 0024
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>修改用户</title>
</head>
<body>
    <jsp:include page="../pub/head.jsp"></jsp:include>
    <div style="text-align: center">
        <h3>修改用户</h3>
    </div>
    <form action='${pageContext.request.contextPath}/updateUser' method='post' onsubmit='return checkForm()'>
        <input type='hidden' name='id' value='${user.id}'/>
        <table align='center' border='1' width='300px'>
            <tr>
                <th>新密码</th>
                <td><input type='password' name='passWord' value='${user.passWord}' onblur='checkPass()' id='passWord'/><span id='passWordCheck'></span></td>
            </tr>
            <tr>
                <th>确认密码</th>
                <td><input type='password' name='confirmPassWord' onblur='confirmPass()' id='confirmPassWord'/><span id='confirmPassWordId'></span></td>
            </tr>
            <tr>
                <td colspan='2' align='center'>
                    <input type='submit' value='保存'/>&nbsp;
                    <input type='reset' value='重置'/></td>
            </tr>
        </table>
    </form>
    <div style="text-align: center">
        <a href='${pageContext.request.contextPath}/index'>返回主页</a>&nbsp;&nbsp;&nbsp;
        <a href='${pageContext.request.contextPath}/logOut'>安全退出</a>
    </div>
</body>
<script type='text/javascript' src="${pageContext.request.contextPath}/js/checkPass.js" ></script>
</html>
