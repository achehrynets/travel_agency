<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <form class="form-horizontal" method="post" action="controller" role="form">
        <input type="hidden" name="action" value="registration" />
        <div class="form-group">
            <label for="login" class="col-sm-4 control-label">Login</label>
            <input style="width: 350px" id="login" placeholder="Login"
                   type="text" name="login" value="${requestScope.login}" required>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">Password</label>
            <input style="width: 350px" id="password" placeholder="Password"
                   type="password" name="password" value="${requestScope.password}">
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-4 control-label">Email</label>
            <input style="width: 350px" id="email" placeholder="Email"
                   type="email" name="email" value="${requestScope.email}" required>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-4 control-label">Name</label>
            <input style="width: 350px" id="name" placeholder="Name"
                   type="text" name="name" value="${requestScope.name}" required>
        </div>
        <div class="form-group">
            <label for="middle_name" class="col-sm-4 control-label">Middle name</label>
            <input style="width: 350px" id="middle_name" placeholder="Middle name"
                   type="text" name="middle_name" value="${requestScope.middle_name}" required>
        </div>
        <div class="form-group">
            <label for="surname" class="col-sm-4 control-label">Surname</label>
            <input style="width: 350px" id="surname" placeholder="Surname"
                   type="text" name="surname" value="${requestScope.surname}" required>
        </div>
        <div class="form-group">
            <label for="passport_id" class="col-sm-4 control-label">Passport number</label>
            <input style="width: 350px" id="passport_id" placeholder="Passport number"
                   type="text" name="passport_id" value="${requestScope.passport_id}" required>
        </div>
        <button style="margin-left: 450px" class="btn btn-success" type="submit">Create an account</button>
    </form>
    <div class="container">
        <p style="margin-left: 380px" class="text-danger">${requestScope.errorMessage}</p>
        <c:forEach items="${requestScope.errorMessages}" var="message">
            <p style="margin-left: 380px" class="text-danger">${message}</p>
        </c:forEach>
    </div>
</div>

</body>
</html>
