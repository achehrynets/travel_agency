<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Edit Country</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container">
        <form  class="form-horizontal" method="post" action="controller" role="form">
            <input type="hidden" name="action" value="editCountry">
            <div class="form-group">
                <input style="width: 350px" id="id" placeholder="id"
                       type="hidden" name="id" value="${requestScope.country.id}" >
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-4 control-label">Country name</label>
                <input style="width: 350px" id="name" placeholder="name"
                       type="text" name="name" value="${requestScope.country.name}" required>
            </div>
            <div class="form-group">
                <label for="visa" class="col-sm-4 control-label">Visa</label>
                <select id="visa" size="1" name="visa">
                    <option value="true">Need</option>
                    <option value="false">Do not need</option>
                </select>
            </div>
            <button style="margin-left: 450px" class="btn btn-success" type="submit">Edit country</button>
        </form>
        <div class="row">
            <p style="margin-left: 380px" class="text-success">${requestScope.infoMessage}</p>
        </div>
        <div class="row">
            <p style="margin-left: 380px" class="text-danger">${requestScope.errorMessage}</p>
            <c:forEach items="${requestScope.errorMessages}" var="message">
                <p style="margin-left: 380px" class="text-danger">${message}</p>
            </c:forEach>
        </div>
    </div>
</body>
</html>
