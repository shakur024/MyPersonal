/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMDAO;

import FMDAOExceptions.FMExceptions;
import FMDTO.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author magicman
 */
public class OrdersFileImpl implements FMOrders {

    Boolean prod;

    public static final String DELIMITER = "::";

    private static final String production_file = "mode.txt";

    List<Order> orderList = new ArrayList();

    ///In this method we load our files with the date we pass in the parameter
    /// We create a scanner object to read from the file, we create a convertToDate
    // and pass the data, we then loop through our file and create our Order object and
    // set the parameters. We add our object in the ArrayList, we catch any errors we find
    
    private List<Order> loadOrder(LocalDate date) throws FMExceptions {
        Scanner scanner;

        String newFile = changeToDate(date);

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(newFile)));

            orderList.clear();
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] tokens = line.split(DELIMITER);
                Order o = new Order();
                o.setOrderNumber(Integer.parseInt(tokens[0]));
                o.setCustomerName(tokens[1]);
                o.setState(tokens[2]);
                o.setTaxRate(new BigDecimal((tokens[3])));
                o.setProductType(tokens[4]);
                o.setArea(new BigDecimal(tokens[5]));
                o.setCostPerSquareFoot(new BigDecimal(tokens[6]));
                o.setLaborCostPerSquareFoot(new BigDecimal(tokens[7]));
                o.setMaterialCost(new BigDecimal(tokens[8]));
                o.setLaborCost(new BigDecimal(tokens[9]));
                o.setTax(new BigDecimal(tokens[10]));
                o.setTotal(new BigDecimal(tokens[11]));

                orderList.add(o);

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            throw new FMExceptions(
                    "Could not load library data into memory." + newFile, e);
        }

        return orderList;

    }

    // In this method we're passing a list of Order object & date object
    // We first create our printerWriter object, we then pass our date parameter
    // in convertToDate method. We loop through the Order List we passed and get the 
    // the object details. We close our printwriter after our for loop and catch any errors
    private void writeOrder(List<Order> writeList, LocalDate date) throws FMExceptions {

        PrintWriter out;

        String newFile = changeToDate(date);

        try {
            out = new PrintWriter(new FileWriter(newFile));

            for (Order currentorder : writeList) {
                out.println(currentorder.getOrderNumber() + DELIMITER
                        + currentorder.getCustomerName() + DELIMITER
                        + currentorder.getState() + DELIMITER
                        + currentorder.getTaxRate() + DELIMITER
                        + currentorder.getProductType() + DELIMITER
                        + currentorder.getArea() + DELIMITER
                        + currentorder.getCostPerSquareFoot() + DELIMITER
                        + currentorder.getLaborCostPerSquareFoot() + DELIMITER
                        + currentorder.getMaterialCost() + DELIMITER
                        + currentorder.getLaborCost() + DELIMITER
                        + currentorder.getTax() + DELIMITER
                        + currentorder.getTotal());

                out.flush();
            }

            out.close();

        } catch (IOException e) {
            throw new FMExceptions(
                    "Could not save data.", e);
        }

    }

    // This method converts the date we pass in the parameter
    // & formats it into format pattern and return our orderDate
    
    public String changeToDate(LocalDate date) {
        LocalDate dates = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String format = dates.format(formatter);
        return "Order" + "_" + format + ".txt";
    }

   
    public String getProd() throws FMExceptions {

        Scanner scan;

        try {

            scan = new Scanner(
                    new BufferedReader(
                            new FileReader(production_file)));
        } catch (FileNotFoundException e) {
            throw new FMExceptions(
                    "-_- Could not load the order", e);
        }

        String choice = scan.nextLine();
        if (choice.equals("PROD")) {
            prod = true;
        } else {
            prod = false;
        }

        return choice;
    }

    // In this method we pass an Order object to add to our List
    // We first modifty our order.setOrderNumber and pass in the size while 
    // increasing by 1. We then add our object into our List and write it to the file
    
    @Override
    public Order addOrder(Order order) throws FMExceptions {
        order.setOrderNumber(orderList.size() + 1);
        orderList.add(order);
        this.writeOrder(orderList, LocalDate.now());
        return order;
    }

    // This method we are passing the date and the order to get an order
    // We load our file with date we passed and set it to our List
    // We loop through our List and if we find the ordernumber we returned that order object
    
    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws FMExceptions {

        orderList = this.loadOrder(date);

        for (Order o : orderList) {
            if (o.getOrderNumber() == orderNumber) {
                return o;
            }
        }
        return null;
    }

    // In this method we are passing our date and ordernumber in the parameter
    // We check our files for the date we passed, we then loop and check for the ordernumber
    // pass in the parameter. We removed the Order object from our list and write to the file and return the object
    
    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws FMExceptions {

        orderList = this.loadOrder(date);

        for (Order order : orderList) {
            if (order.getOrderNumber() == orderNumber) {
                orderList.remove(order);
                this.writeOrder(orderList, LocalDate.now());
                return order;
            }

        }
        return null;
    }

    // In this method we are passing our Order object and date in our parameter
    // We set our index to zero at first and loop through our List
    // if our condition is true in the if the statement we remvoed the index from our List
    // Then we add the order object we passed in our parameter to our List and then write to the file
    // we then return our object and after we end the loop we increase our index 
    @Override
    public Order editOrder(Order order, LocalDate date) throws FMExceptions {

        orderList = this.loadOrder(date);

        int index = 0;
        for (Order orders : orderList) {
            if (orders.getOrderNumber() == order.getOrderNumber()) {
                orderList.remove(index);
                orderList.add(order);
                this.writeOrder(orderList, LocalDate.now());
                return order;
            }
            index++;

        }
        return null;
    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FMExceptions {
        List<Order> List = this.loadOrder(date);
        return List;
    }

}
