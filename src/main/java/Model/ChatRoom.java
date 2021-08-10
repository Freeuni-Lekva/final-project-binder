package Model;

public class ChatRoom {
    private int chat_room_id ;
    private int actor_profile_id ;
    private int subject_profile_id ;
    private String subjectUserName;
    private String image;

    public int getChat_room_id() {
        return chat_room_id;
    }

    public void setChat_room_id(int chat_room_id) {
        this.chat_room_id = chat_room_id;
    }

    public int getActor_profile_id() {
        return actor_profile_id;
    }

    public void setActor_profile_id(int actor_profile_id) {
        this.actor_profile_id = actor_profile_id;
    }

    public int getSubject_profile_id() {
        return subject_profile_id;
    }

    public void setSubject_profile_id(int subject_profile_id) {
        this.subject_profile_id = subject_profile_id;
    }

    public String getSubjectUserName() {
        return subjectUserName;
    }

    public void setSubjectUserName(String subjectUserName) {
        this.subjectUserName = subjectUserName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    private String create_date ;

    public ChatRoom(int chat_room_id, int actor_profile_id, int subject_profile_id,String image, String subjectUserName,  String create_date) {
        this.chat_room_id = chat_room_id;
        this.actor_profile_id = actor_profile_id;
        this.subject_profile_id = subject_profile_id;
        this.image = image;
        this.subjectUserName = subjectUserName;
        this.create_date = create_date;
    }
}
