package Servlets;

import DAO.ActionDAO;
import org.apache.commons.io.IOExceptionList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LikeAndDislikeActionServlet",value = "/LikeAndDislikeActionServlet")
public class LikeAndDislikeActionServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false) == null ) {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }

        PrintWriter out = response.getWriter();

        int actor = Integer.valueOf(request.getParameter("actor"));
        int subject = Integer.valueOf(request.getParameter("subject"));
        int action = Integer.valueOf(request.getParameter("action"));

        try {
            ActionDAO.Action(actor,subject,action);
            out.print("\"Status\":1");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            out.print("\"Status\":2");
        }

    }


}
