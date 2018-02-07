<div class="container">
    <div class="content">
        <div class="error">
            <p id="error"></p>
        </div>
        <form id="addTourForm" class="form-horizontal" method="post" role="form">
            <div class="form-group">
                <h4 style="margin-left: 100px">Transport part:</h4>
                <label for="transport-type" class="col-sm-4 control-label">Transport type</label>
                <select id="transport-type" name="transportType">
                    <option value="1">aircraft</option>
                    <option value="2">bus</option>
                    <option value="3">ship</option>
                </select>
            </div>
            <div class="form-group">
                <label for="departure-point" class="col-sm-4 control-label">Departure point</label>
                <input id="departure-point" placeholder="departure point" name="departurePoint" required/>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Departure date and time</label>
                <input id="departure-date" placeholder="2017-09-10 16:00"
                       value="2017-09-10 16:00" type="text" name="departureDate" required/>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Travel time</label>
                <input id="travel-time" placeholder="08:00" value="08:00" type="text" name="travelTime" required/>
            </div>
            <div class="form-group">
                <label for="arrival-point" class="col-sm-4 control-label">Arrival point</label>
                <input id="arrival-point" placeholder="arrival point" name="arrivalPoint" required/>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Arrival date</label>
                <input id="arrival-date" placeholder="2017-09-10 19:00"
                       value="2017-09-10 19:00" type="text" name="arrivalDate" required/>
            </div>
            <div class="form-group">
                <label for="price" class="col-sm-4 control-label">Price per seat</label>
                <input id="price" placeholder="price per seat" type="text" name="price" required> dollars
            </div>
            <div class="form-group">
                <h4 style="margin-left: 100px">Tour part:</h4>
                <label for="name" class="col-sm-4 control-label">Tour Name</label>
                <input id="name" placeholder="tour name" type="text" name="name" required>
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
                       value="2017-09-10" type="text" name="tourDate" required>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Tour price</label>
                <input id="tour-price" placeholder="100" type="text" name="tourPrice" required>
            </div>
            <div class="form-group">
                <label for="amount-of-days" class="col-sm-4 control-label">Amount of days</label>
                <input id="amount-of-days" placeholder="amount of days" type="number" name="amountOfDays" required>
            </div>
            <div class="form-group">
                <label for="place-quantity" class="col-sm-4 control-label">Place quantity</label>
                <input id="place-quantity" placeholder="place quantity" type="number" name="placeQuantity" required>
            </div>
            <div class="form-group">
                <button style="margin-left: 400px; margin-top: 10px; width: 200px"
                        id="addTour" class="btn btn-success" type="button">Add tour</button>
            </div>
        </form>
    </div>
</div>
