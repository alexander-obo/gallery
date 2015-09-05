<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gallery registration</title>
        <%@include file="include/common_style.jsp"%>
        <link rel="stylesheet" type="text/css" href="css/login_and_registration.css">
    </head>
    <body>
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/registration_validation.js"></script>
        <c:if test="${errorMessages ne null}">
            <div>
                <ul>
                    <c:forEach items="${errorMessages}" var="message">
                        <li>${message}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <div>
            <form id="registration_form" method="POST" action="registration">
                <input type="text" placeholder="Email" name="email" class="text">
                <input type="text" placeholder="Login" name="login" class="text">
                <input type="password" placeholder="Password" id="password" name="password" class="text">
                <input type="password" placeholder="Reenter password" name="reenteredPassword" class="text">
                <input type="submit" value="Sign up" class="text">
            </form>
            <a href="login" class="text">Back to login</a>
        </div>
    </body>
</html>
