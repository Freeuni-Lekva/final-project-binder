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
        <div class="modal" id="changePassword">
            <div  class="userInfoModal">
                <span>Change Password</span>
                <div class="closeToggle">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="dataDismiss('changePassword')"></i>
                </div>
                <input class="userInfoInput" placeholder="old Password">
                <input class="userInfoInput" placeholder="new Password">
                <input class="userInfoInput" placeholder="repeat new Password">
                <button class="submitButton" type="submit">
                    submit
                </button>
            </div>
        </div>
        <div class="modal" id="changeEmail">
            <div  class="userInfoModal">
                <span>Change Password</span>
                <div class="closeToggle">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="dataDismiss('changeEmail')"></i>
                </div>
                <input class="userInfoInput" placeholder="newEmail">
                <button class="submitButton" type="submit">
                    submit
                </button>
            </div>
        </div>
        <div class="modal" id="changeUsername">
            <div  class="userInfoModal">
                <span>Change Password</span>
                <div class="closeToggle">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="dataDismiss('changeUsername')"></i>
                </div>
                <input class="userInfoInput" placeholder="newEmail">
                <button class="submitButton" type="submit">
                    submit
                </button>
            </div>
        </div>
        <div class="modal" id="changeLocation">
            <div  class="userInfoModal">
                <span>Change Password</span>
                <div class="closeToggle">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="dataDismiss('changeLocation')"></i>
                </div>
                <div onmouseover="disPlayDropDown('Cities')"  style="width: 80%"  class="forms"
                     onmouseout="dataDismiss('Cities')" >
                    <div class="dropDownTrigger">
                        <span id="city" >city</span>
                        <div id="Cities" class = "dropDown-content"  onclick="dataDismiss('Cities')">
                            <div class="dropDownContentElement" onclick="changeDate('city','TBILISI')">
                                Tbilisi
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','BATUMI')">
                                Batumi
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','KUTAISI')">
                                Kutaisi
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','RUSTAVI')">
                                Rustavi
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','MTSKHETA')">
                                Mtskheta
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','POTI')">
                                Poti
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','GORI')">
                                Gori
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','ZUGDIDI')">
                                Zugdidi
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','TELAVI')">
                                Telavi
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','KHASURI')">
                                Khasuri
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','AKHALTSIKHE')">
                                Akhaltsikhe
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','OZURGETI')">
                                Ozurgeti
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','BORJOMI')">
                                Borjomi
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','AMBROLAURI')">
                                Ambrolauri
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','SIGHNAGI')">
                                Sighnagi
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','USHGULI')">
                                Ushguli
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','AKHALKALAKI')">
                                Akhalkalaki
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','AKHMETA')">
                                Akhmeta
                            </div>
                            <div class="dropDownContentElement" onclick="changeDate('city','SOKHUMI')">
                                Sokhumi
                            </div>
                        </div>
                    </div>
            </div>
                <button class="submitButton" type="submit">
                    submit
                </button>
        </div>
        </div>



        <span class="navWelcome">Welcome <%=name%></span></span>
        <div class="navEditProfile">
            <span>Options</span>
            <div onmouseover="displayAccountInfo()" onmouseout="dismissAccountModal()" class="AccountInfoTrigger">
                <i  id="AccountInfo" style="color: white; margin-top: 2px" class="fas fa-bars"></i>
                <div class="accountInfoContent">
                    <div class="uploadImageTrigger" onclick="toggleModal('uploadImageContainer')">
                        Upload Image
                    </div>
                    <div onclick="displayInfoModal(0)" class="uploadImageTrigger">
                        Change Password
                    </div>
                    <div class="uploadImageTrigger" onclick="displayInfoModal(1)">
                        Change Email
                    </div>
                    <div class="uploadImageTrigger" onclick="displayInfoModal(2)">
                        Change UserName
                    </div>
                    <div class="uploadImageTrigger" onclick="displayInfoModal(3)">
                        Change Location
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
