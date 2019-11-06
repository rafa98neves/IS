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
        <h3 id="profile-title">Adicionar um novo item</h3>
    </div>

    <form method="post" action="AddItem">
        <div class="profile-box">
            <div id="left">
                Imagem <input type="file" name="picture"> </br>
                Nome <input type="text" name="name"> </br>
            </div>
            <div id="right">
                Preço <input type="number" step="0.01" name="price"> </br>
                Categoria <select></select> </br>
            </div>
            <button class="button2" type="submit"><span>Adicionar</span></button>
        </div>
    </form>

</body>
</html>
