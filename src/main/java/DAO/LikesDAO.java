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

public class LikesDAO {

    private Connection con;

    public LikesDAO(){
        con = MyDatabase.getConnection();
    }


    public HashSet<String> getLikers(String username) throws SQLException {
        HashSet<String> result = new HashSet<>();
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        ResultSet rs = pstmt.executeQuery("SELECT username_liker FROM LIKES WHERE username_liked = " + "\"" + username + "\"");
        while(rs.next()){
            result.add(rs.getString(1));
        }
        return result;
    }

    public HashSet<String> getLiked(String username) throws SQLException {
        HashSet<String> result = new HashSet<>();
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        ResultSet rs = pstmt.executeQuery("SELECT username_liked FROM LIKES WHERE username_liker = " + "\"" + username + "\"");
        while(rs.next()){
            result.add(rs.getString(1));
        }
        return result;
    }

    public void Like(String liker, String likee) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO LIKES (username_liker,username_liked) VALUES (?,?)");
        pstmt.setString( 1,liker);
        pstmt.setString(2,likee);
        pstmt.executeUpdate();
    }

}


