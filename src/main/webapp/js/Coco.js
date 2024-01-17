function choose() {
    let productName = event.target.value;
    $.ajax({
        url : "Coco",
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
    let cocoJson =  JSON.parse($("#cocoJson")[0].textContent);

    for (let i = 0; i < cocoJson.length; i++) {
        $("#main").append(
            "<button value='" + cocoJson[i].name + "' onclick='choose()'>" + cocoJson[i].name + "</button>"
        );
    }
}

function init() {
    showList();
}

$(document).ready(function(){
    init();
});