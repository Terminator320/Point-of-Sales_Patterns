/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.models;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class SalesOrderTest {
    
    public SalesOrderTest() {
    }

    /**
     * Test of getOrder_id method, of class SalesOrder.
     */
    @Test
    public void testGetOrder_id() {
        System.out.println("getOrder_id");
        SalesOrder instance = new SalesOrder();
        int expResult = 0;
        int result = instance.getOrder_id();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrder_id method, of class SalesOrder.
     */
    @Test
    public void testSetOrder_id() {
        System.out.println("setOrder_id");
        int order_id = 0;
        SalesOrder instance = new SalesOrder();
        instance.setOrder_id(order_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class SalesOrder.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        SalesOrder instance = new SalesOrder();
        String expResult = "";
        String result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class SalesOrder.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "";
        SalesOrder instance = new SalesOrder();
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreated_at method, of class SalesOrder.
     */
    @Test
    public void testGetCreated_at() {
        System.out.println("getCreated_at");
        SalesOrder instance = new SalesOrder();
        String expResult = "";
        String result = instance.getCreated_at();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCreated_at method, of class SalesOrder.
     */
    @Test
    public void testSetCreated_at() {
        System.out.println("setCreated_at");
        String created_at = "";
        SalesOrder instance = new SalesOrder();
        instance.setCreated_at(created_at);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinalized_at method, of class SalesOrder.
     */
    @Test
    public void testGetFinalized_at() {
        System.out.println("getFinalized_at");
        SalesOrder instance = new SalesOrder();
        String expResult = "";
        String result = instance.getFinalized_at();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFinalized_at method, of class SalesOrder.
     */
    @Test
    public void testSetFinalized_at() {
        System.out.println("setFinalized_at");
        String finalized_at = "";
        SalesOrder instance = new SalesOrder();
        instance.setFinalized_at(finalized_at);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtotal method, of class SalesOrder.
     */
    @Test
    public void testGetSubtotal() {
        System.out.println("getSubtotal");
        SalesOrder instance = new SalesOrder();
        double expResult = 0.0;
        double result = instance.getSubtotal();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubtotal method, of class SalesOrder.
     */
    @Test
    public void testSetSubtotal() {
        System.out.println("setSubtotal");
        double subtotal = 0.0;
        SalesOrder instance = new SalesOrder();
        instance.setSubtotal(subtotal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTax_total method, of class SalesOrder.
     */
    @Test
    public void testGetTax_total() {
        System.out.println("getTax_total");
        SalesOrder instance = new SalesOrder();
        double expResult = 0.0;
        double result = instance.getTax_total();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTax_total method, of class SalesOrder.
     */
    @Test
    public void testSetTax_total() {
        System.out.println("setTax_total");
        double tax_total = 0.0;
        SalesOrder instance = new SalesOrder();
        instance.setTax_total(tax_total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotal method, of class SalesOrder.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        SalesOrder instance = new SalesOrder();
        double expResult = 0.0;
        double result = instance.getTotal();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotal method, of class SalesOrder.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        double total = 0.0;
        SalesOrder instance = new SalesOrder();
        instance.setTotal(total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalCostPrice method, of class SalesOrder.
     */
    @Test
    public void testGetTotalCostPrice() {
        System.out.println("getTotalCostPrice");
        SalesOrder instance = new SalesOrder();
        double expResult = 0.0;
        double result = instance.getTotalCostPrice();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalCostPrice method, of class SalesOrder.
     */
    @Test
    public void testSetTotalCostPrice() {
        System.out.println("setTotalCostPrice");
        double totalCostPrice = 0.0;
        SalesOrder instance = new SalesOrder();
        instance.setTotalCostPrice(totalCostPrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItems method, of class SalesOrder.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        SalesOrder instance = new SalesOrder();
        List<SalesOrderItem> expResult = null;
        List<SalesOrderItem> result = instance.getItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setItems method, of class SalesOrder.
     */
    @Test
    public void testSetItems() {
        System.out.println("setItems");
        List<SalesOrderItem> items = null;
        SalesOrder instance = new SalesOrder();
        instance.setItems(items);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadItems method, of class SalesOrder.
     */
    @Test
    public void testLoadItems() {
        System.out.println("loadItems");
        SalesOrder instance = new SalesOrder();
        instance.loadItems();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSales method, of class SalesOrder.
     */
    @Test
    public void testGetAllSales() {
        System.out.println("getAllSales");
        List<SalesOrder> expResult = null;
        List<SalesOrder> result = SalesOrder.getAllSales();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSaleOrderInitial method, of class SalesOrder.
     */
    @Test
    public void testAddSaleOrderInitial() {
        System.out.println("addSaleOrderInitial");
        int expResult = 0;
        int result = SalesOrder.addSaleOrderInitial();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSale method, of class SalesOrder.
     */
    @Test
    public void testAddSale() {
        System.out.println("addSale");
        double subtotal = 0.0;
        double totalCostPrice = 0.0;
        int orderId = 0;
        int expResult = 0;
        int result = SalesOrder.addSale(subtotal, totalCostPrice, orderId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finalizeSale method, of class SalesOrder.
     */
    @Test
    public void testFinalizeSale() {
        System.out.println("finalizeSale");
        int order_id = 0;
        String status = "";
        String finalized_at = "";
        double subtotal = 0.0;
        double tax_total = 0.0;
        double total = 0.0;
        SalesOrder.finalizeSale(order_id, status, finalized_at, subtotal, tax_total, total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cancelledOrder method, of class SalesOrder.
     */
    @Test
    public void testCancelledOrder() {
        System.out.println("cancelledOrder");
        int order_id = 0;
        SalesOrder.cancelledOrder(order_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateQuantityItems method, of class SalesOrder.
     */
    @Test
    public void testUpdateQuantityItems() {
        System.out.println("updateQuantityItems");
        int id = 0;
        int quantity = 0;
        SalesOrder.updateQuantityItems(id, quantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sizeSalesOrder method, of class SalesOrder.
     */
    @Test
    public void testSizeSalesOrder() {
        System.out.println("sizeSalesOrder");
        int expResult = 0;
        int result = SalesOrder.sizeSalesOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
