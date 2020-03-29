
var passId = document.getElementById("passWordCheck");
var confirmPassWordId = document.getElementById("confirmPassWordId");

function checkPass(){
    var passWord = document.getElementById("passWord").value;
    var reg = /(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,16}$/i;
    if(reg.test(passWord)){
        passId.innerHTML ="";
        return true;
    } else {
        passId.innerHTML = "密码由字母数字特殊符号组成6~16位，字母至少出现一次".fontcolor("red");
        return false;
    }
}

function confirmPass(){
    var confirmPassWord = document.getElementById("confirmPassWord").value;
    var passWord = document.getElementById("passWord").value;
    if(confirmPassWord.valueOf() == passWord.valueOf()){
        confirmPassWordId.innerHTML ="";
        return true;
    } else {
        confirmPassWordId.innerHTML = "密码不匹配".fontcolor("red");
        return false;
    }
}

function checkForm(){
    var passWord = checkPass();
    var confirmPassWord = confirmPass();
    if(passWord && confirmPassWord){
        return true;
    } else {
        return false;
    }
}
