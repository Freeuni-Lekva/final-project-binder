package DAO;

import DatabaseMetaInfo.MyDatabase;
import Model.ChatRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatchesDAO {
    private static Connection con = MyDatabase.getConnection();

    public static ArrayList<ChatRoom> getMatches(int user_profile_id, int position) throws SQLException {
        ArrayList<ChatRoom> result = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement("SELECT * from chat_room " +
                "where user_profile_id_" + "? = ? ");
        pstmt.setInt(1,position);
        pstmt.setInt(2,user_profile_id);
        ResultSet rs = pstmt.executeQuery();
        int chatBuddyPos = position == 2 ? 2 : 3;

        while(rs.next()){
            int chat_Room_ID = rs.getInt(1);
            int chat_Buddy_ID = rs.getInt(chatBuddyPos);
            String date = rs.getString(4);
            String last_message = rs.getString(5);
            String last_message_date = rs.getString(6);
            String image = UserImagesDAO.getUserImages(chat_Buddy_ID);
            String name = PersonalInfoDAO.getUserInfoByPersonalID(chat_Buddy_ID).getUsername();

            ChatRoom room = new ChatRoom(chat_Room_ID,chat_Buddy_ID,date,last_message,last_message_date,image,name);
            result.add(room);
        }

        return result;
    }
    public static void  deleteMatch(int chat_room_id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("DELETE FROM chat_room " +
                "where chat_room_id = ? ");
        pstmt.setInt(1,chat_room_id);
        pstmt.executeUpdate();
    }

    // for testing(deployed in action trigger)
    public static void  addMatch(int user_profile_id_1 , int user_profile_id_2) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO chat_room (user_profile_id_1,user_profile_id_2) " +
                "VALUES (?,?)");
        pstmt.setInt(1,user_profile_id_1);
        pstmt.setInt(2,user_profile_id_2);
        pstmt.executeUpdate();
    }


}