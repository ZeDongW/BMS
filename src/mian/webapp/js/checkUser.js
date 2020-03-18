
var userId = document.getElementById("userNameCheck");
var passId = document.getElementById("passWordCheck");
function arr(value) {
    var form = document.getElementById("form");
    form.setAttribute("action",value)
}

function checkUserName() {
    var userName = document.getElementById("userName").value;
    var reg = /^[a-zA-Z0-9]{2,16}$/i;
    if(reg.test(userName)){
        userId.innerHTML ="";
        return true;
    } else {
        userId.innerHTML = "用户名由字母和数字组成长度2`16位".fontcolor("red");
        return false;
    }
}

function checkPassWord(){
    var passWord = document.getElementById("passWord").value;
    var reg = /^(?![0-9\W]+$)[a-zA-Z0-9\W]{6,16}$/i;
    if(reg.test(passWord)){
        passId.innerHTML ="";
        return true;
    } else {
        passId.innerHTML = "密码由字母数字特殊符号组成6~16位，字母至少出现一次".fontcolor("red");
        return false;
    }
}

function checkForm(){
    var userName = checkUserName();
    var passWord = checkPassWord();
    if(userName && passWord){
        return true;
    } else {
        return false;
    }
}
