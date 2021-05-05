<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="lang"
       value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale.language}"
       scope="session"/>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="language/language" scope="session"/>

<html lang="${lang}">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles.css"/>

<nav class="navbar">
    <a href="${pageContext.servletContext.contextPath}/controller?command=main" class="header-logo">CAFE</a>

    <div class="navbar-right">
        <div class="dropdown">
            <button class="dropdown-btn">${lang}</button>
            <div class="dropdown-content">
                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=en">
                    English
                </a>
                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=ru">
                    Русский
                </a>
            </div>
        </div>
        <c:if test="${sessionScope.userRole == 'Client'}">
            <a href="${pageContext.request.contextPath}/controller?command=cart-page">
                <fmt:message key="label.cart"/>
            </a>
        </c:if>
        <c:if test="${sessionScope.userRole == 'Admin'}">
            <a href="${pageContext.request.contextPath}/controller?command=admin-page">
                <fmt:message key="label.admin_page"/>
            </a>
        </c:if>
        <c:if test="${sessionScope.userId != null}">
            <a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="label.logout"/>
            </a>
        </c:if>
        <c:if test="${sessionScope.userId == null}">
            <a href="${pageContext.request.contextPath}/controller?command=login-page">
                <fmt:message key="label.login"/>
            </a>
        </c:if>
    </div>
</nav>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
