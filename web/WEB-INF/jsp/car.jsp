<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Описание автомобиля</title>
</head>
<body>
<h1>Описание автомобиля</h1>
<ul>
    <li><strong>Модель авто:</strong> ${car.getModel}</li>
    <li><strong>Описание:</strong> </li>
    <li><strong>Цена:</strong> ${car.getPrice}</li>

</ul>

<form action="order.jsp" method="post">
    <input type="submit" value="Забронировать" />
</form>

<form action="carsMain.jsp.jsp" method="get">
    <input type="submit" value="Назад" />
</form>
</body>
</html>
