package Servlets;

import DAO.UserDAO;
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
        UserDAO userDao = new UserDAO();
        if(!username.isEmpty() && !password.isEmpty()){
            boolean isUser;
            isUser = !username.contains("@");
            User user = null;
            try {
                user = userDao.getUser(username,isUser);
            } catch (SQLException throwables) {
                request.setAttribute("loginWrong",new String("User does not exist"));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }
            if(user.getPassword().equals(password)){
                // TODO: for home.jsp
                request.setAttribute("user", user);
                if(user.getUser_profile_id() != 0){
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home.jsp");
                    requestDispatcher.forward(request, response);
                }else{
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        }else{
            request.setAttribute("loginWrong",new String("Please fill both fields"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }


    }
}
