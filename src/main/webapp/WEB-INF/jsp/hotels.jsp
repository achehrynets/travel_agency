<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Hotels</title>
    <style>
        html {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
        }
        *, *:before, *:after {
            box-sizing: inherit;
            -webkit-box-sizing: inherit;
        }
        .hotel .image img {
            max-width: 100%;
            height: auto;
        }
        .hotel .image {
            float: left;
            max-width: 400px;
            padding-right: 30px;
        }
        .hotel .content {
            overflow: hidden;
        }
        .hotel-holder {
            width: 100%;
            overflow: hidden;
        }
        .hotel {
            float: left;
            width: 70%;
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
                   href="controller?action=addHotelPage">Add hotel</a>
            </c:if>
        </div>
        <div class="message">
            <p class="text-success">${requestScope.infoMessage}</p>
        </div>
        <c:forEach items="#{requestScope.hotels}" var="hotel">
            <div class="hotel-holder">
                <div class="hotel">
                    <div class="image">
                        <img src="imageServlet?path=${hotel.imagePath}">
                    </div>
                    <div class="content">
                        <p>Country name: ${hotel.country.name}</p>
                        <p>Resort name: ${hotel.resort.name}</p>
                        <p>Hotel name: ${hotel.name}</p>
                        <p>Stars: ${hotel.stars}</p>
                        <p>Description: ${hotel.description}</p>
                        <p>Price: ${hotel.price} dollars</p>
                        <c:choose>
                            <c:when test="${sessionScope.role.getName().toLowerCase() eq 'admin'}">
                                <a type="button" class="btn btn-success"
                                   href="controller?action=editHotelPage&id=${hotel.id}&name=${hotel.name}&description=${hotel.description}&stars=${hotel.stars}&price=${hotel.price}">Edit hotel</a>
                                <a type="button" class="btn btn-danger" onclick="return confirm('delete hotel?') ? true : false"
                                   href="controller?action=deleteHotel&id=${hotel.id}&imagePath=${hotel.imagePath}">delete hotel</a>
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
