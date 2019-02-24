function toggleNav() {
    var arrowLeftClass = "fa fa-angle-double-left";
    var arrowRightClass = "fa fa-angle-double-right";
    if ($("#menu").width() > 50) {
        $("#menu").css("width", "0");
        $("#container-fluid").css("margin-left", "0");
        $("#open-close-icon").removeClass(arrowLeftClass).addClass(arrowRightClass);
    } else {
        $("#menu").css("width", "250px");
        $("#container-fluid").css("margin-left", "250px");
        $("#open-close-icon").removeClass(arrowRightClass).addClass(arrowLeftClass);
    }
}

window.onload = function() {
    var chart = new CanvasJS.Chart("chartContainer", {
        responsive : true,
        maintainAspectRatio: false,
        data: [{
            type: "column",
            dataPoints: [{x: 10, y: 10},
                {x: 20, y: 15},
                {x: 30, y: 25},
                {x: 40, y: 30},
                {x: 50, y: 28}]
        }]
    });

    chart.render();
}