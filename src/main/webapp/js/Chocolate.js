function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Chocolate",
        type : "POST",
        data: {
            productName : productName
        },
        success : function(response) {
            console.log(response);
            window.location.href = "Customization";
        }
    });
}

function showList() {
    let cocolateJson =  JSON.parse($("#cocolateJson")[0].textContent);

    for (let i = 0; i < cocolateJson.length; i++) {
        $("#main").append(
            "<button value='" + cocolateJson[i].name + "' onclick='choose()'>" + cocolateJson[i].name + "</button>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});