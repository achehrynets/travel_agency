<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Your profile</title>
    <style>
        .content button {
            margin-left: 280px;
        }
        .user-info h4 {
            margin-left: 220px;
        }
        .user-info p {
            margin-left: 260px;
        }
        .user-orders h4, p {
            margin-left: 220px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content">
        <div class="user-info">
            <h4>User Profile: </h4>
            <p>Login: ${sessionScope.user.login}</p>
            <p>Email: ${sessionScope.user.email}</p>
            <p>Surname: ${sessionScope.user.surname}</p>
            <p>Name: ${sessionScope.user.name}</p>
        </div>
        <div class="user-orders">
            <h4>User orders: </h4>
            <c:choose>
                <c:when test="${fn:length(requestScope.orders) == 0}">
                    <p>No such orders</p>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <tr>
                            <th>Tour name</th>
                            <th>People amount</th>
                            <th>Price for tour</th>
                            <th>Order status</th>
                            <th></th>
                        </tr>
                        <c:forEach items="#{requestScope.orders}" var="order">
                            <tr>
                                <td>${order.tour.name}</td>
                                <td>${order.peopleAmount}</td>
                                <td>${order.totalPrice}</td>
                                <td>${order.status}</td>
                                <td><a class="btn btn-success" type="button"
                                       href="controller?action=tourInfoPage&id=${order.tour.id}">Info about tour</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
        <button type="button" onclick="history.back()">Back to previous page</button>
    </div>
</body>
</html>
