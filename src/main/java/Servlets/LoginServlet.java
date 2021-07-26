package Servlets;

import DAO.SessionsDAO;
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

        if(request.getSession(false) == null){
            request.getSession();
        }

        if(!username.isEmpty() && !password.isEmpty()){
            boolean isUser;
            isUser = !username.contains("@");
            User user;
            try {
                user = UserDAO.getUser(username,isUser);
                if(user.getPassword().equals(password)){
                    request.setAttribute("user", user);

                    SessionsDAO.setSession(request.getSession(false).getId(),username);

                    if(user.getHas_user_profile()){
                        request.setAttribute("fullyRegistered", "true");
                    }else{
                        request.setAttribute("fullyRegistered","false");
                    }
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home.jsp");
                    requestDispatcher.forward(request, response);
                }else{
                    request.setAttribute("loginWrong", "Wrong password");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                    requestDispatcher.forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                request.setAttribute("loginWrong", "User does not exist");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
            }

        }else{
            request.setAttribute("loginWrong", "Please fill both fields");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
