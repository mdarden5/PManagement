/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;





/**
 *
 * @author Miltron
 */
public class ConversionToJson {
  
    public JsonObject ConversionToJson(Map<String, String[]> parameterMap) throws IOException{
 /*       Gson gson = new Gson();
        String json = gson.toJson(parameterMap);
        return json; */
         
        JsonObject userInfo1 = new JsonObject();
        Map<String,String[]> userInfo = new HashMap();
        
        for(Entry<String,String[]> entry : parameterMap.entrySet()){
            userInfo.put(entry.getKey(), entry.getValue());
        }
        
//        userInfo.put("USERNAME",parameterMap.get("USERNAME"));
//        userInfo.put("PASSWORD",parameterMap.get("PASSWORD"));
//        userInfo.put("COMMANDKEY",parameterMap.get("COMMANDKEY"));
        
         Gson gson = new GsonBuilder().create();
        JsonObject userInfoJson = new JsonParser().parse(gson.toJson(userInfo)).getAsJsonObject();
        return userInfoJson;
        
        
        
        
    }
    
 
    
  
    
        
    
        
}

