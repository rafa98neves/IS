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
            <th></th>
        </tr>
        <tr>
            <td>ISTO E UMA IMAGEM</td>
            <td>Peter</td>
            <td>Griffin</td>
            <td>$100</td>
            <td><a href="/novo"> <img class="icon" src="static/edit.png"> </a></td>
            <td><a href="RequestDeleteItem"> <img class="icon" src="static/trash.png"></a></td>
        </tr>
    </table>

    <button class="button3" action="RequestAddItem">+</button>
</body>
</html>
