<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="ejb.UserBean"%>
<html>
<head>
    <title>MyBay</title>
</head>
<body>

    <% UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser");%>


</body>
</html>
