package Model;

import Enums.City;
import Enums.Hobbies;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class PersonalUserInfo {
    private int user_profile_id;
    private String dateOfBirth;
    private String phoneNumber;
    private String username;
    private int age;
    private City city;
    private Hobbies[] hobbies;
    private String sex;
    private int user_id;

    public PersonalUserInfo(String username, String dateOfBirth, String phoneNumber,
                            City city, Hobbies[] hobbies, String sex, int user_id ) {
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.hobbies = hobbies;
        this.age = getCurrentAge(dateOfBirth);
        this.sex = sex;
        this.user_id = user_id;
    }

    public PersonalUserInfo(){

    }

    public int getUser_profile_id(){return user_profile_id;}

    public String getUsername() {
        return username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public Hobbies[] getHobbies() {
        return hobbies;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public int getUser_id(){return user_id;}


    public void setUser_profile_id(int user_profile_id ){ this.user_profile_id = user_profile_id;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setHobbies(Hobbies[] hobbies) {
        this.hobbies = hobbies;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUser_id(int user_id){this.user_id = user_id;}




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


    public int getCurrentAge(String date){
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
        int age = Period.between(birthDate,now).getYears();
        return age;
    }





}
