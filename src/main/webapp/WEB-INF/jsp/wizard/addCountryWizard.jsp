<form class="form-horizontal" method="post" name="/wizard/add/country" role="form">
    <div class="form-group wizard-step">
        <h4>Country not found, please add new</h4>
        <label for="countryName" class="col-sm-4 control-label">Country name</label>
        <input style="width: 350px" id="countryName" placeholder="countryName"
               type="text" name="countryName" value="name" required />
        <button style="margin-left: 30px" type="button"  id="addCountry" class="btn btn-success">Add country</button>
    </div>
    <div class="form-group wizard-step">
        <label for="visa" class="col-sm-4 control-label">Visa</label>
        <select id="visa" size="1" name="visa">
            <option value="true">Need</option>
            <option value="false">Do not need</option>
        </select>
    </div>
</form>
