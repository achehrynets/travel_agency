<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="WEB-INF/jspf/include.jspf"%>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <%@ include file="WEB-INF/jspf/header.jspf"%>
    <div class="container">
        <p style="margin-left: 380px" class="text-danger">${requestScope.errorMessage}</p>
        <c:forEach items="${requestScope.errorMessages}" var="msg">
            <p style="margin-left: 380px" class="text-danger">${msg}</p>
        </c:forEach>
        <button class="btn btn-default" onclick="history.back()">back to previous page</button>
    </div>

</body>
</html>
