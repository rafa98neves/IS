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

    <h3 id="Table-title">Os meus itens...</h3>
    <table class="item-table">
        <tr>
            <th></th>
            <th>Categoria</th>
            <th>Nome</th>
            <th>Preço</th>
            <th></th>
        </tr>
        <tr>
            <td> <a href="RequestItem?ItID=${item.name}"> ISTO E UMA IMAGEM</a></td>
            <td> <a href="RequestItem?ItID=${item.name}">Peter </a></td>
            <td> <a href="RequestItem?ItID=${item.name}">Griffin</a></td>
            <td> <a href="RequestItem?ItID=${item.name}">$100</a></td>
            <td> <a href="RequestDeleteItem"> <img class="icon" src="static/trash.png"></a></td>
        </tr>
    </table>

    <a type="button" class="button3" href="AdicionarItem.jsp">+</a>
</body>
</html>
