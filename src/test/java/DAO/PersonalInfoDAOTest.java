package DAO;

import Exceptions.RegistrationException;
import Model.PersonalUserInfo;
import Model.User;
import org.junit.jupiter.api.*;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PersonalInfoDAOTest {
    private static User user;
    PersonalUserInfo personalUserInfo;
    @BeforeAll
    static void setUserTest() {
        user = new User("testname","testsurname","test@email.com","testusername","-995414549",false);
        assertDoesNotThrow(()->{user = UserDAO.setUser(user);});
        assertThrows(RegistrationException.class,()->{UserDAO.setUser(user);});
    }
    @AfterAll
    static void clean() {
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
    void setUserInfoTest(){
        personalUserInfo = new PersonalUserInfo("testusername", "17/04/2000", "595123456",
                "TBILISI", "LONG_WALKS_ON_THE_BEACH,CHESS,YOGA", "Male", user.getUser_id() );
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo);});
        assertThrows(SQLException.class,()->{PersonalInfoDAO.setUserInfo(personalUserInfo);});
    }

    @AfterEach
    @Test
    void deleteUserInfoTest() throws SQLException {
        PersonalInfoDAO.deleteUserInfo(user.getUser_id());
    }



    @Test
    void testGetUserInfos() {
        assertAll(
                ()->{
                    assertThrows(SQLException.class,()->{PersonalInfoDAO.getUserInfo(0);});
                },
                ()-> {
                    assertThrows(SQLException.class, () -> {
                        PersonalInfoDAO.getUserInfoByPersonalID(0);
                    });
                },
                ()->{
                    assertDoesNotThrow(()->{PersonalInfoDAO.getUserInfo(user.getUser_id());});
                },
                ()->{
                    assertEquals(user.getUser_id(),PersonalInfoDAO.getUserInfo(user.getUser_id()).getUser_id());
                },
                ()->{
                    assertEquals("testusername",PersonalInfoDAO.getUserInfo(user.getUser_id()).getUsername());
                },
                ()->{
                    assertTrue(UserDAO.getUserByID(user.getUser_id()).getHas_user_profile());
                }
        );
    }
    @Test
    void updateUserInfo() {
        assertAll(
                ()->{
                    assertDoesNotThrow(()->{PersonalInfoDAO.getUserInfo(user.getUser_id());});
                },
                ()->{
                    assertEquals(user.getUser_id(),PersonalInfoDAO.getUserInfo(user.getUser_id()).getUser_id());
                },
                ()->{
                    PersonalUserInfo personalUserInfo1 = PersonalInfoDAO.getUserInfo(user.getUser_id());
                    personalUserInfo1.setCity("RUSTAVI");
                    personalUserInfo1.setUsername("testusername1");
                    assertDoesNotThrow(()->{PersonalInfoDAO.updateUserInfo(personalUserInfo1);});
                },
                ()->{
                    assertEquals("testusername1",
                            PersonalInfoDAO.getUserInfoByPersonalID(PersonalInfoDAO.getUserInfo(user.getUser_id()).getUser_profile_id()).getUsername());
                }
        );
    }
}