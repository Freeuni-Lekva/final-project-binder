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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ChangePasswordServlet", value = "/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
        if(request.getSession(false) == null){
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }
        PrintWriter out = response.getWriter();
        String oldPassword = String.valueOf(request.getParameter("oldPassword").hashCode());
        String Password = String.valueOf(request.getParameter("newPassword"));
        String PasswordRepeat = String.valueOf(request.getParameter("newPasswordRepeat"));
        if(!Password.equals(PasswordRepeat)){
            out.print("{\"status\":1}");
            return;
        }else if(oldPassword.equals(String.valueOf(Password.hashCode()))){
            out.print("{\"status\":2}");
            return;
        }

        try {
            int user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
            User user = UserDAO.getUserByID(user_id);
            if(!user.getPassword().equals(oldPassword)){
                out.print("{\"status\":3}");
                return;
            }
            user.setPassword(String.valueOf(Password.hashCode()));
            UserDAO.updateUser(user);
            out.print("{\"status\":4}");
            return;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
