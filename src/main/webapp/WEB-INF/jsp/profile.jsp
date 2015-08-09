<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${param.user}'s gallery</title>
    </head>
    <body>
        <h1>${param.user}'s gallery</h1>
        <c:if test="${pageContext.request.remoteUser eq param.user}">
            <h2>This is you.</h2>
        </c:if>
    </body>
</html>
