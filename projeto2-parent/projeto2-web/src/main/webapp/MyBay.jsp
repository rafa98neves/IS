<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="data.User"%>

<% User currentUser = (User) session.getAttribute("currentSessionUser");%>
<% if (currentUser == null) {
    String redirectURL = "Login.jsp";
    response.sendRedirect(redirectURL);
} %>

<html>
<head>
    <title>MyBay</title>
</head>
<body>
    <jsp:include page="Layout.jsp"></jsp:include>

    <div class="profile-header">
        <h3 id="Table-title">Itens online</h3>
    </div>
    <div class="menu">
        <div id="left">
            <form>
                <select name="category">
                    <option  value="all"> Todas as Categorias </option>
                    <c:forEach items="${sessionScope.categories}" var="category">
                        <option value="${category.getId()}"> ${category.getType()} </option>
                    </c:forEach>
                </select>
                <select name="country">
                    <option value="all"> Todas os países </option>
                    <c:forEach items="${sessionScope.countries}" var="country">
                        <option value="${country.getId()}"> ${country.getName()} </option>
                    </c:forEach>
                </select>
                <label>Depois de</label>    <input name="date" type="date" > </br>
                <label>Preço Mínimo</label> <input name="min" type="number" min="0" step="any"></br>
                <label>Preço Máximo</label> <input name="max" type="number" min="0" step="any"></br>
                <button class="button4" type="submit" href="RequestItemsPageable">Filtrar</button>
            </form>
        </div>
        <div id="right">
            <table class="item-table">
                <tr>
                    <th></th>
                    <th><a href="RequestItemsOrderby?by=categoria">Categoria</a></th>
                    <th><a href="RequestItemsOrderby?by=categoria">Nome</a></th>
                    <th><a href="RequestItemsOrderby?by=categoria">Preço</a></th>
                </tr>
                <c:catch var="exception">
                    <c:forEach items="${items}" var="item">
                        <tr class="tr-items">
                            <td> <a href="RequestItem?ItID=${item.name}">No tem</a> </td>
                            <td> <a href="RequestItem?ItID=${item.name}">No tem</a> </td>
                            <td> <a href="RequestItem?ItID=${item.name}">${item.name}</a> </td>
                            <td> <a href="RequestItem?ItID=${item.name}">${item.price}</a> </td>
                        </tr>
                    </c:forEach>
                </c:catch>
                <c:if test="${not empty exception}">
                    Nenhum item foi encontrado.
                </c:if>
            </table>
        </div>
    </div>
</body>
</html>
