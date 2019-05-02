/*
to enable datepicker, there need to be 2 libraries imported:
<link rel="stylesheet" href="/resources/css/bootstrap-datepicker.min.css"/>
<script src="/resources/js/bootstrap-datepicker.js" type="text/javascript"></script>
 */

let formBorderColors = {
    okData: "green",
    wrongData: "red"
}

initialize();

function initialize() {
    for (var index in formToValidate) {
        if (formToValidate[index].input) {
            formToValidate[index].element.addEventListener("input", update);
            formToValidate[index].element.addEventListener("change", update);
        }
    }

    formToValidate.submit.element.disabled = true;
    updateColors();
}

function update() {
    updateState();
    updateColors();
}

$(document).ready(function(){
    for (var index in formToValidate) {
        if ('popoverMsg' in formToValidate[index]){
            let data = {
                content: formToValidate[index].popoverMsg,
                trigger: "focus",
                placement: "top"
            };
            if ('popoverPosition' in formToValidate[index]) {
                data.placement = formToValidate[index].popoverPosition;
            }
            $(formToValidate[index].element).popover(data);
        }
    }
});

function updateColors() {
    for(var index in formToValidate) {
        if (formToValidate[index].input) {
            if (formToValidate[index].ok && formToValidate[index].color !== formBorderColors.okData) {
                formToValidate[index].color = formBorderColors.okData;
                formToValidate[index].element.style.borderColor = formToValidate[index].color;
            }
            if (!formToValidate[index].ok && formToValidate[index].color !== formBorderColors.wrongData) {
                formToValidate[index].color = formBorderColors.wrongData;
                formToValidate[index].element.style.borderColor = formToValidate[index].color;
            }
        }
    }
}

function updateState() {
    for (var index in formToValidate) {
        if ('regex' in formToValidate[index]) {
            formToValidate[index].ok = formToValidate[index].element.value.match(formToValidate[index].regex);
        }
    }
    runOtherVerifyFunctions();
    formToValidate.submit.active = shouldButtonBeActive();
    formToValidate.submit.element.disabled = !formToValidate.submit.active;
}

function shouldButtonBeActive() {
    for (var index in formToValidate) {
        if ('ok' in formToValidate[index]) {
            if (!formToValidate[index].ok) return false;
        }
    }
    return true;
}

function runOtherVerifyFunctions() {
    for (var index in formToValidate.otherVerifyFunctions) {
        formToValidate.otherVerifyFunctions[index].call();
    }
}