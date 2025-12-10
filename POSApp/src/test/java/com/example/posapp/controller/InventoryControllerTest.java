/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.controller;

import com.example.posapp.models.Inventory;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class InventoryControllerTest {
    
    public InventoryControllerTest() {
    }

    /**
     * Test of initialize method, of class InventoryController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        InventoryController instance = new InventoryController();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of innitAndLoadInventory method, of class InventoryController.
     */
    @Test
    public void testInnitAndLoadInventory() {
        System.out.println("innitAndLoadInventory");
        InventoryController instance = new InventoryController();
        instance.innitAndLoadInventory();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchInventory method, of class InventoryController.
     */
    @Test
    public void testSearchInventory() {
        System.out.println("searchInventory");
        InventoryController instance = new InventoryController();
        instance.searchInventory();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearSearch method, of class InventoryController.
     */
    @Test
    public void testClearSearch() {
        System.out.println("clearSearch");
        InventoryController instance = new InventoryController();
        instance.clearSearch();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInventory method, of class InventoryController.
     */
    @Test
    public void testGetInventory() {
        System.out.println("getInventory");
        ObservableList<Inventory> expResult = null;
        ObservableList<Inventory> result = InventoryController.getInventory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
