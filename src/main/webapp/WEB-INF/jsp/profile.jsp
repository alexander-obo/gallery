<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${param.user}'s gallery</title>
        <%@include file="include/common_style.jsp"%>
    </head>
    <body>
        <%@include file="include/navigation.jsp"%>
        <jsp:useBean id="profile" class="ao.gallery.web.session.Profile" scope="session"/>
        <h1>${param.user}'s gallery</h1>
        <form method="POST" action="profile">
            <input type="submit" value="Logout">
            <input type="hidden" name="action" value="logout">
        </form>
        <c:if test="${pageContext.request.remoteUser eq param.user}">
            <form method="POST" enctype="multipart/form-data" action="profile">
                <input type="file" name="pictures" multiple>
                <input type="submit" value="Submit">
            </form>
        </c:if>
        <c:forEach items="${profile.pictures}" var="picture">
            <img src="data:image/jpg;base64,${picture.thumbnail}"
                 alt="${picture.name}"
                 onclick="testAjax(${picture.id})">
        </c:forEach>
        <div id="pictureContentBlock">
        </div>
        <script src="js/jquery-2.1.4.min.js"></script>
        <script>
            function testAjax(id) {
                var result = $.ajax({
                    url: "profile",
                    method: "POST",
                    data: {
                        id: id
                    },
                    success: function(data, textStatus, jqXHR) {
                        var img = $("#pictureContentBlock").find("img")[0];
                        if (!img) {
                            img = document.createElement("img");
                            $("#pictureContentBlock").append(img);
                        }
                        var srcAttr = document.createAttribute("src");
                        srcAttr.value = "data:image/jpg;base64," + data;
                        img.setAttributeNode(srcAttr);
                    }
                });
            }
        </script>
    </body>
</html>
