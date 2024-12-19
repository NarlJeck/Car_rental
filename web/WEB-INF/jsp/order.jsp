<%--
  Created by IntelliJ IDEA.
  User: narel
  Date: 09.12.2024
  Time: 00:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <title>Заказ аренды автомобиля</title>
</head>
<body>
<h1>Оформление заказа</h1>
<form action="${pageContext.request.contextPath}/order" method="post">
    <table>
        <tr>
            <li><strong>Модель авто:</strong> ${carOrder.modelCar.model}</li>
        </tr>
        <tr>

            <li><strong>Имя:</strong><c:if test="${client.fullName == null}">
                ${clientName}
            </c:if> ${client.fullName}</li>
        </tr>
        <tr>
            <td><label for="startDate">Дата начала аренды:</label></td>
            <td><input type="date" id="startDate" name="startDate" required/></td>
        </tr>
        <tr>
            <td><label for="endDate">Дата окончания аренды:</label></td>
            <td><input type="date" id="endDate" name="endDate" required/></td>
        </tr>
    </table>
    <c:if test="${not empty sessionScope.client}">

        <button type="submit">Подтвердить</button>

    </c:if>
    <div style="color: #e87d0b">
        <td><label> Чтобы подтвердить бронь необходимо зарегестрироваться </label></td>
    </div>
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
    <input type="submit" value="На главную"/>
</form>

</body>
</html>
