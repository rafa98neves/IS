<%@ page import="data.User" %>
<%@ page language="java"
         contentType="text/html; charset=utf-8"
%>

<html>
<head>
    <title>Mybay</title>
    <link rel="stylesheet" href="css/styles.css" type="text/css">
</head>
<body>

    <% User currentUser = (User) session.getAttribute("currentSessionUser");%>
    <% if (currentUser != null) { %>
        <ul>
            <li style="float:left; margin-left: 5%"><a href="MyBay.jsp">MyBay</a></li>
            <li style="float:right; margin-right: 5%"><a href="RequestLogout">Logout</a></li>
            <li style="float:right"><a href="Perfil.jsp">Perfil</a></li>
        </ul>
    <% } %>
</body>
</html>
