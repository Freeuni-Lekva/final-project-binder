package Servlets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Collection;

import DAO.PersonalInfoDAO;
import DAO.SessionsDAO;
import DAO.UserDAO;
import DAO.UserImagesDAO;
import Model.PersonalUserInfo;
import Model.User;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!ServletFileUpload.isMultipartContent(request)){
            throw new ServletException("Content type is not multipart/form-data");
        }
        RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
        if(request.getSession(false) == null ) {
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }

        try{
            int user_id = SessionsDAO.getUser_id(request.getSession(false).getId());
            PersonalUserInfo userInfo = PersonalInfoDAO.getUserInfo(user_id);
            String username = userInfo.getUsername();
            user_id = userInfo.getUser_profile_id();

            Collection<Part> parts = request.getParts();
            int imageNum = 0;
            for(Part p : parts){
                InputStream fileContent = p.getInputStream();

                String fileName = Paths.get(p.getSubmittedFileName()).getFileName().toString();
                String fileExtension = fileName.split("\\.")[1];

                File targetFile = new File("./User_Files/" + username + imageNum + "." + fileExtension);
                FileUtils.copyInputStreamToFile(fileContent, targetFile);
                UserImagesDAO.setUserImage(user_id,targetFile.getPath());
                imageNum++;
            }
            rd.forward(request,response);
        }catch (SQLException ex){
            rd = request.getRequestDispatcher("/LogoutServlet");
            rd.forward(request,response);
        }



    }

}

//Part filePart = request.getPart("fileName");
//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//        InputStream fileContent = filePart.getInputStream();
//        File targetFile = new File("./User_Files/" + fileName);
//        FileUtils.copyInputStreamToFile(fileContent, targetFile);