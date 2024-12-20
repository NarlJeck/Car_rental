<%--
  Created by IntelliJ IDEA.
  User: narel
  Date: 09.12.2024
  Time: 00:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <title><fmt:message key="page.order.order"/></title>
</head>
<body>
<h1><fmt:message key="page.order.placingOrder"/></h1>
<form action="${pageContext.request.contextPath}/order" method="post">
    <table>
        <tr>
            <li><strong><fmt:message key="page.order.modelCar"/>:</strong> ${carOrder.modelCar.model}</li>
        </tr>
        <tr>

            <li><strong><fmt:message key="page.order.clientName"/>:</strong><c:if test="${client.fullName == null}">
                ${clientName}
            </c:if> ${client.fullName}</li>
        </tr>
        <tr>
            <td><label for="startDate"><fmt:message key="page.order.rentalStartDate"/>:</label></td>
            <td><input type="date" id="startDate" name="startDate" required/></td>
        </tr>
        <tr>
            <td><label for="endDate"><fmt:message key="page.order.rentalEndDate"/>:</label></td>
            <td><input type="date" id="endDate" name="endDate" required/></td>
        </tr>
    </table>
    <c:if test="${empty sessionScope.client}">
        <div style="color: #e87d0b">
            <td><label> <fmt:message key="page.order.information"/> </label></td>
        </div>
    </c:if>

    <c:if test="${not empty sessionScope.client}">
        <button type="submit"><fmt:message key="page.order.confirmOrder"/></button>
    </c:if>


    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>
    <input type="hidden" name="carId" value="${requestScope.carOrder.carId}"/>
</form>

<form action="main" method="get">
    <input type="submit" value="<fmt:message key="page.order.main"/>"/>
</form>

</body>
</html>
