<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="data.User"%>
<html>
<head>
    <title>MyBay</title>
    <% User currentUser = (User) session.getAttribute("currentSessionUser");%>
    <% if (currentUser == null) {
        String redirectURL = "/projeto2-web/Login.jsp";
        response.sendRedirect(redirectURL);
    } %>
</head>
<body>
    <jsp:include page="Layout.jsp"></jsp:include>

    Hello <%= currentUser.getName() %> !

</body>
</html>
