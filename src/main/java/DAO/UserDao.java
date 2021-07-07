package DAO;

import DatabaseMetaInfo.MyDatabase;
import Enums.City;
import Enums.Hobbies;
import Exceptions.RegistrationException;
import Model.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection con;


    public UserDao(){
        con = MyDatabase.getConnection();
    }

    public User getUser(String key,Boolean isUsername) throws  SQLException{
        PreparedStatement pstmt = con.prepareStatement("Use binder; ");
        StringBuilder statement = new StringBuilder();
        if(isUsername){
            statement.append("Select * from User where username = \"" + key + "\"");
        }else{
            statement.append("Select * from User where email = \"" + key + "\"");
        }

        User user = new User();
        ResultSet rs = pstmt.executeQuery(statement.toString());
        rs.next();
        user.setName(rs.getString(2));
        user.setSurname(rs.getString(3));
        user.setEmail(rs.getString(4));
        user.setUsername(rs.getString(5));
        user.setPassword(rs.getString(6));
        user.setDateOfBirth(rs.getString(7));
        user.setAge(rs.getInt(8));
        user.setCity(City.valueOf(rs.getString(9)));

        /*
        String hobs = rs.getString(8);
        String[] hobslist = hobs.split(",");
        Hobbies[] hobbies = new Hobbies[hobslist.length];
        int k=0;
        for(String str : hobslist){
            hobbies[k] = Hobbies.valueOf(str);
            k++;
        }
        user.setHobbies(hobbies);
        */

        return user;
    }
    public void setUser(User user) throws SQLException, RegistrationException {
        PreparedStatement pstmt = con.prepareStatement("Select count(id) from user where email = ?");
        pstmt.setString(1,user.getEmail());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        if(rs.getInt(1) > 0){
            throw new RegistrationException("Username with this email is already registered!");
        }else {
            pstmt.close();
            pstmt = con.prepareStatement("INSERT INTO user " +
                    " (name , surname , email, username , password, dateOfBirth, age, city) " +
                    "VALUES (?,?,?,?,?,?,?,?);");
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getDateOfBirth());
            pstmt.setInt(7, user.getAge());
            pstmt.setString(8, user.getCity().toString());

            pstmt.executeUpdate();
        }
    }



}
