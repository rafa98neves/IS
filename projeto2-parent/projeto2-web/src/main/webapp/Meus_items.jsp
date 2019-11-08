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
        <h3 id="Table-title">Os meus itens...</h3>
        <a type="button" class="button3" href="AdicionarItem.jsp">+</a>
    </div>
    <div class="meus_itens">
        <table class="item-table">
            <tr>
                <th></th>
                <th>Categoria</th>
                <th>Nome</th>
                <th>Preço</th>
                <th></th>
            </tr>
            <c:forEach items="${currentUser.getItems()}" var="item">
                <tr>
                    <td> <a href="RequestItem?ItID=${item.getId()}">${item.getPicture()}</a></td>
                    <td> <a href="RequestItem?ItID=${item.getId()}">${item.getCategory().getType()}</a></td>
                    <td> <a href="RequestItem?ItID=${item.getId()}">${item.getName()}</a></td>
                    <td> <a href="RequestItem?ItID=${item.getId()}">${item.getPrice()}</a></td>
                    <td> <a href="RequestDeleteItem"> <img class="icon" src="static/trash.png"></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
