<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gallery registration</title>
        <%@include file="include/common_style.jsp"%>
    </head>
    <body>
        <c:if test="${errorMessage ne null}">
            <div>
                <span>${errorMessage}</span>
            </div>
        </c:if>
        <div>
            <form method="POST" action="registration">
                <input type="text" placeholder="Email" name="email">
                <input type="text" placeholder="Login" name="login">
                <input type="password" placeholder="Password" name="password">
                <input type="submit" value="Sign up">
            </form>
            <a href="login">Back to login</a>
        </div>
    </body>
</html>
