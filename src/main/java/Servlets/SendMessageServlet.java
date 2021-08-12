package Servlets;


import DAO.MessagesDAO;
import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import Exceptions.RegistrationException;
import Model.PersonalUserInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "SendMessageServlet", value = "/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        int chat_room_id = Integer.parseInt(request.getParameter("chat_room_id"));
        int user_profile_id = Integer.parseInt(request.getParameter("user_profile_id"));
        PrintWriter out = response.getWriter();
        try{
            MessagesDAO.addMessage(chat_room_id,user_profile_id,message);
            out.print("{\"status\":1}");
            return;
        } catch (SQLException ex) {
            out.print("{\"status\":2}");
            ex.printStackTrace();
        }

    }

}
