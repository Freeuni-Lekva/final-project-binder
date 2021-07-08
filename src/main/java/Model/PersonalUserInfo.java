package Model;

import Enums.City;
import Enums.Hobbies;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PersonalUserInfo {

    private String dateOfBirth;
    private String phoneNumber;
    private String username;
    private int age;
    private City city;
    private Hobbies[] hobbies;


    public PersonalUserInfo(String dateOfBirth, String phoneNumber, City city, Hobbies[] hobbies) {
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.hobbies = hobbies;

        this.age = getCurrentAge(dateOfBirth);
    }

    public PersonalUserInfo(){

    }

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


    public void setCity(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public void setHobbies(Hobbies[] hobbies) {
        this.hobbies = hobbies;
    }

    public Hobbies[] getHobbies() {
        return hobbies;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCurrentAge(String date){
        Date birthDate = new Date(date);
        long age = System.currentTimeMillis() - birthDate.getTime();
        return (int) (TimeUnit.DAYS.convert(age, TimeUnit.MILLISECONDS)/365);
    }

}
