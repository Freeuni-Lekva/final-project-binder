package Servlets;


import DAO.MessagesDAO;
import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import DAO.UserDAO;
import Model.Message;
import Model.PersonalUserInfo;
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

@WebServlet(name = "GetMessagesServlet", value = "/GetMessagesServlet")
public class GetMessagesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int chat_room_id = Integer.parseInt(request.getParameter("chat_room_id"));
        int user_profile_id = Integer.parseInt(request.getParameter("user_profile_id"));
        PrintWriter out = response.getWriter();

        try{
            PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfoByPersonalID(user_profile_id);
            int logged_user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
            if(userInfo.getUser_id() != logged_user_id){
                out.print("{\"status\":1}");
                return;
            }

            List<Message> messages = MessagesDAO.getMessages(chat_room_id,user_profile_id);
            String json = (new Gson()).toJson(messages);
            out.write(json);
        }catch (SQLException ex){
            ex.printStackTrace();
            out.print("{\"status\":2}");
            return;
        }

    }


}
