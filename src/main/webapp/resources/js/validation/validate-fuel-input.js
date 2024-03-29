let formToValidate = {
    submit: {
        input: false,
        formElement: document.getElementById("fuel-input:submit-new-fueling"),
        color: "",
        popoverMsg: "Remember to choose fuel type!",
        active: false
    },
    fuelPrice: {
        input: true,
        formElement: document.getElementById("fuel-input:fuel-price"),
        color: "",
        ok: false,
        popoverMsg: "Fuel price in PLN. At least 0.0 PLN"
    },
    fuelAmount: {
        input: true,
        formElement: document.getElementById("fuel-input:fuel-amount"),
        color: "",
        ok: false,
        popoverMsg: "In liters. Value must be over 0 liters."
    },
    mileage: {
        input: true,
        formElement: document.getElementById("fuel-input:mileage"),
        color: "",
        ok: false,
        popoverMsg: "Milege is in km. Value must be over 0 km!"
    },
    fuelingDate: {
        input: true,
        formElement: document.getElementById("fuel-input:fueling-date"),
        color: "",
        ok: false,
        datePickerData: {
            format: "dd-mm-yyyy",
            todayBtn: "linked",
            clearBtn: true,
            autoclose: true,
            todayHighlight: true,
            endDate: '+1d',
            datesDisabled: '+1d'
        },
        regex: /^(((((0[1-9])|(1\d)|(2[0-8]))-((0[1-9])|(1[0-2])))|((31-((0[13578])|(1[02])))|((29|30)-((0[1,3-9])|(1[0-2])))))-((20[0-9][0-9]))|(29-02-20(([02468][048])|([13579][26]))))$/
    },
    otherVerifyFunctions: {
        validateFuelCostMoreThanZero: function() {
            if (formToValidate.fuelPrice.formElement.value < 0) {
                formToValidate.fuelPrice.ok = false;
            } else {
                formToValidate.fuelPrice.ok = true;
            }
        },
        validateFuelAmountMoreThanZero: function() {
            if (formToValidate.fuelAmount.formElement.value <= 0) {
                formToValidate.fuelAmount.ok = false;
            } else {
                formToValidate.fuelAmount.ok = true;
            }
        },
        validateMileageMoreThanZero: function() {
            if (formToValidate.mileage.formElement.value <= 0) {
                formToValidate.mileage.ok = false;
            } else {
                formToValidate.mileage.ok = true;
            }
        },
        checkIfFuelingDateIsNotFuture: function() {
            var myString = formToValidate.fuelingDate.formElement.value;
            var myRegexp = /(\d\d)-(\d\d)-(\d\d\d\d)/g;
            var match = myRegexp.exec(myString);
            if (match === null) {
                formToValidate.fuelingDate.ok = false;
                return;
            }
            if (match[1] === null || match[2] === null || match[3] === null){
                formToValidate.fuelingDate.ok = false;
                return;
            }
            var date = new Date(match[3], match[2]-1, match[1]);
            var today = new Date();
            if (today < date) formToValidate.fuelingDate.ok = false;
        }
    }
}