<%@ page language="java"
         contentType="text/html; charset=utf-8"
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>MyBay</title>
</head>

<body>
<h3>Registo</h3>
<form method="post" action="RequestRegistration">
    <table>
        <tr><td>Nome </td> <td><input type="text" name="name" required/></td></tr>
        <tr><td>Email </td> <td><input type="email" name="email" required/></td></tr>
        <tr><td>País </td> <td>
            <select name="country">
                <option value="portugal">Portugal</option>
                <option value="espanha">Espanha</option>
                <option value="inglaterra">Inglaterra</option>
                <option value="marrocos">Marrocos</option>
            </select>
        <tr><td>Data de nascimento </td> <td><input type="date" name="birthdate" required/></td></tr>
        </td></tr>
        <tr><td>Password </td> <td> <input type="password" name="psw" required/></td></tr>
        <tr><td></td> <td><input type="submit" value="Registar"/></td></tr>
    </table>
</form>
<a href="Login.jsp"> Já está registado? </a>
</body>
</html>