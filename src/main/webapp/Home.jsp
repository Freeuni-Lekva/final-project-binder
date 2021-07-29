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
<div id="uploadImageContainer" class="modal">
    <div  class="uploadImageContainer">
        <div class="uploadImageHeader">
            <div>
                upload Image
            </div>
        </div>
    </div>
</div>

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
                    <div class="uploadImageTrigger" onclick="toggleModal('uploadImageContainer')">
                        Upload Image
                    </div>
                    <div class="accountLogOutPos">
                        <form action="LogoutServlet" method="post">
                            <button type="submit" class="accountLogOutPos">
                                <span>Log Out</span>
                                <div style="margin-top: 0px" class="signOutButton" t>
                                    <i style="color: #E67826"   class="fas fa-sign-out-alt"></i>
                                </div>
                            </button>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</body>
</html>
