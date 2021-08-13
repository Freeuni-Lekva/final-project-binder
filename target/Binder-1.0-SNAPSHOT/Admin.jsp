<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.User" %>
<%@ page import="DAO.SessionsDAO" %>
<%@ page import="DAO.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Admin Page</title>
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="Content/Scripts/AdminPage.js"></script>
    <link rel="stylesheet" href="Content/AdminPage.css">
    <%
        if(session == null){
            request.getSession();
            response.sendRedirect("index.jsp");
        }
        try{
            int user_id = SessionsDAO.getUser_id(session.getId());
            User user = UserDAO.getUserByID(user_id);
            if(user.isAdmin() == false){
                response.sendRedirect("index.jsp");
            }
        }catch (SQLException ex){
            response.sendRedirect("index.jsp");
            ex.printStackTrace();
        }
    %>

</head>
<body>
    <h1>Welcome Admin</h1>
    <div class="bannedUsersContainer">
        <span class="bannedUsersContainer__Header">Banned Users</span>
    </div>

    <div class="userBoxContainer">
        <div class="userContainer">
            <span class="userName" id = "userName" val = "">No more users</span>
            <img class="userImage" id = "userImage" src="">
            <div class="userBanContainer">

            </div>
        </div>
    </div>
    <!--<button  id = "BanButton" style="background: #2AFE14" class="userToggleButton">
       <i style="color: white" class="fas fa-check fa-2x"></i>
       </button>
    -->

    <div class="navMainContainer">
        <span class="navWelcome"></span>
        <div class="navSignOut">
            <span>Log out</span>
            <form action="LogoutServlet" method="post">
                <button class="signOutButton" type="submit" >
                    <i style="color: white"   class="fas fa-sign-out-alt"></i>
                </button>
            </form>
        </div>
    </div>

</body>
</html>
