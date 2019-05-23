window.onload = function(e) {
    updateNavbarLinkHighlighted();
}

var path;
var active;
var newActiveItemId;

function updateNavbarLinkHighlighted(){
    active = document.querySelector(".active");
    if (active != null) {
        removeActive();
    }
    highlightNew();
}

function removeActive(){
    active.classList.remove("active");
}

function highlightNew() {
    path = location.pathname.substring(1);

    newActiveItemId = pathMatches(/start/) ? "nav-home" :
    pathMatches(/user-edit|user-change-password|user-delete/ ) ? "nav-account" :
    pathMatches(/cars-input/ ) ? "nav-cars-input" :
    pathMatches(/fuel-input|cost-input/) ? "nav-register-data" :
    pathMatches(/charts/) ? "nav-stats" :
    pathMatches(/contact/) ? "nav-contact" : "";

    var element = document.getElementById(newActiveItemId);
    if (element != null) {
        element.classList.add("active");
    }
}

function pathMatches(regex) {
    if (path.match(regex)) {
        return true;
    }
    return false;
}