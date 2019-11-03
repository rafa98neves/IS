<%@ page language="java" contentType="text/html; charset=utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>MyBay</title>
</head>

<body>
    <h3>Login</h3>
    <form method="post" action="RequestLogin">
            <table>
                <tr><td>Email </td> <td><input type="email" name="email" required/></td></tr>
                <tr><td>Password </td> <td> <input type="password" name="psw" required/></td></tr>
                <tr><td></td> <td><input type="submit" value="Entrar"/></td></tr>
            </table>
    </form>
    <a href="Registo.jsp"> Registar </a>
</body>
</html>