package Servlets;

import DAO.SessionsDAO;
import DAO.UserImagesDAO;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet(name = "AsyncServlet", value = "/AsyncServlet")
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*
        List<String> list = new ArrayList<>();
        try {
            int ragaca = SessionsDAO.getUser_id(request.getSession(false).getId());
             list = UserImagesDAO.getUserImages(ragaca);
        String json = new Gson().toJson(list);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
