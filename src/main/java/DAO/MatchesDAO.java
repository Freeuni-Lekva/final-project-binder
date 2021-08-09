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

    public static ArrayList<ChatRoom> getMatches(int user_id) throws SQLException {
        ArrayList<ChatRoom> result = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement("SELECT * from chat_room " +
                                                        "where user_id_1 = ? " +
                                                        "or user_id_2 = ? ");
        pstmt.setInt(1,user_id);
        pstmt.setInt(2,user_id);
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
    public static void  addMatch(int user_id_1 , int user_id_2) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO chat_room (user_id_1,user_id_2) " +
                "VALUES (?,?)");
        pstmt.setInt(1,user_id_1);
        pstmt.setInt(2,user_id_2);
        pstmt.executeUpdate();
    }


}
