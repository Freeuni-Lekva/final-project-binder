package DAO;

import DatabaseMetaInfo.MyDatabase;
import Model.Message;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessagesDAO {

    private static Connection con = MyDatabase.getConnection();


    public static List<Message> getMessages(int chat_room_id, int user_profile_id) throws SQLException {
        List<Message> result = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement("Select * from message where chat_room_id = ? order by sent_date asc");
        pstmt.setInt(1,chat_room_id);

        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            int id = rs.getInt(1);
            String msg = rs.getString(2);
            int date = rs.getInt(3);
            int chatID = rs.getInt(4);
            boolean isReceived = rs.getInt(5) != user_profile_id;
            Message currentMessage = new Message(id, msg, date, chatID, isReceived);
            result.add(currentMessage);
        }
        return result;
    }

    public static void addMessage(int chat_room_id, int user_profile_id, String message) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("Insert into message (message,chat_room_id,user_profile_id)" +
                "values(?,?,?)");
        pstmt.setString(1,message);
        pstmt.setInt(2,chat_room_id);
        pstmt.setInt(3,user_profile_id);
        pstmt.executeUpdate();
    }


}
