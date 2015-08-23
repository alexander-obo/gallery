<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gallery</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>
    <body>
        <c:if test="${errorMessage ne null}">
            <div>
                <span>${errorMessage}</span>
            </div>
        </c:if>
        <h1>Gallery</h1>
        <div>
            <form method="POST" action="login">
                <input type="text" placeholder="Login" name="login" class="text">
                <input type="password" placeholder="Password" name="password" class="text">
                <div>
                    <input type="submit" value="Login" class="text">
                    <span class="text">or</span>
                    <a href="registration" class="text">Sign up</a>
                </div>
            </form>
        </div>
    </body>
</html>
