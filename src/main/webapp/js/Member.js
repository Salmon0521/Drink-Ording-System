function query() {
    let date = event.target.value;

    $.ajax({
        url : "OrderRecord",
        type : "POST",
        data: {
            date : date
        },
        success : function(response) {
            console.log(response);
            window.location.href = "OrderRecord";
        }
    });
}

function showList() {
    let ordersJson =  JSON.parse($("#ordersJson")[0].textContent);

    for (let i = 1; i < ordersJson.length+1; i++) {
        $("#orderList").append(
            "<tbody>" +
            "<tr>" +
            "<td>" + i
            + "</td>" +
            "<td>" + ordersJson[i-1].amount + "</td>" +
            "<td>" + ordersJson[i-1].dates + "</td>" +
            "<td>" +
            "<strong><button class=\"check-order-btn\" type=\"button\"value='" + ordersJson[i-1].dates + "'onclick=\"query()\">" +
            "查閱</button></strong>" +
            "</td>" +
            "</tr>" +
            "</tbody>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function () {
    init();
});