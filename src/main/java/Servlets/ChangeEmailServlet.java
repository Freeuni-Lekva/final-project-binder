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

@WebServlet(name = "ChangeEmailServlet", value = "/ChangeEmailServlet")
public class ChangeEmailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false) == null){
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }
        PrintWriter out = response.getWriter();
        String newEmail = request.getParameter("email");
        if(!newEmail.contains("@")
        || (newEmail.split("@")[0].length() <=1 || newEmail.split("@")[1].length() <= 1)
        || (!newEmail.split("@")[1].contains("."))
        || (newEmail.lastIndexOf("@") != newEmail.indexOf("@"))){
            out.print("{\"status\":1}");
            return;
        }

        try {
            int user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
            User user = UserDAO.getUserByID(user_id);
            user.setEmail(newEmail);
            UserDAO.updateUser(user);
            out.print("{\"status\":2}");
            return;
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.print("{\"status\":3}");
            RequestDispatcher rd = request.getRequestDispatcher("/LogoutServlet");
            rd.forward(request,response);
            return;
        }
    }

}
