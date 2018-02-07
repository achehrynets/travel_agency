<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Tours</title>
    <style>
        html {
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
        }
        *, *:before, *:after {
            box-sizing: inherit;
            -webkit-box-sizing: inherit;
        }
        .tour .image img {
            max-width: 100%;
            height: auto;
        }
        .tour .image {
            float: left;
            max-width: 400px;
            padding-right: 30px;
        }
        .tour .content {
            overflow: hidden;
        }
        .tour-holder {
            width: 100%;
            overflow: hidden;
        }
        .tour {
            float: left;
            width: 70%;
            padding: 15px;
            margin-left: 50px;
        }
        .add-tour-button a {
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
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <div class="content">
        <div class="message">
            <p class="text-success">${requestScope.infoMessage}</p>
        </div>
        <c:choose>
            <c:when test="${fn:length(requestScope.tours) == 0}">
                <p style="margin-left: 400px">No such tours</p>
            </c:when>
            <c:otherwise>
                <c:forEach items="#{requestScope.tours}" var="tour">
                    <div class="tour-holder">
                        <div class="tour">
                            <div class="image">
                                <img src="imageServlet?path=${tour.resort.imagePath}">
                            </div>
                            <div class="content">
                                <c:if test="${tour.hotTour eq 'true'}">
                                    <h3>HOT TOUR</h3>
                                </c:if>
                                <p>Country name: ${tour.country.name}</p>
                                <p>Resort name: ${tour.resort.name}</p>
                                <p>Hotel name: ${tour.hotel.name}</p>
                                <p>Hotel stars: ${tour.hotel.stars}</p>
                                <p>Tour name: ${tour.name}</p>
                                <p>Tour date: ${tour.tourDate}</p>
                                <p>Tour durability: ${tour.amountOfDays} days</p>
                                <p>Price: ${tour.totalPrice} per person</p>
                                <c:if test="${sessionScope.role.getName().toLowerCase() eq 'admin'}">
                                    <a type="button" class="btn btn-success"
                                       href="controller?action=editTourPage&tour_id=${tour.id}">Edit tour</a>
                                    <c:if test="${tour.hotTour eq 'false'}">
                                        <a type="button" class="btn btn-success" onclick="return confirm('Set current tour as hot?') ? true : false"
                                           href="controller?action=setHotTour&tour_id=${tour.id}">Set tour as hot</a>
                                    </c:if>
                                    <a type="button" class="btn btn-danger" onclick="return confirm('delete tour?') ? true : false"
                                       href="controller?action=deleteTour&id=${tour.id}">delete tour</a>
                                </c:if>
                                <c:if test="${sessionScope.role.getName().toLowerCase() eq 'manager'}">
                                    <c:if test="${tour.hotTour eq 'false'}">
                                        <a type="button" class="btn btn-success" onclick="return confirm('Set current tour as hot?') ? true : false"
                                           href="controller?action=setHotTour&tour_id=${tour.id}">Set tour as hot</a>
                                    </c:if>
                                </c:if>
                                <a type="button" class="btn btn-success"
                                   href="controller?action=tourInfoPage&id=${tour.id}">View info about tour</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <button style="margin-left: 200px; margin-top: 30px" type="button"
                class="btn btn-default" onclick="history.back()">Back to previous page</button>
    </div>
</body>
</html>
