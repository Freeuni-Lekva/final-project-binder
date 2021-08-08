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
import java.io.PrintWriter;
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
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        if(username.length() < 4){
            out.print("{\"status\":1}");
            return;
        }
        try {
            int user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
            PersonalUserInfo userInfo ;
            userInfo = PersonalInfoDAO.getUserInfo(user_id);
            userInfo.setUsername( request.getParameter("username"));
            PersonalInfoDAO.updateUserInfo(userInfo);
            out.print("{\"status\":2}");
            return;
        } catch (SQLException | RegistrationException ex){
            ex.printStackTrace();
            out.print("{\"status\":3}");
            rd = request.getRequestDispatcher("LogoutServlet");
            rd.forward(request,response);
        }
        out.print("{\"status\":4}");
        return;
    }
}
