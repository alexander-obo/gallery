$().ready(function () {
    $.validator.addMethod("noSpaces", function (value, element, param) {
        return value.indexOf(" ") === -1;
    }, "Spaces are now allowed");
    $("#login_form").validate({
        rules: {
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
            }
        },
        messages: {
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
            }
        }
    });
});
