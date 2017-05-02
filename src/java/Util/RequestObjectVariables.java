/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Miltron
 */
public enum RequestObjectVariables {
    COMMANDKEY("COMMANDKEY"),USERNAME("USERNAME"), PASSWORD("PASSWORD");

   
    private final String  variableName;
    
    /**
     *
     */
    RequestObjectVariables (String variableName) {
      this.variableName = variableName;
      
    }
    
    public String getVariable () {
        
        return variableName;
    }
}
