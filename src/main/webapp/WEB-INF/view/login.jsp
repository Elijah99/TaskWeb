<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<jsp:include page="fragments/header.jsp"/>


<head>
    <title><fmt:message key="label.login_page"/></title>
    <link rel="stylesheet" href="./static/styles.css"/>
</head>

<body>
<form name="form" action="${pageContext.request.contextPath}/controller?command=login" method="post"
      class="form-signin">

    <h2 class="form-signin-heading"><fmt:message key="label.sign_in"/></h2>

    <label for="inputEmail" class="sr-only"> </label>
    <input id="inputEmail" minlength="5" maxlength="20" class="form-control" name="login" value="admin" required
           autofocus/>

    <label for="inputPassword" class="sr-only"></label>
    <input type="password" minlength="5" maxlength="20" id="inputPassword" class="form-control" name="password"
           value="admin" required/>
    <input type="submit" value="<fmt:message key="label.login"/>" class="btn btn-lg btn-block">
    <br/>
    <a href="javascript:history.back()"><fmt:message key="label.back"/></a>
    <c:if test="${requestScope.errorMessage != null}">
        <c:out value="${requestScope.errorMessage}"/>
    </c:if>


</form>
</body>

</html>