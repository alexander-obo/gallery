<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gallery</title>
    </head>
    <body>
        <c:if test="${errorMessage ne null}">
            <div>
                <span>${errorMessage}</span>
            </div>
        </c:if>
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
