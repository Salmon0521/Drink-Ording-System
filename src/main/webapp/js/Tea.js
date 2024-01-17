function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Tea",
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
    let teaJson =  JSON.parse($("#teaJson")[0].textContent);

    for (let i = 0; i < teaJson.length; i++) {
        $("#main").append(
            "<button value='" + teaJson[i].name + "' onclick='choose()'>" + teaJson[i].name + "</button>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});