<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib uri="/WEB-INF/tag/cafe-tags.tld" prefix="pagination" %>
<c:set var="pageIndex" value="${not empty param.pageIndex ? param.pageIndex : \"1\"}" scope="session"/>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" %>

<head>
    <title><fmt:message key="label.main_page"/></title>
</head>

<body>
<jsp:include page="fragments/header.jsp"/>

<main class="menu-container">

    <form action="${pageContext.request.contextPath}/controller?command=load-menu" method="post" id="load-menu-form"
          class="">
        <label class="date-menu" for="dateMenu">
            <fmt:message key="label.menu_for"/>
        </label>
        <input type="date" class="date-menu" name="dateMenu" id="dateMenu" value=required="required">
        <button type="submit">
            <fmt:message key="label.menu_load"/>
        </button>
    </form>
    <form action="${pageContext.request.contextPath}/controller?command=create-order" method="post" id="order-form"
          name="order" class="order-form">
        <c:import url="/controller?command=dishesList"/>
        <c:if test="${dishesList != null}">
            <c:forEach var="dish" items="${dishesList}">
                <form class="card" method="post">
                    <img alt="dish" class="card-image"
                         src="${pageContext.request.contextPath}/${dish.imagePath}">

                    <div class="card-container">
                        <h4><b>${dish.name}</b></h4>
                        <p>${dish.description}</p>
                        <p> <fmt:message key="label.price"/> ${dish.price}</p>
                        <c:if test="${sessionScope.userRole == 'Client'}">
                            <p>
                                <label>
                                    <fmt:message key="label.quantity"/>
                                    <input type="number" name="quantityDishes" min="0" max="10">
                                </label>
                                <input type="hidden" name="idDish" value="${dish.id}">
                                <button type="submit"
                                        formaction="${pageContext.request.contextPath}/controller?command=add-to-cart"
                                        class="btn order-btn">
                                    <fmt:message key="label.add"/>
                                </button>
                                <c:if test="${sessionScope.idDishInCart != null}">
                                    <button type="submit"
                                            formaction="${pageContext.request.contextPath}/controller?command=delete-from-cart"
                                            class="btn">
                                        <fmt:message key="label.delete"/>
                                    </button>
                                </c:if>
                            </p>
                        </c:if>
                    </div>
                </form>
            </c:forEach>
            <div class="pagination">
                <c:url var="searchUri" value="/controller?command=main&pageIndex=##"/>
                <pagination:show maxLinks="3" currPage="${pageIndex}" itemsCount="${DishesCount}"
                                   itemsOnPage="${DishesOnPage}" uri="${searchUri}"/>
            </div>
            <c:if test="${sessionScope.userRole == 'Client'}">
                <div class="main-submit-block">
                    <label class="price">
                        <fmt:message key="label.amount"/>
                        <input disabled="disabled">
                    </label>
                    <button class="btn btn-lg" type="submit">
                        <fmt:message key="label.create_order"/>
                    </button>
                </div>
            </c:if>
        </c:if>
    </form>
</main>

</body>

</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"/>
<script>
    $(document).ready(
        function () {
            $(".date-menu").change(function () {
                var date = $(this).val();
                sessionStorage.setItem("dateMenu", date);
            });
        });
</script>