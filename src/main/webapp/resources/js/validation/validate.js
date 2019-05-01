let borderColors = {
    okData: "green",
    wrongData: "red"
}

initialize();

function initialize() {
    for (var index in form) {
        if (form[index].input) {
            form[index].element.addEventListener("input", update);
            form[index].element.addEventListener("change", update);
        }
    }

    form.submit.element.disabled = true;
    updateColors();
}

function update() {
    updateState();
    updateColors();
}

$(document).ready(function(){
    for (var index in form) {
        if ('popoverMsg' in form[index]){
            let data = {
                content: form[index].popoverMsg,
                trigger: "focus",
                placement: "bottom"
            };
            $(form[index].element).popover(data);
        }
    }
});

function updateColors() {
    for(var index in form) {
        if (form[index].input) {
            if (form[index].ok && form[index].color !== borderColors.okData) {
                form[index].color = borderColors.okData;
                form[index].element.style.borderColor = form[index].color;
            }
            if (!form[index].ok && form[index].color !== borderColors.wrongData) {
                form[index].color = borderColors.wrongData;
                form[index].element.style.borderColor = form[index].color;
            }
        }
    }
}

function updateState() {
    for (var index in form) {
        if ('regex' in form[index]) {
            form[index].ok = form[index].element.value.match(form[index].regex);
        }
    }
    runOtherVerifyFunctions();
    form.submit.active = shouldButtonBeActive();
    form.submit.element.disabled = !form.submit.active;
}

function shouldButtonBeActive() {
    for (var index in form) {
        if ('ok' in form[index]) {
            if (!form[index].ok) return false;
        }
    }
    return true;
}

function runOtherVerifyFunctions() {
    for (var index in form.otherVerifyFunctions) {
        form.otherVerifyFunctions[index].call();
    }
}