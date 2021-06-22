<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title><fmt:message key="label.manage_users"/></title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main class="admin-container">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="label.manage_users"/></h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/controller?command=main"><fmt:message
                        key="label.main_page"/></a></li>
                <li class="active"><fmt:message key="label.manage_users"/></li>
            </ol>
        </div>
    </div>

    <div id="usersForm" class="usersForm">
        <c:forEach var="user" items="${sessionScope.users}">
            <div class="user-item">
                <form action="${pageContext.request.contextPath}/controller?command=disable-user"
                      class="usersForm-content" method="post">
                    <input id="login" name="login" type="hidden" value="${user.login}">
                    <label>
                        <fmt:message key="label.username"/>
                        <input value="${user.login}" name="username" disabled="disabled">
                    </label>
                    <label>
                        <fmt:message key="label.points"/>
                        <input value="${user.points}" name="points" disabled="disabled">
                    </label>
                    <label>
                        <fmt:message key="label.money"/>
                        <input value="${user.money}" name="money" disabled="disabled">
                    </label>
                    <c:if test="${user.enabled}">
                        <label>
                            <fmt:message key="label.is_enabled"/>
                            <input value="+" name="input-enabled" disabled="disabled">
                        </label>
                        <button type="submit" class="btn btn-remove">
                            <fmt:message key="label.disable"/>
                        </button>
                    </c:if>
                    <c:if test="${!user.enabled}">
                        <label>
                            <fmt:message key="label.is_enabled"/>
                            <input value="-" name="input-enabled" disabled="disabled">
                        </label>
                        <button type="submit" class="btn btn-add">
                            <fmt:message key="label.enable"/>
                        </button>
                    </c:if>
                    <button type="button" name="btn-update" onclick="onClickUpdate(this)" class="btn btn-add">
                        <fmt:message key="label.update"/>
                    </button>
                </form>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>

<script>
    function onClickUpdate(element) {
        var form = element.form;
        var formElements = form.elements;
        for (var i = 0, len = formElements.length; i < len; ++i) {
            if (formElements[i].tagName !== "input-enabled") {
                formElements[i].disabled = false;
            }
        }
        let button = document.createElement("button");
        button.innerHTML = "Сохранить";
        button.type = "submit";
        button.formAction = "${pageContext.request.contextPath}/controller?command=save-user";
        form.appendChild(button)
    }
</script>