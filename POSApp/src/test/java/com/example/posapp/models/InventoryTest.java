/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.models;

import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class InventoryTest {
    
    public InventoryTest() {
    }

    /**
     * Test of getInvId method, of class Inventory.
     */
    @Test
    public void testGetInvId() {
        System.out.println("getInvId");
        Inventory instance = null;
        int expResult = 0;
        int result = instance.getInvId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInvId method, of class Inventory.
     */
    @Test
    public void testSetInvId() {
        System.out.println("setInvId");
        int invId = 0;
        Inventory instance = null;
        instance.setInvId(invId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQty method, of class Inventory.
     */
    @Test
    public void testGetQty() {
        System.out.println("getQty");
        Inventory instance = null;
        int expResult = 0;
        int result = instance.getQty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQty method, of class Inventory.
     */
    @Test
    public void testSetQty() {
        System.out.println("setQty");
        int qty = 0;
        Inventory instance = null;
        instance.setQty(qty);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowStockThreshold method, of class Inventory.
     */
    @Test
    public void testGetLowStockThreshold() {
        System.out.println("getLowStockThreshold");
        Inventory instance = null;
        int expResult = 0;
        int result = instance.getLowStockThreshold();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLowStockThreshold method, of class Inventory.
     */
    @Test
    public void testSetLowStockThreshold() {
        System.out.println("setLowStockThreshold");
        int lowStockThreshold = 0;
        Inventory instance = null;
        instance.setLowStockThreshold(lowStockThreshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvName method, of class Inventory.
     */
    @Test
    public void testGetInvName() {
        System.out.println("getInvName");
        Inventory instance = null;
        String expResult = "";
        String result = instance.getInvName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInvName method, of class Inventory.
     */
    @Test
    public void testSetInvName() {
        System.out.println("setInvName");
        String invName = "";
        Inventory instance = null;
        instance.setInvName(invName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Inventory.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Inventory instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Inventory.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Inventory instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllInventory method, of class Inventory.
     */
    @Test
    public void testGetAllInventory() {
        System.out.println("getAllInventory");
        ObservableList<Inventory> expResult = null;
        ObservableList<Inventory> result = Inventory.getAllInventory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOne method, of class Inventory.
     */
    @Test
    public void testGetOne() {
        System.out.println("getOne");
        int invId = 0;
        Inventory expResult = null;
        Inventory result = Inventory.getOne(invId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editLowStoke method, of class Inventory.
     */
    @Test
    public void testEditLowStoke() {
        System.out.println("editLowStoke");
        int lowStockThreshold = 0;
        int invId = 0;
        Inventory.editLowStoke(lowStockThreshold, invId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editItemQTY method, of class Inventory.
     */
    @Test
    public void testEditItemQTY() {
        System.out.println("editItemQTY");
        int qty = 0;
        int invId = 0;
        Inventory.editItemQTY(qty, invId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of subtractQuantitySafe method, of class Inventory.
     */
    @Test
    public void testSubtractQuantitySafe() {
        System.out.println("subtractQuantitySafe");
        int invId = 0;
        int amount = 0;
        boolean expResult = false;
        boolean result = Inventory.subtractQuantitySafe(invId, amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
