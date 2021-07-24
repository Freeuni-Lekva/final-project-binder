package Servlets;

import DAO.CookiesDAO;
import DAO.PersonalInfoDAO;
import DAO.UserDAO;
import Enums.City;
import Enums.Hobbies;
import Exceptions.RegistrationException;
import Model.PersonalUserInfo;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PersonalInfoServlet", value = "/PersonalInfoServlet")
public class PersonalInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonalUserInfo userInfo = new PersonalUserInfo();
        User currUser ;

        String username = "";
        try{
            username = CookiesDAO.getUsername(request.getSession(false).getId());
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        userInfo.setUsername(username);
        userInfo.setCity(City.valueOf(request.getParameter("city")));
        userInfo.setPhoneNumber(request.getParameter("phoneNumber"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        userInfo.setDateOfBirth(dateOfBirth);
        userInfo.setAge(userInfo.getCurrentAge(userInfo.getDateOfBirth()));
        Hobbies [] hobbies = new Hobbies[1];
        hobbies[0] = Hobbies.LONG_WALKS_ON_THE_BEACH;
        userInfo.setHobbies(hobbies);
        userInfo.setSex("MALE");
        try {
            PersonalInfoDAO.setUserInfo(userInfo);
            userInfo = PersonalInfoDAO.getUserInfo(userInfo.getUsername());
            currUser = UserDAO.getUser(username,true);
            currUser.setUser_profile_id(userInfo.getId());
            UserDAO.updateUser(currUser);
            request.setAttribute("user", currUser);
            if(currUser.getUser_profile_id() != 0){
                request.setAttribute("fullyRegistered", "true");
            }else{
                request.setAttribute("fullyRegistered","false");
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home.jsp");
            requestDispatcher.forward(request, response);
        } catch (RegistrationException | SQLException ex){
            ex.printStackTrace();
            request.setAttribute("registrationFailed", true);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
    }
}
