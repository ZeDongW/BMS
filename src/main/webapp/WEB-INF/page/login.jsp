<%@page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>图书管理系统</title>
</head>
<body>
<div style="text-align: center">
    <h2>欢迎来到图书管理系统</h2>
    <form action="" method="post" id="form" onsubmit="return checkForm()">
        用户名：<input type="text" name="userName" onblur="checkUserName()" id="userName"/><span
            id="userNameCheck"></span><br>
        <div style="align-content: center; color: red">${message}</div>
        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="passWord" onblur="checkPassWord()" id="passWord"/><span
            id="passWordCheck"></span><br>
        <div style="align-content: center; color: green">${success}</div>
        <input type="submit" onclick='arr("${pageContext.request.contextPath}/regist")' value="注册"/>
        <input type="submit" onclick='arr("${pageContext.request.contextPath}/login")' value="登录"/>
    </form>
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/checkUser.js"></script>
</html>
