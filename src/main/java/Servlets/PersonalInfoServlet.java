package Servlets;

import DAO.SessionsDAO;
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
        if(request.getSession(false) == null ){
            response.sendRedirect("index.jsp");
        }

        PersonalUserInfo userInfo = new PersonalUserInfo();
        User currUser ;

        String username = "";
        try{
            username = SessionsDAO.getUsername(request.getSession(false).getId()); // todo user_id gvinda aq username is magivrad
        }catch (SQLException ex){
            RequestDispatcher rd = request.getRequestDispatcher("/LogoutServlet");
            rd.forward(request,response);
            return;
        }

        userInfo.setUsername(username);
        userInfo.setCity(request.getParameter("city"));
        userInfo.setPhoneNumber(request.getParameter("phoneNumber"));
        userInfo.setDateOfBirth(request.getParameter("dateOfBirth"));
        userInfo.setHobbies(request.getParameter("hobbies"));
        userInfo.setSex(request.getParameter("sex"));
        try {
            userInfo.setAge(PersonalUserInfo.getCurrentAge(userInfo.getDateOfBirth(),"d/M/yyyy"));
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
            response.sendRedirect("Home.jsp");
        }
    }
}
