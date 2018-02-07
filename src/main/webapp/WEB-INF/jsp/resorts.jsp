<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Resorts</title>
    <style>
        html {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
        }
        *, *:before, *:after {
            box-sizing: inherit;
            -webkit-box-sizing: inherit;
        }
        .resort .image img {
            max-width: 100%;
            height: auto;
        }
        .resort .image {
            float: left;
            max-width: 400px;
            padding-right: 30px;
        }
        .resort .content {
            overflow: hidden;
        }
        .resort-holder {
            width: 100%;
            overflow: hidden;
        }
        .resort {
            float: left;
            width: 50%;
            padding: 15px;
            margin-left: 50px;
        }
        .add-resort-button a {
            width: 200px;
            margin-left: 350px;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .message p {
            margin-left: 350px;
            margin-top: 30px
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container">
        <div class="add-resort-button">
            <c:if test="${sessionScope.role.getName().toLowerCase() eq 'admin'}">
                <a type="button" class="btn btn-success"
                   href="controller?action=addResortPage">Add resort</a>
            </c:if>
        </div>
        <div class="message">
            <p class="text-success">${requestScope.infoMessage}</p>
        </div>
        <c:forEach items="${requestScope.resorts}" var="resort">
            <div class="resort-holder">
                <div class="resort">
                    <div class="image">
                        <img src="imageServlet?path=${resort.imagePath}" />
                    </div>
                    <div class="content">
                        <p>Resort name</p>
                        <h4>${resort.name}</h4>
                        <p>Resort description</p>
                        <h4>${resort.description}</h4>
                        <c:choose>
                            <c:when test="${sessionScope.role.getName().toLowerCase() eq 'admin'}">
                                <a type="button" class="btn btn-success"
                                    href="controller?action=editResortPage&id=${resort.id}&name=${resort.name}&description=${resort.description}">Edit resort</a>
                                <a style="margin-top: 10px" type="button" class="btn btn-danger" onclick="return confirm('delete resort?') ? true : false"
                                   href="controller?action=deleteResort&id=${resort.id}&imagePath=${resort.imagePath}">delete resort</a>
                            </c:when>
                            <c:otherwise>
                                <a type="button" class="btn btn-success">View tours</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
