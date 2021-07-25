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
        HttpSession httpSession = request.getSession(false);
        PersonalUserInfo userInfo = new PersonalUserInfo();
        User currUser ;

        String username = "";
        try{
            username = CookiesDAO.getUsername(request.getSession(false).getId()); // todo user_id gvinda aq username is magivrad
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        userInfo.setUsername(username);
        userInfo.setCity(City.valueOf(request.getParameter("city")));
        userInfo.setPhoneNumber(request.getParameter("phoneNumber"));
        userInfo.setDateOfBirth(request.getParameter("dateOfBirth"));
        userInfo.setAge(userInfo.getCurrentAge(userInfo.getDateOfBirth()));
        Hobbies [] hobbies = new Hobbies[1];
        hobbies[0] = Hobbies.LONG_WALKS_ON_THE_BEACH;
        userInfo.setHobbies(hobbies);
        userInfo.setSex("MALE");
        try {
            currUser = UserDAO.getUser(username,true);
            currUser.setHas_user_profile("Y");
            userInfo.setUser_id(currUser.getUser_id());
            PersonalInfoDAO.setUserInfo(userInfo);
            UserDAO.updateUser(currUser);
            request.setAttribute("user", currUser);
            request.setAttribute("fullyRegistered", "true");

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
