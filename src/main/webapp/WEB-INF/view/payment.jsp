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
    <div class ="payment-type-container">
        <div class="payment-radio btn">
            <input id="cash" type="radio" name="cash" value="1" checked>
            <label for="cash">Cash</label>
        </div>

        <div class="payment-radio btn">
            <input id="personal-account" type="radio" name="personal-account" value="2">
            <label for="personal-account">Personal Account</label>
        </div>
    </div>
</main>
</html>