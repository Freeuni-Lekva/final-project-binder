package Model;

import Enums.City;
import Enums.Hobbies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;
class PersonalUserInfoTest {

    @Test
    void setCity_test_valid() {
        PersonalUserInfo p = new PersonalUserInfo();
        String arg = "TBILISI";
        p.setCity(arg);
        assertTrue(p.getCityEnum() == City.TBILISI);
    }
    @Test
    void setCity_test_invalid1() {
        PersonalUserInfo p = new PersonalUserInfo();
        String arg = "dsad";
        Assertions.assertThrows(IllegalArgumentException.class,() ->{
            p.setCity(arg);
            City a = p.getCityEnum();});
    }
    @Test
    void setCity_test_invalid2() {
        PersonalUserInfo p = new PersonalUserInfo();
        String arg = "";
        p.setCity(arg);
        assertTrue(p.getCityEnum() == null);
    }

    @Test
    void getCityTest_valid() {
        PersonalUserInfo p = new PersonalUserInfo();
        String arg = "TBILISI";
        p.setCity(arg);
        assertTrue(p.getCity() == "TBILISI");
    }
    @Test
    void getCityTest_invalid() {
        PersonalUserInfo p = new PersonalUserInfo();
        assertTrue(p.getCity() == "");
    }
    @Test
    void hobbiesToStringTest_null() {
        Hobbies[] hobbies = null;
        assertEquals("",PersonalUserInfo.HobbiesToString(hobbies));
    }
    @Test
    void hobbiesToStringTest_1() {
        Hobbies[] hobbies = {Hobbies.CHESS};
        assertEquals("CHESS",PersonalUserInfo.HobbiesToString(hobbies));
    }
    @Test
    void hobbiesToStringTest_multiple() {
        Hobbies[] hobbies = {Hobbies.CHESS,
                Hobbies.YOGA,
                Hobbies.EATING};
        assertEquals("CHESS,YOGA,EATING",PersonalUserInfo.HobbiesToString(hobbies));
    }
    @Test
    void stringToHobbiesTest_empty() {
        String arg = "";
        assertEquals(null,PersonalUserInfo.StringToHobbies(arg));
    }

    @Test
    void stringToHobbiesTest_one() {
        String arg = "CHESS";
        Hobbies[] hobbies = {Hobbies.CHESS};
        assertArrayEquals(hobbies,PersonalUserInfo.StringToHobbies(arg));
    }
    @Test
    void stringToHobbiesTest_multiple() {
        String arg = "CHESS,YOGA,EATING";
        Hobbies[] hobbies = {Hobbies.CHESS,Hobbies.YOGA,Hobbies.EATING};
        assertArrayEquals(hobbies,PersonalUserInfo.StringToHobbies(arg));
    }
    @Test
    void stringToHobbiesTest_invalid1() {
        String arg = "CHESS,YOGA,EATING,ddsa";
        Assertions.assertThrows(IllegalArgumentException.class,()->{PersonalUserInfo.StringToHobbies(arg);});
    }
    @Test
    void stringToHobbiesTest_invalid2() {
        String arg = "ddsa";
        Assertions.assertThrows(IllegalArgumentException.class,()->{PersonalUserInfo.StringToHobbies(arg);});
    }
    //Time dependent
    @Test
    void getCurrentAge_validString() {
        String arg2 = "d/M/yyyy";
        String arg1 = "7/4/2000";
        assertEquals(21,PersonalUserInfo.getCurrentAge(arg1,arg2));
    }
    @Test
    void getCurrentAge_invalidString() {
        String arg2 = "d/M/yyyy";
        String arg1 = "7/4/200";
        Assertions.assertThrows(DateTimeParseException.class,()->{PersonalUserInfo.getCurrentAge(arg1,arg2);});

    }
    @Test
    void getCurrentAge_invalidString1() {
        String arg2 = "d/M/yyyy";
        String arg1 = "";
        Assertions.assertThrows(DateTimeParseException.class,()->{PersonalUserInfo.getCurrentAge(arg1,arg2);});
    }
    @Test
    void getCurrentAge_invalidString2() {
        String arg2 = "d/M/yyyy";
        String arg1 = null;
        Assertions.assertThrows(DateTimeParseException.class,()->{PersonalUserInfo.getCurrentAge(arg1,arg2);});
    }
}