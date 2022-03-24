/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author solomon
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public void printInt(int num){
        System.out.println(num);
    }
    
      @Override
    public void printBigDecimal(BigDecimal num){
        System.out.println(num);
    }
   
    @Override
    public double readDouble(String prompt) {
        double doubleIn = 0;
        boolean isValid = true;
        
        while (isValid) {
            System.out.println(prompt);
            String stringIn = sc.nextLine();
            try {
                doubleIn = Double.parseDouble(stringIn);
                isValid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Sorry, that input is invalid.");
            }
        }
        return doubleIn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double doubleIn = 0;
        boolean isValid = true;
        
        while (isValid) {
        doubleIn = readDouble(prompt);
        if (doubleIn >= min && doubleIn <= max) {
            isValid = false;
            }
        }
        return doubleIn;
    }

    @Override
    public float readFloat(String prompt) {
        float floatIn = 0;
        boolean isValid = true;
        
        while (isValid) {
            System.out.println(prompt);
            String stringIn = sc.nextLine();
            try {
                floatIn = Float.parseFloat(stringIn);
                isValid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Sorry, that input is invalid.");
            }
        }
        return floatIn;    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float floatIn = 0;
        boolean isValid = true;
        
        while (isValid) {
        floatIn = readFloat(prompt);
        if (floatIn >= min && floatIn <= max) {
            isValid = false;
            }
        }
        return floatIn;
    }

    @Override
    public int readInt(String prompt) {
        int intIn = 0;
        boolean isValid = true;
        
        while (isValid) {
            System.out.println(prompt);
            String stringIn = sc.nextLine();
            try {
                intIn = Integer.parseInt(stringIn);
                isValid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Sorry, that input is invalid.");
            }
        }
        return intIn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int intIn = 0;
        boolean isValid = true;
        
        while (isValid) {
        intIn = readInt(prompt);
        if (intIn >= min && intIn <= max) {
            isValid = false;
            }
        }
        return intIn;
    }

    @Override
    public long readLong(String prompt) {
        long longIn = 0;
        boolean isValid = true;
        
        while (isValid) {
            System.out.println(prompt);
            String stringIn = sc.nextLine();
            try {
                longIn = Long.parseLong(stringIn);
                isValid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Sorry, that input is invalid.");
            }
        }
        return longIn;    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long longIn = 0;
        boolean isValid = true;
        
        while (isValid) {
        longIn = readLong(prompt);
        if (longIn >= min && longIn <= max) {
            isValid = false;
            }
        }
        return longIn;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String stringIn = sc.nextLine();
        return stringIn;
    }
    

} 