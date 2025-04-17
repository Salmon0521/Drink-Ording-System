function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Coffee",
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
    let coffeeJson =  JSON.parse($("#coffeeJson")[0].textContent);

    for (let i = 0; i < coffeeJson.length; i++) {
        $("#main").append(
            "<button value='" + coffeeJson[i].name + "' onclick='choose()'>" + coffeeJson[i].name + "</button>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});