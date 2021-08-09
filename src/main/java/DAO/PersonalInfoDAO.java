package DAO;

import DatabaseMetaInfo.MyDatabase;
import Enums.City;
import Enums.Hobbies;
import Exceptions.RegistrationException;
import Model.PersonalUserInfo;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalInfoDAO {

    private static Connection con = MyDatabase.getConnection();

    public static PersonalUserInfo getUserInfo(String username) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        StringBuilder statement = new StringBuilder();
        statement.append("Select * from User_profile where username = \"" + username + "\"");
        PersonalUserInfo userInfo = new PersonalUserInfo();
        ResultSet rs = pstmt.executeQuery(statement.toString());
        rs.next();
        userInfo.setUser_profile_id(rs.getInt(1));
        userInfo.setUsername(rs.getString(2));
        userInfo.setCity(rs.getString(3));
        userInfo.setDateOfBirth(rs.getString(4));
        userInfo.setAge(rs.getInt(5));
        userInfo.setPhoneNumber(rs.getString(6));
        userInfo.setHobbies(rs.getString(7));
        userInfo.setSex(rs.getString(8));
        return userInfo;
    }
    public static PersonalUserInfo getUserInfo(int user_id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        StringBuilder statement = new StringBuilder();
        statement.append("Select * from User_profile where user_id = \"" + user_id + "\"");
        PersonalUserInfo userInfo = new PersonalUserInfo();
        ResultSet rs = pstmt.executeQuery(statement.toString());
        rs.next();
        userInfo.setUser_profile_id(rs.getInt(1));
        userInfo.setUsername(rs.getString(2));
        userInfo.setCity(rs.getString(3));
        userInfo.setDateOfBirth(rs.getString(4));
        userInfo.setAge(rs.getInt(5));
        userInfo.setPhoneNumber(rs.getString(6));
        userInfo.setHobbies( rs.getString(7));
        userInfo.setSex(rs.getString(8));
        userInfo.setUser_id(rs.getInt(9));
        return userInfo;
    }

    public static PersonalUserInfo getUserInfoByPersonalID(int user_personal_id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        StringBuilder statement = new StringBuilder();
        statement.append("Select * from User_profile where user_profile_id = " + user_personal_id);
        PersonalUserInfo userInfo = new PersonalUserInfo();
        ResultSet rs = pstmt.executeQuery(statement.toString());
        rs.next();
        userInfo.setUser_profile_id(rs.getInt(1));
        userInfo.setUsername(rs.getString(2));
        userInfo.setCity(rs.getString(3));
        userInfo.setDateOfBirth(rs.getString(4));
        userInfo.setAge(rs.getInt(5));
        userInfo.setPhoneNumber(rs.getString(6));
        userInfo.setHobbies( rs.getString(7));
        userInfo.setSex(rs.getString(8));
        userInfo.setUser_id(rs.getInt(9));
        return userInfo;
    }

    public static void setUserInfo(PersonalUserInfo userInfo) throws SQLException, RegistrationException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO User_profile " +
                " (username,City, dateOfBirth, age, phone_number, hobbies, sex , user_id) " +
                "VALUES (?,?,?,?,?,?,?,?);");
        pstmt.setString(1, userInfo.getUsername());
        pstmt.setString(2, userInfo.getCity());
        pstmt.setString(3, userInfo.getDateOfBirth());
        pstmt.setInt(4, userInfo.getAge());
        pstmt.setString(5, userInfo.getPhoneNumber());
        pstmt.setString(6,PersonalUserInfo.HobbiesToString(userInfo.getHobbies()));
        pstmt.setString(7,userInfo.getSex());
        pstmt.setInt(8,userInfo.getUser_id());
        pstmt.executeUpdate();
    }


    public static void updateUserInfo(PersonalUserInfo userInfo) throws SQLException, RegistrationException {
        PreparedStatement pstmt = con.prepareStatement("UPDATE user_profile " +
                " SET username = ? , city = ? , dateOfBirth = ? , age = ? , phone_number = ? , hobbies = ?, sex = ? , user_id = ? "
                + "where user_profile_id = ?"
        );
        pstmt.setString(1, userInfo.getUsername());
        pstmt.setString(2, userInfo.getCity());
        pstmt.setString(3, userInfo.getDateOfBirth());
        pstmt.setInt(   4, userInfo.getAge());
        pstmt.setString(5, userInfo.getPhoneNumber());
        pstmt.setString(6, PersonalUserInfo.HobbiesToString(userInfo.getHobbies()));
        pstmt.setString(7,userInfo.getSex());
        pstmt.setInt(8,userInfo.getUser_id());
        pstmt.setInt(9,userInfo.getUser_profile_id());
        pstmt.executeUpdate();
    }

    public static int GetUserProfileID(int userID) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement("SELECT user_profile_id " +
                " from user_profile "
                + "where user_id = ?"
        );
        pstmt.setInt(userID,1);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }



}
