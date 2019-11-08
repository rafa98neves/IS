<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="data.User"%>

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

    <div class="profile-header">
        <h3 id="profile-title">Adicionar um novo item</h3>
    </div>

    <form method="post" action="RequestAddItem">
        <div class="profile-box">
            <div id="left">
                Imagem <input type="file" name="picture"> </br>
                Nome <input type="text" name="name"> </br>
                Preço <input type="number" step="0.01" name="price"> </br>
            </div>
            <div id="right">
                Categoria
                <select name="category">
                    <c:forEach items="${informationBean.categories}" var="category">
                        <option value="${category.getId()}"> ${category.getType()} </option>
                    </c:forEach>
                </select> </br>

                País
                <select name="country">
                    <c:forEach items="${informationBean.countries}" var="country">
                        <c:choose>
                            <c:when test="${country.getId() == sessionScope.currentSessionUser.getCountry().getId()}">
                                <option value="${country.getId()}" selected> ${country.getName()}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${country.getId()}" > ${country.getName()}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select> </br>
            </div>
            <button class="button2" type="submit"><span>Adicionar</span></button>
        </div>
    </form>

</body>
</html>
