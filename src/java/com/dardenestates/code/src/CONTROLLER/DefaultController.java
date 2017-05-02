/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dardenestates.code.src.CONTROLLER;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Miltron
 */
public abstract class DefaultController implements ControllerInterface {

    @Override
    public boolean validate(Map<String, String[]> parameterMap, List messages, MysqlDataSource dataSource) {
    return validateData(parameterMap, messages) && validateBusinessLogic(parameterMap, messages,dataSource);
    }

    @Override
    public boolean processRequest(Map<String, String[]> parameterMap, List messages, MysqlDataSource dataSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    protected boolean validateBusinessLogic(Map<String, String[]> parameterMap, List messages, MysqlDataSource dataSource) {
    return true;
    }
    protected boolean validateData(Map<String, String[]> parameterMap, List messages){
    return true;
    }
}
