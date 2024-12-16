<%--
  Created by IntelliJ IDEA.
  User: narel
  Date: 10.12.2024
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:if test="${not empty sessionScope.client}">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </c:if>

</div>
