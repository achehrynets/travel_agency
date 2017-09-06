<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="container">
        <form class="form-horizontal" method="post" action="controller" role="form">
            <input type="hidden" name="action" value="login" />
            <div class="form-group">
                <label for="login" class="col-sm-4 control-label">Login</label>
                <input style="width: 350px" id="login" placeholder="Login" type="text" name="login" required>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-4 control-label">Password</label>
                <input style="width: 350px" id="password" placeholder="Password" type="password" name="password" required>
            </div>
            <button style="margin-left: 450px; width:200px" class="btn btn-success" type="submit">Sign in</button>
        </form>
        <div class="container">
            <p style="margin-left: 380px" class="text-danger">${requestScope.errorMessage}</p>
            <c:forEach items="${requestScope.errorMessages}" var="msg">
                <p style="margin-left: 380px" class="text-danger">${msg}</p>
            </c:forEach>
            <a onclick="history.back()">back to previous page</a>
        </div>
    </div>
</body>
</html>
