package Main;

import DAO.PersonalInfoDAO;
import DAO.UserDAO;
import Exceptions.RegistrationException;
import Implementations.Suggestion;
import Model.PersonalUserInfo;
import Model.User;

import java.sql.SQLException;

public class runner {



    public static void main(String[] args) throws SQLException, RegistrationException {
        //generate suggestions
        /*for(int i=1; i<=20; i++){
            User male = new User("gurami" + i,"abra","a@gmail.com" + i,"gurami" + i,String.valueOf("gurami2000".hashCode()),
                    true);
            UserDAO.setUser(male);
            PersonalUserInfo maleInfo = new PersonalUserInfo(male.getUsername(),"01/02/2000","597580350","TBILISI","LONG_WALKS_ON_THE_BEACH","MALE",male.getUser_id());
            PersonalInfoDAO.setUserInfo(maleInfo);
            User female = new User("nona" + i,"kime","ab@gmail.com" + i,"nona" + i,String.valueOf("gurami2000".hashCode()),
                    true);
            UserDAO.setUser(female);
            PersonalUserInfo femaleInfo = new PersonalUserInfo(female.getUsername(),"01/02/2000","597580350","TBILISI","LONG_WALKS_ON_THE_BEACH","FEMALE", female.getUser_id());
            PersonalInfoDAO.setUserInfo(femaleInfo);
        }*/

        /* test action
        PersonalUserInfo sample = PersonalInfoDAO.getUserInfo(287);
        for(int i=0; i<20; i++) {
            System.out.println(sample.getUsername());
            Suggestion suggestion = new Suggestion(sample);
            if(suggestion == null) break;
            System.out.println(suggestion.getSuggestedUser().getUsername());
            suggestion.Like();
        }*/
    }




}
