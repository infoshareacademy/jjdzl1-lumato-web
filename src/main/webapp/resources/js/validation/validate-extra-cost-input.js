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
        validateExtraCostPriceMoreThanZero: function() {
            if (formToValidate.extraCostPrice.formElement.value < 0) {
                formToValidate.extraCostPrice.ok = false;
            } else {
                formToValidate.extraCostPrice.ok = true;
            }
        },
        checkIfExtraCostDateIsNotFuture: function() {
            var myString = formToValidate.extraCostDate.formElement.value;
            var myRegexp = /(\d\d)-(\d\d)-(\d\d\d\d)/g;
            var match = myRegexp.exec(myString);
            if (match === null) {
                formToValidate.extraCostDate.ok = false;
                return;
            }
            if (match[1] === null || match[2] === null || match[3] === null){
                formToValidate.extraCostDate.ok = false;
                return;
            }
            var date = new Date(match[3], match[2]-1, match[1]);
            var today = new Date();
            if (today < date) formToValidate.extraCostDate.ok = false;
        }
    }
}