package DAO;

import DatabaseMetaInfo.MyDatabase;
import Enums.City;
import Enums.Hobbies;
import Exceptions.RegistrationException;
import Model.PersonalUserInfo;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalInfoDAO {

    private Connection con;


    public PersonalInfoDAO(){
        con = MyDatabase.getConnection();
    }

    public PersonalUserInfo getUserInfo(String key) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        StringBuilder statement = new StringBuilder();
        statement.append("Select * from User_profile where username = \"" + key + "\"");
        PersonalUserInfo userInfo = new PersonalUserInfo();
        ResultSet rs = pstmt.executeQuery(statement.toString());
        rs.next();
        userInfo.setUsername(key);
        userInfo.setID(rs.getInt(1));
        userInfo.setCity(City.valueOf(rs.getString(2)));
        userInfo.setDateOfBirth(rs.getString(3));
        userInfo.setAge(rs.getInt(4));
        userInfo.setPhoneNumber(rs.getString(5));

        String hobs = rs.getString(6);
        String[] hobbiesList = hobs.split(",");
        Hobbies[] hobbies = new Hobbies[hobbiesList.length];
        int k=0;
        for(String str : hobbiesList){
            hobbies[k] = Hobbies.valueOf(str);
            k++;
        }
        userInfo.setHobbies(hobbies);
        return userInfo;
    }

    public void setUserInfo(PersonalUserInfo userInfo) throws SQLException, RegistrationException {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO User_profile " +
                    " (username,City, dateOfBirth, age, phone_number, hobbies) " +
                    "VALUES (?,?,?,?,?,?);");
            pstmt.setString(1, userInfo.getUsername());
            pstmt.setString(2, userInfo.getCity().toString());
            pstmt.setString(3, userInfo.getDateOfBirth());
            pstmt.setInt(4, userInfo.getAge());
            pstmt.setString(5, userInfo.getPhoneNumber());

            StringBuilder str = new StringBuilder();
            Hobbies[] hobbies = userInfo.getHobbies();
            for(int i=0; i<hobbies.length; i++){
                str.append(hobbies[i].toString() + ",");
            }
            pstmt.setString(6, str.toString());
            pstmt.executeUpdate();
    }


    public void updateUserInfo(PersonalUserInfo userInfo) throws SQLException, RegistrationException {
        PreparedStatement pstmt = con.prepareStatement("UPDATE user " +
                " SET username = ? , city = ? , dateOfBirth = ? , age = ? , phoneNumber = ? , hobbies = ? "
                + "where username = ?"
        );
        pstmt.setString(1, userInfo.getUsername());
        pstmt.setString(2, userInfo.getCity().toString());
        pstmt.setString(3, userInfo.getDateOfBirth());
        pstmt.setInt(   4, userInfo.getAge());
        pstmt.setString(5, userInfo.getPhoneNumber());

        Hobbies[] hobbies = userInfo.getHobbies();
        StringBuilder str = new StringBuilder();
        for(int i=0; i<hobbies.length; i++){
            str.append(hobbies[i].toString() + ",");
        }
        pstmt.setString(6, str.toString());
        pstmt.setString(7,userInfo.getUsername());
        pstmt.executeUpdate();
    }





}
