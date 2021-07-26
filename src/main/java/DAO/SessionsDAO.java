package DAO;

import DatabaseMetaInfo.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionsDAO {

    private static Connection con;

    public static String getUsername (String sessionID) throws SQLException {
        con = MyDatabase.getConnection();
        String username = "";
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        ResultSet rs = pstmt.executeQuery(
                "SELECT username " +
                        "FROM SESSIONS " +
                        "WHERE sessionID = " + "\"" +  sessionID + "\""
                        ); //"AND expireDate > " + System.currentTimeMillis()
        if(!rs.next()){
            return null;
        }
        username = rs.getString(1);
        return username;
    }

    public static void setSession(String sessionID, String username) throws SQLException {
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO SESSIONS (sessionID,username)" +
                "VALUES(?,?) ");
        pstmt.setString(1,sessionID);
        pstmt.setString(2,username);
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


}
