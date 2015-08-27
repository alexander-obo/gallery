<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${param.user}'s gallery</title>
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
        <c:forEach items="${pictures}" var="picture">
            <button onclick="testAjax(${picture.id})">${picture.name}</button>
        </c:forEach>
        <script src="js/jquery-2.1.4.min.js"></script>
        <script>
            function testAjax(id) {
                var result = $.ajax({
                    url: "profile",
                    method: "POST",
                    data: {
                        id: id
                    },
//                    beforeSend: function (xhr) {
//                        xhr.overrideMimeType("text/plain; charset=x-user-defined");
//                    }
                    success: function(data, textStatus, jqXHR) {
                        var img = document.createElement("img");
                        var att = document.createAttribute("src");
                        att.value = "data:image/jpg;base64," + data;
                        img.setAttributeNode(att);
                        document.body.appendChild(img);
                    }
                });
                console.log(result);
            }
        </script>
    </body>
</html>
