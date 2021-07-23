package DAO;

import DatabaseMetaInfo.MyDatabase;
import Model.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class ActionDAO{

    private static Connection con;
    private static HashSet<String> result;
    public static final int ACTION_LIKE = 1;
    public static final int ACTION_DISLIKE = -1;

    //get list of users who liked/disliked this user
    public static HashSet<String> getActors(String username, int action) throws SQLException {
        con = MyDatabase.getConnection();
        result = new HashSet<>();
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        ResultSet rs = pstmt.executeQuery(
                "SELECT username_actor " +
                        "FROM Actions " +
                        "WHERE username_subject = " + "\"" + username + "\" " +
                        "AND relation =" + action);
        while(rs.next()){
            result.add(rs.getString(1));
        }
        return result;
    }

    //get list of users liked/disliked by this user
    public static HashSet<String> getSubjects(String username,int action) throws SQLException {
        con = MyDatabase.getConnection();
        result = new HashSet<>();
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        ResultSet rs = pstmt.executeQuery(
                "SELECT username_subject " +
                        "FROM Actions " +
                        "WHERE username_actor = " + "\"" + username + "\" " +
                        "AND relation =" + action);
        while(rs.next()){
            result.add(rs.getString(1));
        }
        return result;
    }

    //add relation of these users in DB
    public static void Action(String actor, String subject, int action) throws SQLException{
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO Actions (username_actor,username_subject,relation) VALUES (?,?,?)");
        pstmt.setString( 1,actor);
        pstmt.setString(2,subject);
        pstmt.setInt(3,action);
        pstmt.executeUpdate();
    }


}


