/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.controller;

import com.example.posapp.models.MenuItem;
import com.example.posapp.models.SalesOrder;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 2480396
 */
public class SalesOrderControllerTest {
    
    public SalesOrderControllerTest() {
    }
    

    @Test
    public void testGetPopularItemsSaleMap() {
        System.out.println("getPopularItemsSaleMap");
        SalesOrderController instance = new SalesOrderController();
        HashMap<Integer, SalesOrder> expResult = null;
        HashMap<Integer, SalesOrder> result = instance.getPopularItemsSaleMap();
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
        Map<Integer, Integer> activeOrder = null;
        Map<Integer, MenuItem> menuItems = null;
        SalesOrderController instance = new SalesOrderController();
        instance.loadOrder(activeOrder, menuItems);
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
     * Test of searchButtonClick method, of class SalesOrderController.
     */
    @Test
    public void testSearchButtonClick() {
        System.out.println("searchButtonClick");
        ActionEvent event = null;
        SalesOrderController instance = new SalesOrderController();
        instance.searchButtonClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refreshButtonClick method, of class SalesOrderController.
     */
    @Test
    public void testRefreshButtonClick() {
        System.out.println("refreshButtonClick");
        ActionEvent event = null;
        SalesOrderController instance = new SalesOrderController();
        instance.refreshButtonClick(event);
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
    
}
