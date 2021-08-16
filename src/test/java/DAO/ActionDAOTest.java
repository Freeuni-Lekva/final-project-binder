package DAO;

import Model.PersonalUserInfo;
import Model.User;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ActionDAOTest {
    private  User user;
    private  PersonalUserInfo personalUserInfo;
    private  User user1;
    private  PersonalUserInfo personalUserInfo1;
    private  User user2;
    private  PersonalUserInfo personalUserInfo2;
    private  User user3;
    private  PersonalUserInfo personalUserInfo3;
    @BeforeEach
    void init(){
        user = new User("testname","testsurname","test@email.com","testusername","-995414549",false);
        user1 = new User("testname","testsurname","test1@email.com","testusername1","-995414549",false);
        user2 = new User("testname","testsurname","test2@email.com","testusername2","-995414549",false);
        user3 = new User("testname","testsurname","test3@email.com","testusername3","-995414549",false);
        assertDoesNotThrow(()->{user = UserDAO.setUser(user);});
        assertDoesNotThrow(()->{user1 = UserDAO.setUser(user1);});
        assertDoesNotThrow(()->{user2 = UserDAO.setUser(user2);});
        assertDoesNotThrow(()->{user3 = UserDAO.setUser(user3);});
        personalUserInfo = new PersonalUserInfo("testusername", "17/04/2000", "595123456",
                "TBILISI", "LONG_WALKS_ON_THE_BEACH,CHESS,YOGA", "Male", user.getUser_id() );
        personalUserInfo1 = new PersonalUserInfo("testusername1", "17/04/2000", "595123456",
                "TBILISI", "LONG_WALKS_ON_THE_BEACH,YOGA", "Female", user1.getUser_id() );
        personalUserInfo2 = new PersonalUserInfo("testusername2", "17/04/2000", "595123456",
                "TBILISI", "LONG_WALKS_ON_THE_BEACH,CHESS,YOGA", "Female", user2.getUser_id() );
        personalUserInfo3 = new PersonalUserInfo("testusername3", "17/04/2000", "595123456",
                "RUSTAVI", "LONG_WALKS_ON_THE_BEACH,CHESS,YOGA", "Female", user3.getUser_id() );
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo);});
        assertDoesNotThrow(()->{personalUserInfo.setUser_profile_id(PersonalInfoDAO.getUserInfo(user.getUser_id()).getUser_profile_id());});
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo1);});
        assertDoesNotThrow(()->{personalUserInfo1.setUser_profile_id(PersonalInfoDAO.getUserInfo(user1.getUser_id()).getUser_profile_id());});
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo2);});
        assertDoesNotThrow(()->{personalUserInfo2.setUser_profile_id(PersonalInfoDAO.getUserInfo(user2.getUser_id()).getUser_profile_id());});
        assertDoesNotThrow(()->{PersonalInfoDAO.setUserInfo(personalUserInfo3);});
        assertDoesNotThrow(()->{personalUserInfo3.setUser_profile_id(PersonalInfoDAO.getUserInfo(user3.getUser_id()).getUser_profile_id());});
    }
    @AfterEach
    void tear() throws SQLException {
        UserDAO.deleteUser(user);
        UserDAO.deleteUser(user1);
        UserDAO.deleteUser(user2);
        UserDAO.deleteUser(user3);
    }
    @Test
    void actionMatchTest1() {
        assertAll(
                ()->{
                    assertDoesNotThrow(()->{ActionDAO.Action(personalUserInfo.getUser_profile_id(),personalUserInfo1.getUser_profile_id(),1);
                });
                },
                ()->{
                    assertDoesNotThrow(()->{ActionDAO.Action(personalUserInfo.getUser_profile_id(),personalUserInfo2.getUser_profile_id(),1);
                    });
                },
                ()->{
                    assertDoesNotThrow(()->{ActionDAO.Action(personalUserInfo1.getUser_profile_id(),personalUserInfo.getUser_profile_id(),1);
                    });
                },
                ()->{
                    assertDoesNotThrow(()->{ActionDAO.Action(personalUserInfo2.getUser_profile_id(),personalUserInfo.getUser_profile_id(),-1);
                    });
                },()->{
                    assertEquals( 1,ActionDAO.isMatch(personalUserInfo.getUser_profile_id(),personalUserInfo1.getUser_profile_id())
                    );
                },()->{
                    assertEquals( 1,ActionDAO.isMatch(personalUserInfo1.getUser_profile_id(),personalUserInfo.getUser_profile_id())
                    );
                },()->{
                    assertEquals( 0,ActionDAO.isMatch(personalUserInfo2.getUser_profile_id(),personalUserInfo.getUser_profile_id())
                    );
                },()->{
                    assertEquals( 1,ActionDAO.isMatch(personalUserInfo.getUser_profile_id(),personalUserInfo2.getUser_profile_id())
                    );
                },()->{
                    assertEquals( 0,ActionDAO.isMatch(personalUserInfo2.getUser_profile_id(),personalUserInfo1.getUser_profile_id())
                    );
                },()->{
                    assertEquals( 0,ActionDAO.isMatch(personalUserInfo3.getUser_profile_id(),0)
                    );
                }
        );
    }
}
