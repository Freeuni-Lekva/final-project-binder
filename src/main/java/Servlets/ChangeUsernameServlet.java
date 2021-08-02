package Servlets;

import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import DAO.UserDAO;
import Exceptions.RegistrationException;
import Model.PersonalUserInfo;
import Model.User;
import Exceptions.RegistrationException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangeUsernameServlet", value = "/ChangeUsernameServlet")
public class ChangeUsernameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
        if(request.getSession(false) == null ) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }

        String username = request.getParameter("username");
        if(username.length() < 4){
            request.setAttribute("UsernameChangeFailed","Username must consist of at least 4 characters");
            rd.forward(request,response);
        }
        try {
            int user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
            PersonalUserInfo userInfo ;
            userInfo = PersonalInfoDAO.getUserInfo(user_id);
            userInfo.setUsername( request.getParameter("username"));
            PersonalInfoDAO.updateUserInfo(userInfo);
        } catch (SQLException | RegistrationException ex){
            ex.printStackTrace();
            rd = request.getRequestDispatcher("/LogoutServlet");
            rd.forward(request,response);
        }
        rd.forward(request,response);




    }
}
