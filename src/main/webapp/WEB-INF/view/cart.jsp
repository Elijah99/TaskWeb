<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="label.cart"/></title>
</head>
<jsp:include page="fragments/header.jsp"/>
<main>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="label.ordered"/></h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/controller?command=main"><fmt:message
                        key="label.main_page"/></a></li>
                <li class="active"><fmt:message key="label.cart"/></li>
            </ol>
        </div>
    </div>
    <div class="cart-content">
        <c:if test="${empty sessionScope.cart}">
            <h1 class="label"><fmt:message key="label.cart_empty"/></h1>
        </c:if>
        <c:if test="${not empty sessionScope.cart}">
            <c:forEach var="dishItem" items="${requestScope.cartDishes}">
                <form class="card" method="post">
                    <div class="card-container">
                        <h4><h2>${dishItem.dish.name}</h2></h4>
                        <p>${dishItem.dish.description}</p>
                        <p><fmt:message key="label.price"/> ${dishItem.dish.price}</p>
                        <p>
                            <label>
                                <fmt:message key="label.quantity"/> <input disabled="disabled"
                                                                           value="${dishItem.quantity}">
                            </label>
                            <input type="hidden" name="idDish" value="${dishItem.dish.id}">
                            <button type="submit"
                                    formaction="${pageContext.request.contextPath}/controller?command=delete-from-cart"
                                    class="btn btn-delete">
                                <fmt:message key="label.delete"/>
                            </button>
                        </p>
                    </div>
                </form>
            </c:forEach>
            <form class="main-submit-block" method="post"
                  action="${pageContext.request.contextPath}/controller?command=payment-page">
                <label>
                    <fmt:message key="label.amount"/>
                    <input class="cart-price" disabled="disabled" value="${sessionScope.cartPrice}">
                </label>
                <button type="submit" class="btn">
                    <fmt:message key="label.pay"/>
                </button>
            </form>
        </c:if>
    </div>
</main>
</html>
