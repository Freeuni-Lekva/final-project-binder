package Servlets;

import DAO.SessionsDAO;
import DAO.UserDAO;
import Exceptions.RegistrationException;
import Model.User;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession(false) == null ){
            request.getSession();
        }
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        String name = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = String.valueOf(request.getParameter("RegisterPassword").hashCode());
        String email = request.getParameter("email");

        if(name.isEmpty()  || username.isEmpty() || email.isEmpty()
        ){
            request.setAttribute("registrationFailed", new String("Please fill all forms"));
            rd.forward(request, response);
        }
        User user = new User(0,name,surname,email,username,password,false);

        try {
            UserDAO.setUser(user);
            SessionsDAO.setSession(request.getSession(false).getId(),user.getUsername());
            rd = request.getRequestDispatcher("CompleteRegister.jsp");
            rd.forward(request, response);
        } catch (RegistrationException | SQLException ex){
            ex.printStackTrace();
            request.setAttribute("registrationFailed", new String("user already exists"));
            rd.forward(request, response);
        }
    }
}