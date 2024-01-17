function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Latte",
        type : "POST",
        data: {
            productName : productName
        },
        success : function(response) {
            console.log(response);
            window.location.href = "Sugarice";
        }
    });
}

function showList() {
    let latteJson =  JSON.parse($("#latteJson")[0].textContent);

    for (let i = 0; i < latteJson.length; i++) {
        $("#main").append(
            "<button value='" + latteJson[i].name + "' onclick='choose()'>" + latteJson[i].name + "</button>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});