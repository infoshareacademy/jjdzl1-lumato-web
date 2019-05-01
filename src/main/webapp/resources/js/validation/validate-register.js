let form = {
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
        regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/
    },
    confirmPassword: {
        input: true,
        element: document.getElementById("registration-form:confirmPassword"),
        color: "",
        ok: false,
        popoverMsg: "Passwords must match!"
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

$(document).ready(function(){
	for (var index in form) {
		if (form[index].popoverMsg){
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
        if (form[index].regex) {
            form[index].ok = form[index].element.value.match(form[index].regex);
        }
    }
    form.confirmPassword.ok = form.password.element.value === form.confirmPassword.element.value;
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