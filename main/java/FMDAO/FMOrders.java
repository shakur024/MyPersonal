/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FMDAO;

import FMDAOExceptions.FMExceptions;
import FMDTO.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author magicman
 */
public interface FMOrders {
        
    public Order addOrder( Order order) throws FMExceptions;
    public Order getOrder( LocalDate date, int orderNumber) throws FMExceptions;
    public Order removeOrder( LocalDate date, int orderNumber) throws FMExceptions;
    public Order editOrder( Order order, LocalDate date) throws FMExceptions;
    public List<Order> getAllOrders(LocalDate date) throws FMExceptions;
}
