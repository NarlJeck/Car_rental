<%--
  Created by IntelliJ IDEA.
  User: narel
  Date: 19.12.2024
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список авто, клиентов и заказов</title>
</head>
<body>
<h1>Список авто</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Модель</th>
        <th>Статус</th>
        <th>Цена аренды</th>
        <th>Действие</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="car" items="${requestScope.cars}">


        <td>${car.carId}</td>
        <td>${car.modelCar.model}</td>
        <td>
            <select>
                <option value="available">Доступен</option>
                <option value="rented">В аренде</option>
                <option value="maintenance">На обслуживании</option>
            </select>
        </td>
        <td><input type="text" value="1000" /></td>
        <td><button type="button">Изменить</button></td>
        </c:forEach>
    </tr>
    <!-- Добавьте дополнительные строки по аналогии с вышеуказанной -->
    </tbody>
</table>

<h1>Список клиентов</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td>Иван Иванов</td>
        <td>ivan@example.com</td>
    </tr>
    <!-- Добавьте дополнительные строки по аналогии с вышеуказанной -->
    </tbody>
</table>

<h1>Список заказов</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID клиента</th>
        <th>ID авто</th>
        <th>Дата начала</th>
        <th>Дата окончания</th>
        <th>Статус</th>
        <th>Действие</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td>1</td>
        <td><input type="date" /></td>
        <td><input type="date" /></td>
        <td>
            <select>
                <option value="active">Активен</option>
                <option value="completed">Завершен</option>
                <option value="canceled">Отменен</option>
            </select>
        </td>
        <td><button type="button">Добавить</button></td>
    </tr>
    <!-- Добавьте дополнительные строки по аналогии с вышеуказанной -->
    </tbody>
</table>
</body>
</html>