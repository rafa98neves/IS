<%@ page import="data.User" %>
<%@ page language="java"
         contentType="text/html; charset=utf-8"
%>

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
    <h3 id="profile-title">Registo</h3>
</div>
<form method="post" action="RequestRegistration">
    <div class="profile-box">
        <div id="left">
            Nome    <input type="text" name="name" required/> </br>
            Email   <input type="email" name="email" placeholder="exemplo@email.com" required/> </br>
            Password   <input type="password" name="psw" required/> </br>
            <a href="Login.jsp"> Já está registado? </a>
        </div>
        <div id="right">
            País <select name="country">
            <option value="portugal">Portugal</option>
            <option value="espanha">Espanha</option>
            <option value="inglaterra">Inglaterra</option>
            <option value="marrocos">Marrocos</option>
        </select> </br>
            Data de nascimento  <input type="date" name="birthdate" required/></br>
        </div>
        <button class="button2" type="submit"><span>Registar</span></button>
    </div>
</form>
</body>
</html>