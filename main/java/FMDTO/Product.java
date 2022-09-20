/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMDTO;

import java.math.BigDecimal;

/**
 *
 * @author magicman
 */
public class Product {
      
    String productType;
   
    BigDecimal CostPerSquareFoot;
   
    BigDecimal LaborCostPerSqareFoot;
    
    public Product(){
        
    }
    
    public Product (String productType) {
        this.productType = productType; 
    } 

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal CostPerSquareFoot) {
        this.CostPerSquareFoot = CostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSqareFoot() {
        return LaborCostPerSqareFoot;
    }

    public void setLaborCostPerSqareFoot(BigDecimal LaborCostPerSqareFoot) {
        this.LaborCostPerSqareFoot = LaborCostPerSqareFoot;
    }
 
}
