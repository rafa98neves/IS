<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <div class="profile-header">
        <h3 id="Table-title">Os meus itens...</h3>
        <a type="button" class="button3" href="AdicionarItem.jsp">+</a>
    </div>
    <div class="menu">
        <jsp:include page="Filters.jsp"></jsp:include>
        <div id="right">
            <table class="item-table">
                <tr>
                    <form action="RequestItemsPageable">
                        <input type="hidden" name="search" value="${requestScope.search}">
                        <input type="hidden" name="category" value="${requestScope.category}">
                        <input type="hidden" name="country" value="${requestScope.country}">
                        <input type="hidden" name="min" value="${requestScope.min}">
                        <input type="hidden" name="max" value="${requestScope.max}">
                        <input type="hidden" name="date" value="${requestScope.date}">

                        <th></th>
                        <th><button type="submit" name="by" value="categoria">Categoria</button></th>
                        <th><button type="submit" name="by" value="nome">Nome</button></th>
                        <th><button type="submit" name="by" value="preco">Preço</button></th>
                        <th></th>
                    </form>
                </tr>
                <c:forEach items="<%=currentUser.getItems()%>" var="item">
                    <tr>
                        <td> <a href="RequestItem?ItID=${item.getId()}"><img src="${item.getPicture()}"></a></td>
                        <td> <a href="RequestItem?ItID=${item.getId()}">${item.getCategory().getType()}</a></td>
                        <td> <a href="RequestItem?ItID=${item.getId()}">${item.getName()}</a></td>
                        <td> <a href="RequestItem?ItID=${item.getId()}">${item.getPrice()}</a></td>
                        <td> <a href="RemoveItem?ItID=${item.getId()}"> <img class="icon" src="static/trash.png"></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
