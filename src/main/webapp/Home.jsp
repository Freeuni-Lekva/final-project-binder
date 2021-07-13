<%--
  Created by IntelliJ IDEA.
  User: luka
  Date: 7/10/2021
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="DAO.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Binder</title>
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="Content/HomePage.css">
    <script src="Content/Scripts/HomePage.js"></script>
</head>
<body>
    <%UserDAO currUser = (UserDAO) request.getAttribute("user");%>
    <div class="navMainContainer">
        <span class="navWelcome">Welcome <%=currUser.getUser("userName", true).getName()%></span></span>
        <div class="navEditProfile">
            <span>Edit Profile</span>
            <i style="color: white; margin-top: 2px" class="fas fa-bars"></i>
        </div>

    </div>
    <button onclick="toggleModal('CompleteRegistrationModal')" class="completeRegisterButton">
        Complete registration
    </button>
    <form action="PersonalInfoServlet" name="RegisterInfoForm" method="post">
        <div id="CompleteRegistrationModal" class="modal">
            <div class="registrationModalContainer">
                <input style="width: 80%" name="username" class="forms" placeholder="username">
                <input style="width: 80%" name="dateOfBirth" class="forms" placeholder="dateOfBirth">
                <input style="width: 80%" name="phoneNumber" class="forms" placeholder="phoneNumber">
                <input style="width: 80%" name="city" class="forms" placeholder="city">
                <input style="width: 80%" name="hobbies" class="forms" placeholder="hobbies">

                <button type="submit" class="registerButton">Register</button>
            </div>
        </div>
    </form>
</body>
</html>
