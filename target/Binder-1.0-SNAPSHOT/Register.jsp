<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 08-Jul-21
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personalize your profile</title>
    <link rel="stylesheet" href="Content/LoginPageStyle.css">
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <script src="Content/Scripts/LoginAndRegisterScript.js"></script>
</head>
<body>
    <form action="PersonalInfoServlet" name="RegisterInfoForm" method="post">
            <div class="registerModalContainer">
                <input style="width: 80%" name="username" class="userInputContainer__Input" placeholder="username">
                <input style="width: 80%" name="dateOfBirth" class="userInputContainer__Input" placeholder="dateOfBirth">
                <input style="width: 80%" name="phoneNumber" class="userInputContainer__Input" placeholder="phoneNumber">
                <input style="width: 80%" name="city" class="userInputContainer__Input" placeholder="city">
                <input style="width: 80%" name="hobbies" class="userInputContainer__Input" placeholder="hobbies">

                <button type="submit" class="registerButton">Register</button>
            </div>
    </form>
</body>
</html>
