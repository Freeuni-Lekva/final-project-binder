package DAO;

import DatabaseMetaInfo.MyDatabase;
import Enums.City;
import Enums.Hobbies;
import Exceptions.RegistrationException;
import Model.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static Connection con;

    public static User getUser(String key,Boolean isUsername) throws  SQLException{
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        StringBuilder statement = new StringBuilder();
        if(isUsername){
            statement.append("Select * from User where username = \"" + key + "\"");
        }else{
            statement.append("Select * from User where email = \"" + key + "\"");
        }

        User user = new User();
        ResultSet rs = pstmt.executeQuery(statement.toString());
        rs.next();

        user.setName(rs.getString(2));
        user.setSurname(rs.getString(3));
        user.setEmail(rs.getString(4));
        user.setUsername(rs.getString(5));
        user.setPassword(rs.getString(6));
        user.setSex(rs.getString(7));
        user.setUser_profile_id(rs.getInt(8));
        return user;
    }
    public static void setUser(User user) throws SQLException, RegistrationException {
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement("Select count(id) from user " +
                "where  email = ? OR username = ?");
        pstmt.setString(1,user.getEmail());
        pstmt.setString(2,user.getUsername());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        if(rs.getInt(1) > 0){
            throw new RegistrationException("User with this username or email is already registered!");
        }else {
            pstmt.close();
            pstmt = con.prepareStatement("INSERT INTO user " +
                    " (name , surname , email, username , password , gender) " +
                    "VALUES (?,?,?,?,?,?);");

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getSex());
            pstmt.executeUpdate();
        }
    }
    public static void updateUser(User user) throws SQLException {
        con = MyDatabase.getConnection();
        PreparedStatement pstmt = con.prepareStatement("UPDATE user " +
                " SET name = ? , surname = ? , email = ? , username = ? , password = ? , gender = ? , user_profile_id = ? " +
                        "where username = ?"
                );
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getSurname());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getUsername());
        pstmt.setString(5, user.getPassword());
        pstmt.setString(6,user.getSex());
        pstmt.setInt(7, user.getUser_profile_id());
        pstmt.setString(8,user.getUsername());
        pstmt.executeUpdate();
    }
}
