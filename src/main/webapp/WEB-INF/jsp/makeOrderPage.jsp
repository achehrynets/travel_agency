<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Book Tour</title>
    <style>
        .form-group p{
            margin-left: 375px;
        }
        .error p {
            margin-left: 375px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <form id="order-form" class="form-horizontal" method="post" action="controller">
        <input type="hidden" name="action" value="addOrder" />
        <input type="hidden" name="tour_id" value="${requestScope.id}" />
        <input type="hidden" id="place-quantity" name="place_quantity" value="${requestScope.placeQuantity}" />
        <input type="hidden" id="price" name="tour_price" value="${requestScope.price}" />
        <div class="form-group">
            <p>Tour name: ${requestScope.name}</p>
        </div>
        <div class="form-group">
            <p>Price: ${requestScope.price} dollars per person</p>
        </div>
        <div class="form-group">
            <label for="people-amount" class="col-sm-4 control-label">How many people?</label>
            <input id="people-amount" style="width: 350px" placeholder="0"
                   type="number" name="people_amount" required>
        </div>
        <div class="form-group">
            <input style="margin-left: 450px" class="btn btn-success" value="Submit" type="submit" />
        </div>
        <div class="error">
            <p id="error"></p>
        </div>
    </form>
    <script type="text/javascript">
        $("#order-form").on('submit', function(e){
            var placeQuantity = $('#place-quantity').val();
            var peopleAmount = $('#people-amount').val();
            if (peopleAmount > placeQuantity) {
                document.getElementById('error').innerHTML = 'Sorry, but in this tour there are only ' + placeQuantity + ' place(s) left';
                return false;
            }
            return true;
        });
    </script>
</body>
</html>
