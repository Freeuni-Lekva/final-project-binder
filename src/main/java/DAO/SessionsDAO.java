package DAO;

import DatabaseMetaInfo.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionsDAO {

    private static Connection con;

    public static int getUser_id (String sessionID) throws SQLException {
        con = MyDatabase.getConnection();
        int user_id = -1;
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        ResultSet rs = pstmt.executeQuery(
                "SELECT user_id " +
                        "FROM SESSIONS " +
                        "WHERE sessionID = " + "\"" +  sessionID + "\""
                        );
        if(!rs.next()){
            return user_id;
        }
        user_id = rs.getInt(1);
        return user_id;
    }

    public static void setSession(String sessionID, int user_id) throws SQLException {
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO SESSIONS (sessionID,user_id)" +
                "VALUES(?,?) ");
        pstmt.setString(1,sessionID);
        pstmt.setInt(2,user_id);
        pstmt.executeUpdate();
    }


    public static void deleteSession(String sessionID) throws SQLException{
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
                "DELETE from SESSIONS WHERE SESSIONID = ?" );
        pstmt.setString(1,sessionID);
        pstmt.executeUpdate();
    }

    public static void deleteAllSessions() throws SQLException{
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
                "DELETE from SESSIONS" );
        pstmt.executeUpdate();
    }
    public static void deleteSession(int user_ID) throws SQLException{
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
                "DELETE from SESSIONS WHERE user_id = ?" );
        pstmt.setInt(1,user_ID);
        pstmt.executeUpdate();
    }


}
