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

    public static ArrayList<ChatRoom> getMatches(int user_profile_id) throws SQLException {
        ArrayList<ChatRoom> result = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement("SELECT * from chat_room " +
                                                        "where actor_profile_id = ? ");
        pstmt.setInt(1,user_profile_id);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            ChatRoom room = new ChatRoom(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                    rs.getString(4),rs.getString(5),rs.getString(6));
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
    public static void  addMatch(int actorID , int subjectID, String subjectName, String subjectImage) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO chat_room (actor_profile_id,subject_profile_id,subject_image,subject_name) " +
                "VALUES (?,?,?,?)");
        pstmt.setInt(1,actorID);
        pstmt.setInt(2,subjectID);
        pstmt.setString(3,subjectImage);
        pstmt.setString(4,subjectName);
        pstmt.executeUpdate();
    }


}
