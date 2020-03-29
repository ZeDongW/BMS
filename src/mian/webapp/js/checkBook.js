const priceId = document.getElementById("priceCheck");
const bookNumId = document.getElementById("bookNumCheck");
const publishDateId = document.getElementById("publishDateCheck");


function checkPrice() {
    const price = document.getElementById("price").value;
    const reg = /^\d+(\.\d*)?|\.\d+$/i;
    if (reg.test(price)) {
        priceId.innerHTML = "";
        return true;
    } else {
        priceId.innerHTML = "价格只能是数字".fontcolor("red");
        return false;
    }
}

function checkBookNum() {
    const bookNum = document.getElementById("bookNum").value;
    const reg = /^\d+$/i;
    if (reg.test(bookNum)) {
        bookNumId.innerHTML = "";
        return true;
    } else {
        bookNumId.innerHTML = "书号只能是整数".fontcolor("red");
        return false;
    }
}

function checkPublishDate() {
    const publishDate = document.getElementById("publishDate").value;
    const reg = /^(((\d{2}(([02468][048])|([13579][26]))[\-]((((0?[13578])|(1[02]))[\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-]((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-]((((0?[13578])|(1[02]))[\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-]((0?[1-9])|(1[0-9])|(2[0-8])))))))$/i;
    if (reg.test(publishDate)) {
        publishDateId.innerHTML = "";
        return true;
    } else {
        publishDateId.innerHTML = "日期格式只能为yyyy-mm-dd".fontcolor("red");
        return false;
    }
}

function checkForm() {
    const price = checkPrice();
    const bookNum = checkBookNum();
    const publishDate = checkPublishDate();
    if (price && bookNum && publishDate) {
        return true;
    } else {
        return false;
    }
}
