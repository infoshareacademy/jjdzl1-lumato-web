let formToValidate = {
    submit: {
        input: false,
        element: document.getElementById("add-car-form:submit-new-car"),
        color: "",
        popoverMsg: "Remember to choose fuel type!",
        active: false
    },
    brand: {
        input: true,
        element: document.getElementById("add-car-form:brand"),
        color: "",
        ok: false,
        popoverMsg: "Example: Honda, Peugeot, Ford...",
        regex: /.+/
    },
    model: {
        input: true,
        element: document.getElementById("add-car-form:model"),
        color: "",
        ok: false,
        popoverMsg: "Example: Civic, Ka, 407...",
        regex: /.+/
    },
    productionYear: {
        input: true,
        element: document.getElementById("add-car-form:productionYear"),
        color: "",
        ok: false,
        popoverMsg: "Cannot be before 1900",
        regex: /^19.{2}|2.{3}$/
    },
    registrationPlate: {
        input: true,
        element: document.getElementById("add-car-form:registrationPlate"),
        color: "",
        ok: false,
        popoverMsg: "3 to 10 characters",
        regex: /^.{3,10}$/
    }
}