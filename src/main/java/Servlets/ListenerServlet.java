package Servlets;

import DAO.SessionsDAO;
import Model.PersonalUserInfo;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebListener
public class ListenerServlet implements HttpSessionListener, ServletContextListener{

    private String TempImagesPath = "";
    private String BackUpImagesPath = "";

    public void LoadFiles(String from,String to){
        File source = new File(from);
        File dest = new File(to);
        try {
            FileUtils.copyDirectory(source, dest);
            FileUtils.deleteDirectory(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BackUpImagesPath = "./User_Files/";
        TempImagesPath = sce.getServletContext().getRealPath("/Content/User_Files");
        LoadFiles(BackUpImagesPath,TempImagesPath);

        ServletContext context = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LoadFiles(TempImagesPath,BackUpImagesPath);
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
