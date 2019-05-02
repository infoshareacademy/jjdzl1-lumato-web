/*regex for dd-mm-yyyy:
/^(((((0[1-9])|(1\d)|(2[0-8]))-((0[1-9])|(1[0-2])))|((31-((0[13578])|(1[02])))|((29|30)-((0[1,3-9])|(1[0-2])))))-((20[0-9][0-9]))|(29-02-20(([02468][048])|([13579][26]))))$/
* */

let formToValidate = {
    submit: {
        input: false,
        element: document.getElementById("fuel-input:submit-new-fueling"),
        color: "",
        popoverMsg: "Remember to choose fuel type!",
        active: false
    },
    fuelPrice: {
        input: true,
        element: document.getElementById("fuel-input:fuel-price"),
        color: "",
        ok: false,
        popoverMsg: "Fuel price in PLN. At least 0.0 PLN"
    },
    fuelAmount: {
        input: true,
        element: document.getElementById("fuel-input:fuel-amount"),
        color: "",
        ok: false,
        popoverMsg: "In liters. Value must be over 0 liters."
    },
    mileage: {
        input: true,
        element: document.getElementById("fuel-input:mileage"),
        color: "",
        ok: false,
        popoverMsg: "Milege is in km. Value must be over 0 km!"
    },
    fuelingDate: {
        input: true,
        element: document.getElementById("fuel-input:fueling-date"),
        color: "",
        ok: false,
        regex: /^(((((0[1-9])|(1\d)|(2[0-8]))-((0[1-9])|(1[0-2])))|((31-((0[13578])|(1[02])))|((29|30)-((0[1,3-9])|(1[0-2])))))-((20[0-9][0-9]))|(29-02-20(([02468][048])|([13579][26]))))$/
    },
    otherVerifyFunctions: {
        validateFuelCostMoreThanZero: function() {
            if (formToValidate.fuelPrice.element.value < 0) {
                formToValidate.fuelPrice.ok = false;
            } else {
                formToValidate.fuelPrice.ok = true;
            }
        },
        validateFuelAmountMoreThanZero: function() {
            if (formToValidate.fuelAmount.element.value <= 0) {
                formToValidate.fuelAmount.ok = false;
            } else {
                formToValidate.fuelAmount.ok = true;
            }
        },
        validateMileageMoreThanZero: function() {
            if (formToValidate.mileage.element.value <= 0) {
                formToValidate.mileage.ok = false;
            } else {
                formToValidate.mileage.ok = true;
            }
        }
    }
}