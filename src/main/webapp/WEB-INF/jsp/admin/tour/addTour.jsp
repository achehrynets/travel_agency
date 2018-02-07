<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Add Tour</title>
    <style>
        .wizard-step h3 {
            width: 600px;
            margin-left: 350px;
        }

        .wizard-step h4 {
            margin-left: 350px;
        }

        .wizard-step input, textarea {
            width: 350px;
        }

        .wizard-step button {
            margin-left: 30px;
        }

        .wizard-step p {
            margin-left: 350px;
        }
        .error p {
            margin-left: 350px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container">
        <div id="step">
            <form class="form-horizontal" method="get" name="/wizard/check/country" role="form">
                <div class="form-group wizard-step">
                    <h3>Step 1: Add the country where the tour is located</h3>
                    <label for="countryName" class="col-sm-4 control-label">Country name</label>
                    <input id="countryName" placeholder="Country name"
                           type="text" name="countryName" value="Country name" required />
                    <button style="margin-left: 30px" type="button" id="checkCountry" class="btn btn-success">Next step</button>
                </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).on('click', '#addTour', function (event) {
                event.stopPropagation();
                event.preventDefault();
                var transportType = $('#transport-type').val();
                var departurePoint = $('#departure-point').val();
                var departureDate = $('#departure-date').val();
                var travelTime = $('#travel-time').val();
                var arrivalPoint = $('#arrival-point').val();
                var arrivalDate = $('#arrival-date').val();
                var price = $('#price').val();
                var name = $('#name').val();
                var tourType = $('#tour-type').val();
                var tourDate = $('#tour-date').val();
                var tourPrice = $('#tour-price').val();
                var amountOfDays = $('#amount-of-days').val();
                var placeQuantity = $('#place-quantity').val();
                $.post('wizard/add/tour', {
                    transportType: transportType,
                    departurePoint: departurePoint,
                    departureDate: departureDate,
                    travelTime: travelTime,
                    arrivalPoint: arrivalPoint,
                    arrivalDate: arrivalDate,
                    price: price,
                    name: name,
                    tourType: tourType,
                    tourDate: tourDate,
                    tourPrice: tourPrice,
                    amountOfDays: amountOfDays,
                    placeQuantity: placeQuantity
                }, function (message) {

                }).fail(function (message) {
                });
                });
            });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).on('click', '#addHotel', function (event) {
                event.stopPropagation();
                event.preventDefault();
                var form = document.getElementById('addHotelForm');
                var data = new FormData(form);
                $.ajax({
                    url: 'wizard/add/hotel',
                    type: 'POST',
                    data: data,
                    processData : false,
                    contentType : false,
                    success : function(responseText) {
                        $('#step').html(responseText);
                    },
                    error : function(responseText) {
                        $('#error').html(responseText);
                    }
                });
            });
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).on('click', '#addResort', function (event) {
                event.stopPropagation();
                event.preventDefault();
                var form = document.getElementById('addResortForm');
                var data = new FormData(form);
                $.ajax({
                    url: 'wizard/add/resort',
                    type: 'POST',
                    data: data,
                    processData : false,
                    contentType :false,
                    success : function(responseText) {
                        $('#step').html(responseText);
                    },
                    error : function(responseText) {
                        $('#error').html(responseText);
                    }
                });
            });
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).on('click', '#checkHotel', function (event) {
                var hotelName = $('#hotelName').val();
                $.get('wizard/check/hotel',{hotelName : hotelName}, function (responseText) {
                    $('#step').html(responseText);
                });
            });
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).on('click', '#checkResort', function (event) {
                var resortName = $('#resortName').val();
                $.get('wizard/check/resort',{resortName : resortName}, function (responseText) {
                    $('#step').html(responseText);
                });
            });
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(document).on('click', '#checkCountry', function (event) {
                var countryName = $('#countryName').val();
                $.get('wizard/check/country',{countryName : countryName}, function (responseText) {
                    $('#step').html(responseText);
                });
            });
        });
    </script>


    <script type="text/javascript">
        $(document).ready(function () {
            $(document).on('click', '#addCountry', function (event) {
                var countryName = $('#countryName').val();
                var visa = $('#visa').val();
                var data = 'countryName=' + countryName + '&visa=' + visa;
                $.get('wizard/add/country', data, function (responseText) {
                    $('#step').html(responseText);
                });
            });
        });
    </script>
</body>
</html>
