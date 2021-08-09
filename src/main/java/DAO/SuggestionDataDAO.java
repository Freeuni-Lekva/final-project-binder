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

    public static ArrayList<Integer> getSuggestions(PersonalUserInfo user) throws SQLException {
        con = MyDatabase.getConnection();
        ArrayList<Integer> result = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement(
                    "SELECT user_profile_id FROM User_profile WHERE sex  != ? " +
                            "AND user_profile_id not in (SELECT subject_id from ACTIONS where actor_id =  ? ) " +
                            "AND CITY = ? ");
        pstmt.setString(1,user.getSex());
        pstmt.setInt(2,user.getUser_profile_id());
        pstmt.setString(3,user.getCity());
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            System.out.println(rs.toString());
            result.add(rs.getInt(1));
        }
        return result;
    }


}
