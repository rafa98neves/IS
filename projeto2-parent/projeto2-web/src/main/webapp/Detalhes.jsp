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
    <jsp:useBean id="informationBean" class="ejb.InformationBean"/>

    <div style="width: 75%; margin-left: 3%;" class="profile-header">
        <h3 id="profile-title">Detalhes de ${item.getName()} </h3>
        <h3 style="float: right">${item.getOwner().getName()}</h3>
    </div>

    <c:choose>
    <c:when test="${item.getOwner().getEmail() == sessionScope.currentSessionUser.getEmail()}">
    <form method="post" action="RequestItemChange">
        <input type="hidden" name="itemId" value="${item.getId()}">
        <div class="profile-box">
            <div id="left">
                Nome    <input type="text" name="name" value="<c:out value="${item.getName()}"/>"> </br>
                Preço   <input type="number" name="price" value="<c:out value="${item.getPrice()}"/>"> </br>
                País
                <select name="country">
                    <c:forEach items="${informationBean.countries}" var="country">
                        <c:choose>
                            <c:when test="${item.getCountry().getId() == country.getId()}">
                                <option value="${country.getId()}" selected> ${country.getName()}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${country.getId()}" > ${country.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select></br>
            </div>
            <div id="right">
                Data de inserção  <input type="date" name="date" readonly value="<c:out value="${item.getDateOfInsertion()}"/>"> </br>
                Categoria
                <select name="category">
                <c:forEach items="${informationBean.categories}" var="category">
                    <c:choose>
                    <c:when test="${item.getCategory().getType() == category.getType()}">
                        <option value="${category.getId()}" selected> ${category.getType()}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${category.getId()}" > ${category.getType()}</option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
                </select></br>
            </div>
            <a href="RemoveItem?ItID=${item.getId()}"><button class="button_delete_item" type="button"><span>Eliminar</span></button></a>
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
                Categoria  <input type="text" name="category" value="<c:out value="${requestScope.item.getCategory().getType()}"/>" readonly></br>
            </div>
        </div>
    </c:otherwise>
    </c:choose>
</body>
</html>
