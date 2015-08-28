<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${param.user}'s gallery</title>
        <%@include file="include/common_style.jsp"%>
    </head>
    <body>
        <h1>${param.user}'s gallery</h1>
        <%@include file="include/navigation.jsp"%>
        <form method="POST" action="profile">
            <input type="submit" value="Logout">
            <input type="hidden" name="action" value="logout">
        </form>
        <c:if test="${pageContext.request.remoteUser eq param.user}">
            <form method="POST" enctype="multipart/form-data" action="profile">
                <input type="file" name="picture_1">
                <input type="file" name="picture_2">
                <input type="file" name="picture_3">
                <input type="file" name="picture_4">
                <input type="file" name="picture_5">
                <input type="submit" value="Submit">
            </form>
        </c:if>
        <c:forEach items="${thumbnails}" var="thumbnail">
            <img src="data:image/jpg;base64,${thumbnail}">
        </c:forEach>
    </body>
</html>
