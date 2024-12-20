<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp"%>
    <title><fmt:message key="page.car.descriptionCar"/></title>
</head>
<body>
<h1><fmt:message key="page.car.descriptionCar"/></h1>

<ul>
    <li><strong><fmt:message key="page.car.model"/>:</strong> ${requestScope.carCurrent.modelCar.model}</li>
    <li><strong><fmt:message key="page.car.carType"/>:</strong> ${requestScope.carCurrent.typCar.type}</li>
    <li><strong><fmt:message key="page.car.countSeats"/>:</strong> ${requestScope.carCurrent.numberSeats}</li>
    <li><strong><fmt:message key="page.car.color"/>:</strong> ${requestScope.carCurrent.carColor.color}</li>
    <li><strong><fmt:message key="page.car.registrationNumber"/>:</strong> ${requestScope.carCurrent.registrationNumber}</li>
    <li><strong><fmt:message key="page.car.pricePerDay"/>:</strong> ${requestScope.carCurrent.rentalPricePerDay} $/сут</li>
</ul>

<form action="order" method="get">
    <input type="hidden" name="carId" value="${requestScope.carCurrent.carId}" />
    <input type="submit" value="<fmt:message key="page.car.book"/>"  />
</form>

<form action="main" method="get">
    <input type="submit" value="<fmt:message key="page.car.back"/>" />
</form>
</body>
</html>
