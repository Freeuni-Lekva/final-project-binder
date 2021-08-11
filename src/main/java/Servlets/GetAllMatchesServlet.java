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
        String sex = request.getParameter("sex");
        PrintWriter out = response.getWriter();

        try {
            int position = sex.equals("MALE") ? 1 : 2;
            List<ChatRoom> chats = MatchesDAO.getMatches(user_profile_id,position);
            String json = (new Gson()).toJson(chats);
            out.write(json);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
