/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.controller;

import com.example.posapp.models.SalesOrderItem;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class SalesOrderControllerTest {
    
    public SalesOrderControllerTest() {
    }

    /**
     * Test of getPopularItemsSaleMap method, of class SalesOrderController.
     */
    @Test
    public void testGetPopularItemsSaleMap() {
        System.out.println("getPopularItemsSaleMap");
        SalesOrderController instance = new SalesOrderController();
        HashMap<Integer, Integer> expResult = null;
        HashMap<Integer, Integer> result = instance.getPopularItemsSaleMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class SalesOrderController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        SalesOrderController instance = new SalesOrderController();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of innitAndLoadInventory method, of class SalesOrderController.
     */
    @Test
    public void testInnitAndLoadInventory() {
        System.out.println("innitAndLoadInventory");
        SalesOrderController instance = new SalesOrderController();
        instance.innitAndLoadInventory();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadOrder method, of class SalesOrderController.
     */
    @Test
    public void testLoadOrder() {
        System.out.println("loadOrder");
        ObservableList<SalesOrderItem> orderLines = null;
        SalesOrderController instance = new SalesOrderController();
        instance.loadOrder(orderLines);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of applyInventoryForCurrentOrder method, of class SalesOrderController.
     */
    @Test
    public void testApplyInventoryForCurrentOrder() {
        System.out.println("applyInventoryForCurrentOrder");
        SalesOrderController instance = new SalesOrderController();
        instance.applyInventoryForCurrentOrder();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refreshSubTotal method, of class SalesOrderController.
     */
    @Test
    public void testRefreshSubTotal() {
        System.out.println("refreshSubTotal");
        SalesOrderController instance = new SalesOrderController();
        instance.refreshSubTotal();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtotalAsDouble method, of class SalesOrderController.
     */
    @Test
    public void testGetSubtotalAsDouble() {
        System.out.println("getSubtotalAsDouble");
        SalesOrderController instance = new SalesOrderController();
        double expResult = 0.0;
        double result = instance.getSubtotalAsDouble();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalCostPrice method, of class SalesOrderController.
     */
    @Test
    public void testGetTotalCostPrice() {
        System.out.println("getTotalCostPrice");
        SalesOrderController instance = new SalesOrderController();
        double expResult = 0.0;
        double result = instance.getTotalCostPrice();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchButtonClick method, of class SalesOrderController.
     */
    @Test
    public void testSearchButtonClick() {
        System.out.println("searchButtonClick");
        SalesOrderController instance = new SalesOrderController();
        instance.searchButtonClick();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refreshButtonClick method, of class SalesOrderController.
     */
    @Test
    public void testRefreshButtonClick() {
        System.out.println("refreshButtonClick");
        SalesOrderController instance = new SalesOrderController();
        instance.refreshButtonClick();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeItem method, of class SalesOrderController.
     */
    @Test
    public void testRemoveItem() throws Exception {
        System.out.println("removeItem");
        ActionEvent event = null;
        SalesOrderController instance = new SalesOrderController();
        instance.removeItem(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancelOrder method, of class SalesOrderController.
     */
    @Test
    public void testCancelOrder() {
        System.out.println("cancelOrder");
        ActionEvent event = null;
        SalesOrderController instance = new SalesOrderController();
        instance.cancelOrder(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkOutClick method, of class SalesOrderController.
     */
    @Test
    public void testCheckOutClick() {
        System.out.println("checkOutClick");
        ActionEvent event = null;
        SalesOrderController instance = new SalesOrderController();
        instance.checkOutClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invalidSaleOrder method, of class SalesOrderController.
     */
    @Test
    public void testInvalidSaleOrder() {
        System.out.println("invalidSaleOrder");
        String title = "";
        String msg = "";
        SalesOrderController instance = new SalesOrderController();
        instance.invalidSaleOrder(title, msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
