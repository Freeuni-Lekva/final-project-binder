package Main;

import DAO.UserDAO;
import Enums.City;
import Exceptions.RegistrationException;
import Model.User;

import java.sql.SQLException;

public class runner {

    public static void main(String[] args) throws SQLException {
        UserDAO userDao = new UserDAO();
        User user = new User("gurami","abramishvili","gabra19@gmail.com","gabra",
                "gurami2000","male"
        ,0);


        try {
            userDao.setUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (RegistrationException e) {
            e.printStackTrace();
        }
        User user1 = userDao.getUser(user.getUsername(),true);

        System.out.println(user1.getPassword());

    }


}
