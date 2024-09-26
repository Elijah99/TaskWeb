<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="label.error"/></title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main>
    <h2 class="err-label">404</h2>
    <div class="err-msg">
        <p class="err-msg"><fmt:message key="label.page_not_found"/></p>
        <a class="err-btn" href="${pageContext.request.contextPath}/controller?command=main">
            <fmt:message key="label.main_page"/>
        </a>
    </div>
</main>
</body>
</html>
