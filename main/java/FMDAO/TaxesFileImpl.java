/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMDAO;

import FMDAOExceptions.FMExceptions;
import FMDTO.Taxes;
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
public class TaxesFileImpl implements FMTaxes {

    public static final String tax_file = "/Users/magicman/Documents/ShakurRepoPersonal/FlooringMastery/src/main/Resources/taxes.txt";

    public static final String DELIMETER = "::";

    List<Taxes> taxList = new ArrayList();

    @Override
    public List<Taxes> getAllTaxes() throws FMExceptions {
        loadTaxes();
        return new ArrayList<>(taxList);
    }

    @Override
    public Taxes getTaxes(String state) throws FMExceptions {
        List<Taxes> taxList = getAllTaxes();

        for (Taxes t : taxList) {
            if (t.getState().equals(state)) {
                return t;
            }

        }
        return null;
    }

    private void loadTaxes() throws FMExceptions {
      Scanner scanner = null;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(tax_file)));
        } catch (FileNotFoundException e) {
            throw new FMExceptions(
                    "-_- Could not load Tax data into memory.", e);
        }

        taxList.clear();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(DELIMETER);

            Taxes t = new Taxes();
            t.setState(tokens[0]);
            t.setTaxRate(new BigDecimal(tokens[1]));

            taxList.add(t);

        }

        scanner.close();
    }

}
