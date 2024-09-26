<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="label.payment_success_page"/></title>
</head>
<body>
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
    <div class="err-msg">
        <p class="err-msg"><fmt:message key="label.payment_success"/></p>
        <p class="err-msg"><fmt:message key="label.back_to_main"/></p>
        <a class="err-btn" href="${pageContext.request.contextPath}/controller?command=main">
            <fmt:message key="label.main_page"/>
        </a>
    </div>
</main>
</body>
</html>
