<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kharkiv Travel</title>
    <%@include file="WEB-INF/jspf/include.jspf"%>
</head>
<body>
    <%@include file="WEB-INF/jspf/header.jspf"%>
    <div class="container">
        <form class="form-horizontal" method="post" action="controller" role="search">
            <input type="hidden" name="action" value="searchTour" />
            <div class="form-group">
                <label for="tour-type" class="col-sm-4 control-label">Tour type</label>
                <select id="tour-type" name="tour_type">
                    <option value="1">rest</option>
                    <option value="2">excursion</option>
                    <option value="3">shopping</option>
                </select>
            </div>
            <div class="form-group">
                <label for="price" class="col-sm-4 control-label">Price is not more than</label>
                <input id="price" placeholder="500" type="text" name="price"> dollars per person
            </div>
            <div class="form-group">
                <label for="people-amount" class="col-sm-4 control-label">People amount</label>
                <input id="people-amount" placeholder="10" type="text" name="people_amount">
            </div>
            <div class="form-group">
                <label for="hotel-stars" class="col-sm-4 control-label">Hotel stars</label>
                <select id="hotel-stars" name="hotel_stars">
                    <option value="1">one</option>
                    <option value="2">two</option>
                    <option value="3">three</option>
                    <option value="4">four</option>
                    <option value="5">five</option>
                </select>
            </div>
            <button style="margin-left: 350px" class="btn btn-success" type="submit">Search tours</button>
            <div class="container">
                <p style="margin-left: 380px; margin-top: 15px" class="text-danger">${requestScope.errorMessage}</p>
                <c:forEach items="${requestScope.errorMessages}" var="msg">
                    <p style="margin-left: 380px" class="text-danger">${msg}</p>
                </c:forEach>
                <button class="btn btn-default" onclick="history.back()">back to previous page</button>
            </div>
        </form>
    </div>
</body>
</html>
