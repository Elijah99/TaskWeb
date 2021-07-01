<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title><fmt:message key="label.admin_page"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles.css"/>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main class="admin-container">
    <div class="center-btns">
        <button class="showAddMenuFormBtn btn" onclick="showAddMenuForm()">
            <fmt:message key="label.add_dish_to_menu"/>
        </button>
        <button class="showUsersFormBtn btn" onclick="showUsersForm()">
            <fmt:message key="label.manage_users"/>
        </button>
    </div>
    <div id="addMenuForm" style="display: none" class="add-menu-container">
        <form method="post" action="${pageContext.request.contextPath}/controller?command=add-menu">
            <label><fmt:message key="label.name"/></label>
            <input type="text" class="form-control" name="name">
            <label><fmt:message key="label.description"/></label>
            <input type="text" class="form-control" name="description">
            <label><fmt:message key="label.price"/></label>
            <input type="number" step="0.01" class="form-control" name="price">
            <label><fmt:message key="label.date"/></label>
            <input type="date" class="form-control" name="date">
            <br>
            <button type="submit" class="btn btn-block btn-lg">
                <fmt:message key="label.add"/>
            </button>
        </form>
    </div>
    <div id="usersForm" class="usersForm">
        <c:forEach var="user" items="${sessionScope.users}">
            <div class="user-item">
                <form action="${pageContext.request.contextPath}/controller?command=disable-user"
                      class="usersForm-content" method="post">
                    <input id="login" name="login" type="hidden" value="${user.login}">
                    <label>
                        <fmt:message key="label.username"/>
                        <input value="${user.login}" disabled="disabled">
                    </label>
                    <label>
                        <fmt:message key="label.points"/>
                        <input value="${user.points}" disabled="disabled">
                    </label>
                    <label>
                        <fmt:message key="label.is_enabled"/>
                        <input value="${user.enabled}" disabled="disabled">
                    </label>
                    <button type="submit" class="btn btn-remove">
                        <fmt:message key="label.disable"/>
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>

<script src="../js/admin-page.js"/>