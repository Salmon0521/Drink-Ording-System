function register() {
    let r_account = $("#r_account").val();
    let r_password = $("#r_password").val();
    let r_password_check = $("#r_password_check").val();
    let r_tel = $("#r_tel").val();

    if (r_account != "" && r_password != "" && r_tel != "") {
        if(r_password != r_password_check){
            alert("請確認填寫相同的密碼");
        }
        else if(r_tel.length != 10){
            alert("請確認填寫正確的【10位數】手機號碼");
        }
        else {
            $.ajax({
                type: 'post',
                url: 'Register',
                data: {
                    "R_Account": r_account,
                    "R_Password": r_password,
                    "R_Tel": r_tel,
                },
                success: function (response) {
                    console.log(response.status);
                    if (response === "1") {
                        alert("帳號已被註冊 請重新輸入!");
                        window.location.href = "Register";
                    }
                    else if (response === "0"){
                        alert("帳號成功註冊!");
                        window.location.href = "Login";
                    }
                }
            });
        }

    } else {
        alert("請填寫完整的資料!");
    }
}