let form = {
    submit: {
        input: false,
        element: document.getElementById("registration-form:submit-registration-form"),
        color: "",
        active: false,
    },
    email: {
        input: true,
        element: document.getElementById("registration-form:email"),
        color: "",
        ok: false,
        regex: /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/
    },
    firstName: {
        input: true,
        element: document.getElementById("registration-form:firstName"),
        color: "",
        ok: false,
        regex: /.+/
    },
    lastName: {
        input: true,
        element: document.getElementById("registration-form:lastName"),
        color: "",
        ok: false,
        regex: /.+/
    },
    password: {
        input: true,
        element: document.getElementById("registration-form:password"),
        color: "",
        ok: false,
        regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/
    },
    confirmPassword: {
        input: true,
        element: document.getElementById("registration-form:confirmPassword"),
        color: "",
        ok: false
    }
}

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
    /* update all inputs that have regex */
    for (var index in form) {
        if (form[index].regex) {
            form[index].ok = form[index].element.value.match(form[index].regex);
        }
    }
    /* update inputs with other requirements */
    form.confirmPassword.ok = form.password.element.value === form.confirmPassword.element.value;
    /* enable/disable submit button */
    form.submit.active = form.email.ok && form.firstName.ok && form.lastName.ok && form.password.ok && form.confirmPassword.ok;
    form.submit.element.disabled = !form.submit.active;
}