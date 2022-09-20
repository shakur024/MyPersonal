/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author magicman
 */
public class Order {

    int OrderNumber;
    String CustomerName;
    String State;
    BigDecimal TaxRate;
    String ProductType;
    BigDecimal Area;
    BigDecimal CostPerSquareFoot;
    BigDecimal LaborCostPerSquareFoot;
    BigDecimal MaterialCost;
    BigDecimal LaborCost;
    BigDecimal Tax;
    BigDecimal Total;
    LocalDate date;

    public Order() {
       
    }

    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal TaxRate) {
        this.TaxRate = TaxRate;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public BigDecimal getArea() {
        return Area;
    }

    public void setArea(BigDecimal Area) {
        this.Area = Area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal CostPerSquareFoot) {
        this.CostPerSquareFoot = CostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = LaborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return MaterialCost;
    }

    public void setMaterialCost(BigDecimal MaterialCost) {
        this.MaterialCost = MaterialCost;
    }

    public BigDecimal getLaborCost() {
        return LaborCost;
    }

    public void setLaborCost(BigDecimal LaborCost) {
        this.LaborCost = LaborCost;
    }

    public BigDecimal getTax() {
        return Tax;
    }

    public void setTax(BigDecimal Tax) {
        this.Tax = Tax;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
