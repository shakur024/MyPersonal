/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMUI;

import FMDTO.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author magicman
 */
public class FMView {

    private UserIO io;

    public FMView(UserIO io) {
        this.io = io;
    }

    public int printMenu() {
        io.print("1. Display Orders");
        io.print("2. Add an order");
        io.print("3. Edit an order");
        io.print("4. Remove an order");
        io.print("5. Exit");

        return io.readInt("please select from the choice", 1, 5);
    }

    public Order getOrderInfo() {

        
        String customerName = io.readString("Please enter your name");
        if (customerName.equals("")) {
            return null;
        }
 
        String state = io.readString("Please enter your State");
        String productType = io.readString("Please enter the product you want to get");
        BigDecimal area = io.readBigDecimal("please enter in your area");

        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        return currentOrder;

    }

    public Order editOrder() {
        Order newOrder = getEditInfo();
        return newOrder;
    }

    public Order getEditInfo() {

        String customerName = io.readString("Please enter your name");
        String state = io.readString("Please enter your State");
        String productType = io.readString("Please enter the product you want to purchase");
        BigDecimal area = io.readBigDecimal("please enter in your zipcode");

        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);

        return currentOrder;

    }

    public String getAnswer() {
        String response = io.readString("Would you like to commit (Y/N) ?");
        return response;
    }

    public LocalDate getDate() {
        LocalDate date = io.readDate("Please enter your order date here, must use MM-dd-yyyy format.");
        return date;
    }

    public int getOrderNumber() {
        System.out.println("\n");
        int orderNumber = io.readInt("Please Enter your Order Number");
        System.out.println("\n");
        return orderNumber;
    }

    public int getEditNumber() {
        int number = io.readInt("Please enter the number you want to edit");
        return number;
    }

    public void displayOrderList(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print(currentOrder.getOrderNumber() + "::"
                    + currentOrder.getCustomerName() + "::"
                    + currentOrder.getState() + "::" + currentOrder.getTaxRate() + "::" + currentOrder.getProductType()
                    + "::" + currentOrder.getArea() + "::" + currentOrder.getCostPerSquareFoot()
                    + "::" + currentOrder.getLaborCostPerSquareFoot() + "::" + currentOrder.getMaterialCost()
                    + "::" + currentOrder.getLaborCost() + "::" + currentOrder.getTax() + "::"
                    + currentOrder.getTotal());
        }
        io.readString("please hit enter to continue");

    }

    public void addOrderHeader() {
        io.print("***Add Order***");
    }

    public void displayUnknownCommand() {
        io.print("Unknown Command!!!");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

}
