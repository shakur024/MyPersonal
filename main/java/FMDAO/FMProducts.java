/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FMDAO;

import FMDAOExceptions.FMExceptions;
import FMDTO.Product;
import java.util.List;

/**
 *
 * @author magicman
 */
public interface FMProducts {
        
 public List<Product> getAllProducts() throws FMExceptions;
 
 public Product getProduct(String productType)throws FMExceptions;   
    
}
