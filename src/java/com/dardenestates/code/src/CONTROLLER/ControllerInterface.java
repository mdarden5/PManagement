/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dardenestates.code.src.CONTROLLER;

import com.google.gson.JsonObject;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Miltron
 */
public interface ControllerInterface {

    /**
     * validate verifies that there is data and it is the correct data type
     * @param parameterMap information to be validated
     * @param messages  messages that will be displayed to the user
     * @param dataSource connection with the database
     * @return a message if there is an error
     */
    public boolean validate(Map <String, String[]> parameterMap, List messages, MysqlDataSource dataSource );
    /**
     * processRequest takes the validated data and executes business logic
     * @param parameterMap takes user data needed for business logic
     * @param messages messages that will be displayed to user
     * @param dataSource connection with the database
     * @return if there is an error
     */
    public boolean processRequest(Map <String, String[]> parameterMap, List messages, MysqlDataSource dataSource);
    
    /**
     * 
     * @param jsonObject jsonServlet content
     * @param messages messages that will be displayed to the user
     * @param dataSource connection with the database
     * @return a message if there is an error
     */
    public boolean validate(JsonObject jsonObject, List messages, MysqlDataSource dataSource );
    /**
     * 
     * @param jsonObject jsonServlet content
     * @param messages messages that will be displayed to the user
     * @param dataSource connection with the database
     * @return if there is an error
     */
    public boolean processRequest(JsonObject jsonObject, List messages, MysqlDataSource dataSource);
}
