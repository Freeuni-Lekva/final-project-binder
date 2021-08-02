package DAO;

import DatabaseMetaInfo.MyDatabase;

import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImagesDAO {

    private static String DEFAULT_MALE_ICON_PATH = "./User_Thumbs/DEFAULT_THUMB_MALE.jpg";
    private static String DEFAULT_FEMALE_ICON_PATH = "./User_Thumbs/DEFAULT_THUMB_FEMALE.jpg";

    private static Connection con = MyDatabase.getConnection();

    public static void setUserImage(int user_profile_ID, String path)throws SQLException {
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO Image (image_path,user_profile_id)" +
                            "VALUES(?,?) ");
            pstmt.setString(1, path);
            pstmt.setInt(2, user_profile_ID);
            pstmt.executeUpdate();
    }

    public static void removeUserIcon(int user_profile_ID) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("update image " +
                "set (isProfilePicture = false) " +
                "where user_profile_id = ?" +
                "and isProfilePicture = true");
        pstmt.setInt(1,user_profile_ID);
        pstmt.executeUpdate();
    }

    public static void setUserIconPath(int user_profile_ID, String path) throws SQLException {
        removeUserIcon(user_profile_ID);
        PreparedStatement pstmt = con.prepareStatement(
                "UPDATE Image set (isProfilePicture = true)" +
                        "where user_profile_id = ?" +
                        "and image_path = ?");
        pstmt.setInt(1, user_profile_ID);
        pstmt.setString(2,path);
        pstmt.executeUpdate();
    }

    public static String getUserIconPath(int user_profile_ID) throws SQLException{
        PreparedStatement pstmt = con.prepareStatement(
                "Select Image_path from image" +
                        "where user_profile_id = ?" +
                        "AND isProfilePicture = true");
        pstmt.setInt(1, user_profile_ID);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getString(1);
    }


}
