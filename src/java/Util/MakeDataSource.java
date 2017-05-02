/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author Miltron
 */
public class MakeDataSource {
    
    public static MysqlDataSource makeDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("WEBAPP");
            dataSource.setPassword("password");
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("propertymanagement");
            dataSource.setPort(3306);
            return dataSource;
    }
}
