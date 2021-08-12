package Model;

public class Message {

    private int id;
    private String message;
    private int sentDate;
    private int chat_room_id;
    private boolean isReceived;

    public Message(int id, String message, int sentDate, int chat_room_id, boolean isReceived) {
        this.id = id;
        this.message = message;
        this.sentDate = sentDate;
        this.chat_room_id = chat_room_id;
        this.isReceived = isReceived;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSentDate() {
        return sentDate;
    }

    public void setSentDate(int sentDate) {
        this.sentDate = sentDate;
    }

    public int getChat_room_id() {
        return chat_room_id;
    }

    public void setChat_room_id(int chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }
}
