<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="Content/LoginPageStyle.css">
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <script src="Content/Scripts/LoginAndRegisterScript.js"></script>
</head>
<body>

<div class="mainContainer">
    <div class="navMainContainer">
        <div class="navHeader">
            <img src="Content/Images/heartLogo.png" width="30" height="30"/>
            <span class="navHeader__text">Binder</span>
        </div>
        <div>
            <button onclick="toggleModal('LoginModal')">Login</button>
            <button onclick="toggleModal('RegisterModal')">register</button>
        </div>
    </div>
    <div id="LoginModal" class="modal">
        <div  class="loginModalContainer">
            <div class="closeButton">
                <i style="color: white" class="fas fa-times"
                onclick="toggleModal('LoginModal')"></i>
            </div>
            <div class="userInputContainer">
                <i style="color: white" class="fas fa-user"></i>
                <input class="userInputContainer__Input" type = "text" placeholder="Email">
            </div>
            <div class="userInputContainer">
                <i style="color: white" class="fas fa-lock"></i>
                <input id="LoginPassword" class="userInputContainer__Input" type="Password" placeholder="Password">
                <i id="loginEye" style="color: white" class="fas fa-eye" onclick="toggleVisibility('LoginPassword', 'loginEye')"></i>
            </div>
            <a href="#" class="redirectToRegister">Don't have an account? <br>click here to register</a>

            <button class="LoginButton">Login</button>
        </div>

    </div>
    <div class="modal" id="RegisterModal">
        <div class="registerModalContainer">
            <div class="closeButton">
                <i style="color: white" class="fas fa-times"
                   onclick="toggleModal('RegisterModal')"></i>
            </div>
            <div class="nameSurnameInputContainer">
                <input class="nameAndSurname" placeholder="Name">
                <input class="nameAndSurname" placeholder="Surname">
            </div>
            <input style="width: 80%" class="userInputContainer__Input" placeholder="Username">

            <div class="userInputContainer">
                <i style="color: white" class="fas fa-user"></i>
                <input class="userInputContainer__Input" placeholder="Email">
                <i style="color: white" class="far fa-envelope"></i>
            </div>
            <div class="userInputContainer">
                <i style="color: white" class="fas fa-lock"></i>
                <input id="RegisterPassword" class="userInputContainer__Input" type="Password" placeholder="Password">
                <i id="RegisterEye" style="color: white" class="fas fa-eye" onclick="toggleVisibility('RegisterPassword', 'RegisterEye')"></i>
            </div>
            <div class="userInputContainer">
                <i style="color: white" class="fas fa-lock"></i>
                <input id="RegisterCnFmPassword" class="userInputContainer__Input" type="Password" placeholder="Confirm Password">
                <i id="RegisterCnfmEye" style="color: white" class="fas fa-eye" onclick="toggleVisibility('RegisterCnFmPassword', 'RegisterCnfmEye')"></i>
            </div>
            <div class="chooseSex">
                <span>Choose Sex</span>
            </div>
            <div class="registerSexToggleContainer">
                <div class="registerSexToggle">
                    <div onclick="toggleSex('Male')" id="MaleSex" class="sexToggle"></div>
                    <span>Male</span>
                </div>
                <div class="registerSexToggle">
                    <div onclick="toggleSex('Female')" id="FemaleSex" class="sexToggle"></div>
                    <span>Female</span>
                </div>

            </div>
            <button class="registerButton">Register</button>

        </div>
    </div>

    </div>
</body>
</html>