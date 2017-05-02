/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dardenestates.code.src.CONTROLLER;

import Util.RequestObjectVariables;
import com.dardenestates.code.src.MODELS.USERSDAO;
import com.google.gson.JsonObject;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.List;
import java.util.Map;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Miltron
 */
public class LoginCommand extends DefaultController implements ControllerInterface{
  
    
    @Override
    public boolean validateData(Map<String, String[]> parameterMap, List messages) {
    boolean returner;
    // Extract username and password
    String[] userName =  parameterMap.get(RequestObjectVariables.USERNAME.getVariable());
    String[] password =  parameterMap.get(RequestObjectVariables.PASSWORD.getVariable());
    // Verify username and password has data
    if (userName!=null && userName.length > 0 && userName.length < 2 ){
        
          returner = true;
    } else {
        messages.add("Please correct username and submit");
        returner = false;
    }
    if (password!=null && password.length > 0 && password.length < 2 ){
        //dont want to override previous if statement
        returner = returner && true;
        
    } else {
        messages.add("Please correct password and submit");
        returner = false;
    }
    
    return returner;
    }
    
    @Override
    public boolean processRequest(Map<String, String[]> parameterMap, List messages, MysqlDataSource dataSource) {
        

    // Extract username and password
    String userName =  parameterMap.get(RequestObjectVariables.USERNAME.getVariable())[0];
    String password =  parameterMap.get(RequestObjectVariables.PASSWORD.getVariable())[0];
    //Verify that user input for username and password matches with those of th database
    
    try (USERSDAO userdao = new USERSDAO(dataSource)){
        if (userdao.validatePassword(userName, password)){
            messages.add("you've successfully logged in!!!!");
        } else {
            messages.add("incorrect username and password combo!");
            return false;
        }
        } catch (SQLException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    return true;
    }

    @Override
    public boolean processRequest(JsonObject jsonObject, List messages, MysqlDataSource dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validate(JsonObject jsonObject, List messages, MysqlDataSource dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
