<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Orders</title>
    <style>
        .orders h3, h4 {
            margin-left: 240px;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="content">
        <p style="margin-left: 350px" class="text-success">${requestScope.infoMessage}</p>
        <div class="orders">
            <h3>Orders : </h3>
            <c:choose>
                <c:when test="${fn:length(requestScope.orders) == 0}">
                    <h4>No such orders</h4>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <tr>
                            <th>
                                <p>Login</p>
                            </th>
                            <th>
                                <p>Surname</p>
                            </th>
                            <th>
                                <p>Name</p>
                            </th>
                            <th>
                                <p>Phone number</p>
                            </th>
                            <th>
                                <p>Tour name</p>
                            </th>
                            <th>
                                <p>People amount</p>
                            </th>
                            <th>
                                <p>Price for tour</p>
                            </th>
                            <th>
                                <p>Current order status</p>
                            </th>
                        </tr>
                        <c:forEach items="${requestScope.orders}" var="order">
                            <tr>
                                <td>
                                    <p>${order.user.login}</p>
                                </td>
                                <td>
                                    <p>${order.user.surname}</p>
                                </td>
                                <td>
                                    <p>${order.user.name}</p>
                                </td>
                                <td>
                                    <p>${order.user.phone}</p>
                                </td>
                                <td>
                                    <p>${order.tour.name}</p>
                                </td>
                                <td>
                                    <p>${order.peopleAmount}</p>
                                </td>
                                <td>
                                    <p>${order.totalPrice}</p>
                                </td>
                                <td>
                                    <p>${order.status}</p>
                                </td>
                                <td>
                                    <form class="form-inline" method="post" action="controller" role="form">
                                        <input type="hidden"  name="action" value="changeOrderStatus">
                                        <input type="hidden"  name="order_id" value="${order.id}">
                                        <input type="hidden"  name="user_email" value="${order.user.email}">
                                        <div class="form-group">
                                            <select name="status">
                                                <option value="2">paid</option>
                                                <option value="3">canceled</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success">Change status</button>
                                        </div>
                                    </form>
                                    <form class="form-inline" method="post" action="controller" role="form">
                                        <input type="hidden"  name="action" value="addDiscountToOrder">
                                        <input type="hidden"  name="order_id" value="${order.id}">
                                        <input type="hidden"  name="price" value="${order.totalPrice}">
                                        <input type="hidden"  name="user_email" value="${order.user.email}">
                                        <div class="form-group">
                                            Discount: <input type="text" placeholder="discount" name="discount" required> %
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-success">Add discount</button>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${fn:length(requestScope.ordersTask) == 0}">
                    <h4>No such orders</h4>
                </c:when>
                <c:otherwise>
                    <table class="table">
                        <tr>
                            <th>
                                <p>Login</p>
                            </th>
                            <th>
                                <p>Email</p>
                            </th>
                            <th>
                                <p>Total price</p>
                            </th>
                            <th>
                                <p>Current order status</p>
                            </th>
                        </tr>
                        <c:forEach items="${requestScope.ordersTask}" var="order">
                            <tr>
                                <td>
                                    <p>${order.user.login}</p>
                                </td>
                                <td>
                                    <p>${order.user.email}</p>
                                </td>
                                <td>
                                    <p>${order.totalPrice}</p>
                                </td>
                                <td>
                                    <p>${order.status}</p>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
        <button style="margin-left: 240px" class="btn btn-default" onclick="history.back()">Back to previous page</button>
    </div>
</body>
</html>
