package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
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
        //Part filePart = request.getPart("fileName");
        Collection<Part> parts = request.getParts();
        for(Part p : parts){
            String fileName = Paths.get(p.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = p.getInputStream();
            File targetFile = new File("./User_Files/" + fileName);
            FileUtils.copyInputStreamToFile(fileContent, targetFile);
        }
//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//        InputStream fileContent = filePart.getInputStream();
//        File targetFile = new File("./User_Files/" + fileName);
//        FileUtils.copyInputStreamToFile(fileContent, targetFile);
    }

}