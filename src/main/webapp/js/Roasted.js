function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Roasted",
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
    let roastedJson =  JSON.parse($("#roastedJson")[0].textContent);

    for (let i = 0; i < roastedJson.length; i++) {
        $("#main").append(
            "<button value='" + roastedJson[i].name + "' onclick='choose()'>" + roastedJson[i].name + "</button>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});