package DAO;

import Model.PersonalUserInfo;
import Model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SuggestionDataDAOTest {
    private static User user;
    private static PersonalUserInfo personalUserInfo;
    private static User user1;
    private static PersonalUserInfo personalUserInfo1;
    private static User user2;
    private static PersonalUserInfo personalUserInfo2;
    @BeforeAll
    static void init(){
        user = new User("testname","testsurname","test@email.com","testusername","-995414549",false);
        user1 = new User("testname","testsurname","test1@email.com","testusername1","-995414549",false);
        user2 = new User("testname","testsurname","test2@email.com","testusername2","-995414549",false);
        assertDoesNotThrow(()->{user = UserDAO.setUser(user);});
        assertDoesNotThrow(()->{user1 = UserDAO.setUser(user1);});
        assertDoesNotThrow(()->{user2 = UserDAO.setUser(user2);});
        personalUserInfo = new PersonalUserInfo("testusername", "17/04/2000", "595123456",
                "TBILISI", "LONG_WALKS_ON_THE_BEACH,CHESS,YOGA", "Male", user.getUser_id() );
        personalUserInfo1 = new PersonalUserInfo("testusername1", "17/04/2000", "595123456",
                "TBILISI", "LONG_WALKS_ON_THE_BEACH,YOGA", "Female", user1.getUser_id() );
        personalUserInfo2 = new PersonalUserInfo("testusername2", "17/04/2000", "595123456",
                "BATUMI", "LONG_WALKS_ON_THE_BEACH,CHESS,YOGA", "Female", user2.getUser_id() );
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo);});
        assertDoesNotThrow(()->{personalUserInfo.setUser_profile_id(PersonalInfoDAO.getUserInfo(user.getUser_id()).getUser_profile_id());});
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo1);});
        assertDoesNotThrow(()->{personalUserInfo1.setUser_profile_id(PersonalInfoDAO.getUserInfo(user1.getUser_id()).getUser_profile_id());});
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo2);});
        assertDoesNotThrow(()->{personalUserInfo2.setUser_profile_id(PersonalInfoDAO.getUserInfo(user2.getUser_id()).getUser_profile_id());});
    }

    @AfterAll
    static void tear() throws SQLException {
        UserDAO.deleteUser(user);
        UserDAO.deleteUser(user1);
        UserDAO.deleteUser(user2);
    }
    @Test
    void getSuggestions() {
        assertAll(
                ()->{assertDoesNotThrow(
                        ()->{
                            SuggestionDataDAO.getSuggestions(personalUserInfo).contains(personalUserInfo1.getUser_profile_id());
                            SuggestionDataDAO.getSuggestions(personalUserInfo).contains(personalUserInfo2.getUser_profile_id());
                            SuggestionDataDAO.getSuggestions(personalUserInfo2).contains(personalUserInfo1.getUser_profile_id());
                            SuggestionDataDAO.getSuggestions(personalUserInfo2).contains(personalUserInfo.getUser_profile_id());
                            SuggestionDataDAO.getSuggestions(personalUserInfo1).contains(personalUserInfo.getUser_profile_id());
                            SuggestionDataDAO.getSuggestions(personalUserInfo1).contains(personalUserInfo2.getUser_profile_id());
                        }
                );},
                ()->{
                    assertTrue(SuggestionDataDAO.getSuggestions(personalUserInfo).contains(personalUserInfo1.getUser_profile_id()));
                    assertTrue(!SuggestionDataDAO.getSuggestions(personalUserInfo).contains(personalUserInfo2.getUser_profile_id()));
                    assertTrue(!SuggestionDataDAO.getSuggestions(personalUserInfo2).contains(personalUserInfo1.getUser_profile_id()));
                    assertTrue(!SuggestionDataDAO.getSuggestions(personalUserInfo2).contains(personalUserInfo.getUser_profile_id()));
                    assertTrue(SuggestionDataDAO.getSuggestions(personalUserInfo1).contains(personalUserInfo.getUser_profile_id()));
                    assertTrue(!SuggestionDataDAO.getSuggestions(personalUserInfo1).contains(personalUserInfo2.getUser_profile_id()));
                }
        );
    }
}