<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gallery</title>
        <%@include file="include/common_style.jsp"%>
        <link rel="stylesheet" type="text/css" href="css/login_and_registration.css">
    </head>
    <body>
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/login_validation.js"></script>
        <c:if test="${errorMessages ne null}">
            <div>
                <ul>
                    <c:forEach items="${errorMessages}" var="errorMessage">
                        <li>${errorMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <h1>Gallery</h1>
        <div>
            <form id="login_form" method="POST" action="login">
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
