package Servlets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;

import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import DAO.UserDAO;
import DAO.UserImagesDAO;
import Model.PersonalUserInfo;
import Model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@MultipartConfig
@WebServlet(name = "ImageDownloadServlet",value = "/ImageDownloadServlet")
public class ImageDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!ServletFileUpload.isMultipartContent(request)){
            throw new ServletException("Content type is not multipart/form-data");
        }
        if(request.getSession(false) == null ) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }

        if(ServletFileUpload.isMultipartContent(request)) {
            try {
                int user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
                PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfo(user_id);
                String username = userInfo.getUsername();
                user_id = userInfo.getUser_profile_id();

                int imageCount = UserImagesDAO.getUserImages(user_id).size();
                if(imageCount >= 3){
                    PrintWriter out = response.getWriter();
                    out.print("{\"status\":1}");
                    return;
                }

                Collection<Part> parts = request.getParts();
                for(Part p : parts){
                    InputStream fileContent = p.getInputStream();

                    String fileName = Paths.get(p.getSubmittedFileName()).getFileName().toString();
                    String fileExtension = fileName.split("\\.")[1];
                    if(!"jpg,png,gif,jpeg".contains(fileExtension)){
                        PrintWriter out = response.getWriter();
                        out.print("{\"status\":2}");
                        return;
                    }
                    String relativePath = "Content\\User_Files\\" + username + imageCount + "." + fileExtension;
                    String htmlPath = "Content/User_Files/" + username + imageCount + "." + fileExtension;
                    File targetFile = new File( request.getSession(false).getServletContext().getRealPath("") + relativePath);
                    FileUtils.copyInputStreamToFile(fileContent, targetFile);
                    UserImagesDAO.setUserImage(user_id, htmlPath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            out.print("{\"status\":3}");
        }
    }
}