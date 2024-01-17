function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Milk",
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
    let milkJson =  JSON.parse($("#milkJson")[0].textContent);

    for (let i = 0; i < milkJson.length; i++) {
        $("#main").append(
            "<button value='" + milkJson[i].name + "' onclick='choose()'>" + milkJson[i].name + "</button>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});