const passId = document.getElementById("passWordCheck");
const confirmPassWordId = document.getElementById("confirmPassWordId");

function checkPass() {
    const passWord = document.getElementById("passWord").value;
    const reg = /(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,16}$/i;
    if (reg.test(passWord)) {
        passId.innerHTML = "";
        return true;
    } else {
        passId.innerHTML = "密码由字母数字特殊符号组成6~16位，字母至少出现一次".fontcolor("red");
        return false;
    }
}

function confirmPass() {
    const confirmPassWord = document.getElementById("confirmPassWord").value;
    const passWord = document.getElementById("passWord").value;
    if (confirmPassWord.valueOf() == passWord.valueOf()) {
        confirmPassWordId.innerHTML = "";
        return true;
    } else {
        confirmPassWordId.innerHTML = "密码不匹配".fontcolor("red");
        return false;
    }
}

function checkForm() {
    const passWord = checkPass();
    const confirmPassWord = confirmPass();
    if (passWord && confirmPassWord) {
        return true;
    } else {
        return false;
    }
}
