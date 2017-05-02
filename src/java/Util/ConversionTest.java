/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Miltron
 */
public class ConversionTest {
    public static void main (String args[]) throws IOException{
        
        Map <String, String[]> mapObject = new HashMap();
        Map <String, String> userinfo = new HashMap();
         
        
        String stringArr[] = {"ROOT"};
        String stringArr2[] = {"PROGRAM"};
        
        
        
        
        mapObject.put("USERNAME",stringArr );
        mapObject.put("PASSWORD", stringArr2);

        ConversionToJson convert2Json = new ConversionToJson();
       
       JsonObject x= convert2Json.ConversionToJson(mapObject);
       System.out.println(x);
    

    }
}
//JsonObject obj = new JsonParser().parse().getAsJsonObject();