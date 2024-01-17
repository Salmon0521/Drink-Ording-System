function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Hand",
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
    let handJson =  JSON.parse($("#handJson")[0].textContent);

    for (let i = 0; i < handJson.length; i++) {
        $("#main").append(
            "<button value='" + handJson[i].name + "' onclick='choose()'>" + handJson[i].name + "</button>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});