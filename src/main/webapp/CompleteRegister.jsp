<%@ page import="DAO.UserDAO" %>
<%@ page import="Model.User" %>
<%@ page import="DAO.SessionsDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Binder</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="Content/HomePage.css">
    <script src="Content/Scripts/HomePage.js"></script>
    <%
        if(session == null){
            request.getSession();
            response.sendRedirect("index.jsp");
        }
        String name = "";
        try {
            User user = UserDAO.getUserByID(SessionsDAO.getUser_id(session.getId()));
            if(user.isBanned()){
                SessionsDAO.deleteSession(session.getId());
                response.sendRedirect("login.jsp");
            }else if(user == null){
                response.sendRedirect("login.jsp");
            }else if(user.getHas_user_profile()){
                response.sendRedirect("Home.jsp");
            }

            name = user.getUsername();
        } catch (SQLException ex) {
            response.sendRedirect("index.jsp");
        }
    %>
</head>
<body>
<div class="chatsContainer">
    <span class="chatContainer__Header">Chats</span>
</div>

<div class="navMainContainer">
    <span class="navWelcome">Welcome <%=name%></span></span>
    <div class="navEditProfile">
        <span>Log out</span>
        <form action="LogoutServlet" method="post">
            <button class="signOutButton" type="submit">
                <i style="color: white"   class="fas fa-sign-out-alt"></i>
            </button>
        </form>
    </div>
</div>
<button onclick="toggleModal('CompleteRegistrationModal')" class="completeRegisterButton">
    Complete registration
</button>
<form action="PersonalInfoServlet" name="RegisterInfoForm" method="post">
    <div id="CompleteRegistrationModal" class="modal ">
        <div class="registrationModalContainer">
            <div id="Hobbies" class="hobbieContainer">
                <div class="hobbieContainerHeader" >
                    <span>Choose up to 5 Hobbies</span>
                    <i class="far fa-window-close closeToggle" onclick="disMissHobbies()" ></i>
                </div>
                <div class="hobbieContainerBody">
                    <div id="LONG_WALKS_ON_THE_BEACH" class="hobbieElement" onclick="chooseHobbie('LONG_WALKS_ON_THE_BEACH')" >
                        Long walks on beach
                    </div>
                    <div id="HOBBY2" class="hobbieElement" onclick="chooseHobbie('HOBBY2')">
                        HOBBY2
                    </div>
                    <div id="HOBBY3" class="hobbieElement" onclick="chooseHobbie('HOBBY3')">
                        HOBBY3
                    </div>
                    <div id="HOBBY4" class="hobbieElement" onclick="chooseHobbie('HOBBY4')">
                        HOBBY4
                    </div>
                    <div id="HOBBY5" class="hobbieElement" onclick="chooseHobbie('HOBBY5')">
                        HOBBY5
                    </div>
                    <div id="HOBBY6" class="hobbieElement" onclick="chooseHobbie('HOBBY6')">
                        HOBBY6
                    </div>
                    <div id="HOBBY7" class="hobbieElement" onclick="chooseHobbie('HOBBY7')">
                        HOBBY7
                    </div>
                    <div id='HOBBY8'class="hobbieElement" onclick="chooseHobbie('HOBBY8')">
                        HOBBY8
                    </div>
                    <div id="HOBBY9" class="hobbieElement" onclick="chooseHobbie('HOBBY9')">
                        HOBBY9
                    </div>
                </div>
                <input id="OutputHobbies"  style="display: none" name="hobbies" class="forms">
            </div>

            <div class="registerBody">
                <div class="dateOfBirthYearContainer">
                    <div class="elementContainer">
                        <span>Day:</span>
                        <div class="elementDay">
                            <div onmouseover="disPlayDropDown('dayDropDown')" onmouseout="dataDismiss('dayDropDown')"
                                 class="dropDownTrigger"
                            >
                                <span id="userDay" class="dropDownContentElement">1</span>
                                <div id="dayDropDown" class="dropDown-content" onclick="dataDismiss('dayDropDown')" >
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '1')">1</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '2')">2</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '3')">3</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '4')">4</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '5')">5</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '6')">6</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '7')">7</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '8')">8</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '9')">9</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '10')">10</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '11')">11</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '12')">12</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '13')">13</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '14')">14</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '15')">15</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '16')">16</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '17')">17</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '18')">18</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '19')">19</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '20')">20</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '21')">21</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '22')">22</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '23')">23</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '24')">24</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '25')">25</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '26')">26</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '27')">27</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '28')">28</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '29')">29</div> <div class="dropDownContentElement" onclick="changeDate('userDay', '30')">30</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userDay', '31')">31</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="elementContainer">
                        <span>Month:</span>
                        <div class="elementMonth">
                            <div onmouseover="disPlayDropDown('MonthDropDown')"
                                 onmouseout="dataDismiss('MonthDropDown')" class="dropDownTrigger">
                                <span id="userMonth" class="dropDownContentElement">1</span>
                                <div  id="MonthDropDown" class="dropDown-content"
                                      onclick="dataDismiss('MonthDropDown')">
                                    <div class="dropDownContentElement" onclick="changeDate('userMonth','1')">1</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','2')">2</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userMonth','3')">3</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','4')">4</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userMonth','5')">5</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','6')">6</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userMonth','7')">7</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','8')">8</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userMonth','9')">9</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','10')">10</div>
                                    <div class="dropDownContentElement" onclick="changeDate('userMonth','11')">11</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','12')">12</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="elementContainer">
                        <span>Year:</span>
                        <input oninput="setDate()"  class="elementYear" placeholder="2000">
                    </div>
                </div>
                <input id="dateData" style="display: none" name="dateOfBirth" class="forms">
                <input id="phoneNumber" style="width: 80%" name="phoneNumber" class="forms" placeholder="phoneNumber">
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
                <input id="OutputCity"  style="display: none" name="city" class="forms">
                <div onclick="hobbiesAppear()"   class="hobbieButton">
                    Hobbies
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
                    <input  class="genderInput"  style="display: none" name="sex" class="forms">
                </div>
                <%
                    String ErrorMessage = (String) request.getAttribute("ErrorMessage");
                    if(ErrorMessage != null)
                    {
                        System.out.println(ErrorMessage);
                        out.print("<p style=\"color:red\">" + ErrorMessage + "</p>");
                    }
                %>
                <button class="submitButton" type="submit" class="registerButton">
                    Complete Registration</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
