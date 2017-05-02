/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dardenestates.code.src.Servlets;

import static Util.MakeDataSource.makeDataSource;
import Util.RequestObjectVariables;
import Util.RoutingEnum;
import com.dardenestates.code.src.CONTROLLER.ControllerInterface;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






/**
 *
 * @author Miltron
 */
@WebServlet(name = "JsonServlet", urlPatterns = {"/JsonServlet"})
public class JsonServlet extends HttpServlet {

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
            throws ServletException, IOException {

        
        response.setContentType("application/json");
        
       
        BufferedReader reader = request.getReader();
        List <String> message = new ArrayList ();
        JsonObject jsonObject ;
        
        
        if(reader.ready()) {
            JsonParser jsonParser = new JsonParser();    

            try { jsonObject = jsonParser.parse(reader).getAsJsonObject();
            
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println( e.getStackTrace());
                jsonObject = new JsonObject();
                jsonObject.add("Exception_Code",new JsonPrimitive("malformed request structure"));
            }
            
        } else {
                jsonObject = new JsonObject();
                jsonObject.add("Exception_Code",new JsonPrimitive("Empty Request"));
                System.out.println(" Empty Request ");
        }
            try (PrintWriter out  = response.getWriter()) {
                    
                    out.write(jsonObject.toString());
            
            }
                    
                //extract the commandkey
                String commandKey = jsonObject.get("COMMANDKEY").getAsString();
                // route the command
                RoutingEnum routingEnum =   RoutingEnum.valueOf(commandKey);
                ControllerInterface command =  routingEnum.getExecutable();
                String urlLocation = routingEnum.getResponseURL();
                
                
                if (command.validate(jsonObject, message,makeDataSource())){
                    MysqlDataSource dataSource = new MysqlDataSource();
                    if (command.processRequest(jsonObject, message, dataSource)) {
                        response.sendRedirect(urlLocation);
               }    
            }
                
                //get the value of command
                //match it with the enum
        
        
        
        
        
        
        
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
