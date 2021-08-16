package DAO;

import Exceptions.RegistrationException;
import Model.PersonalUserInfo;
import Model.User;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserImagesDAOTest {
    private static User user;
    private static PersonalUserInfo personalUserInfo;
    @BeforeAll
    static void setTest() throws SQLException {
        user = new User("testname","testsurname","test@email.com","testusername","-995414549",false);
        assertDoesNotThrow(()->{user = UserDAO.setUser(user);});
        assertThrows(RegistrationException.class,()->{UserDAO.setUser(user);});
        personalUserInfo = new PersonalUserInfo("testusername", "17/04/2000", "595123456",
                "TBILISI", "LONG_WALKS_ON_THE_BEACH,CHESS,YOGA", "Male", user.getUser_id() );
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo);});
        assertThrows(SQLException.class,()->{PersonalInfoDAO.setUserInfo(personalUserInfo);});
        personalUserInfo.setUser_profile_id(PersonalInfoDAO.getUserInfo(user.getUser_id()).getUser_profile_id());
    }
    @AfterAll
    static void clear() throws SQLException {
        UserDAO.deleteUser(user);
    }

    @BeforeEach
    void setUserImageTest() {
        assertDoesNotThrow(()->{
            UserImagesDAO.setUserImage(personalUserInfo.getUser_profile_id(),"path");
        });
        assertThrows(
            SQLException.class,
            ()-> {
                UserImagesDAO.setUserImage(personalUserInfo.getUser_profile_id(),"path");
            }
        );
    }
    @AfterEach
    void deleteImageTest() {
        assertDoesNotThrow(()->{UserImagesDAO.deleteImage(personalUserInfo.getUser_profile_id(),"path");});
    }
    @Test
    void getUserImagesTest() {
        assertAll(
                ()->{
                    assertDoesNotThrow(()->{UserImagesDAO.getUserImages(personalUserInfo.getUser_profile_id());});
                },
                ()->{
                    assertEquals("path",UserImagesDAO.getUserImages(personalUserInfo.getUser_profile_id()));
                }
        );
    }



}