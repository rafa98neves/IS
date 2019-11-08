<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java"
         contentType="text/html; charset=utf-8"
%>

<html>
<head>
    <title>Mybay</title>
    <link rel="stylesheet" href="css/layout.css" type="text/css">
</head>

<body>
<jsp:useBean id="informationBean" class="ejb.InformationBean"/>
    <div id="left">
        <form>
            <input type="hidden" name="search" value="${requestScope.search}">
            <select name="category">
                <option  value="all"> Todas as Categorias </option>
                <c:forEach items="${informationBean.categories}" var="category">
                    <option value="${category.getId()}"> ${category.getType()} </option>
                </c:forEach>
            </select>
            <select name="country">
                <option value="all"> Todos os países </option>
                <c:forEach items="${informationBean.countries}" var="country">
                    <option value="${country.getId()}"> ${country.getName()} </option>
                </c:forEach>
            </select>
            <label>Depois de</label>    <input name="date" type="date" > </br>
            <label>Preço Mínimo</label> <input name="min" type="number" min="0" step="any"></br>
            <label>Preço Máximo</label> <input name="max" type="number" min="0" step="any"></br>
            <button class="button4" type="submit" href="RequestItemsPageable">Filtrar</button>
        </form>
    </div>
</body>
</html>