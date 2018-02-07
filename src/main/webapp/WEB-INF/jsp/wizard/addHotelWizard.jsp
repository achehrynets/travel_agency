<div class="container">
    <div class="content">
        <form  id="addHotelForm" enctype="multipart/form-data" class="form-horizontal" method="post" role="form">
            <div class="form-group">
                <label for="name" class="col-sm-4 control-label">Hotel name</label>
                <input id="name" placeholder="name"
                       type="text" name="name" required>
            </div>
            <div class="form-group">
                <label for="stars" class="col-sm-4 control-label">Hotel stars</label>
                <select id="stars" name="stars">
                    <option value="1">one</option>
                    <option value="2">two</option>
                    <option value="3">three</option>
                    <option value="4">four</option>
                    <option value="5">five</option>
                </select>
            </div>
            <div class="form-group">
                <label for="price" class="col-sm-4 control-label">Price</label>
                <input id="price" placeholder="price"
                       type="text" name="price" required> dollars
            </div>
            <div class="form-group">
                <label for="description" class="col-sm-4 control-label">Description</label>
                <textarea id="description" placeholder="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="image" class="col-sm-4 control-label">Hotel image</label>
                <input id="image" type="file" name="image"/>
            </div>
            <button style="margin-left: 350px" class="btn btn-success" id="addHotel" type="button">Add hotel</button>
        </form>
        <div class="row">
            <p>
                <button class="btn btn-default" onclick="history.back()">previous page</button>
            </p>
        </div>
    </div>
</div>