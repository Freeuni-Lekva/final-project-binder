<%@ page import="DAO.UserDAO" %>
<%@ page import="Model.User" %>
<%@ page import="DAO.SessionsDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DAO.PersonalInfoDAO" %>
<%@ page import="Model.PersonalUserInfo" %>
<%@ page import="Implementations.Suggestion" %>
<%@ page import="DAO.UserImagesDAO" %>
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
        String name = "";
        String path = "";
        String sex = "";
        int id = 0;
        int profileID = 0;
        try {
            User user = UserDAO.getUserByID(SessionsDAO.getUser_id(session.getId()));
            PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfo(user.getUser_id());
            request.setAttribute("userInfo",userInfo);
            name = user.getUsername();
            id = user.getUser_id();
            profileID = userInfo.getUser_profile_id();
            sex = userInfo.getSex();
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
    <div class="suggestionContainer">
        <span class="suggestionName" id = "suggestionName" val = "">No more suggestions</span>
        <span id = "suggestedUserGender" hidden><%out.write(sex);%></span>
        <span id = "currentUserID" hidden><%out.write(String.valueOf(id));%></span>
        <span id = "currentUserProfileID" hidden><%out.write(String.valueOf(profileID));%></span>
        <img class="suggestionImage" id = "suggestionImage" src="">
        <div class="suggestionButtonsContainer">
            <button  id = "LikeButton" style="background: #2AFE14" class="suggestionToggleButton">
                <i style="color: white" class="fas fa-check fa-2x"></i>
            </button>
            <button id = "DislikeButton" style="background: #FE3014" class="suggestionToggleButton">
                <i style="color: white" class="fas fa-times fa-2x"></i>
            </button>
        </div>
    </div>
</div>
<div id="uploadImageContainer" class="modal">
    <div  class="uploadImageContainer">
        <form id="sampleUploadFrm" method="POST" action="#" enctype="multipart/form-data">
            <div class="form-group">
                <div  class="input-group input-file uploadImageHeader" name="file">
						<span class="input-group-btn">
							<button  class="uploadImageImageChooser btn btn-default btn-choose" type="button">Choose</button>
						</span>
                    <input style="display: none"
                           type="text" class="form-control" placeholder='Choose a file...' />

                    <span  class="input-group-btn">
							<button class="btn btn-warning btn-reset uploadImageSubmitButton" type="button">Reset</button>
					</span>
                    <button type="button" class = "uploadImageSubmitButton" id="uploadBtn">Submit</button>
                    <p id = "errorMessage" value = ""></p>
                </div>
            </div>
        </form>
        <img class="imagePreviewContainer" id="ImagePreview" src="" alt="Picture Preview">
    </div>
</div>

<div class="chatsContainer">
    <span  class="chatContainer__Header">Chats</span>
    <div id ="chatsContainerBody" class="chatsContainerBody">

    </div>
    <div class="openedChatContainer">
        <div class="openedChatHeader">
            <span id="currentOpenChatName">dfgdgf</span>
            <i onclick="chatDismiss()" class="fas fa-times closeToggle"></i>
        </div>
        <div class="openedChatBody">

        </div>
        <div class="openedChatInputContainer">
            <input class="openedChatInput" placeholder="Send message">
            <i id="sendMessageId" style="color: #E67826; cursor: pointer" class="far fa-paper-plane fa-2x"></i>
        </div>
    </div>
</div>
<div class="navMainContainer">
    <span class="navWelcome"><%out.write("Welcome " + name);%></span>
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
                <button class="submitButton" id = "changePasswordButton">
                    submit
                </button>
                <p id = "changePasswordError" value = ""></p>
            </div>
    </div>

    <div class="modal" id="changeEmail">
        <div  class="userInfoModal">
            <span>Change Email</span>
            <div class="closeToggle">
                <i style="color: white; cursor: pointer" class="fas fa-times"
                    onclick="dataDismiss('changeEmail')"></i>
            </div>
            <input name = "email" id = "email" class="userInfoInput" placeholder="New Email">
            <button id = "changeEmailButton" class="submitButton" >
                submit
            </button>
            <p id = "changeEmailError" value = ""></p>
        </div>
    </div>

    <div class="modal" id="changeUsername">
            <div  class="userInfoModal">
                <span>Change Username</span>
                <div class="closeToggle">
                    <i style="color: white; cursor: pointer" class="fas fa-times"
                       onclick="dataDismiss('changeUsername')"></i>
                </div>
                <input class="userInfoInput" name="username" id = "username" placeholder="newUsername">
                <button class="submitButton" id = "changeUsernameID">
                    submit
                </button>
                <p id = "changeUsernameError" value = ""></p>
            </div>
    </div>

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
                   </div>
                <button class="submitButton" id = "changeLocationButton" type="submit">
                    submit
                </button>
                <p id = "changeLocationError" value = ""></p>
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