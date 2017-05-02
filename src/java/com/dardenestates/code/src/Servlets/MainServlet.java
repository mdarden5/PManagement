
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.dardenestates.code.src.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TemporaryView.WebSiteUnderConstructionPrinter;
import Util.ConversionToJson;
import Util.MakeDataSource;
import static Util.MakeDataSource.makeDataSource;
import Util.RequestObjectVariables;
import Util.RoutingEnum;
import com.dardenestates.code.src.CONTROLLER.ControllerInterface;
import com.dardenestates.code.src.MODELS.USERSDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.dardenestates.code.src.CONTROLLER.LoginCommand;
import com.dardenestates.code.src.CONTROLLER.RegistrationCommand;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miltron
 */
public class MainServlet extends HttpServlet {
    WebSiteUnderConstructionPrinter pagePrinter = new WebSiteUnderConstructionPrinter();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        

        
        try (PrintWriter out = response.getWriter()) {
            
            
            /* TODO output your page here. You may use following sample code. */

            //Extract Object request
            List <String> message = new ArrayList ();
            Map <String,String[]> userInfo =request.getParameterMap();
    //CONVERTED USER INFO 4/20    
            ConversionToJson convertToJson = new ConversionToJson(); 
            JsonObject jsonUserInfo =  convertToJson.ConversionToJson(userInfo);
            //Verify Object request
            Gson gson = new GsonBuilder().create();
         //   String stringkey = new String();
            String commandKey = jsonUserInfo.get(RequestObjectVariables.COMMANDKEY.getVariable()).toString();
           
            
            // Route Object Request to Executable Code
            
            // Build Object Response
            
          
            try {
            RoutingEnum  routingObject = RoutingEnum.valueOf(commandKey);
           } catch (Exception e){
                
                e.getStackTrace();
                System.out.println();
           }
            
            ControllerInterface command =  routingObject.getExecutable();
            String urlLocation = routingObject.getResponseURL();
           
            MakeDataSource makeDataSource = new MakeDataSource();
            
            if (command.validate(userInfo, message,makeDataSource() )){
                MysqlDataSource dataSource = new MysqlDataSource();
               if (command.processRequest(userInfo, message, dataSource)) {
                   response.sendRedirect(urlLocation);
               }
            }
           
            
            //comments are always apologies
            
            out.print(pagePrinter.getConstructionPage(message.toString()));
        } 
    
    }
    
//     protected static MysqlDataSource makeDataSource() {
//        MysqlDataSource dataSource = new MysqlDataSource();
//            dataSource.setUser("WEBAPP");
//            dataSource.setPassword("password");
//            dataSource.setServerName("localhost");
//            dataSource.setDatabaseName("propertymanagement");
//            dataSource.setPort(3306);
//            return dataSource;
//    }
}

//~ Formatted by Jindent --- http://www.jindent.com
