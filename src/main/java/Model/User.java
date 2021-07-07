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
    private String dateOfBirth;
    private String sex;
    private int age;

    private City city;
   // private Hobbies[] hobbies;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User(String name, String surname,
                String email, String username,
                String password,
                String dateOfBirth, String sex,
                City city, Hobbies[] hobbies) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password; //hashed
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.city = city;
        //this.hobbies = hobbies;

        this.age = getCurrentAge(dateOfBirth);
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

    public void setCity(City city) {
        this.city = city;
    }

    /*public void setHobbies(Hobbies[] hobbies) {
        this.hobbies = hobbies;
    }*/

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

    public City getCity() {
        return city;
    }

    /*
    public Hobbies[] getHobbies() {
        return hobbies;
    }*/

    public int getCurrentAge(String date){
        Date birthDate = new Date(date);
        long age = System.currentTimeMillis() - birthDate.getTime();
        return (int) (TimeUnit.DAYS.convert(age, TimeUnit.MILLISECONDS)/365);
    }

}
