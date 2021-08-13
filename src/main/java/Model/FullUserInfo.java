package Model;

import Enums.City;
import Enums.Hobbies;

public class FullUserInfo extends User {
    private String name;
    private String surname;
    private String email;
    private String username;

    private boolean has_user_profile;
    private int age;
    private String image;
    private String phoneNumber;
    private String city;
    private String sex;

    private boolean isBanned = false;
    private boolean isAdmin = false;


    public FullUserInfo(String name, String surname, String email, String username, boolean has_user_profile, int age, String image, String phoneNumber, String city, String sex, boolean isBanned, boolean isAdmin) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.has_user_profile = has_user_profile;
        this.age = age;
        this.image = image;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.sex = sex;
        this.isBanned = isBanned;
        this.isAdmin = isAdmin;
    }

    public FullUserInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isHas_user_profile() {
        return has_user_profile;
    }

    public void setHas_user_profile(boolean has_user_profile) {
        this.has_user_profile = has_user_profile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}