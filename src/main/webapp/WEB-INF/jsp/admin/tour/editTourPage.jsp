<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Edit Tour</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <form id="addTourForm" class="form-horizontal" action="controller" method="post" role="form">
        <input type="hidden" name="action" value="editTour"/>
        <input type="hidden" name="id" value="${requestScope.tour.id}"/>
        <div class="form-group">
            <h4 style="margin-left: 400px">Edit tour:</h4>
            <label for="name" class="col-sm-4 control-label">Tour Name</label>
            <input id="name" placeholder="tour name" type="text" name="name" value="${requestScope.tour.name}" required>
        </div>
        <div class="form-group">
            <label for="tour-type" class="col-sm-4 control-label">Tour type</label>
            <select id="tour-type" name="tourType">
                <option value="1">rest</option>
                <option value="2">excursion</option>
                <option value="3">shopping</option>
            </select>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Tour date</label>
            <input id="tour-date" placeholder="2017-09-10" min="2017-09-18"
                   value="${requestScope.tour.tourDate}" type="text" name="tourDate" required>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">Tour price</label>
            <input id="tour-price" placeholder="100" type="text" name="tourPrice" value="${requestScope.tour.totalPrice}" required>
        </div>
        <div class="form-group">
            <label for="amount-of-days" class="col-sm-4 control-label">Amount of days</label>
            <input id="amount-of-days" placeholder="amount of days" type="number"
                   name="amountOfDays" value="${requestScope.tour.amountOfDays}" required>
        </div>
        <div class="form-group">
            <label for="place-quantity" class="col-sm-4 control-label">Place quantity</label>
            <input id="place-quantity" placeholder="place quantity" type="number"
                   name="placeQuantity" value="${requestScope.tour.placeQuantity}" required>
        </div>
        <div class="form-group">
            <button style="margin-left: 450px; margin-top: 10px; width: 200px" class="btn btn-success" type="submit">Edit tour</button>
        </div>
    </form>
</body>
</html>
