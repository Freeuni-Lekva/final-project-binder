package Servlets;

import DAO.CookiesDAO;
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
        getServletContext().setAttribute("JSESSIONID",request.getSession(false));
        User user = new User();
        user.setName(request.getParameter("firstname"));
        user.setSurname(request.getParameter("surname"));
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(String.valueOf(request.getParameter("RegisterPassword").hashCode()));
        user.setHas_user_profile("N");
        if(user.getName().isEmpty()  || user.getUsername().isEmpty() ||
                user.getEmail().isEmpty()
        ){
            request.setAttribute("registrationFailed", new String("Please fill all forms"));
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
            return;
        }
        try {
            UserDAO.setUser(user);
            CookiesDAO.setCookie(request.getSession(false).getId(),user.getUsername());
            request.setAttribute("user" ,user);
            request.setAttribute("fullyRegistered","false");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Home.jsp");
            requestDispatcher.forward(request, response);
        } catch (RegistrationException | SQLException ex){
            ex.printStackTrace();
            request.setAttribute("registrationFailed", new String("user already exists"));
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }

    }
    public void destroy() {
    }
}