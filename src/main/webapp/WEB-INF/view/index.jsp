<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" %>

<head>
    <title>Index Page</title>
    <link rel="stylesheet" href="../../static/styles.css"/>
</head>

<body>
<jsp:include page="fragments/header.jsp"/>

<main class="menu-container">

    <form id="order-form" class="order-form">
        <form id="load-menu-form" class="">
            <label class="date-menu" for="dateMenu">
                <fmt:message key="label.menu_for"/>
            </label>
            <input type="date" class="date-menu" id="dateMenu" value="dateMenu">
            <button formaction="${pageContext.request.contextPath}/controller?command=load-menu" type="submit">
                <fmt:message key="label.menu_load"/>
            </button>
        </form>
        <c:if test="${dishes != null}">
            <c:forEach var="dish" items="${dishes}">
                <div class="card">
                    <img src="${dish.imagePath}">
                    <div class="card-container">
                        <h4><b>${dish.name}</b></h4>
                        <p>${dish.description}</p>
                        <p>${dish.price}</p>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </form>
</main>

</body>

</html>