/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.dardenestates.code.src.CONTROLLER.ControllerInterface;
import com.dardenestates.code.src.CONTROLLER.LoginCommand;
import com.dardenestates.code.src.CONTROLLER.RegistrationCommand;
import java.util.function.Supplier;

/**
 *
 * @author Miltron
 */
public enum RoutingEnum {
   LOGIN(()-> new LoginCommand(),"http://localhost:8084/PManagement/Home.html"), NEWREGISTRATION(()->new RegistrationCommand(),"http://localhost:8084/PManagement/SuccessLogin.html");
   Supplier <ControllerInterface>  supplier;
   String urlLocation;
    //C
   RoutingEnum (Supplier <ControllerInterface> supplier, String urlLocation) {
       this.supplier = supplier;
       this.urlLocation = urlLocation;
   };
   //supplier function is a anonymous function that returns a value

    
//Storage interface
   //removes state
    /**
     *
     * @return
     */
   public ControllerInterface getExecutable() {
        
       return supplier.get();
    }
   public String getResponseURL(){
       return urlLocation;
   }
}
