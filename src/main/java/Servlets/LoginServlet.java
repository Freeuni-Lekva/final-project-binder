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
        if(request.getSession(false) == null){
            request.getSession();
        }
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        String username = request.getParameter("email");
        String password = String.valueOf(request.getParameter("password").hashCode());

        if(username.isEmpty() || password.isEmpty()) {
            request.setAttribute("loginWrong", "Please fill both fields");
            rd.forward(request, response);
        }else{
            boolean isUser = !username.contains("@");
            try {
                User user = UserDAO.getUser(username,isUser);
                if(user.getPassword().equals(password)){
                    SessionsDAO.setSession(request.getSession(false).getId(),user.getUser_id());
                    if(user.getHas_user_profile()){
                        rd = request.getRequestDispatcher("Home.jsp");
                    }else{
                        rd = request.getRequestDispatcher("CompleteRegister.jsp");
                    }
                    rd.forward(request, response);
                }else{
                    request.setAttribute("loginWrong", "Wrong password");
                    rd.forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                request.setAttribute("loginWrong", "User does not exist");
                rd.forward(request, response);
            }
        }
    }
}
