<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles.css"/>

<main>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=add-menu"
          class="card-container">
        <label>Название</label>
        <input type="text" class="form-control" name="name">
        <label>Описание</label>
        <input type="text" class="form-control" name="description">
        <label>Цена</label>
        <input type="number" step="0.01" class="form-control" name="price">
        <label>Дата</label>
        <input type="date" class="form-control" name="date">
        <br>
        <button type="submit" class="btn btn-block btn-lg">
            Добавить
        </button>
    </form>
</main>
</html>
