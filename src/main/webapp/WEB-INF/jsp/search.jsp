<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
    </head>
    <body>
        <div>
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
