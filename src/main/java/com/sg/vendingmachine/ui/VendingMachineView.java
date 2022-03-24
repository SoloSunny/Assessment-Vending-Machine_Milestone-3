/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author solomon
 */
public class VendingMachineView {

   private UserIO io;
   private Change change;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("VENDING MACHINE - Welcome!");
        io.print("1. PURCHASE Item");
        io.print("2. LIST Items");
        io.print("3. EXIT");

        return io.readInt("Please choose from the above selection .", 1, 3);
    }

    public void displayItemList(List<Item> itemList) {
       for (Item currentItem : itemList) {
            if (currentItem.getItemQuantity()>0)
                
            io.print(currentItem.getItemNumber() + " "
                    + currentItem.getItemName() + " "
                    + currentItem.getItemPrice() + " "
                    + currentItem.getItemQuantity());
        }
    
        io.readString("Press ENTER to continue.");
    }
    
    public void displayItem(Item item) {
	    if (item != null) {
	        io.print(item.getItemNumber());
	        io.print(item.getItemName());
	        io.printBigDecimal(item.getItemPrice());
                io.printInt(item.getItemQuantity());
	        io.print("");
	    } else {
	        io.print("No such Item in this Vending Machine.");
	    }
	    io.readString("Press ENTER to continue.");
	}

    public void displayItemBanner() {
        io.print("*** ITEM ***");
    }
    
    public void displayItemPurchaseBanner() {
        io.print("*** PURCHASE ITEM ***");
    }
    
    public String getItemNumberChoice() {
        int choice = io.readInt("Please ENTER the Item Number.", 1, 4);
        String stringChoice = String.valueOf(choice);
        return stringChoice; 
    }

    public String displayThankYouPurchase() {
        return io.readString("THANK YOU for purchasing, ENJOY!");
    }

    public void displayDisplayAllBanner() {
        io.print("*** ALL ITEMS ***");
    }

    public void displayExitBanner() {
        io.print("SEE YOU!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }
    
    public BigDecimal displayRequestDeposit(){
        Double cash = io.readDouble("Please INSERT coins.");
        BigDecimal bd = new BigDecimal(cash);
        
        return bd; 
    }
    
    public void displayDepositSuccessful(){
        io.print("Coin inserted successfully.");
    }
    
    public void displayErrorMessage(String errorMsg) {
	    io.print("*** ERROR - Please try again ***");
	    io.print(errorMsg);
	}
   
    public void displayChange(String change) {
        io.print("Your change is " + change);
    }

} 