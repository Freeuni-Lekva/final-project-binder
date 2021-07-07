package Servlets;

import DAO.UserDao;
import Enums.City;
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
        HttpSession httpSession = request.getSession();


        User user = new User();
        user.setName(request.getParameter("firstname"));
        user.setSurname(request.getParameter("surname"));
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(String.valueOf(request.getParameter("RegisterPassword").hashCode()));
        user.setSex(request.getParameter("gender"));
        user.setCity(City.TBILISI);
        UserDao userDAO = new UserDao();
        try {
            userDAO.setUser(user);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } catch (RegistrationException | SQLException ex){
            ex.printStackTrace();
            request.setAttribute("registrationFailed", true);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }

    }
    public void destroy() {
    }
}