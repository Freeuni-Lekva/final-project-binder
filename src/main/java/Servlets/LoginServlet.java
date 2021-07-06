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
        HttpSession httpSession = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        UserDao userDao = new UserDao();
        if((email != null || username != null) && password != null){
            String key;
            boolean isUser;
            key = (email == null?username:email);
            isUser = (email == null?true:false);
            User user = null;
            try {
                user = userDao.getUser(key,isUser);
            } catch (SQLException throwables) {
                httpSession.setAttribute("loginWrong",new String("User does not exist"));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");

            }
            if(user.getPassword()==String.valueOf(password.hashCode())){
                // TODO: for home.jsp
                httpSession.setAttribute("user", user);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            }

        }else{
            httpSession.setAttribute("loginNotFilled",new String("Please fill both fields"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        }

    }
}
