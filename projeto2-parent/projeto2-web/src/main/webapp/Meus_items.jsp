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

    <div class="profile-header">
        <h3 id="Table-title">Os meus itens...</h3>
        <a type="button" class="button3" href="AdicionarItem.jsp">+</a>
    </div>

    <div class="menu">
        <div id="left">
            <form>
                <select name="category">
                    <option> Todas as Categorias </option>
                    <option> Outra </option>
                </select>
                <select name="country">
                    <option> Todas os países </option>
                    <option> Outro </option>
                </select>
                <label>Depois de</label>    <input type="date" > </br>
                <label>Preço Mínimo</label> <input type="number" min="0" value="0"> </br>
                <label>Preço Máximo</label> <input type="number" min="0" value="9999"> </br>
            </form>
        </div>
        <div id="right">
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
        </div>
    </div>
</body>
</html>
