package Servlets;


import DAO.PersonalInfoDAO;
import DAO.UserDAO;
import DAO.UserImagesDAO;
import Model.FullUserInfo;
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

@WebServlet(name = "GetUserDetailsServlet", value = "/GetUserDetailsServlet" )
public class GetUserDetailsServlet extends HttpServlet {

    private static String DEFAULT_IMAGE_MALE = "Content/Images/DEFAULT_THUMB_MALE.jpg";
    private static String DEFAULT_IMAGE_FEMALE = "Content/Images/DEFAULT_THUMB_FEMALE.jpg";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int user_id = Integer.parseInt(req.getParameter("user_ID"));
        PrintWriter out = resp.getWriter();
        try{
            User user = UserDAO.getUserByID(user_id);
            FullUserInfo fullInfo = (FullUserInfo) user;
            if(user.getHas_user_profile()){
                PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfo(user_id);
                fullInfo.setAge(userInfo.getAge());
                fullInfo.setCity(userInfo.getCity());
                fullInfo.setSex(userInfo.getSex());
                fullInfo.setPhoneNumber(userInfo.getPhoneNumber());
                try{
                    String image = UserImagesDAO.getUserImages(userInfo.getUser_profile_id());
                    fullInfo.setImage(image);
                }catch (SQLException ex){
                    ex.printStackTrace();
                    String image = (fullInfo.getSex() == "MALE") ? DEFAULT_IMAGE_MALE : DEFAULT_IMAGE_FEMALE;
                    fullInfo.setImage(image);
                }
            }
            String json = (new Gson()).toJson(fullInfo);
            out.write(json);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}