var LOGIN_DELIMITERS = [".", "-", "_"];
$().ready(function () {
    $.validator.addMethod("noSpaces", function (value, element, param) {
        return value.indexOf(" ") === -1;
    }, "Spaces are now allowed");
    $.validator.addMethod("loginValidation", function (value, element, param) {
        for (i = 0; i < value.length - 1; i++) {
            if (LOGIN_DELIMITERS.indexOf(value[i]) !== -1 && LOGIN_DELIMITERS.indexOf(value[i + 1]) !== -1) {
                return false;
            }
        }
        var regexp = new RegExp("^[A-Za-z\\d][A-Za-z\\d\\.\\-_]{1,18}[A-Za-z\\d]$");
        return regexp.test(value);
    }, "Login is not valid");
    $("#registration_form").validate({
        rules: {
            email: {
                required: true,
                email: true,
                maxlength: 50
            },
            login: {
                required: true,
                minlength: 3,
                maxlength: 20,
                noSpaces: true,
                loginValidation: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20,
                noSpaces: true
            },
            reenteredPassword: {
                equalTo: "#password"
            }
        },
        messages: {
            email: {
                required: "Email is required",
                email: "Email is not valid",
                maxlength: "Email should be less than 50 symbols"
            },
            login: {
                required: "Login is required",
                minlength: "Login should be greater than 3 symbols",
                maxlength: "Login should be less than 20 symbols",
                noSpaces: "Login should not contains a space"
            },
            password: {
                required: "Password is required",
                minlength: "Password should be greater than 6 symbols",
                maxlength: "Password should be less than 20 symbols",
                noSpaces: "Password should not contains a space"
            },
            reenteredPassword: {
                equalTo: "Reentered password should be equal to password"
            }
        }
    });
});
