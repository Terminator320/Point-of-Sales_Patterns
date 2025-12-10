/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.controller;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class PaymentControllerTest {
    
    public PaymentControllerTest() {
    }

    /**
     * Test of setSalesOrderController method, of class PaymentController.
     */
    @Test
    public void testSetSalesOrderController() {
        System.out.println("setSalesOrderController");
        SalesOrderController controller = null;
        PaymentController instance = new PaymentController();
        instance.setSalesOrderController(controller);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class PaymentController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        PaymentController instance = new PaymentController();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSalesOrderTotal method, of class PaymentController.
     */
    @Test
    public void testSetSalesOrderTotal() {
        System.out.println("setSalesOrderTotal");
        SalesOrderController salesOrderController = null;
        int orderID = 0;
        PaymentController instance = new PaymentController();
        instance.setSalesOrderTotal(salesOrderController, orderID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
