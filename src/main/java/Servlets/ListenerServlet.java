package Servlets;

import DAO.SessionsDAO;
import Model.PersonalUserInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.SQLException;

@WebListener
public class ListenerServlet implements HttpSessionListener, ServletContextListener{


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            SessionsDAO.deleteAllSessions();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ServletContextListener.super.contextDestroyed(sce);
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("Path",new String());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        try {
            SessionsDAO.deleteSession(httpSessionEvent.getSession().getId());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        HttpSessionListener.super.sessionDestroyed(httpSessionEvent);
    }



}
