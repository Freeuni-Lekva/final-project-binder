package Servlets;


import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import DAO.UserDAO;
import Enums.Hobbies;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetUserHobbiesServlet", value = "/GetUserHobbiesServlet")
public class GetUserHobbiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_personal_id = Integer.parseInt(req.getParameter("user_personal_id"));
        PrintWriter out = resp.getWriter();
        try{
            SessionsDAO.getUser_id(req.getSession(false).getId());
            PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfoByPersonalID(user_personal_id);
            Hobbies[] hobbies = userInfo.getHobbies();
            List<String> res = new ArrayList<>();
            for(Hobbies h : hobbies)
                res.add(h.toString().replace("_", " "));
            String json = (new Gson()).toJson(res);
            if(json.isEmpty()) json = "null";
            out.write(json);

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            String json = "";
            if(json.isEmpty()) json = "null";
            out.write(json);
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
            String json = "";
            if(json.isEmpty()) json = "null";
            out.write(json);
        }
    }
}