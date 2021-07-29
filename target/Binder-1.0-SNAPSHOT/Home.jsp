<%@ page import="DAO.UserDAO" %>
<%@ page import="Model.User" %>
<%@ page import="DAO.SessionsDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Binder</title>
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="Content/HomePage.css">
    <script src="Content/Scripts/HomePage.js"></script>
    <%
        if(session == null){
            request.getSession();
            response.sendRedirect("index.jsp");
        }
        String name = SessionsDAO.getUsername(session.getId());
        if(name == null){
            response.sendRedirect("index.jsp");
        }
    %>
</head>
<body>
<div class="chatsContainer">
    <span class="chatContainer__Header">Chats</span>
</div>
    <div class="navMainContainer">
        <span class="navWelcome">Welcome <%=name%></span></span>
        <div class="navEditProfile">
            <span>Options</span>
            <div onmouseover="displayAccountInfo()" onmouseout="dismissAccountModal()" class="AccountInfoTrigger">
                <i  id="AccountInfo" style="color: white; margin-top: 2px" class="fas fa-bars"></i>
                <div class="accountInfoContent">
                    ragaca ragaca
                </div>
            </div>
        </div>
    </div>
</body>
</html>
