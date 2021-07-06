package DAO;

import DatabaseMetaInfo.MyDatabase;
import Enums.City;
import Enums.Hobbies;
import Model.User;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private PreparedStatement pstmt;

    public UserDao(){
        Connection con = MyDatabase.getConnection();
        try {
            pstmt = con.prepareStatement("");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getUser(String key,Boolean isUsername) throws  SQLException{
        StringBuilder statement = new StringBuilder("use binder; ");
        if(isUsername){
            statement.append("Select * from Users where username = \'" + key + "\'");
        }else{
            statement.append("Select * from Users where email = \'" + key + "\'");
        }

        User user = new User();
        ResultSet rs = pstmt.executeQuery(statement.toString());

        user.setName(rs.getString(1));
        user.setSurname(rs.getString(2));
        user.setEmail(rs.getString(3));
        user.setUsername(rs.getString(4));
        user.setPassword(rs.getString(5));
        user.setDateOfBirth(rs.getString(6));
        user.setCity(City.valueOf(rs.getString(7)));

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
    public void setUser(User user) throws SQLException {
        // TODO : hobby, gender ar dagvimatebia jer , gamoiyenet variables, values cvladebi
        String name = user.getName();
        String surname = user.getSurname();
        String  email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        String dateOfBirth = user.getDateOfBirth();
        int age = user.getAge();
        City city = user.getCity();
        //Hobbies hobbies ;
        //String gender ..
        String variables = " name , surname , email, username , password, dateOfBirth,age,city,hobbies ";
        String values = "\'" + name + "\',\'" + surname + "\',\'" + email
                + "\',\'" + username + "\',\'"+ password + "\',\'" +dateOfBirth +"\'," + age
                + ",\'" + city.toString() +"\'";
        // Variables
        StringBuilder statement = new StringBuilder("use binder; " );
        statement.append("INSERT INTO user ("  );
        statement.append(variables);
        statement.append(" ) ");
        // Values
        statement.append("VALUES ( ");
        statement.append(values);
        statement.append(" ) ");
        ResultSet rs = pstmt.executeQuery(statement.toString());



    }



}
