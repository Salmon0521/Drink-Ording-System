<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>member information</title>
    <link rel="stylesheet" href="css/member.css">
    <script src="lib/jquery.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/Member.js"></script>

</head>

<body>
<header>
    <h1>我的帳戶</h1>
</header>
<span id="ordersJson" style="display: none;">${ordersJson}</span>
<div class="member-container">
    <div class="member-detailss">
    <div class="member-details">

        <h2>會員資料</h2>
        <p><strong>帳號：${sessionScope.account}</strong> </p>
    </div>

    <div class="member-details">
        <h2>會員等級</h2>
        <p><strong>等級：</strong> <span class="membership-level">${sessionScope.levels}</span></p>
    </div>
        <div class="member-details">

            <p><a href="CustomerMenu">返回</a></p></div>
        </div>
    <div class="member-details">
        <h2>訂單紀錄</h2>
        <table id="orderList">
            <thead>
            <tr>
                <th>訂單編號</th>
                <th>金額</th>
                <th>日期</th>
                <th></th>
            </tr>
            </thead>

        </table>

    </div>
</div>
</body>
</html>