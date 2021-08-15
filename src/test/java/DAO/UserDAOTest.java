package DAO;

import Exceptions.RegistrationException;
import Model.User;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    User user;
    @AfterEach
    void clean() throws SQLException {
        UserDAO.deleteUser(user);
    }
    @BeforeEach
    void setUserTest() {
        user = new User("testname","testsurname","test@email.com","testusername","-995414549",false);
        assertDoesNotThrow(()->{user = UserDAO.setUser(user);});
        assertThrows(RegistrationException.class,()->{UserDAO.setUser(user);});
    }
    @Test
    void getUser() {
        assertAll(
                ()->{
                    assertThrows(SQLException.class,()->{UserDAO.getUser("A12",true);});
                },
                ()-> {
                    assertThrows(SQLException.class, () -> {
                        UserDAO.getUser("A12@hey.ds", false);
                    });
                },
                ()->{
                    assertDoesNotThrow(()->{UserDAO.getUser("test@email.com",false);});
                },
                ()->{
                    assertDoesNotThrow(()->{UserDAO.getUser("testusername",true);});
                },
                ()->{
                    assertEquals("test@email.com",UserDAO.getUser("test@email.com",false).getEmail());
                },
                ()->{
                    assertEquals("testusername",UserDAO.getUser("testusername",true).getUsername());
                }
                );
    }
    @Test
    void getUserByID() {
        assertAll(
                ()->{
                    assertThrows(SQLException.class,()->{UserDAO.getUserByID(-100000);});
                },
                ()->{
                    assertDoesNotThrow(()->{UserDAO.getUserByID(user.getUser_id());});
                },
                ()->{
                    assertEquals("testusername",UserDAO.getUserByID(user.getUser_id()).getUsername());
                }
        );
    }
    @Test
    void updateUser() {
        User user_updated  = new User("testname1","testsurname1","test@email.com1","testusername1","-995414549",false);
        user_updated.setUser_id(user.getUser_id());
        assertAll(
                ()->{
                    assertDoesNotThrow(()->{UserDAO.updateUser(user_updated);});
                },
                ()->{
                    assertEquals("testusername1",UserDAO.getUserByID(user.getUser_id()).getUsername());
                }
        );
    }

    @Test
    void banTests() {
        assertAll(
                ()->{
                    assertDoesNotThrow(()->{UserDAO.banUser(user.getUser_id());});
                },
                ()->{
                    assertTrue(UserDAO.getUserByID(user.getUser_id()).isBanned());
                },
                ()->{
                    assertTrue(UserDAO.getBannedUsers().stream().map(User::getUser_id).anyMatch(e -> e==user.getUser_id()));
                },
                ()->{
                    assertDoesNotThrow(()->{UserDAO.unbanUser(user.getUser_id());});
                },
                ()->{
                    assertFalse(UserDAO.getUserByID(user.getUser_id()).isBanned());
                }
        );
    }

}