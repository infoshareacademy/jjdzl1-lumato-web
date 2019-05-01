let formToValidate = {
    submit: {
        input: false,
        element: document.getElementById("registration-form:submit-registration-form"),
        color: "",
        active: false
    },
    email: {
        input: true,
        element: document.getElementById("registration-form:email"),
        color: "",
        ok: false,
        popoverMsg: "Email should match pattern: something@something.something",
        regex: /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/
    },
    firstName: {
        input: true,
        element: document.getElementById("registration-form:firstName"),
        color: "",
        ok: false,
        popoverMsg: "First name must have at least 1 character",
        regex: /.+/
    },
    lastName: {
        input: true,
        element: document.getElementById("registration-form:lastName"),
        color: "",
        ok: false,
        popoverMsg: "First name must have at least 1 character",
        regex: /.+/
    },
    password: {
        input: true,
        element: document.getElementById("registration-form:password"),
        color: "",
        ok: false,
        popoverMsg: "Password must be at least 8-character-long and containt: 1 small letter, 1 large letter, 1 digit, 1 special character.",
        popoverPosition: "bottom",
        regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/
    },
    confirmPassword: {
        input: true,
        element: document.getElementById("registration-form:confirmPassword"),
        color: "",
        ok: false,
        popoverMsg: "Passwords must match!",
        popoverPosition: "bottom"
    },
    otherVerifyFunctions: {
        checkIfPasswordsMatch: function() {
            formToValidate.confirmPassword.ok = formToValidate.password.element.value === formToValidate.confirmPassword.element.value;
        }
    }
}