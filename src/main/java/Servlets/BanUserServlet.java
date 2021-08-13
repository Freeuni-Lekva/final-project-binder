package Servlets;


import DAO.SessionsDAO;
import DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "BanUserServlet", value = "BanUserServlet")
public class BanUserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_id"));
        PrintWriter out = resp.getWriter();

        try{
            UserDAO.banUser(user_id);
            SessionsDAO.deleteSession(user_id);
            out.print("{\"status\":1}");
            return;
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.print("{\"status\":2}");
            return;
        }
    }
}