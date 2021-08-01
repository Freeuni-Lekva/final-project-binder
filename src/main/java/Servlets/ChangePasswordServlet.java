package Servlets;


import DAO.SessionsDAO;
import DAO.UserDAO;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangePasswordServlet", value = "/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
        if(request.getSession(false) == null){
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }
        String oldPassword = String.valueOf(request.getParameter("oldPassword").hashCode());
        String Password = String.valueOf(request.getParameter("newPassword"));
        String PasswordRepeat = String.valueOf(request.getParameter("newPasswordRepeat"));
        if(!Password.equals(PasswordRepeat)){
            request.setAttribute("PassChangeFailed","Please fill new password fields correctly");
            rd.forward(request,response);
        }else if(oldPassword.equals(String.valueOf(Password.hashCode()))){
            request.setAttribute("PassChangeFailed","New password must be different from the current");
            rd.forward(request,response);
        }

        try {
            String username = SessionsDAO.getUsername(request.getSession(false).getId());
            User user = UserDAO.getUser(username,true);
            if(!user.getPassword().equals(oldPassword)){
                request.setAttribute("PassChangeFailed","Please enter your current password correctly");
                rd.forward(request,response);
            }
            user.setPassword(String.valueOf(Password.hashCode()));
            UserDAO.updateUser(user);
            rd.forward(request,response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
