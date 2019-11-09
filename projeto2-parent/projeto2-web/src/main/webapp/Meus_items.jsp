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
    <div class="profile-header">
        <h3 id="Table-title">Meus Itens</h3>
        <a type="button" class="button3" href="AdicionarItem.jsp">+</a>
    </div>
    <div class="meus_itens">
        <table class="item-table">
            <tr>
                <th></th>
                <th>Nome</th>
                <th>Data de inserção</th>
                <th>Preço</th>
                <th></th>
            </tr>
            <c:choose>
                <c:when test="${not empty sessionScope.currentSessionUser.getItems()}">
                    <c:forEach items="<%=currentUser.getItems()%>" var="item">
                        <tr>
                            <td> <a href="RequestItem?ItID=${item.getId()}"><img class="picture" src="${item.getPicture()}"/></a></td>
                            <td> <a href="RequestItem?ItID=${item.getId()}">${item.getName()}</a></td>
                            <td> <a href="RequestItem?ItID=${item.getId()}">${item.dateOfInsertion}</a></td>
                            <td> <a href="RequestItem?ItID=${item.getId()}">${item.getPrice()}</a></td>
                            <td> <a href="RemoveItem?ItID=${item.getId()}"> <img class="icon" src="static/trash.png"></a></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="no_item_message">Não tem itens atualmente!</div>
                </c:otherwise>
            </c:choose>
        </table>
    </div>
</body>
</html>
