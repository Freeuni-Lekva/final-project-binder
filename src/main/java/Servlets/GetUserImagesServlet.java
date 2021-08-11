package Servlets;

import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import DAO.SuggestionDataDAO;
import DAO.UserImagesDAO;
import Model.PersonalUserInfo;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "GetUserImagesServlet", value = "/GetUserImagesServlet")
public class GetUserImagesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int user_profile_id = Integer.valueOf(request.getParameter("suggestedPersonalId"));
        PrintWriter out = response.getWriter();
        //List<String> list = new ArrayList<>();
        try {
            PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfoByPersonalID(user_profile_id);
            String result = UserImagesDAO.getUserImages(userInfo.getUser_profile_id());
            if (result.isEmpty()) {
                out.print("{\"status\":0}");
                return;
            }
            String json = (new Gson()).toJson(result);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
