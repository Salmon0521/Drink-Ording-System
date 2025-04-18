function showList() {
    let productInOrderJson =  JSON.parse($("#productInOrderJson")[0].textContent);
    let count = 0;
    for (let i = 0; i < productInOrderJson.length; i++) {
        let total = productInOrderJson[i].price * productInOrderJson[i].quantity;
        count += total;
        $("#productList").append(
            "<tbody>" +
            "<tr>" +
            "<td>" + productInOrderJson[i].name + "</td>" +
            "<td>" + productInOrderJson[i].sugar + "</td>" +
            "<td>" + productInOrderJson[i].ice + "</td>" +
            "<td>" + productInOrderJson[i].size + "</td>" +
            "<td> NT$" + productInOrderJson[i].price + "</td>" +
            "<td>" + productInOrderJson[i].quantity + "</td>" +
            "<td> NT$" + total + "</td>" +
            "</tr>" +
            "</tbody>"
        );
    }
    document.getElementById("total").innerHTML = "NT$" + count;
}

function init() {
    showList();
}

$(document).ready(function () {
    init();
});