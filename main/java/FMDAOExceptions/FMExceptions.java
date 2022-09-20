/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMDAOExceptions;

/**
 *
 * @author magicman
 */
public class FMExceptions extends Exception {
    
    public FMExceptions (String message) {
        super(message);
    }
    
    public FMExceptions (String message, Throwable cause) {
        super(message, cause);
    }
    
}


