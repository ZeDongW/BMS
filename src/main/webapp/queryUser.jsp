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
    <center>欢迎登陆， <font size='+2' color='green'>${user.userName}</font><br />
        <h3>修改用户</h3>
    </center>
    <form action='${pageContext.request.contextPath}/updateUser' method='post' onsubmit='return checkForm()'>
        <input type='hidden' name='id' value='${user1.id}'/>
        <table align='center' border='1' width='300px'>
            <tr>
                <th>用户名</th>
                <td><input type='text' name='userName' value='${user1.userName}' onblur='checkUserName()' id='userName'/><span id='userNameCheck'></span></td>
            </tr>
            <tr>
                <th>密码</th>
                <td><input type='password' name='passWord' value='${user1.passWord}' onblur='checkPassWord()' id='passWord'/><span id='passWordCheck'></span></td>
                </tr>
            <tr>
                <td colspan='2' align='center'>
                    <input type='submit' value='保存'/>&nbsp;
                    <input type='reset' value='重置'/></td>
             </tr>
        </table>
        <center>
            <a href='${pageContext.request.contextPath}/index'>返回主页</a>&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/logOut'>安全退出</a>
        </center>
    </form>
</body>
<script type='text/javascript' src='js/checkUser.js' ></script>
</html>
