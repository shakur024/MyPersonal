/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FMDAO;

import FMDAOExceptions.FMExceptions;
import FMDTO.Taxes;
import java.util.List;

/**
 *
 * @author magicman
 */
public interface FMTaxes {

    public List<Taxes> getAllTaxes() throws FMExceptions;

    public Taxes getTaxes(String state) throws FMExceptions;

}
