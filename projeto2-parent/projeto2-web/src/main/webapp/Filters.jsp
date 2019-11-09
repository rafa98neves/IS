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
        <h3 class="filter_title">Filtrar por:</h3>
        <select id="filters" onchange="show()">
            <option value="default" selected>Escolha um filtro</option>
            <option value="categoria">Categoria</option>
            <option value="data">Data</option>
            <option value="preco">Preço</option>
        </select>
        <form id="category">
            <input type="hidden" name="search" value="${requestScope.search}">
            <select name="category">
                <c:forEach items="${informationBean.categories}" var="category">
                    <option value="${category.getId()}"> ${category.getType()} </option>
                </c:forEach>
            </select>
            <button class="button4" type="submit" href="RequestItemsPageable">Filtrar</button>
        </form>
        <form id="country">
            <input type="hidden" name="search" value="${requestScope.search}">
            <select name="country">
                <option value="all"> Todos os países </option>
                <c:forEach items="${informationBean.countries}" var="country">
                    <option value="${country.getId()}"> ${country.getName()} </option>
                </c:forEach>
            </select>
            <button class="button4" type="submit" href="RequestItemsPageable">Filtrar</button>
        </form>
        <form id="date">
            <input type="hidden" name="search" value="${requestScope.search}">
            <label>Depois de</label>    <input name="date" type="date" > </br>
            <button class="button4" type="submit" href="RequestItemsPageable">Filtrar</button>
        </form>
        <form id="range">
            <input type="hidden" name="search" value="${requestScope.search}">
            <label>Preço Mínimo</label> <input name="min" type="number" min="0" step="any"></br>
            <label>Preço Máximo</label> <input name="max" type="number" min="0" step="any"></br>
            <button class="button4" type="submit" href="RequestItemsPageable">Filtrar</button>
        </form>
    </div>
</body>

<script>
    function show() {
        var dropDown = document.getElementById("filters").value;

        if(dropDown === "default"){
            document.getElementById('category').style.display = 'none';
            document.getElementById('country').style.display = 'none';
            document.getElementById('date').style.display = 'none';
            document.getElementById('range').style.display = 'none';
        } else {
            document.getElementById('category').style.display = 'none';
            document.getElementById('country').style.display = 'none';
            document.getElementById('date').style.display = 'none';
            document.getElementById('range').style.display = 'none';
            if(dropDown === "categoria"){
                document.getElementById('category').style.display = 'block';
            } else if(dropDown === "pais"){
                document.getElementById('country').style.display = 'block';
            } else if(dropDown === "data"){
                document.getElementById('date').style.display = 'block';
            } else if(dropDown === "preco"){
                document.getElementById('range').style.display = 'block';
            }
        }
    }
</script>
</html>