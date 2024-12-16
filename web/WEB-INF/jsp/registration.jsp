<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration</title>
    <style>
        input::placeholder {
            color: rgba(0, 0, 0, 0.5); /* Полупрозрачный черный цвет */
        }
    </style>
</head>
<body>
<h1>REGISTRATION</h1>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="full_name">Name:
        <input type="text" name="full_name" id="full_name" placeholder="Ivan Ivanov">
    </label><br>
    <br>
    <label for="phone_number">Phone Number:
        <input type="text" name="phone_number" id="phone_number" placeholder="291112233">
    </label><br>
    <br>
    <label for="email">Email:
        <input type="text" name="email" id="email" placeholder="ivan@gamil.com">
    </label><br>
    <br>
    <label for="residential_address">Address:
        <input type="text" name="residential_address" id="residential_address">
    </label><br>
    <br>
    <label for="password">Password:
        <input type="password" name="password" id="password">
    </label><br>
    <br>
    <button type="submit">Send</button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>

</form>
</body>
</html>
