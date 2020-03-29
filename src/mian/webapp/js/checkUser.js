const userId = document.getElementById("userNameCheck");
const passId = document.getElementById("passWordCheck");


function arr(value) {
    const form = document.getElementById("form");
    form.setAttribute("action", value)
}

function checkUserName() {
    const userName = document.getElementById("userName").value;
    const reg = /^[a-zA-Z0-9]{2,16}$/i;
    if (reg.test(userName)) {
        userId.innerHTML = "";
        return true;
    } else {
        userId.innerHTML = "用户名由字母和数字组成长度2`16位".fontcolor("red");
        return false;
    }
}

function checkPassWord() {
    const passWord = document.getElementById("passWord").value;
    const reg = /^(?![0-9\W]+$)[a-zA-Z0-9\W]{6,16}$/i;
    if (reg.test(passWord)) {
        passId.innerHTML = "";
        return true;
    } else {
        passId.innerHTML = "密码由字母数字特殊符号组成6~16位，字母至少出现一次".fontcolor("red");
        return false;
    }
}

function checkForm() {
    const userName = checkUserName();
    const passWord = checkPassWord();
    return userName && passWord;
}
