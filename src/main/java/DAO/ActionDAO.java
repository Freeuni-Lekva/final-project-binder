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
    public static HashSet<String> getActors(int userID, int action) throws SQLException {
        con = MyDatabase.getConnection();
        result = new HashSet<>();
        PreparedStatement pstmt = con.prepareStatement("SELECT actor_id " +
                        "FROM Actions " +
                        "WHERE subject_id = ?" +
                        "AND relation = ?");
        pstmt.setInt(1,userID);
        pstmt.setInt(2,action);
        ResultSet rs = pstmt.executeQuery();
        int count = 0;
        while(rs.next()){
            result.add(rs.getString(1));
            count++;
            if(count == 10) break;
        }
        return result;
    }

    //get list of users liked/disliked by this user
    public static HashSet<String> getSubjects(int userID,int action) throws SQLException {
        con = MyDatabase.getConnection();
        result = new HashSet<>();
        PreparedStatement pstmt = con.prepareStatement("SELECT subject_id " +
                "FROM Actions " +
                "WHERE actor_id = ?" +
                "AND relation = ?");
        pstmt.setInt(1,userID);
        pstmt.setInt(2,action);
        ResultSet rs = pstmt.executeQuery();
        int count = 0;
        while(rs.next()){
            result.add(rs.getString(1));
            count++;
            if(count == 10) break;
        }
        return result;
    }

    //add relation of these users in DB
    public static void Action(int actorID, int subjectID, int action) throws SQLException{
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO Actions (actor_id,subject_id,relation) VALUES (?,?,?)");
        pstmt.setInt( 1,actorID);
        pstmt.setInt(2,subjectID);
        pstmt.setInt(3,action);
        pstmt.executeUpdate();
    }


}


