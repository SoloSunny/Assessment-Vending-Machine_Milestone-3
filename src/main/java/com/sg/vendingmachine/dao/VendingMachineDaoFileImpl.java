/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author solomon
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    public static String RECEIPT_FILE = "receipt.txt";
    public static final String DELIMITER = "::";

    private Map<String, Item> items = new HashMap<>();
    
    public VendingMachineDaoFileImpl() {   
    }
    
    public VendingMachineDaoFileImpl(String whichFile) {
        RECEIPT_FILE = whichFile;
    }

   
    @Override
    public List<Item> getAllItems()
            throws VendingMachinePersistenceException {
        loadReceipt();
        return new ArrayList<>(items.values());
    }

    @Override
    public Item getItem(String itemNumber)
            throws VendingMachinePersistenceException {
        loadReceipt();
        return items.get(itemNumber);
    }

    @Override
    public void purchaseItem(String itemNumber) 
        throws VendingMachinePersistenceException {
        
       Item item = getItem(itemNumber);
       item.purchaseItem();
        
        writeReceipt();
      
    }
    
    private void loadReceipt() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(RECEIPT_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load receipt data into memory.", e);
        }
        
        String currentLine;
       
        String[] currentTokens;
        
        while (scanner.hasNextLine()) {
          
            currentLine = scanner.nextLine();
       
            currentTokens = currentLine.split(DELIMITER);
            
            Item currentItem = new Item(currentTokens[0]);
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemPrice(new BigDecimal(currentTokens[2]));
            currentItem.setItemQuantity(Integer.parseInt(currentTokens[3]));
          
            items.put(currentItem.getItemNumber(), currentItem);
        }
  
        scanner.close();
    }
    
    private void writeReceipt() throws VendingMachinePersistenceException {
	    
	    PrintWriter out;
	    
	    try {
	        out = new PrintWriter(new FileWriter(RECEIPT_FILE));
	    } catch (IOException e) {
	        throw new VendingMachinePersistenceException(
	                "Could not save receipt data.", e);
	    }
	    
	 
	    List<Item> itemList = this.getAllItems();
	    for (Item currentItem : itemList) {
	     
	        out.println(currentItem.getItemNumber() + DELIMITER
	                + currentItem.getItemName() + DELIMITER
                        + currentItem.getItemPrice() + DELIMITER
	                + currentItem.getItemQuantity());
	      
	        out.flush();
	    }
	 
	    out.close();
	}
	

} 