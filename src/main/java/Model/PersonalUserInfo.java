package Model;

import Enums.City;
import Enums.Hobbies;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class PersonalUserInfo {

    private String dateOfBirth;
    private String phoneNumber;
    private String username;
    private int age;
    private City city;
    private Hobbies[] hobbies;
    private int id;
    private String sex;

    public PersonalUserInfo(String username, String dateOfBirth, String phoneNumber, City city, Hobbies[] hobbies, String sex) {
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.hobbies = hobbies;
        this.age = getCurrentAge(dateOfBirth);
        this.sex = sex;
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

    public void setHobbies(String hobbies) {
        String[] hobbiesList = hobbies.split(",");
        Hobbies[] res = new Hobbies[hobbiesList.length];
        int k = 0;
        for (String str : hobbiesList) {
            res[k] = Hobbies.valueOf(str);
            k++;
        }
        this.hobbies = res;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCurrentAge(String date){
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
        int age = Period.between(birthDate,now).getYears();
        return age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
