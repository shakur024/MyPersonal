/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FMUI;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author magicman
 */
public class UserIOConsoleImpl implements UserIO{
   Scanner scan = new Scanner(System.in);

    
    public void print(String message) {
        System.out.println(message);
    }

    
    public double readDouble(String prompt) {
        print(prompt);
        double a = Double.parseDouble(scan.nextLine());
        return a;
    }

    
    public double readDouble(String prompt, double min, double max) {
        do {
            print(prompt);
            double a = Double.parseDouble(scan.nextLine());
            if (a >= min && a <= max) {
                return a;
            } else {
                System.out.println("Number must between " + min + " and " + max);
            }
        } while (true);
    }

    
    public float readFloat(String prompt) {
        print(prompt);
        float a = Float.parseFloat(scan.nextLine());
        return a;
    }

    
    public float readFloat(String prompt, float min, float max) {
        do {
            print(prompt);
            float a = Float.parseFloat(scan.nextLine());
            if (a >= min && a <= max) {
                return a;
            } else {
                System.out.println("Number must be between " + min + " and " + max);
            }
        } while (true);

    }

   
    public int readInt(String prompt) {
        print(prompt);
        int a = Integer.parseInt(scan.nextLine());
        return a;
    }

   
    public int readInt(String prompt, int min, int max) {
        do {

            print(prompt);
            int a = Integer.parseInt(scan.nextLine());
            if (a >= min && a <= max) {
                return a;
            } else {
                System.out.println("Number must bebetween " + min + " and " + max);
            }
        } while (true);
    }

    
    public long readLong(String prompt) {
        print(prompt);
        long a = Long.parseLong(scan.nextLine());
        return a;
    }

    
    public BigDecimal readBigDecimal(String prompt) {
        print(prompt);
        BigDecimal a = BigDecimal.valueOf(Long.parseLong(scan.nextLine()));
        return a;
    }

  
    public long readLong(String prompt, long min, long max) {
        do {
            print(prompt);
            long a = Long.parseLong(scan.nextLine());
            if (a >= min && a <= max) {
                return a;
            } else {
                System.out.println("Number must be between " + min + "and " + max);
            }
        } while (true);
    }

   
    public String readString(String prompt) {
        print(prompt);
        String s = scan.nextLine();
        return s;
    }

   
    public LocalDate readDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        print(prompt);
        String s = scan.nextLine();
        LocalDate date = LocalDate.parse(s, formatter);
        return date;
    }
  
}
