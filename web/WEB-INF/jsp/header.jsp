<%--
  Created by IntelliJ IDEA.
  User: narel
  Date: 10.12.2024
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div>--%>
<%--    <c:if test="${not empty sessionScope.client}">--%>
<%--        <form action="${pageContext.request.contextPath}/logout" method="post">--%>
<%--            <button type="submit">Logout</button>--%>
<%--        </form>--%>
<%--    </c:if>--%>
<%--    <form action="${pageContext.request.contextPath}/login" method="post">--%>
<%--        <button type="submit">LogIn</button>--%>
<%--    </form>--%>
<%--    <form action="${pageContext.request.contextPath}/registration" method="post">--%>
<%--        <button type="submit">Registration</button>--%>
<%--    </form>--%>

<%--</div>--%>

<div style="display: flex; justify-content: flex-end; align-items: center; position: absolute; top: 10px; right: 10px;">
    <c:if test="${not empty sessionScope.client}">
        <form action="${pageContext.request.contextPath}/logout" method="post" style="margin-left: 10px;">
            <button type="submit">Logout</button>
        </form>
    </c:if>
    <form action="${pageContext.request.contextPath}/login" method="post" style="margin-left: 10px;">
        <button type="submit">LogIn</button>
    </form>
    <form action="${pageContext.request.contextPath}/registration" method="post" style="margin-left: 10px;">
        <button type="submit">Registration</button>
    </form>
</div>