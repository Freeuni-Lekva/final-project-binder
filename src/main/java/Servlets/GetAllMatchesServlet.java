package Servlets;

import DAO.MatchesDAO;
import Model.ChatRoom;
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


@WebServlet(name = "GetAllMatchesServlet",value =  "/GetAllMatchesServlet")
public class GetAllMatchesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_profile_id = Integer.parseInt(request.getParameter("user_Profile_ID"));
        PrintWriter out = response.getWriter();

        try {
            List<ChatRoom> chats = MatchesDAO.getMatches(user_profile_id);
            String json = (new Gson()).toJson(chats);
            out.write(json);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
