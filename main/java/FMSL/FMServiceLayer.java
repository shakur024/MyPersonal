/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMSL;

import FMDAO.FMOrders;
import FMDAO.FMProducts;
import FMDAO.FMTaxes;
import FMDAOExceptions.FMExceptions;
import FMDTO.Order;
import FMDTO.Product;
import FMDTO.Taxes;
import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_HALF_UP;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author magicman
 */
public class FMServiceLayer {

    FMOrders orderDao;
    FMProducts productDao;
    FMTaxes taxDao;

    public FMServiceLayer(FMOrders orderDao, FMProducts productDao, FMTaxes taxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    public Order addOrder(Order newOrder) throws FMExceptions {
        orderDao.addOrder(newOrder);
        return newOrder;

    }

    public List<Order> getAllOrders(LocalDate date) throws FMExceptions {
        return orderDao.getAllOrders(date);
    }

    public Order getOrder(LocalDate date, int orderNumber) throws FMExceptions {
        return orderDao.getOrder(date, orderNumber);
    }

    public void removeOrder(LocalDate date, int orderNumber) throws FMExceptions {
        orderDao.removeOrder(date, orderNumber);
    }

    public Order editOrder(Order editOrder) throws FMExceptions {

        orderDao.editOrder(editOrder, editOrder.getDate());
        return editOrder;
    }

    public List<Product> getAllProducts() throws FMExceptions {
        return productDao.getAllProducts();
    }

    public Product getProducts(String productType) throws FMExceptions {
        return productDao.getProduct(productType);
    }

    public List<Taxes> getAllTaxes() throws FMExceptions {
        return taxDao.getAllTaxes();
    }

    public Taxes getTaxes(String state) throws FMExceptions {
        return taxDao.getTaxes(state);
    }

    // In this method we are calculating the price for our Order
    // We are getting the taxes, product from our DAO's and the 
    // values we set them in our files. We then do caluclations 
    // and set our Order object to the values we get and return it
    
    public Order getCalculation(Order o) throws FMExceptions {

        Product p = productDao.getProduct(o.getProductType());  // using my product Dao

        Taxes t = taxDao.getTaxes(o.getState()); // using my Tax

        BigDecimal cost = p.getCostPerSquareFoot();
        o.setCostPerSquareFoot(cost);

        BigDecimal labor = p.getLaborCostPerSqareFoot();
        o.setLaborCostPerSquareFoot(labor);

        BigDecimal tax = t.getTaxRate();
        o.setTaxRate(tax);

        BigDecimal TotalCost = cost.multiply(o.getArea()).setScale(2, ROUND_HALF_UP);
        o.setMaterialCost(TotalCost);

        BigDecimal TotalLabor = labor.multiply(o.getArea()).setScale(2, ROUND_HALF_UP);
        o.setLaborCost(TotalLabor);

        BigDecimal salesTax = tax.divide(new BigDecimal("100"));

        BigDecimal totalTax = TotalCost.add(TotalLabor).multiply(salesTax).setScale(2, ROUND_HALF_UP);
        o.setTax(totalTax);

        BigDecimal totalForOrder = TotalCost.add(TotalLabor).add(totalTax).setScale(2, ROUND_HALF_UP);
        o.setTotal(totalForOrder);

        return o;

    }

}
