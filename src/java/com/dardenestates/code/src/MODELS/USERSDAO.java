/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dardenestates.code.src.MODELS;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Miltron
 */
public class USERSDAO implements AutoCloseable{
    
    Connection conn;
    
    /**
     * dataSource 
     * @param dataSource
     */
    public  USERSDAO ( MysqlDataSource dataSource) throws SQLException {
    
    this.conn = dataSource.getConnection();
}
    @Override
    public void close () throws SQLException{
    conn.close();
    }
    
    /**
   * persists a user
   * @param userName DBDT: VARCHAR 25   
   * @param password DBDT: VARCHAR 25
   */
    public void insert(String userName, String password) throws SQLException {
         
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO usersLogin (username,password) VALUES(?,?)");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            stmt.executeUpdate();
    }      
    
    
    /**
     * checks password
     * @param userName
     * @param password
     * @return true or false if password match
     */
    public boolean validatePassword (String userName, String password) throws SQLException{
       
        PreparedStatement stmt = conn.prepareStatement( "SELECT 1 FROM usersLogin WHERE USERNAME=? AND PASSWORD =?");
        stmt.setString(1, userName);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
        
    }
    
    public boolean userNameAvailability (String userName) throws SQLException{
       
        PreparedStatement stmt = conn.prepareStatement( "SELECT 1 FROM usersLogin WHERE USERNAME=?");
        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();
        return !rs.next();
        
    }
    
}
