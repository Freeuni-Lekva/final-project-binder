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

@WebServlet(name = "ChangeEmailServlet", value = "/ChangeEmailServlet")
public class ChangeEmailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
        if(request.getSession(false) == null){
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }
        String newEmail = request.getParameter("email");
        if(!newEmail.contains("@")
        || (newEmail.split("@")[0].length() <=1 || newEmail.split("@")[1].length() <= 1)
        || (!newEmail.split("@")[1].contains("."))
        || (newEmail.lastIndexOf("@") != newEmail.indexOf("@"))){
            request.setAttribute("EmailChangeFailed","Invalid email");
            rd.forward(request,response);
        }

        try {
            String username = SessionsDAO.getUsername(request.getSession(false).getId());
            User user = UserDAO.getUser(username,true);
            User user1 = UserDAO.getUser(newEmail,false);
            if(user1 != null){
                request.setAttribute("EmailChangeFailed","This email is already in use");
                rd.forward(request,response);
            }
            user.setEmail(newEmail);
            UserDAO.updateUser(user);
            rd.forward(request,response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
