<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Tour ${requestScope.tour.name}</title>
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
        <div class="tour-holder">
            <div class="tour">
                <div class="image">
                    <img src="imageServlet?path=${requestScope.tour.resort.imagePath}">
                </div>
                <div class="content">
                    <c:if test="${requestScope.tour.hotTour eq 'true'}">
                        <h3>HOT TOUR</h3>
                    </c:if>
                    <p>Country name: ${requestScope.tour.country.name}</p>
                    <c:if test="${requestScope.tour.country.visa eq 'true'}">
                        <p>Visa: need</p>
                    </c:if>
                    <c:if test="${requestScope.tour.country.visa eq 'false'}">
                        <p>Visa: do not need</p>
                    </c:if>
                    <p>Resort name: ${requestScope.tour.resort.name}</p>
                    <p>Resort description: ${requestScope.tour.resort.description}</p>
                    <p>Hotel name: ${requestScope.tour.hotel.name}</p>
                    <p>Hotel stars: ${requestScope.tour.hotel.stars}</p>
                    <p>Hotel description: ${requestScope.tour.hotel.description}</p>
                    <p>Transport type:
                    <c:choose>
                        <c:when test="${requestScope.tour.flight.transportTypeId eq '1'}">
                            aircraft
                        </c:when>
                        <c:when test="${requestScope.tour.flight.transportTypeId eq '2'}">
                            bus
                        </c:when>
                        <c:when test="${requestScope.tour.flight.transportTypeId eq '3'}">
                            ship
                        </c:when>
                    </c:choose>
                    </p>
                    <p>Departure point: ${requestScope.tour.flight.departurePoint}</p>
                    <p>Departure date: ${requestScope.tour.flight.departureDate}</p>
                    <p>Travel time: ${requestScope.tour.flight.travelTime}</p>
                    <p>Arrival point: ${requestScope.tour.flight.arrivalPoint}</p>
                    <p>Arrival date: ${requestScope.tour.flight.arrivalDate}</p>
                    <p>Tour name: ${requestScope.tour.name}</p>
                    <p>Tour date: ${requestScope.tour.tourDate}</p>
                    <p>Tour durability: ${requestScope.tour.amountOfDays} days</p>
                    <p>Price: ${requestScope.tour.totalPrice} per person</p>
                    <c:choose>
                        <c:when test="${sessionScope.role.getName().toLowerCase() eq 'admin'}">
                            <a type="button" class="btn btn-success"
                               href="controller?action=editTourPage&tour_id=${requestScope.tour.id}">Edit tour</a>
                            <c:if test="${requestScope.tour.hotTour eq 'false'}">
                                <a type="button" class="btn btn-success" onclick="return confirm('Set current tour as hot?') ? true : false"
                                   href="controller?action=setHotTour&tour_id=${requestScope.tour.id}">Set tour as hot</a>
                            </c:if>
                            <a type="button" class="btn btn-danger" onclick="return confirm('delete tour?') ? true : false"
                               href="controller?action=deleteTour&id=${requestScope.tour.id}">delete tour</a>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${sessionScope.role.getName().toLowerCase() eq 'manager'}">
                                <c:if test="${requestScope.tour.hotTour eq 'false'}">
                                    <a type="button" class="btn btn-success" onclick="return confirm('Set current tour as hot?') ? true : false"
                                       href="controller?action=setHotTour&tour_id=${requestScope.tour.id}">Set tour as hot</a>
                                </c:if>
                            </c:if>
                            <a type="button" class="btn btn-success"
                                href="controller?action=orderPage&id=${requestScope.tour.id}&name=${requestScope.tour.name}&placeQuantity=${requestScope.tour.placeQuantity}&price=${requestScope.tour.totalPrice}">Order the tour</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    <button style="margin-left: 200px; margin-top: 30px" type="button"
            class="btn btn-default" onclick="history.back()">Back to previous page</button>
</div>
</body>
</html>
