<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>MyBay</title>
</head>

<body>
    <jsp:include page="Layout.jsp"></jsp:include>

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