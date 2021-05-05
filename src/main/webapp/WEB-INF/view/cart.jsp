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
    <div class="cart-content">
        <fmt:message key="label.ordered"/>
        <c:forEach var="dishItem" items="${requestScope.cartDishes}">
            <form class="card" method="post">
                    <%--                    <img class="card-image" src="${dish.imagePath}">--%>
                <img alt="dish" class="card-image"
                     src="${pageContext.request.contextPath}/${dishItem.dish.imagePath}">
                <div class="card-container">
                    <h4><b>${dishItem.dish.name}</b></h4>
                    <p>${dishItem.dish.description}</p>
                    <p> <fmt:message key="label.price"/> ${dishItem.dish.price}</p>

                    <p>
                        <label>
                            <fmt:message key="label.quantity"/> <input disabled="disabled" value="${dishItem.quantity}">
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
              action="${pageContext.request.contextPath}/controller?command=create-order">
            <label>
                <fmt:message key="label.amount"/>
                <input class="cart-price" disabled="disabled" value="${requestScope.cartPrice}">
            </label>
            <button type="submit" class="btn">
                <fmt:message key="label.pay"/>
            </button>
        </form>
    </div>
</main>
</html>
