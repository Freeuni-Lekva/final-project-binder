package Servlets;

import DAO.CookiesDAO;
import DAO.UserDAO;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        for(Cookie c : cookies){
            if(c.getName().equals("JSESSIONID")){
                try {
                    CookiesDAO.deleteCookie(c.getValue());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.getSession(false).invalidate();
                rd.forward(request,response);
            }
        }
    }


}
