<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gallery registration</title>
    </head>
    <body>
        <div>
            <form method="POST" action="registration">
                <input type="text" placeholder="Email" name="email">
                <input type="text" placeholder="Login" name="login">
                <input type="password" placeholder="Password" name="password">
                <input type="submit" value="Sign up">
            </form>
        </div>
    </body>
</html>
