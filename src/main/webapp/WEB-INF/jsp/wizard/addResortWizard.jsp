<div class="container">
    <div class="content">
        <form id="addResortForm" enctype="multipart/form-data" method="post" class="form-horizontal" role="form">
            <div class="form-group wizard-step">
                <label for="name" class="col-sm-4 control-label">Resort name</label>
                <input id="name" placeholder="name"
                       type="text" name="name" required>
            </div>
            <div class="form-group wizard-step">
                <label for="description" class="col-sm-4 control-label">Description</label>
                <textarea id="description" name="description" required></textarea>
            </div>
            <div class="form-group wizard-step">
                <label for="image" class="col-sm-4 control-label">Resort image</label>
                <input id="image" type="file" name="image"/>
            </div>
            <button style="margin-left: 380px" class="btn btn-success" type="button" id="addResort">Add resort</button>
        </form>
        <div class="error">
            <button style="margin-left: 300px" onclick="history.back()">Previous page</button>
            <p style="margin-left: 350px" id="error"></p>
        </div>
    </div>
</div>