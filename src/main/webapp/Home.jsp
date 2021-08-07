<%@ page import="DAO.UserDAO" %>
<%@ page import="Model.User" %>
<%@ page import="DAO.SessionsDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DAO.PersonalInfoDAO" %>
<%@ page import="Model.PersonalUserInfo" %>
<%@ page import="Implementations.Suggestion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Binder</title>
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="Content/HomePage.css">
    <link rel="stylesheet" href="Content/Suggestion.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="Content/Scripts/HomePage.js"></script>

    <%
        if(session == null){
            request.getSession();
            response.sendRedirect("index.jsp");
        }
        Suggestion suggestion = null;
        String name = "";
        try {
            User user = UserDAO.getUserByID(SessionsDAO.getUser_id(session.getId()));
            PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfo(user.getUser_id());
            request.setAttribute("userInfo",userInfo);
            name = user.getUsername();
            suggestion = new Suggestion(userInfo);
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendRedirect("index.jsp");
        }


        //ArrayList<string> imagePaths = UserImagesDAO.getUserImages(userInfo.getUser_profile_id)
        //if(suggestion.getSuggestedUser == null) -> no suggested more users
    %>
</head>
<body>
<div class="suggestionBoxContainer">
    <%--img src="<%=System.getProperty("catalina.home") + "/bin/User_Files/ragaca0.jpg"%>" >--%>
    <div class="suggestionContainer">
        <span class="suggestionName">ragaca</span>
        <img class="suggestionImage" src="Content/UserImages/d65n3vh-cf16163a-ad52-461b-b0a1-d41b04c10a8b.jpg">
        <div class="suggestionButtonsContainer">
            <div style="background: #2AFE14" class="suggestionToggleButton">
                <i style="color: white" class="fas fa-check fa-2x"></i>
            </div>
            <div style="background: #FE3014" class="suggestionToggleButton">
                <i style="color: white" class="fas fa-times fa-2x"></i>
            </div>
        </div>
    </div>

</div>
<div id="uploadImageContainer" class="modal">
    <div  class="uploadImageContainer">
        <form id="sampleUploadFrm" method="POST" action="#" enctype="multipart/form-data">
            <div class="form-group">
                <div  class="input-group input-file" name="file">
						<span class="input-group-btn">
							<button  class="uploadImageImageChooser btn btn-default btn-choose" type="button">Choose</button>
						</span>
                    <input
                           type="text" class="form-control" placeholder='Choose a file...' />

                    <span class="input-group-btn">
							<button class="btn btn-warning btn-reset" type="button">Reset</button>
					</span>
                    <button type="button" class = "uploadImageSubmitButton" id="uploadBtn">Submit</button>
                    <p id = "errorMessage" value = ""></p>
                </div>
            </div>
        </form>
        <img pclass="imagePreviewContainer" id="ImagePreview" src="" alt="Picture Preview">
    </div>
</div>

<div class="chatsContainer">
    <span class="chatContainer__Header">Chats</span>

</div>
<div class="navMainContainer">
    <span class="navWelcome"><%out.write("Welcome " + name);%></span>
    <form action="ChangePasswordServlet" name="changePasswordForm" method="post">
        <div class="modal" id="changePassword">
            <div  class="userInfoModal">
                <span>Change Password</span>
                <div class="closeToggle">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="dataDismiss('changePassword')"></i>
                </div>
                <input class="userInfoInput" id = "oldPassword" name = "oldPassword" placeholder="old Password">
                <input class="userInfoInput" id = "newPassword" name = "newPassword" placeholder="new Password">
                <input class="userInfoInput" id = "newPasswordRepeat" name = "newPasswordRepeat" placeholder="repeat new Password">
                <%if(request.getAttribute("PassChangeFailed") != null)
                    out.write("<p style=\"color:red;\" >" + request.getAttribute("PassChangeFailed") +"<p>"); %>
                <button class="submitButton" type="submit">
                    submit
                </button>
            </div>
        </div>
    </form>
    <form action="ChangeEmailServlet" name="ChangeEmailServlet" method="post">
        <div class="modal" id="changeEmail">
            <div  class="userInfoModal">
                <span>Change Email</span>
                <div class="closeToggle">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="dataDismiss('changeEmail')"></i>
                </div>
                <input name = "email" id = "email" class="userInfoInput" placeholder="New Email">
                <%if(request.getAttribute("EmailChangeFailed") != null)
                    out.write("<p style=\"color:red;\">" + request.getAttribute("EmailChangeFailed") +"<p>");%>
                <button class="submitButton" type="submit">
                    submit
                </button>
            </div>
        </div>
    </form>
    <form action="ChangeUsernameServlet" name="ChangeUsernameForm" method="post">
        <div class="modal" id="changeUsername">
            <div  class="userInfoModal">
                <span>Change Username</span>
                <div class="closeToggle">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="dataDismiss('changeUsername')"></i>
                </div>
                <input class="userInfoInput" name="username" placeholder="newUsername">
                <%if(request.getAttribute("UsernameChangeFailed") != null)
                    out.write("<p style=\"color:red;\" >" + request.getAttribute("UsernameChangeFailed") +"<p>"); %>
                <button class="submitButton" type="submit">
                    submit
                </button>
            </div>
        </div>
    </form>
    <form action="ChangeLocationServlet" name="ChangeLocationForm" method="post">
        <div class="modal" id="changeLocation">
            <div  class="userInfoModal">
                <span>Change Location</span>
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
                    <input id="OutputCity"  style="display: none" name="city" class="forms">
                    <%if(request.getAttribute("LocationUpdateFailed") != null)
                        out.write("<p style=\"color:red;\" >" + request.getAttribute("LocationUpdateFailed") +"<p>"); %>
                </div>
                <button class="submitButton" type="submit">
                    submit
                </button>
            </div>

        </div>
    </form>
    <div class="navEditProfile">
        <span>Options</span>
        <div onmouseover="displayAccountInfo()" onmouseout="dismissAccountModal()" class="AccountInfoTrigger">
            <i onclick="requestSent()"  id="AccountInfo" style="color: white; margin-top: 2px" class="fas fa-bars"></i>
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