package Servlets;

import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import DAO.UserDAO;
import Exceptions.RegistrationException;
import Model.PersonalUserInfo;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangeLocationServlet", value = "/ChangeLocationServlet")
public class ChangeLocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home.jsp");
        if(request.getSession(false) == null ) {
            requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request,response);
        }
        String username;

        try {
            int user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
            PersonalUserInfo userInfo ;
            userInfo = PersonalInfoDAO.getUserInfo(user_id);
            userInfo.setCity( request.getParameter("city"));
            PersonalInfoDAO.updateUserInfo(userInfo);
        } catch (SQLException | RegistrationException ex){
            ex.printStackTrace();
            requestDispatcher = request.getRequestDispatcher("/LogoutServlet");
            requestDispatcher.forward(request,response);
        }
        requestDispatcher.forward(request,response);
    }
}
