
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
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miltron
 */
public class RegistrationCommand extends DefaultController implements ControllerInterface {
    Boolean returner = false;


    @Override
    public boolean processRequest(Map<String, String[]> parameterMap, List messages, MysqlDataSource dataSource) {
        //TO DO verify the username isnt already in the database
    String userName =  parameterMap.get(RequestObjectVariables.USERNAME.getVariable())[0];
    String password =  parameterMap.get(RequestObjectVariables.PASSWORD.getVariable())[0];    
        
    try (USERSDAO userdao = new USERSDAO(dataSource)){
        userdao.insert(userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    

    @Override
    public boolean validateBusinessLogic(Map<String, String[]> parameterMap, List messages, MysqlDataSource dataSource) {
    String[] userName =  parameterMap.get(RequestObjectVariables.USERNAME.getVariable());
        String[] password =  parameterMap.get(RequestObjectVariables.PASSWORD.getVariable());
       
        //verify userName and password meet requirements before registration
        
        
        if (userName != null && 2 > userName.length && userName.length > 0 && userName[0].length() >= 8 && !userName[0].equals(userName[0].toLowerCase()) && !userName[0].equals(userName[0].toUpperCase())){
            returner = true;
        } else {
            messages.add("Please follow the requirements for the username and resubmit.");
        }
        if( password != null && 2 > password.length && password.length > 0 && password[0].length() >= 8 && !password[0].equals(password[0].toLowerCase())&& !password[0].equals(password[0].toUpperCase())) {
            returner = returner && true;
        } else {
            messages.add("Please follow the requirements for the password and resubmit.");
            returner = false;
        }
        
        try (USERSDAO usersdao = new USERSDAO(dataSource)){
            //verify userName against database to ensure the userName is available
            if(returner == true && usersdao.userNameAvailability(userName[0]) ){
                  messages.add("Your Registration was successful, please login.");
            } else{
                messages.add("Username currently used please enter a new username and resubmit.");
                returner = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       return returner;
    }

    @Override
    public boolean processRequest(JsonObject jsonObject, List messages, MysqlDataSource dataSource) {
        String username = jsonObject.get("USERNAME").getAsString();
        String password = jsonObject.get("PASSWORD").getAsString();
        
        RegistrationCommand rg = new RegistrationCommand();
        rg.validate(parameterMap, messages, dataSource);
                return true;
    }

    @Override
    public boolean validate(JsonObject jsonObject, List messages, MysqlDataSource dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

