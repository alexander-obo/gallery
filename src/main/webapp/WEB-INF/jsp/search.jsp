<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
        <%@include file="include/common_style.jsp"%>
    </head>
    <body>
        <div>
            <%@include file="include/navigation.jsp"%>
            <form action="search">
                <input type="text" name="userName">
                <input type="submit" value="Search">
            </form>
            <ul>
                <c:forEach items="${usersNames}" var="userName">
                    <li>
                        <a href="profile?user=${userName}">${userName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>
