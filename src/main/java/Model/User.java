package Model;

import Enums.City;
import Enums.Hobbies;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class User {
    private int user_id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private boolean has_user_profile;


    public User(int user_id,
                String name, String surname,
                String email, String username,
                String password,
                boolean has_user_profile) {
        this.user_id = user_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password; //hashed
        this.has_user_profile = has_user_profile;


    }
    public User(){

    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHas_user_profile(String has_user_profile){
        this.has_user_profile = has_user_profile.equals("Y");
    }


    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public boolean getHas_user_profile(){
        return has_user_profile;
    }

}
