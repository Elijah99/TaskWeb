<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="label.add_dish_title"/></title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
</head>

<body>
<jsp:include page="fragments/header.jsp"/>
<main>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="label.add_dish_to_menu"/></h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/controller?command=main"><fmt:message
                        key="label.main_page"/></a></li>
                <li class="active"><fmt:message key="label.add_dish_title"/></li>
            </ol>
        </div>
    </div>
    <div id="addMenuForm" class="add-menu-container">
        <form method="post" action="${pageContext.request.contextPath}/controller?command=add-dish-to-menu">
            <label><fmt:message key="label.name"/></label>
            <input type="text" class="form-control" name="name">
            <label><fmt:message key="label.description"/></label>
            <input type="text" class="form-control" name="description">
            <label><fmt:message key="label.price"/></label>
            <input type="number" step="0.01" class="form-control" min="0" name="price">
            <br>
            <button type="submit" class="btn btn-block btn-lg">
                <fmt:message key="label.add"/>
            </button>
        </form>
    </div>
</main>
</body>
</html>