<%@ page import="data.User" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<% User currentUser = (User) session.getAttribute("currentSessionUser");%>
<% if (currentUser != null) {
    String redirectURL = "RequestItemsPageable";
    response.sendRedirect(redirectURL);
} %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="css/layout.css" type="text/css">
    <title>MyBay</title>
</head>

<body>
    <div class="profile-header">
        <h3 id="profile-title">Login</h3>
    </div>
    <form method="post" action="RequestLogin">
        <div class="profile-box">
            <div id="left">
                Email    <input type="email" name="email" required > </br>
                Password   <input type="password" name="psw" > </br>
                <a style="margin-top:2%;" href="Registo.jsp"> Registar </a>
                <button class="button_login" type="submit"><span>Entrar</span></button>
            </div>
        </div>
    </form>
</body>
</html>