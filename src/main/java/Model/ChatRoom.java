package Model;

public class ChatRoom {
    private int chat_room_id ;
    private int user_id_1 ;
    private int user_id_2 ;
    private String create_date ;
    private String last_message ;
    private String last_message_sent_date;
    public ChatRoom(int chat_room_id,int user_id_1,int user_id_2,
                    String create_date , String last_message , String last_message_sent_date ){
        this.chat_room_id = chat_room_id;
        this.user_id_1 = user_id_1;
        this.user_id_2 = user_id_2;
        this.create_date = create_date;
        this.last_message = last_message;
        this.last_message_sent_date = last_message_sent_date;
    }


    public int getChat_room_id() {
        return chat_room_id;
    }


    public int getUser_id_1() {
        return user_id_1;
    }

    public int getUser_id_2() {
        return user_id_2;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getLast_message() {
        return last_message;
    }

    public String getLast_message_sent_date() {
        return last_message_sent_date;
    }

    public void setChat_room_id(int chat_room_id){
        this.chat_room_id = chat_room_id;
    }

    public void setUser_id_1(int user_id_1) {
        this.user_id_1 = user_id_1;
    }

    public void setUser_id_2(int user_id_2) {
        this.user_id_2 = user_id_2;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public void setLast_message_sent_date(String last_message_sent_date) {
        this.last_message_sent_date = last_message_sent_date;
    }
}
