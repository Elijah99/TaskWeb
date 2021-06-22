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
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="label.menu"/></h1>
            <ol class="breadcrumb">
                <li class="active"><fmt:message key="label.main_page"/> </a>
                </li>
            </ol>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/controller?command=cart-page" method="post" id="order-form"
          name="order" class="order-form">
        <c:import url="/controller?command=dishesList"/>
        <c:if test="${dishesList != null}">
            <c:forEach var="dish" items="${dishesList}">
                <form method="post">
                    <div class="card">
                        <div class=" card-container">
                            <h4><h2>${dish.name}</h2></h4>
                            <p>${dish.description}</p>
                            <p><fmt:message key="label.price"/> ${dish.price}</p>
                            <c:if test="${sessionScope.userRole == 'Client'}">
                                <p>
                                    <label>
                                        <fmt:message key="label.quantity"/>
                                        <input type="number" name="quantityDishes" min="0" max="10">
                                    </label>
                                    <input type="hidden" name="idDish" value="${dish.id}">
                                    <button type="submit"
                                            formaction="${pageContext.request.contextPath}/controller?command=add-to-cart"
                                            value="${dish}"
                                            class="btn order-btn">
                                        <fmt:message key="label.add"/>
                                    </button>
                                    <c:if test="${requestScope.cartDishes.contains(dish)}">
                                        <button type="submit"
                                                formaction="${pageContext.request.contextPath}/controller?command=delete-from-cart"
                                                class="btn btn-remove">
                                            <fmt:message key="label.delete"/>
                                        </button>
                                    </c:if>
                                </p>
                            </c:if>
                        </div>
                    </div>
                </form>
            </c:forEach>
            <div class="pagination">
                <c:url var="searchUri" value="/controller?command=main&pageIndex=##"/>
                <pagination:show maxLinks="3" currPage="${pageIndex}" itemsCount="${DishesCount}"
                                 itemsOnPage="${DishesOnPage}" uri="${searchUri}"/>
            </div>
        </c:if>
    </form>
</main>

</body>

</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"/>
<script>
    function addDishToCart(dish) {
        ${requestScope.cartDishes.add(dish)}
    }

    $(document).ready(
        function () {
            $(".date-menu").change(function () {
                var date = $(this).val();
                sessionStorage.setItem("dateMenu", date);
            });
        });

</script>