package Servlets;


import DAO.SessionsDAO;
import DAO.UserDAO;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UnbanUserServlet", value = "/UnbanUserServlet")
public class UnbanUserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("userID"));
        PrintWriter out = resp.getWriter();

        try{
            int logged_user_id = SessionsDAO.getUser_id(req.getSession(false).getId());
            User user = UserDAO.getUserByID(logged_user_id);
            if(user.isAdmin() == false){
                out.print("{\"status\":3}");
                return;
            }
            UserDAO.unbanUser(user_id);
            out.print("{\"status\":1}");
            return;
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.print("{\"status\":2}");
            return;
        }
    }
}