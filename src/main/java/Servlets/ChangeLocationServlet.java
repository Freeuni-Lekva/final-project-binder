package Servlets;

import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import DAO.UserDAO;
import Enums.City;
import Exceptions.RegistrationException;
import Model.PersonalUserInfo;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChangeLocationServlet", value = "/ChangeLocationServlet")
public class ChangeLocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
        if(request.getSession(false) == null ) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }

        try {
            int user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
            PersonalUserInfo userInfo;
            userInfo = PersonalInfoDAO.getUserInfo(user_id);

            String city = request.getParameter("city");
            if(userInfo.getCity().equals(city)){
                request.setAttribute("LocationUpdateFailed","New location must differ from the current one");
                rd.forward(request,response);
            }

            userInfo.setCity( request.getParameter("city"));
            PersonalInfoDAO.updateUserInfo(userInfo);
        } catch (SQLException | RegistrationException ex){
            ex.printStackTrace();
            rd = request.getRequestDispatcher("/LogoutServlet");
            rd.forward(request,response);
        }
        rd.forward(request,response);
    }
}
