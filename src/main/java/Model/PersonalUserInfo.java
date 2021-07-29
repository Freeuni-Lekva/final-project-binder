package Model;

import Enums.City;
import Enums.Hobbies;

import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;


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
        this.age = getCurrentAge(dateOfBirth, "d/M/yyyy");
        this.sex = sex;
        this.user_id = user_id;
    }

    public PersonalUserInfo(String username, String dateOfBirth, String phoneNumber,
                            String city, String hobbies, String sex, int user_id ) {
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.city = City.valueOf(city.toUpperCase(Locale.ROOT));
        this.hobbies = StringToHobbies(hobbies);
        this.age = getCurrentAge(dateOfBirth, "d/M/yyyy");
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

    public String  getCity() {
        if(city == null){
            return "";
        }
        return city.toString();
    }
    public City getCityEnum(){
        return city;
    }
    public Hobbies[] getHobbies() {
        return hobbies;
    }
    public int getAge() { return age; }

    public String getSex() { return sex; }

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

    public void setCity(String city) {
        if(city.isEmpty()){
            this.city = null;
        }else {
            this.city = City.valueOf(city);
        }
    }

    public void setHobbies(Hobbies[] hobbies) {
        this.hobbies = hobbies;
    }

    public void setHobbies(String hobbies){ this.hobbies = StringToHobbies(hobbies);}

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUser_id(int user_id){this.user_id = user_id;}

    public static String HobbiesToString(Hobbies[] hobbies) {
        if(hobbies == null){
            return "";
        }
        Stream<Hobbies> stream = Arrays.stream(hobbies);
        return String.join(",",stream.map(Enum::toString).toArray(String[]::new));
    }
    public static Hobbies[] StringToHobbies(String str){
        if(str.isEmpty()){
            return null;
        }
        String[] strArray = str.split(",");
        Stream<String> stream = Arrays.stream(strArray);
        return stream.map(Hobbies::valueOf).toArray(Hobbies[]::new);
    }

    public static int getCurrentAge(String date , String format){
        LocalDate now = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
        int age = Period.between(birthDate,now).getYears();
        return age;
    }





}
