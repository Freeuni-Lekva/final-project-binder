package Servlets;

import DAO.CookiesDAO;
import Model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.SQLException;

@WebListener
public class ListenerServlet implements HttpSessionListener, ServletContextListener{

    /*
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("rame");
        try {
            CookiesDAO.deleteCookie((String)  sce.getServletContext().getAttribute("JSESSIONID"));
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        ServletContextListener.super.contextDestroyed(sce);
    }*/

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("JSESSIONID", httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        try {
            CookiesDAO.deleteCookie((String) httpSessionEvent.getSession().getAttribute("JSESSIONID"));
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        HttpSessionListener.super.sessionDestroyed(httpSessionEvent);
    }



}
