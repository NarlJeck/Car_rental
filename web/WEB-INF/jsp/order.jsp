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
    <title>Заказ аренды автомобиля</title>
</head>
<body>
<h1>Оформление заказа</h1>
<form action="calculateCost" method="post">
    <table>
        <tr>
            <li><strong>Модель авто:</strong> ${car.getModel}</li>
        </tr>
        <tr>
            <li><strong>Имя:</strong> ${client.fullName}</li>
        </tr>
        <tr>
            <td><label for="startDate">Дата начала аренды:</label></td>
            <td><input type="date" id="startDate" name="startDate" required /></td>
        </tr>
        <tr>
            <td><label for="endDate">Дата окончания аренды:</label></td>
            <td><input type="date" id="endDate" name="endDate" required /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Рассчитать стоимость" /></td>
            <td>
                <span id="Cost">0</span>
            </td>
        </tr>
    </table>
</form>

<form action="confirmOrder" method="post">
    <input type="hidden" name="startDate" id="confirmStartDate" />
    <input type="hidden" name="endDate" id="confirmEndDate" />
    <input type="submit" value="Подтвердить заказ" />
</form>


<form action="main" method="get">
    <input type="submit" value="На главную" />
</form>

</body>
</html>
