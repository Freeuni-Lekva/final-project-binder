<%@ page import="DAO.UserDAO" %>
<%@ page import="Model.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DAO.SessionsDAO" %>
<%@ page import="java.io.File" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<%
    if(request.getSession(false) == null){
        request.getSession();
    }
    session.setAttribute("JSESSIONID",session.getId());
    try{
        if(SessionsDAO.getUser_id(session.getId()) != -1){
            User user = UserDAO.getUserByID(SessionsDAO.getUser_id(session.getId()));
            if(user.isBanned()){
                SessionsDAO.deleteSession(session.getId());
                response.sendRedirect("login.jsp");
            }
            else if(user.isAdmin()){
                response.sendRedirect("Admin.jsp");
            }
            else if(user.getHas_user_profile() == true){
                response.sendRedirect("Home.jsp");
            }else{
                response.sendRedirect("CompleteRegister.jsp");
            }
        }
    }catch (SQLException ex){
        ex.printStackTrace();
    }

%>

<html>
<head>
    <link rel="stylesheet" href="Content/LoginPageStyle.css">
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <script src="Content/Scripts/LoginAndRegisterScript.js"></script>
    <title>Binder</title>
</head>

<body>

<div class="mainContainer">
    <%String MessageLogin = (String) request.getAttribute("loginWrong");%>
    <%String MessageRegister = (String) request.getAttribute("registrationFailed");%>


    <div class="navMainContainer">
        <div class="navHeader">
            <img src="Content/Images/heartLogo.png" width="30" height="30"/>
            <span class="navHeader__text">Binder</span>
        </div>
        <div class="modalButtonsContainer">
            <div onclick="toggleModal('LoginModal')" class="loginAndRegisterToggleContainer">
                <i  class="fas fa-heart"></i>
                Login
            </div>

            <div  onclick="toggleModal('RegisterModal')" class="loginAndRegisterToggleContainer">
                <i style="color: #F0A3FE" class="fas fa-heart"></i> Register</div>
        </div>
    </div>
    <form action="LoginServlet" name="loginForm" method="post">
        <div id="LoginModal" class="modal">
            <div  class="loginModalContainer">
               <span class="servletMessageLogin"><%=MessageLogin%></span>
                <div class="closeButton">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="toggleModal('LoginModal')"></i>
                </div>
                <div class="userInputContainer">
                    <i style="color: white; cursor: pointer" class="fas fa-user"></i>
                    <input class="userInputContainer__Input" name="email" type = "text" placeholder="Email or Username">
                </div>
                <div class="userInputContainer">
                    <i style="color: white; cursor: pointer" class="fas fa-lock"></i>
                    <input id="LoginPassword" name="password" class="userInputContainer__Input" type="Password" placeholder="Password">
                    <i id="loginEye" style="color: white; cursor: pointer" class="fas fa-eye" onclick="toggleVisibility('LoginPassword', 'loginEye')"></i>
                </div>

                <div onclick="toggleModal('LoginModal'); toggleModal('RegisterModal')" class="loginAndRegisterToggleContainer">
                    <i style="color: white" > Don't have an account?<br>click here to register </i>
                </div>

                <button type="submit" class="LoginButton">Login</button>
            </div>
        </div>
    </form>
    <form action="RegisterServlet" name="registerForm" method="post">
        <div class="modal" id="RegisterModal">
            <div class="registerModalContainer">
                <span class="servletRegisterMessage"><%=MessageRegister%></span>
                <div class="closeButton">
                    <i style="color: white" class="fas fa-times"
                       onclick="toggleModal('RegisterModal')"></i>
                </div>
                <div class="nameSurnameInputContainer">
                    <input name="firstname" class="nameAndSurname" placeholder="Name">
                    <input name="surname" class="nameAndSurname" placeholder="Surname">
                </div>
                <input style="width: 80%" name="username" class="userInputContainer__Input" placeholder="Username">

                <div class="userInputContainer">
                    <i style="color: white" class="fas fa-user"></i>
                    <input class="userInputContainer__Input" name="email" placeholder="Email">
                    <i style="color: white" class="far fa-envelope"></i>
                </div>
                <div class="userInputContainer">
                    <i style="color: white" class="fas fa-lock"></i>
                    <input id="RegisterPassword" name="RegisterPassword" class="userInputContainer__Input" type="Password" placeholder="Password">
                    <i id="RegisterEye" style="color: white" class="fas fa-eye" onclick="toggleVisibility('RegisterPassword', 'RegisterEye')"></i>
                </div>
                <div class="userInputContainer">
                    <i style="color: white" class="fas fa-lock"></i>
                    <input id="RegisterCnFmPassword" class="userInputContainer__Input" type="Password" placeholder="Confirm Password">
                    <i id="RegisterCnFmEye" style="color: white" class="fas fa-eye" onclick="toggleVisibility('RegisterCnFmPassword', 'RegisterCnFmEye')"></i>
                </div>
                <button type="submit" class="registerButton">Register</button>
            </div>
        </div>
    </form>

</div>
</body>
</html>