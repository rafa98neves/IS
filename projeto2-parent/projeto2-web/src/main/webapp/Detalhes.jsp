<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" import="data.User"%>

<% User currentUser = (User) session.getAttribute("currentSessionUser");%>
<% if (currentUser == null) {
    String redirectURL = "/projeto2-web/Login.jsp";
    response.sendRedirect(redirectURL);
} %>

<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", 0);
%>

<html>
<head>
    <title>MyBay</title>
</head>
<body>
    <jsp:include page="Layout.jsp"></jsp:include>

    <div style="width: 75%; margin-left: 3%;" class="profile-header">
        <h3 id="profile-title">Detalhes de ${item.getName()} </h3>
        <h3 style="float: right">${item.getOwner().getName()}</h3>
    </div>

    <c:choose>
    <c:when test="${item.getOwner().getEmail() == sessionScope.currentSessionUser.getEmail()}">
    <form method="post" action="RequestItemChange">
        <div class="profile-box">
            <div id="left">
                Nome    <input type="text" name="name" value="<c:out value="${item.getName()}"/>"> </br>
                Preço   <input type="number" name="price" value="<c:out value="${item.getPrice()}"/>"> </br>
            </div>
            <div id="right">
                Data de inserção  <input type="date" name="date" readonly value="<c:out value="${item.getDateOfInsertion()}"/>"> </br>
                Categoria  <input type="text" name="category"></br>
            </div>
            <button class="button2" type="submit"><span>Confirmar</span></button>
        </div>
    </form>
    </c:when>
    <c:otherwise>
        <div class="profile-box">
            <div id="left2">
                Nome    <input type="text" name="name" value="<c:out value="${item.getName()}"/>" readonly> </br>
                Preço   <input type="number" step="0.01" name="price" value="<c:out value="${item.getPrice()}"/>" readonly> </br>
            </div>
            <div id="right2">
                Data de inserção  <input type="date" name="date" readonly> </br>
                Categoria  <input type="text" name="category" readonly></br>
            </div>
        </div>
    </c:otherwise>
    </c:choose>
</body>
</html>
