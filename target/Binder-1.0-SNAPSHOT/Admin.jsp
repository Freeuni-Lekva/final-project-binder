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
        <div class="bannedUserBody"></div>
    </div>

    <div class="userBoxContainer">
        <div class="userContainer">
            <div class="userContainerHeader" id = "userName" val = "">
                <span id="currentUserUsername">
                </span>
                <span id="currentUserStatus">
                </span>
            </div>
            <div class="userImageandInfoContainer">
                <img  class="userContainerImage" src="Content/Images/DEFAULT_UNI_PROFILE.jpg">
                <div class="userInfoBox">
                    <span id="currentUserAge" class="userInfoValue"></span>
                    <span id="currentUserSex" class="userInfoValue"></span>
                    <span id="currentUserCity" class="userInfoValue"></span>
                    <span id="currentUserName" class="userInfoValue"></span>
                    <span id="currentUserSurname" class="userInfoValue"></span>
                    <span id="currentUserEmail" class="userInfoValue"></span>
                </div>
            </div>
            <button  class="userBanButton">
                Ban User
            </button>
            <button  class="userUnbanButton">
                Unban User
            </button>
            <div  class="userIsAdminDisplay">
                User is Admin
            </div>
        </div>
    </div>
    <!--<button  id = "BanButton" style="background: #2AFE14" class="userToggleButton">
       <i style="color: white" class="fas fa-check fa-2x"></i>
       </button>
    -->

    <div class="navMainContainer">
        <span class="navWelcome"></span>
        <div class="searchForUserContainer">
            <input class="searchForUserInput" placeholder="Search for User">
            <i id="searchForUserId" style="color: white; cursor: pointer" class="fas fa-search fa-2x"></i>

        </div>
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
