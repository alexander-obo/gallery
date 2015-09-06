$().ready(function () {
    $.validator.addMethod("noSpaces", function (value, element, param) {
        return value.indexOf(" ") === -1;
    }, "Spaces are now allowed");
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
                noSpaces: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 20
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
                maxlength: "Password should be less than 20 symbols"
            },
            reenteredPassword: {
                equalTo: "Reentered password should be equal to password"
            }
        }
    });
});
