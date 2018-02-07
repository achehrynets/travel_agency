<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Edit resort</title>
    <style>
        .content input, textarea {
            width: 350px;
        }
        .content button {
            margin-left: 450px;
        }
        .row p {
            margin-left: 380px
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container">
        <div class="content">
            <form  class="form-horizontal" method="post" action="controller" role="form">
                <input type="hidden" name="action" value="editResort">
                <div class="form-group">
                    <input id="id" placeholder="id"
                           type="hidden" name="id" value="${requestScope.resort.id}" >
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-4 control-label">Resort name</label>
                    <input id="name" placeholder="name"
                           type="text" name="name" value="${requestScope.resort.name}" required>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-4 control-label">Description</label>
                    <textarea id="description" name="description">${requestScope.resort.description}</textarea>
                </div>
                <button  class="btn btn-success" type="submit">Edit resort</button>
            </form>
            <div class="row">
                <p class="text-success">${requestScope.infoMessage}</p>
            </div>
            <div class="row">
                <p class="text-danger">${requestScope.errorMessage}</p>
                <c:forEach items="${requestScope.errorMessages}" var="message">
                    <p class="text-danger">${message}</p>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>
