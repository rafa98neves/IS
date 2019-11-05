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
        <c:forEach items="${items}" var="item">
            <tr>
                <td>No tem</td>
                <td>No tem</td>
                <td>${item.name}</td>
                <td>${item.price}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
