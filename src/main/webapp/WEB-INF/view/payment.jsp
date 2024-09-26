<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="label.payment_page"/></title>
</head>
<jsp:include page="fragments/header.jsp"/>
<main>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><fmt:message key="label.payment_page"/></h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/controller?command=main"><fmt:message
                        key="label.main_page"/></a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=cart-page"><fmt:message
                        key="label.cart"/></a></li>
                <li class="active"><fmt:message key="label.payment_page"/></li>
            </ol>
        </div>
    </div>
    <form class="payment-type-container">
        <c:if test="${not empty requestScope.notEnoughMoney}">
            <label class="payment-elem"><fmt:message
                    key="label.not_enough_money"/>: ${sessionScope.cartPrice - sessionScope.userMoney}</label>
        </c:if>
        <div>
            <label class="payment-elem"><fmt:message key="label.amount"/>: ${sessionScope.cartPrice}</label>
        </div>
        <div>
            <label><fmt:message key="label.money_on_personal_account"/>: ${sessionScope.userMoney} </label>
        </div>
        <a class="btn-lg center-btns payment-elem err-btn"
           href="${pageContext.request.contextPath}/controller?command=create-order">
            <fmt:message key="label.pay"/>
        </a>
    </form>
</main>
</html>