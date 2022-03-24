/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author solomon
 */
public class VendingMachineServiceLayerTest {
    
    VendingMachineDao dao = new VendingMachineDaoFileImpl("testreceipt.txt");
    File file = new File("testreceipt.txt");
    
    VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
    Change change = new Change();
    VendingMachineServiceLayer service 
            = new VendingMachineServiceLayerImpl(dao, auditDao, change);
    Item item = new Item("1");
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Before
    public void setUp() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("testreceipt.txt"), "utf-8"))) {
            writer.write("1::Twix::1.50::10"
                    + "\n2::Chips::2.75::10"
                    + "\n3::Coke::1.00::10"
                    + "\n4::Water::2.50::10");
        }
        
        item.setItemName("Twix");
        item.setItemPrice(new BigDecimal ("1.50"));
        item.setItemQuantity(10);
    }
    
    
    @After
    public void tearDown() {
        file.delete();
    
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllItems() throws Exception {
        List<Item> allItems = service.getAllItems();
        assertEquals(4, allItems.size());
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetItem() throws Exception {
          assertEquals(item, service.getItem("1"));
    }

    /**
     * Test of purchaseItem method, of class VendingMachineServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testPurchaseItem() throws Exception {
      String purchaseItem = service.purchaseItem("1", new BigDecimal("3.00"));
       assertEquals("You're receiving 1 Dollars "
               + "\nYou're receiving 2 Quarters "
               + "\nYou're receiving 0 Dimes "
               + "\nYou're receiving 0 Nickels "
               + "\nYou're receiving 0 Pennies", purchaseItem);
    }
    
    @Test 
    public void testPurchaseItemInsufficientFundsExp() throws Exception {
        try{
          service.purchaseItem("1", new BigDecimal("1.45"));
        }catch (InsufficientFundsException e) {
        }
    }
    
    @Test
    public void testNoInventoryExp() throws Exception {
       try{
       service.purchaseItem("3", new BigDecimal("5.00"));
       } catch (NoItemInventoryException e){
           
       }
    }
    
}