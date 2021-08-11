package Model;

public class ChatRoom {
    private int chat_room_id ;
    private int chat_buddy_id;
    private String create_date ;
    private String last_message ;
    private String last_message_sent_date;
    private String image;
    private String chat_buddy_name;

    public ChatRoom(int chat_room_id, int chat_buddy_id, String create_date, String last_message, String last_message_sent_date, String image, String chat_buddy_name) {
        this.chat_room_id = chat_room_id;
        this.chat_buddy_id = chat_buddy_id;
        this.create_date = create_date;
        this.last_message = last_message;
        this.last_message_sent_date = last_message_sent_date;
        this.image = image;
        this.chat_buddy_name = chat_buddy_name;
    }

    public int getChat_room_id() {
        return chat_room_id;
    }

    public void setChat_room_id(int chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public int getChat_buddy_id() {
        return chat_buddy_id;
    }

    public void setChat_buddy_id(int chat_buddy_id) {
        this.chat_buddy_id = chat_buddy_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getLast_message() {
        return last_message;
    }

    public void setLast_message(String last_message) {
        this.last_message = last_message;
    }

    public String getLast_message_sent_date() {
        return last_message_sent_date;
    }

    public void setLast_message_sent_date(String last_message_sent_date) {
        this.last_message_sent_date = last_message_sent_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChat_buddy_name() {
        return chat_buddy_name;
    }

    public void setChat_buddy_name(String chat_buddy_name) {
        this.chat_buddy_name = chat_buddy_name;
    }
}