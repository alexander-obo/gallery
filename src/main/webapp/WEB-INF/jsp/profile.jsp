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
            <form method="POST" enctype="multipart/form-data" action="profile?user=${param.user}">
                <input type="file" name="picture">
                <input type="submit" value="Submit">
            </form>
        </c:if>
        <c:forEach items="${thumbnails}" var="thumbnail">
            <img src="data:image/jpg;base64,${thumbnail}">
        </c:forEach>
    </body>
</html>
