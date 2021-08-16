package DAO;

import Exceptions.RegistrationException;
import Model.User;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class SessionsDAOTest {
    private static User user;
    @BeforeAll
    static void setup(){
        user = new User("testname","testsurname","test@email.com","testusername","-995414549",false);
        assertDoesNotThrow(()->{user = UserDAO.setUser(user);});
        assertThrows(RegistrationException.class,()->{UserDAO.setUser(user);});
    }
    @AfterAll
    static void clean(){
        assertAll(
                () -> {
                    assertDoesNotThrow(() -> UserDAO.getUserByID(user.getUser_id()).getHas_user_profile());
                },
                () -> {
                    assertFalse(UserDAO.getUserByID(user.getUser_id()).getHas_user_profile());
                },
                () -> {
                    UserDAO.deleteUser(user);
                }
        );
    }
    @BeforeEach
    void setSessionTest() {
        assertDoesNotThrow(()->{SessionsDAO.setSession("session_id",user.getUser_id());});
    }
    @AfterEach
    void deleteSessionsTest() {
        assertDoesNotThrow(()->{
            SessionsDAO.deleteSession("session_id");
        });
    }
    @Test
    void getUser_id() {
        assertAll(
                ()->{
                    assertDoesNotThrow(()->{SessionsDAO.getUser_id("session_id");});
                },
                ()->{
                    assertEquals(user.getUser_id(),SessionsDAO.getUser_id("session_id"));
                }
        );
    }
    @Test
    void testDeleteSession2() {
        assertAll(
                ()->{
                    assertThrows(SQLIntegrityConstraintViolationException.class,()->{SessionsDAO.setSession("session_id2",0);});
                },
                ()->{
                    assertDoesNotThrow(()->{SessionsDAO.deleteSession(user.getUser_id());});
                },
                ()-> {
                    assertDoesNotThrow(() -> {
                        SessionsDAO.setSession("session_id", user.getUser_id());
                    });
                }
        );
    }
}