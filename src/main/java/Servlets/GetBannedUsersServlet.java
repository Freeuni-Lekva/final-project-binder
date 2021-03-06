package Servlets;


import DAO.SessionsDAO;
import DAO.UserDAO;
import Model.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "GetBannedUsersServlet", value = "/GetBannedUsersServlet")
public class GetBannedUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        try {
            int user_id = SessionsDAO.getUser_id(req.getSession(false).getId());
            User user = UserDAO.getUserByID(user_id);
            if(user.isAdmin() == false){
                out.print("{\"status\":3}");
                return;
            }

            List<User> bannedUsers = UserDAO.getBannedUsers();
            String json = (new Gson()).toJson(bannedUsers);
            out.write(json);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            out.print("{\"status\":2}");
            return;
        }


    }
}
