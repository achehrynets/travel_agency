<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/include.jspf"%>
<html>
<head>
    <title>Countries</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div class="container">
        <table>
            <tr>
                <th>
                    <p style="margin-left: 320px">Country name</p>
                </th>
                <th>
                    <p style="margin-left: 30px">Visa</p>
                </th>
                <c:forEach items="${requestScope.countries}" var="country">
                    <tr>
                        <td>
                            <p style="margin-left: 350px">${country.name}</p>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${country.visa eq true}">
                                    <p style="margin-left: 30px">Need</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="margin-left: 30px">Do not need</p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a style="margin-left: 40px" class="btn btn-success"
                               href="controller?action=editCountyPage&id=${country.id}&name=${country.name}">edit</a>
                        </td>
                        <td>
                            <a style="margin-left: 20px" class="btn btn-danger"
                               href="controller?action=deleteCountry&id=${country.id}&name=${country.name}"
                               onclick="return confirm('delete country?') ? true : false">delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tr>
        </table>
        <a class="btn btn-default" style="margin-left: 500px; margin-top: 40px"
           href="controller?action=addCountryPage">Add country</a>
    </div>
    <div class="container">
        <p style="margin-left: 350px; margin-top: 30px" class="text-success">${requestScope.infoMessage}</p>
    </div>
    <div class="container">
        <p style="margin-left: 350px" class="text-danger">${requestScope.errorMessage}</p>
        <p>
            <a class="btn btn-default" onclick="history.back()">previous page</a>
        </p>
    </div>
</body>
</html>
