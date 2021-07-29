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
        RequestDispatcher rd = request.getRequestDispatcher("CompleteRegister.jsp");

        if(request.getSession(false) == null ) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }


        String username = "";
        String city = request.getParameter("city");
        String phoneNumber = request.getParameter("phoneNumber");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String hobbies = request.getParameter("hobbies");
        String sex = request.getParameter("sex");

        try{
            username = SessionsDAO.getUsername(request.getSession(false).getId()); // todo user_id gvinda aq username is magivrad
        }catch (SQLException ex){
            rd = request.getRequestDispatcher("/LogoutServlet");
            rd.forward(request,response);
        }

        PersonalUserInfo userInfo = new PersonalUserInfo(username,dateOfBirth,phoneNumber,city,hobbies,sex,0);

        try {
            User currUser;
            userInfo.setAge(PersonalUserInfo.getCurrentAge(userInfo.getDateOfBirth(),"d/M/yyyy"));
            currUser = UserDAO.getUser(username,true);
            currUser.setHas_user_profile("Y");
            userInfo.setUser_id(currUser.getUser_id());
            PersonalInfoDAO.setUserInfo(userInfo);
            UserDAO.updateUser(currUser);
            rd.forward(request, response);
        } catch (RegistrationException | SQLException ex){
            ex.printStackTrace();
            rd.forward(request,response);
        }
    }
}
