package Model;

import Enums.City;
import Enums.Hobbies;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class User {

    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String sex;
    private int user_profile_id;

    public User(String name, String surname,
                String email, String username,
                String password,
                String sex,
                int user_profile_id) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password; //hashed
        this.sex = sex;
        this.user_profile_id = user_profile_id;
    }



    public User(){

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



    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public int getUser_profile_id() {
        return user_profile_id;
    }

    public void setUser_profile_id(int user_profile_id) {
        this.user_profile_id = user_profile_id;
    }


}
