<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Add resort</title>
    <style>
        .content input, textarea {
            width: 350px;
        }
        .content button{
            margin-left: 450px;
        }
        .row {
            margin-left: 350px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container">
        <div class="content">
            <form enctype="multipart/form-data" class="form-horizontal" method="post" action="controller" role="form">
                <input type="hidden" name="action" value="addResort">
                <div class="form-group">
                    <label for="name" class="col-sm-4 control-label">Resort name</label>
                    <input id="name" placeholder="name"
                           type="text" name="name" value="${requestScope.resort.name}" required>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-4 control-label">Description</label>
                    <textarea id="description" name="description">${requestScope.resort.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="country-name" class="col-sm-4 control-label">Country name</label>
                    <select id="country-name" name="countryId">
                        <c:forEach items="${requestScope.countries}" var="country">
                            <option value="${country.id}">${country.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="image" class="col-sm-4 control-label">Resort image</label>
                    <input id="image" type="file" name="image"/>
                </div>
                <button class="btn btn-success" type="submit">Add resort</button>
            </form>
            <div class="row">
                <p class="text-danger">${requestScope.errorMessage}</p>
                <c:forEach items="${requestScope.errorMessages}" var="message">
                    <p class="text-danger">${message}</p>
                </c:forEach>
                <p>
                    <a class="btn btn-default" onclick="history.back()">previous page</a>
                </p>
            </div>
        </div>
    </div>
</body>
</html>
