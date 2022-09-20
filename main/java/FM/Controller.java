/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FM;

import FMDAOExceptions.FMExceptions;
import FMDTO.Order;
import FMSL.FMServiceLayer;
import FMUI.FMView;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author magicman
 */
public class Controller {
    
    FMServiceLayer service;

    FMView view;
    
    public Controller (FMServiceLayer service, FMView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws FMExceptions {

        boolean Proceed = true;
        int menuOption = 0;
        while (Proceed) {

            menuOption = getMenuOption();

            switch (menuOption) {
                case 1:
                    DisplayOrders();
                    break;
                case 2:
                    AddOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    Proceed = false;
                    exitMessage();
                    break;
                default:
                    unknownCommand();
                    System.exit(0);
            }

        }

    }
    
    // We prompt the user and get the date they want an order of
    // We then pass in our getAllOrders() from our Service and pass the date
    // we then call our view method to Display our Order

    private void DisplayOrders() throws FMExceptions {
        LocalDate date = view.getDate();
        view.displayOrderList(service.getAllOrders(date));
    }

    // We prompt the user for Order information from our view class & set it to 
    // Order object. We call our Service and do our calculations for the Order
    // We then display our Order and then prompt the user if they want to add it to the file
    // if user enter y or Y then we add the Order
    
    private void AddOrder() throws FMExceptions {
        view.addOrderHeader();
        Order order = view.getOrderInfo();
        service.getCalculation(order);
        view.displayOrderList(Arrays.asList(order));
        if (view.getAnswer().equalsIgnoreCase("y")) {
            service.addOrder(order);
        }
    }

    // We first get the Date & orderNumber from the user
    // We then get call our Service and get the order with that date and number
    // if Order exists we prompt the user for new Order information & set it to order object
    // We then pass our date & number to our new Order object
    // Then we do our calculation and call our Service edit method while passing our edited order obhect
    // We then display our order
    
    private void editOrder() throws FMExceptions {
        LocalDate date = view.getDate();
        int orderNumber = view.getEditNumber();
        Order order = service.getOrder(date, orderNumber);
        if (order != null) {
            Order editedOrder = view.getEditInfo();
            editedOrder.setDate(date);
            editedOrder.setOrderNumber(orderNumber);
            service.getCalculation(editedOrder);
            service.editOrder(editedOrder);
            view.displayOrderList(Arrays.asList(editedOrder));
        }

    }

    // We get the date & orderNumber from the user
    // We call our Service getOrder method and pass in our date and ordernumber
    // then we call our removeOrder method from our Service and pass in our
    // Date and ordernumber to remove
    
    
    private void removeOrder() throws FMExceptions {
        LocalDate date = view.getDate();
        int number = view.getOrderNumber();
        Order order = service.getOrder(date, number);
        service.removeOrder(date, number);
    }

    private void unknownCommand() {
        view.displayUnknownCommand();
    }

    private int getMenuOption() {
        return view.printMenu();
    }
    
    private void exitMessage(){
        view.displayExitBanner();
    }

}
