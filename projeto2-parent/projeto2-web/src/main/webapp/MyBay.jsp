<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="data.User"%>

<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>


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
        <jsp:include page="Filters.jsp"></jsp:include>
        <div id="right">
            <table class="item-table">
                <tr>
                    <form action="RequestItemsPageable">
                        <input type="hidden" name="search" value="${requestScope.search}">
                        <input type="hidden" name="category" value="${requestScope.category}">
                        <input type="hidden" name="country" value="${requestScope.country}">
                        <input type="hidden" name="min" value="${requestScope.min}">
                        <input type="hidden" name="max" value="${requestScope.max}">
                        <input type="hidden" name="date" value="${requestScope.date}">

                        <th></th>
                        <th><button type="submit" name="by" value="categoria">Categoria</button></th>
                        <th><button type="submit" name="by" value="nome">Nome</button></th>
                        <th><button type="submit" name="by" value="preco">Preço</button></th>
                    </form>
                </tr>
                <c:choose>
                    <c:when test="${not empty items}">
                    <c:forEach items="${items}" var="item">
                        <tr class="tr-items">
                            <td> <a href="RequestItem?ItID=${item.getId()}"><img src="${item.picture}"></a> </td>
                            <td> <a href="RequestItem?ItID=${item.getId()}">${item.category.type}</a> </td>
                            <td> <a href="RequestItem?ItID=${item.getId()}">${item.name}</a> </td>
                            <td> <a href="RequestItem?ItID=${item.getId()}">${item.price}</a> </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="no_item_message">Não existem itens à venda!</div>
                </c:otherwise>
                </c:choose>
            </table>
        </div>
    </div>
</body>
</html>
