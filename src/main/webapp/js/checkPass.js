function checkPass(){

    const passId = document.getElementById("passWordCheck");
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
    const confirmPassWordId = document.getElementById("confirmPassWordId");
    const confirmPassWord = document.getElementById("confirmPassWord").value;
    const passWord = document.getElementById("passWord").value;
    if (confirmPassWord.valueOf() === passWord.valueOf()) {
        confirmPassWordId.innerHTML = "";
        return true;
    } else {
        confirmPassWordId.innerHTML = "密码不匹配".fontcolor("red");
        return false;
    }
}

function checkForm(){
    return checkPass() && confirmPass();
}
