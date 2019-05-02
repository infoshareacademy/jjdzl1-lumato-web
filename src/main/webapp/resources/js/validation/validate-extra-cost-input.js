/*regex for dd-mm-yyyy:
/^(((((0[1-9])|(1\d)|(2[0-8]))-((0[1-9])|(1[0-2])))|((31-((0[13578])|(1[02])))|((29|30)-((0[1,3-9])|(1[0-2])))))-((20[0-9][0-9]))|(29-02-20(([02468][048])|([13579][26]))))$/
* */

let formToValidate = {
    submit: {
        input: false,
        formElement: document.getElementById("extra-cost-input:submit-new-extra-cost"),
        color: "",
        popoverMsg: "Remember to choose a car!",
        active: false
    },
    extraCostPrice: {
        input: true,
        formElement: document.getElementById("extra-cost-input:extra-cost-price"),
        color: "",
        ok: false,
        popoverMsg: "Price of extra cost in PLN. Needs to be at least 0 PLN!"
    },
    description: {
        input: true,
        formElement: document.getElementById("extra-cost-input:description"),
        color: "",
        ok: false,
        popoverMsg: "Type something to describe your cost. At least 3 characters, but no more than 50.",
        regex: /^\S{3,5}.{0,45}$/
    },
    extraCostDate: {
        input: true,
        formElement: document.getElementById("extra-cost-input:extra-cost-date"),
        color: "",
        ok: false,
        popoverMsg: "The date must be of form: DD-MM-YYYY",
        datePickerData: {
            format: "dd-mm-yyyy",
            todayBtn: "linked",
            clearBtn: true,
            autoclose: true,
            todayHighlight: true
        },
        regex: /^(((((0[1-9])|(1\d)|(2[0-8]))-((0[1-9])|(1[0-2])))|((31-((0[13578])|(1[02])))|((29|30)-((0[1,3-9])|(1[0-2])))))-((20[0-9][0-9]))|(29-02-20(([02468][048])|([13579][26]))))$/
    },
    otherVerifyFunctions: {
        validateExtraCostPriceMoreThanZero: function() {
            if (formToValidate.extraCostPrice.formElement.value < 0) {
                formToValidate.extraCostPrice.ok = false;
            } else {
                formToValidate.extraCostPrice.ok = true;
            }
        }
    }
}