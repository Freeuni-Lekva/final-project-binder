package Servlets;

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
        PersonalInfoDAO infoDao = new PersonalInfoDAO();
        UserDAO userDAO = new UserDAO();
        User currUser = (User) request.getAttribute("user");

        userInfo.setUsername(request.getParameter("username"));
        userInfo.setCity(City.valueOf(request.getParameter("city")));
        userInfo.setPhoneNumber(request.getParameter("phoneNumber"));
        userInfo.setDateOfBirth(request.getParameter("dateOfBirth"));
        userInfo.setAge(userInfo.getCurrentAge(userInfo.getDateOfBirth()));
        Hobbies [] hobbies = new Hobbies[1];
        hobbies[0] = Hobbies.LONG_WALKS_ON_THE_BEACH;
        userInfo.setHobbies(hobbies);

        try {
            infoDao.setUserInfo(userInfo);
            userInfo = infoDao.getUserInfo(userInfo.getUsername());
            currUser.setUser_profile_id(userInfo.getID());
            userDAO.updateUser(currUser);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home.jsp");
            requestDispatcher.forward(request, response);
        } catch (RegistrationException | SQLException ex){
            ex.printStackTrace();
            request.setAttribute("registrationFailed", true);
            RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
            rd.forward(request, response);
        }
    }
}
