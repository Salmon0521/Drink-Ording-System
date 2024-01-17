<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/login.css">
    <script src="lib/jquery.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/Register.js"></script>

</head>
<body>
<div class="bg-img">
    <div class="content">
        <header>會員註冊</header>
        <form>
            <div class="field">
                <span class="fa fa-user"></span>
                <input type="text" id="r_account" required placeholder="Username">
            </div>
            <div class="field space">
                <span class="fa fa-lock"></span>
                <input type="password" id="r_password" class="pass-key" required placeholder="Password">

            </div>
            <div class="field space">
                <span class="fa fa-lock"></span>
                <input type="password" id="r_password_check" class="pass-key" required placeholder="Confirm Password">

            </div>
            <div class="field space">
                <span class="fa fa-lock"></span>
                <input type="tel" id="r_tel" class="pass-key" required placeholder="Phone number">

            </div>
            <br/>
            <div style="text-align:center;">
                <button type="button" style="padding: 15px 50px;background-color: #008CBA;" onclick="register()" value="Sign_up" >立即註冊</button>
            </div>
        </form>
        <div class="signup"></div>
    </div>
</div>


</body>
</html>



