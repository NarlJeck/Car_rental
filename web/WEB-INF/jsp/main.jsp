
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h1>Автомобили</h1>
<ul>
    <li>
    <c:forEach var="car" items="${requestScope.cars}">

        ID: ${car.carId}

        Year: ${car.year}

        Seats: ${car.numberSeats}

        Price: ${car.rentalPricePerDay}

        Registration: ${car.registrationNumber}

        Color: ${car.carColor.color}

        Model: ${car.modelCar.model}

        Status : ${car.statusCar.status}

        Type : ${car.typCar.type}
        </li >
    </c:forEach>
</ul>

</body>
</html>

