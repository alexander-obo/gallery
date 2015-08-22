<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gallery</title>
    </head>
    <body>
        <div>
            <form method="POST" action="login">
                <input type="text" placeholder="Login" name="login">
                <input type="password" placeholder="Password" name="password">
                <input type="submit" value="Login">
            </form>
            <a href="registration.html">Registration</a>
        </div>
    </body>
</html>
