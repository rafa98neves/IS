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
        <h3 id="profile-title">Perfil de <%= currentUser.getName() %></h3>
        <button class="button1"><span><a style="text-decoration: none; color:white;" href="RemoveProfile">Eliminar perfil</a></span></button>
    </div>
    <form method="post" action="RequestProfileChange">
        <div class="profile-box">
            <div id="left">
                Nome    <input type="text" name="name" value=<%= currentUser.getName()%> > </br>
                Email   <input type="email" name="email" value=<%= currentUser.getEmail() %> > </br>
            </div>
            <div id="right">
                País <select name="country"  value=<%= currentUser.getCountry().getName() %>>
                        <option value="espanha">Espanha</option>
                        <option value="portugal">Portugal</option>
                        <option value="inglaterra">Inglaterra</option>
                        <option value="marrocos">Marrocos</option>
                     </select> </br>
                Data de nascimento  <input type="date" name="birthdate"  placeholder=<%= currentUser.getBirthdate().toString() %> ></br>
            </div>
            <button class="button2" type="submit"><span>Confirmar</span></button>
        </div>
    </form>

</body>
</html>
