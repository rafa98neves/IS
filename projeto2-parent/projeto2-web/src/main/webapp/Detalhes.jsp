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
        <h3 id="profile-title">Detalhes de ${item.getName()} - ${item.getOwner.getName()}}</h3>
    </div>


    <c:if test="${item.getOwner != currentUser}">
    <div class="profile-box">
        <div id="left">
            Nome    <input type="text" name="name" value=${item.getName} readonly> </br>
            Preço   <input type="number" name="price" value=${item.getPrice} readonly> </br>
        </div>
        <div id="right">
            Data de inserção  <input type="date" name="date" value=${item.getDateOfInsertion} readonly> </br>
            Categoria  <input type="text" name="category"  value=${item.getCategory.getName} readonly></br>
        </div>
    </div>
    </c:if>
    <c:otherwise>
    <form method="post" action="RequestItemChange">
        <div class="profile-box">
            <div id="left2">
                Nome    <input type="text" name="name" value=${item.getName}> </br>
                Preço   <input type="number" name="price" value=${item.getPrice}> </br>
            </div>
            <div id="right2">
                Data de inserção  <input type="date" name="date" value=${item.getDateOfInsertion}> </br>
                Categoria  <input type="text" name="category"  value=${item.getCategory.getName}></br>
            </div>
            <button class="button2" type="submit"><span>Confirmar</span></button>
        </div>
    </form>
    </c:otherwise>

</body>
</html>
