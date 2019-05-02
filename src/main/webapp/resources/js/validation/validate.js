/*
Import those 4 on the bottom of page (or at least 2 if you don't use datepicker anywhere):

To activate datepickers (if they are used):
<link rel="stylesheet" href="/resources/css/bootstrap-datepicker.min.css"/>
<script src="/resources/js/bootstrap-datepicker.js" type="text/javascript"></script>

Pass form validation object, f.e.:
<script src="/resources/js/validation/validate-fuel-input.js" type="text/javascript"></script>

Pass this script (validate.js):
<script src="/resources/js/validation/validate.js" type="text/javascript"></script>

regex for dd-mm-yyyy:
/^(((((0[1-9])|(1\d)|(2[0-8]))-((0[1-9])|(1[0-2])))|((31-((0[13578])|(1[02])))|((29|30)-((0[1,3-9])|(1[0-2])))))-((20[0-9][0-9]))|(29-02-20(([02468][048])|([13579][26]))))$/
 */

let formBorderColors = {
    okData: "green",
    wrongData: "red"
}

function update() {
    updateState();
    updateColors();
}

function addDatepickersIfDeclared() {
    for (var index in formToValidate) {
        if ('datePickerData' in formToValidate[index]) {
            let data = formToValidate[index].datePickerData;
            $(formToValidate[index].formElement).datepicker(data);
            $(formToValidate[index].formElement).datepicker().on('changeDate', update);
        }
    }
}

function addPopovers() {
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
            $(formToValidate[index].formElement).popover(data);
        }
    }
}

function addEventListenersToForm() {
    for (var index in formToValidate) {
        if (formToValidate[index].input) {
            formToValidate[index].formElement.addEventListener("input", update);
            formToValidate[index].formElement.addEventListener("change", update);
        }
    }
}

$(document).ready(function(){
    addEventListenersToForm();
    formToValidate.submit.formElement.disabled = true;
    update();
    // updateColors();
    addPopovers();
    addDatepickersIfDeclared();
});

function updateColors() {
    for(var index in formToValidate) {
        if (formToValidate[index].input) {
            if (formToValidate[index].ok && formToValidate[index].color !== formBorderColors.okData) {
                formToValidate[index].color = formBorderColors.okData;
                formToValidate[index].formElement.style.borderColor = formToValidate[index].color;
            }
            if (!formToValidate[index].ok && formToValidate[index].color !== formBorderColors.wrongData) {
                formToValidate[index].color = formBorderColors.wrongData;
                formToValidate[index].formElement.style.borderColor = formToValidate[index].color;
            }
        }
    }
}

function updateState() {
    for (var index in formToValidate) {
        if ('regex' in formToValidate[index]) {
            formToValidate[index].ok = formToValidate[index].formElement.value.match(formToValidate[index].regex);
        }
    }
    runOtherVerifyFunctions();
    formToValidate.submit.active = shouldButtonBeActive();
    formToValidate.submit.formElement.disabled = !formToValidate.submit.active;
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