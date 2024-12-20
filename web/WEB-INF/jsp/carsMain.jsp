<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<head>
    <%@include file="header.jsp"%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="page.carsMain.namePage"/></title>
</head>
<body>

<header>
    <h1><fmt:message key="page.carsMain.namePage"/></h1>
    <nav>
        <script>
            function handleSelectChange(selectElement) {
                const selectedValue = selectElement.value;
                alert("Вы выбрали: " + selectedValue);
                //здеь придумать отображать все машины выбранной модели
            }
        </script>
        <select name="model" id="model" onchange="handleSelectChange(model)">
            <c:forEach var="model" items="${requestScope.carModels}">
                <option value=${fn:toUpperCase(model.model)}>${fn:toUpperCase(model.model)}</option>
            </c:forEach>
        </select>
    </nav>
</header>

<main>
    <h2><fmt:message key="page.carsMain.availableCar"/></h2>
    <ul>

        <c:forEach var="car" items="${requestScope.cars}">

            <c:if test="${car.statusCar.status == 'Available'}">
                <li>
                    <a href=/car?carId=${car.carId}>${car.modelCar.model}</a>
                    <p> <fmt:message key="page.carsMain.pricePerDay"/>: ${car.rentalPricePerDay} $</p>
                    <p><fmt:message key="page.carsMain.carType"/>: ${car.typCar.type}</p>
                </li>
            </c:if>


        </c:forEach>


    </ul>
</main>
</body>
</html>