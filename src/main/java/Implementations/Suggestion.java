package Implementations;

import DAO.ActionDAO;
import DAO.PersonalInfoDAO;
import DAO.SuggestionDataDAO;
import Model.PersonalUserInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Suggestion {

    private int userID;
    private PersonalUserInfo userInfo;
    private PersonalUserInfo suggestedUser;
    private ArrayList<Integer> validUsers;
    private Random rand;

    public Suggestion(PersonalUserInfo userInfo){
        this.userInfo = userInfo;
        this.userID = userInfo.getUser_id();
        this.rand = new Random();
    try {
            Generate();
            next();
    }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void Generate() throws SQLException {
        this.validUsers = SuggestionDataDAO.getSuggestions(userInfo);
    }

    public PersonalUserInfo getSuggestedUser(){
        return suggestedUser;
    }

    public void next(){
        try {
            suggestedUser = PersonalInfoDAO.getUserInfo(validUsers.get(rand.nextInt(validUsers.size())));
            validUsers.remove((Integer) suggestedUser.getUser_id());
            if(validUsers.size() == 0) Generate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            suggestedUser = null;
        }
    }

    public void Like() throws SQLException{
        ActionDAO.Action(userID,suggestedUser.getUser_id(),ActionDAO.ACTION_LIKE);
        next();
    }
    public void Dislike() throws SQLException{
        ActionDAO.Action(userID,suggestedUser.getUser_id(),ActionDAO.ACTION_DISLIKE);
        next();
    }

}
