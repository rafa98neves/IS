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

    <h3 id="Table-title">Itens online</h3>
    <table class="item-table">
        <tr>
            <th></th>
            <th>Categoria</th>
            <th>Nome</th>
            <th>Preço</th>
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
</body>
</html>
