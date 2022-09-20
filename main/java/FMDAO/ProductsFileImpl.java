/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMDAO;

import FMDAOExceptions.FMExceptions;
import FMDTO.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author magicman
 */
public class ProductsFileImpl implements FMProducts {

    public static final String products_file = "/Users/magicman/Documents/ShakurRepoPersonal/FlooringMastery/src/main/Resources/products.txt";

    public static final String DELIMETER = "::";
    List<Product> productList = new ArrayList();

    public void loadProducts() throws FMExceptions {

        Scanner scanner = null;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(products_file)));
        } catch (FileNotFoundException e) {
            throw new FMExceptions(
                    "-_- Could not load Product data into memory.", e);
        }

       String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] tokens = line.split(DELIMETER);

            Product p = new Product();
            p.setProductType(tokens[0]);
            p.setCostPerSquareFoot(new BigDecimal(tokens[1]));
            p.setLaborCostPerSqareFoot(new BigDecimal(tokens[2]));

            productList.add(p);

        }

        scanner.close();
    }

    @Override
    public List<Product> getAllProducts() throws FMExceptions {
        loadProducts();
        return new ArrayList<>(productList);
    }

    @Override
    public Product getProduct(String productType) throws FMExceptions {
        List<Product> products = getAllProducts();

        for (Product p : products) {
            if (p.getProductType().equals(productType)) {
                return p;
            }

        }
        return null;
    }

}
