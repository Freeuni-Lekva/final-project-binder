package Implementations;

import DAO.ActionDAO;
import DAO.PersonalInfoDAO;
import DAO.SuggestionDataDAO;
import DAO.UserDAO;
import Model.PersonalUserInfo;
import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Suggestion {

    private String username;
    private PersonalUserInfo userInfo;
    private PersonalUserInfo suggestedUser;
    private ArrayList<String> validUsers;
    private Random rand;
    public Suggestion(PersonalUserInfo userInfo){
        this.userInfo = userInfo;
        this.username = userInfo.getUsername();
        this.rand = new Random();
    try {
            Generate();
    }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void Generate() throws SQLException {
        this.validUsers = SuggestionDataDAO.getSuggestions(userInfo);
        suggestedUser = PersonalInfoDAO.getUserInfo(validUsers.get(rand.nextInt(validUsers.size())));
    }

    public PersonalUserInfo getSuggestedUser(){
        return suggestedUser;
    }

    public void next(){
        try {
            Generate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Like() throws SQLException{
        ActionDAO.Action(username,suggestedUser.getUsername(),ActionDAO.ACTION_LIKE);
        next();
    }
    public void Dislike() throws SQLException{
        ActionDAO.Action(username,suggestedUser.getUsername(),ActionDAO.ACTION_DISLIKE);
        next();
    }



}
