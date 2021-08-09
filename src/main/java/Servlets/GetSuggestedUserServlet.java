package Servlets;

import DAO.PersonalInfoDAO;
import DAO.SuggestionDataDAO;
import DAO.UserDAO;
import Model.PersonalUserInfo;
import Model.User;
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

@WebServlet(name = "GetSuggestedUserServlet", value = "/GetSuggestedUserServlet")
public class GetSuggestedUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int user_id = Integer.valueOf(request.getParameter("userID"));
        PrintWriter out = response.getWriter();
        Random rand = new Random();
        try {
            PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfo(user_id);
            ArrayList<Integer> validUsers = SuggestionDataDAO.getSuggestions(userInfo);
            if(validUsers.isEmpty()){
                out.print("{\"status\":0}");
                return;
            }else{
                int suggested_user_id = validUsers.get(rand.nextInt(validUsers.size()));
                PersonalUserInfo suggestedUser = PersonalInfoDAO.getUserInfoByPersonalID(suggested_user_id);
                List<String> info = new ArrayList<>();

                info.add(String.valueOf(suggested_user_id));
                info.add(suggestedUser.getUsername());
                info.add(String.valueOf(suggestedUser.getAge()));

                String json = (new Gson()).toJson(info);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.write(json);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }



}
