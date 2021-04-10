<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="lang"
       value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="language/language" scope="session"/>

<html lang="${lang}">
<link rel="stylesheet" href="static/styles.css"/>

<nav class="navbar">
    <a href="${pageContext.servletContext.contextPath}/controller?command=main" class="header-logo">CAFE</a>

    <div class="navbar-right">
        <select size="1" class="dropdown-content">
            <option value="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=en">
                English
            </option>
            <option value="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=ru">
                Русский
            </option>
        </select>
        <c:if test="${sessionScope.user != null}">
            <a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="label.logout"/>
            </a>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <a href="${pageContext.request.contextPath}/controller?command=login-page">
                <fmt:message key="label.login"/>
            </a>
        </c:if>
    </div>
</nav>
</html>