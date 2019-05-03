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