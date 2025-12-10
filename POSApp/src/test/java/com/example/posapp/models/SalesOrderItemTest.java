/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.models;

import java.util.List;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class SalesOrderItemTest {
    
    public SalesOrderItemTest() {
    }

    /**
     * Test of getSalesOrderItemId method, of class SalesOrderItem.
     */
    @Test
    public void testGetSalesOrderItemId() {
        System.out.println("getSalesOrderItemId");
        SalesOrderItem instance = null;
        int expResult = 0;
        int result = instance.getSalesOrderItemId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSalesOrderItemId method, of class SalesOrderItem.
     */
    @Test
    public void testSetSalesOrderItemId() {
        System.out.println("setSalesOrderItemId");
        int salesOrderItemId = 0;
        SalesOrderItem instance = null;
        instance.setSalesOrderItemId(salesOrderItemId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSalesOrderId method, of class SalesOrderItem.
     */
    @Test
    public void testGetSalesOrderId() {
        System.out.println("getSalesOrderId");
        SalesOrderItem instance = null;
        int expResult = 0;
        int result = instance.getSalesOrderId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSalesOrderId method, of class SalesOrderItem.
     */
    @Test
    public void testSetSalesOrderId() {
        System.out.println("setSalesOrderId");
        int salesOrderId = 0;
        SalesOrderItem instance = null;
        instance.setSalesOrderId(salesOrderId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMenuItemId method, of class SalesOrderItem.
     */
    @Test
    public void testGetMenuItemId() {
        System.out.println("getMenuItemId");
        SalesOrderItem instance = null;
        int expResult = 0;
        int result = instance.getMenuItemId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMenuItemId method, of class SalesOrderItem.
     */
    @Test
    public void testSetMenuItemId() {
        System.out.println("setMenuItemId");
        int menuItemId = 0;
        SalesOrderItem instance = null;
        instance.setMenuItemId(menuItemId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantityUsed method, of class SalesOrderItem.
     */
    @Test
    public void testGetQuantityUsed() {
        System.out.println("getQuantityUsed");
        SalesOrderItem instance = null;
        int expResult = 0;
        int result = instance.getQuantityUsed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantityUsed method, of class SalesOrderItem.
     */
    @Test
    public void testSetQuantityUsed() {
        System.out.println("setQuantityUsed");
        int quantityUsed = 0;
        SalesOrderItem instance = null;
        instance.setQuantityUsed(quantityUsed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMenuItem method, of class SalesOrderItem.
     */
    @Test
    public void testGetMenuItem() {
        System.out.println("getMenuItem");
        SalesOrderItem instance = null;
        MenuItem expResult = null;
        MenuItem result = instance.getMenuItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMenuItemsBySalesOrderID method, of class SalesOrderItem.
     */
    @Test
    public void testGetMenuItemsBySalesOrderID() {
        System.out.println("getMenuItemsBySalesOrderID");
        int salesOrderId = 0;
        ObservableList<SalesOrderItem> expResult = null;
        ObservableList<SalesOrderItem> result = SalesOrderItem.getMenuItemsBySalesOrderID(salesOrderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMenuItemToOrder method, of class SalesOrderItem.
     */
    @Test
    public void testAddMenuItemToOrder() {
        System.out.println("addMenuItemToOrder");
        int menuItemId = 0;
        int salesOrderId = 0;
        int quantityToAdd = 0;
        SalesOrderItem.addMenuItemToOrder(menuItemId, salesOrderId, quantityToAdd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeItemsBySalesOrderId method, of class SalesOrderItem.
     */
    @Test
    public void testRemoveItemsBySalesOrderId() {
        System.out.println("removeItemsBySalesOrderId");
        int salesOrderId = 0;
        SalesOrderItem.removeItemsBySalesOrderId(salesOrderId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemsForOrder method, of class SalesOrderItem.
     */
    @Test
    public void testGetItemsForOrder() {
        System.out.println("getItemsForOrder");
        int salesOrderId = 0;
        List<SalesOrderItem> expResult = null;
        List<SalesOrderItem> result = SalesOrderItem.getItemsForOrder(salesOrderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
