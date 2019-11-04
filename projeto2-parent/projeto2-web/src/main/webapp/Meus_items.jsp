<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="data.User"%>

<% User currentUser = (User) session.getAttribute("currentSessionUser");%>
<% if (currentUser == null) {
    String redirectURL = "/projeto2-web/Login.jsp";
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
            <th>Categoria</th>
            <th>Nome</th>
            <th>Preço</th>
            <th></th>
            <th></th>
        </tr>
        <tr>
            <td>Peter</td>
            <td>Griffin</td>
            <td>$100</td>
            <td>ISTO E UMA IMAGEM</td>
            <td> ELIMINAR </td>
        </tr>

        <button class="button2" action="AddItem">+</button>
    </table>
</body>
</html>
