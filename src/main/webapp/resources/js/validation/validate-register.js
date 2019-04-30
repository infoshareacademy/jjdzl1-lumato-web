var submitButton = document.getElementById("registration-form:submit-registration-form");
var emailInput = document.getElementById("registration-form:email");
var firstNameInput = document.getElementById("registration-form:firstName");
var lastNameInput = document.getElementById("registration-form:lastName");
var passwordInput = document.getElementById("registration-form:password");
var confirmPasswordInput = document.getElementById("registration-form:confirmPassword");
var isSubmitDisabled;
var emailRegex = /\S+@\S+\.\S+/;
var firstNameRegex = /.+/;
var lastNameRegex = /.+/;
var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;

disableSubmit();

function disableSubmit() {
    submitButton.disabled = true;
    isSubmitDisabled = true;
}

function switchStateIfNeeded() {
    if (submitRequiresStateSwitch()) {
        isSubmitDisabled = !isSubmitDisabled;
        submitButton.disabled = isSubmitDisabled;
    }
}

function submitRequiresStateSwitch() {
    if (allFieldsFilledProperly() && isSubmitDisabled) return true;
    if (!allFieldsFilledProperly() && !isSubmitDisabled) return true;
    return false;
}

function allFieldsFilledProperly() {
    if (!emailInput.value.match(emailRegex)) return false;
    if (!firstNameInput.value.match(firstNameRegex)) return false;
    if (!lastNameInput.value.match(lastNameRegex)) return false;
    if (!passwordInput.value.match(passwordRegex)) return false;
    if (passwordInput.value != confirmPasswordInput.value) return false;
    return true;
}

emailInput.addEventListener("change", switchStateIfNeeded);
firstNameInput.addEventListener("change", switchStateIfNeeded);
lastNameInput.addEventListener("change", switchStateIfNeeded);
passwordInput.addEventListener("change", switchStateIfNeeded);
confirmPasswordInput.addEventListener("change", switchStateIfNeeded);
confirmPasswordInput.addEventListener("change", switchStateIfNeeded);