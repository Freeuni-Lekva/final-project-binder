package DAO;

import DatabaseMetaInfo.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CookiesDAO {

    private static Connection con;

    public static String getUsername (String sessionID) throws SQLException {
        con = MyDatabase.getConnection();
        String username = "";
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        ResultSet rs = pstmt.executeQuery(
                "SELECT username " +
                        "FROM COOKIES " +
                        "WHERE sessionID = " + "\"" +  sessionID + "\""
                        ); //"AND expireDate > " + System.currentTimeMillis()
        if(!rs.next()){
            return null;
        }
        username = rs.getString(1);
        return username;
    }

    public static void setCookie(String sessionID, String username) throws SQLException {
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO COOKIES (sessionID,username)" +
                "VALUES(?,?) ");
        pstmt.setString(1,sessionID);
        pstmt.setString(2,username);
        pstmt.executeUpdate();
    }


    public static void deleteCookie(String sessionID) throws SQLException{
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement(
                "DELETE from COOKIES WHERE SESSIONID = ?" );
        pstmt.setString(1,sessionID);
        pstmt.executeUpdate();
    }




}
