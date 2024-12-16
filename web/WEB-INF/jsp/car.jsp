<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp"%>
    <title>Описание автомобиля</title>
</head>
<body>
<h1>Описание автомобиля</h1>

<ul>
    <li><strong>Модель авто:</strong> ${requestScope.carCurrent.modelCar.model}</li>
    <li><strong>Кузов:</strong> ${requestScope.carCurrent.typCar.type}</li>
    <li><strong>Колличество мест:</strong> ${requestScope.carCurrent.numberSeats}</li>
    <li><strong>Цвет:</strong> ${requestScope.carCurrent.carColor.color}</li>
    <li><strong>Регистрационный номер:</strong> ${requestScope.carCurrent.registrationNumber}</li>
    <li><strong>Цена:</strong> ${requestScope.carCurrent.rentalPricePerDay} $/сут</li>
</ul>

<form action="order" method="get">
    <input type="submit" value="Забронировать" />
</form>

<form action="main" method="get">
    <input type="submit" value="Назад" />
</form>
</body>
</html>
