<%@ page import="data.User" %>
<%@ page language="java"
         contentType="text/html; charset=utf-8"
%>

<html>
<head>
    <title>Mybay</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="css/layout.css" type="text/css">

</head>

<body>

    <% User currentUser = (User) session.getAttribute("currentSessionUser");%>
    <% if (currentUser != null) { %>
        <nav id="navbar" class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" id="Title-navbar" href="RequestItemsPageable">MyBay</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <form class="form-inline my-2 my-lg-0" action="RequestItemsPageable">
                    <input class="form-control mr-sm-2" style="margin-left: 80px;" type="search" name="search" placeholder="Procure o seu item..." aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Procurar</button>
                </form>
            </div>
            <div class="dropdown">
                <p class="username-text">  <%=currentUser.getName()%></p>
                <img src="static/user.png" class="dropimg">
                <ul class="dropdown-content">
                    <a href="Perfil.jsp">O meu perfil</a>
                    <a href="Meus_items.jsp">Os meus itens</a>
                    <a href="RequestLogout">Logout</a>
                </ul>
            </div>
        </nav>
    <%}%>
</body>
</html>