<%--
  Created by IntelliJ IDEA.
  User: luka
  Date: 7/10/2021
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="DAO.UserDAO" %>
<%@ page import="Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Binder</title>
    <script src="https://kit.fontawesome.com/9bff1b7661.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="Content/HomePage.css">
    <script src="Content/Scripts/HomePage.js"></script>
    <%
        User currUser = (User) request.getAttribute("user");
        String name = null;
        if(currUser != null){
            name = currUser.getName();
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
            <span>Edit Profile</span>
            <i style="color: white; margin-top: 2px" class="fas fa-bars"></i>
        </div>

    </div>
    <button onclick="toggleModal('CompleteRegistrationModal')" class="completeRegisterButton">
        Complete registration
    </button>
    <form action="PersonalInfoServlet" name="RegisterInfoForm" method="post">
        <div id="CompleteRegistrationModal" class="modal ">
            <div class="registrationModalContainer">
                <input name="username" class="forms" placeholder="username">
                <div class="dateOfBirthYearContainer">
                    <div class="elementContainer">
                        <span>Day:</span>
                        <div class="elementDay">
                            <div id="userDay" onclick="toggleDropDown('dayDropDown')" class="dropDownTrigger">1</div>
                            <div id="dayDropDown" class="dropDown-content">
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
                    <div class="elementContainer">
                        <span>Month:</span>
                        <div class="elementMonth">
                            <div id="userMonth" onclick="toggleDropDown('MonthDropDown')" class="dropDownTrigger">1</div>
                            <div  id="MonthDropDown" class="dropDown-content">
                                <div class="dropDownContentElement" onclick="changeDate('userMonth','1')">1</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','2')">2</div>
                                <div class="dropDownContentElement" onclick="changeDate('userMonth','3')">3</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','4')">4</div>
                                <div class="dropDownContentElement" onclick="changeDate('userMonth','5')">5</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','6')">6</div>
                                <div class="dropDownContentElement" onclick="changeDate('userMonth','7')">7</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','8')">8</div>
                                <div class="dropDownContentElement" onclick="changeDate('userMonth','9')">9</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','10')">10</div>
                                <div class="dropDownContentElement" onclick="changeDate('userMonth','11')">11</div> <div class="dropDownContentElement" onclick="changeDate('userMonth','12')">12</div>
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
                <input id="city"  style="width: 80%" name="city" class="forms" placeholder="city">
                <input id="" style="width: 80%" name="hobbies" class="forms" placeholder="hobbies">
                <button type="submit" class="registerButton">Register</button>
            </div>
        </div>
    </form>
</body>
</html>
