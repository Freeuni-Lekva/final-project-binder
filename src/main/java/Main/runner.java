package Main;

import DAO.UserDAO;
import Enums.City;
import Model.User;

import java.sql.SQLException;

public class runner {

    public static void main(String[] args) {
        UserDAO userDao = new UserDAO();
        User user = new User("gurami","abramishvili","gabra19@gmail.com","gabra",
                "gurami2000","02/03/2000","male"
        , City.TBILISI,null);

        /*
        try {
           // userDao.setUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (RegistrationException e) {
            e.printStackTrace();
        }*/


        User user1 = null;
        try {
            user1 = userDao.getUser(user.getUsername(),true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(user1.getPassword());

    }


}
