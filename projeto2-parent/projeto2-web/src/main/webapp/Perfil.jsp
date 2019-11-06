<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <form method="post" action="EditProfile">
        <div class="profile-box">
            <div id="left">
                Nome    <input type="text" name="name" value="<%= currentUser.getName()%>" > </br>
                Email   <input type="email" name="email" value="<%= currentUser.getEmail() %>" > </br>
            </div>
            <div id="right">
                País <select name="country">
                        <c:forEach items="${sessionScope.countries}" var="country">
                            <option value="${country.getId()}"> ${country.getName()} </option>
                        </c:forEach>
                    </select> </br>
                Data de nascimento  <input type="date" name="birthdate"  value="<%= currentUser.getBirthdate().toString()%>" ></br>
            </div>
            <button class="button2" type="submit"><span>Confirmar</span></button>
        </div>
    </form>

</body>
</html>
