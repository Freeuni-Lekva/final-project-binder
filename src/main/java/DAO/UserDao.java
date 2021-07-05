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

    public User getUser(String username) throws  SQLException{
        StringBuilder statement = new StringBuilder("use Binder ");
        statement.append("Select * from Users where username = \'" + username + "\'");
        User user = new User();
        ResultSet rs = pstmt.executeQuery(statement.toString());

        user.setName(rs.getString(1));
        user.setSurname(rs.getString(2));
        user.setEmail(rs.getString(3));
        user.setUsername(rs.getString(4));
        user.setPassword(rs.getString(5));
        user.setDateOfBirth(rs.getString(6));
        user.setCity(City.valueOf(rs.getString(7)));

        String hobs = rs.getString(8);
        String[] hobslist = hobs.split(",");
        Hobbies[] hobbies = new Hobbies[hobslist.length];
        int k=0;
        for(String str : hobslist){
            hobbies[k] = Hobbies.valueOf(str);
            k++;
        }
        user.setHobbies(hobbies);

        return user;
    }



}
