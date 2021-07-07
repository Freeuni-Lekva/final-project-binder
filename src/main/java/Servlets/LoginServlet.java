package Servlets;

import DAO.UserDao;
import Enums.City;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("email");
        String password = String.valueOf(request.getParameter("password").hashCode());
        UserDao userDao = new UserDao();
        if(username != null && password != null){
            boolean isUser;
            isUser = (username.contains("@") ? false:true);
            User user = null;
            try {
                user = userDao.getUser(username,isUser);
            } catch (SQLException throwables) {
                request.setAttribute("loginWrong",new String("User does not exist"));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
            if(user.getPassword()==password){
                // TODO: for home.jsp
                request.setAttribute("user", user);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                requestDispatcher.forward(request, response);
            }
        }else{
            request.setAttribute("loginNotFilled",new String("Please fill both fields"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }


    }
}
