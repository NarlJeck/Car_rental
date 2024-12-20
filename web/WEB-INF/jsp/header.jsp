<%--
  Created by IntelliJ IDEA.
  User: narel
  Date: 10.12.2024
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fnt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <div id="locale">
        <form action="${pageContext.request.contextPath}/locale" method="post">
            <button type="submit" name="lang" value="ru_RU">RU</button>
            <button type="submit" name="lang" value="en_US">EN</button>
        </form>
    </div>
    <fnt:setLocale value="${sessionScope.lang !=null ? sessionScope.lang : (param.lang !=null ? param.lang: 'en_US')}"/>
    <fnt:setBundle basename="translations"/>
</div>

<div style="display: flex; justify-content: flex-end; align-items: center; position: absolute; top: 10px; right: 10px;">
    <c:if test="${not empty sessionScope.client}">
        <div id="logout">
            <form action="${pageContext.request.contextPath}/logout" method="post" style="margin-left: 10px;">
                <button type="submit">Logout</button>
            </form>
        </div>

    </c:if>
    <c:if test="${pageContext.request.requestURI != '/WEB-INF/jsp/login.jsp'}">
        <div id="login">
            <form action="${pageContext.request.contextPath}/login" method="post" style="margin-left: 10px;">
                <button type="submit">LogIn</button>
            </form>
        </div>
    </c:if>


    <div id="Registration">
        <form action="${pageContext.request.contextPath}/registration" method="post" style="margin-left: 10px;">
            <button type="submit">Registration</button>
        </form>
    </div>


</div>