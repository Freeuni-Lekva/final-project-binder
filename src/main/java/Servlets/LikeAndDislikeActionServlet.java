package Servlets;

import DAO.ActionDAO;
import DAO.MatchesDAO;
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
        String sex = request.getParameter("sex");
        try {
            ActionDAO.Action(actor,subject,action);
            if(action == 1 && ActionDAO.isMatch(subject,actor) == 1){
                if(sex.equals("MALE")) {
                    MatchesDAO.addMatch(actor, subject);
                }else {
                    MatchesDAO.addMatch(subject,actor);
                }

                out.print("{\"status\":3}");
                return;
            }else{
                out.print("{\"status\":1}");
                return;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            out.print("{\"status\":2}");
            return;
        }

    }


}