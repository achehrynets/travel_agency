    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container">
        <table>
            <tr>
                <th>
                    <p style="margin-left: 320px">User name</p>
                </th>
                <th>
                    <p style="margin-left: 30px">User surname</p>
                </th>
                <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>
                    <p style="margin-left: 330px">${user.name}</p>
                </td>
                <td>
                    <p style="margin-left: 30px">${user.surname}</p>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.blocked eq true}">
                            <a class="btn btn-danger" style="margin-left: 30px"
                               onclick="return confirm('Unblock user?') ? true : false"
                               href="controller?action=blockOrUnblockUser&id=${user.id}&isBlock=false">Unblock</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-success" style="margin-left: 30px"
                               onclick="return confirm('Block user?') ? true : false"
                               href="controller?action=blockOrUnblockUser&id=${user.id}&isBlock=true">Block</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </c:forEach>
            </tr>
        </table>
    </div>
    <div class="container">
        <p style="margin-left: 350px" class="text-success">${requestScope.infoMessage}</p>
    </div>
    <div class="container">
        <p style="margin-left: 350px" class="text-danger">${requestScope.errorMessage}</p>
        <p>
            <a class="btn btn-default" onclick="history.back()">previous page</a>
        </p>
    </div>

</body>
</html>
