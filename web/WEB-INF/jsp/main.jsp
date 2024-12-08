
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h1>Автомобили</h1>
<ul>
    <c:forEach var="car" items="${requestScope.cars}">

        <p>

                ${car}

        </p>
    </c:forEach>

</ul>

</body>
</html>

