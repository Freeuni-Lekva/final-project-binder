package DAO;

import DatabaseMetaInfo.MyDatabase;
import Model.PersonalUserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

public class SuggestionDataDAO {

    private static Connection con;

    public static ArrayList<String> getSuggestions(PersonalUserInfo user) throws SQLException {
        con = MyDatabase.getConnection();
        ArrayList<String> result = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement(
                    "SELECT username FROM User_profile " +
                        "WHERE sex  != " + "?" +" " +
                        "AND username not in (SELECT username_subject from ACTIONS where username_actor =  ? )" +
                        "AND CITY = ? ");
        pstmt.setString(1,user.getSex());
        pstmt.setString(2,user.getUsername());
        pstmt.setString(3,user.getCity());
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            result.add(rs.getString(1));
        }
        return result;
    }


}
