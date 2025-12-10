/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.models;

import java.time.LocalDateTime;
import javafx.collections.ObservableList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class PaymentTest {
    
    public PaymentTest() {
    }

    /**
     * Test of getPaymentID method, of class Payment.
     */
    @Test
    public void testGetPaymentID() {
        System.out.println("getPaymentID");
        Payment instance = null;
        int expResult = 0;
        int result = instance.getPaymentID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentID method, of class Payment.
     */
    @Test
    public void testSetPaymentID() {
        System.out.println("setPaymentID");
        int paymentID = 0;
        Payment instance = null;
        instance.setPaymentID(paymentID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderID method, of class Payment.
     */
    @Test
    public void testGetOrderID() {
        System.out.println("getOrderID");
        Payment instance = null;
        int expResult = 0;
        int result = instance.getOrderID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrderID method, of class Payment.
     */
    @Test
    public void testSetOrderID() {
        System.out.println("setOrderID");
        int orderID = 0;
        Payment instance = null;
        instance.setOrderID(orderID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMethodPayment method, of class Payment.
     */
    @Test
    public void testGetMethodPayment() {
        System.out.println("getMethodPayment");
        Payment instance = null;
        Payment.PaymentMethod expResult = null;
        Payment.PaymentMethod result = instance.getMethodPayment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMethodPayment method, of class Payment.
     */
    @Test
    public void testSetMethodPayment() {
        System.out.println("setMethodPayment");
        Payment.PaymentMethod methodPayment = null;
        Payment instance = null;
        instance.setMethodPayment(methodPayment);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTips method, of class Payment.
     */
    @Test
    public void testGetTips() {
        System.out.println("getTips");
        Payment instance = null;
        double expResult = 0.0;
        double result = instance.getTips();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTips method, of class Payment.
     */
    @Test
    public void testSetTips() {
        System.out.println("setTips");
        double tips = 0.0;
        Payment instance = null;
        instance.setTips(tips);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPaymentDate method, of class Payment.
     */
    @Test
    public void testGetPaymentDate() {
        System.out.println("getPaymentDate");
        Payment instance = null;
        LocalDateTime expResult = null;
        LocalDateTime result = instance.getPaymentDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPaymentDate method, of class Payment.
     */
    @Test
    public void testSetPaymentDate() {
        System.out.println("setPaymentDate");
        LocalDateTime paymentDate = null;
        Payment instance = null;
        instance.setPaymentDate(paymentDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of formatTips method, of class Payment.
     */
    @Test
    public void testFormatTips() {
        System.out.println("formatTips");
        Payment instance = null;
        String expResult = "";
        String result = instance.formatTips();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Payment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Payment instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Payment.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Payment instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Payment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Payment instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertPayment method, of class Payment.
     */
    @Test
    public void testInsertPayment() {
        System.out.println("insertPayment");
        int orderID = 0;
        Payment instance = null;
        boolean expResult = false;
        boolean result = instance.insertPayment(orderID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPayment method, of class Payment.
     */
    @Test
    public void testGetAllPayment() {
        System.out.println("getAllPayment");
        ObservableList<Payment> expResult = null;
        ObservableList<Payment> result = Payment.getAllPayment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePayment method, of class Payment.
     */
    @Test
    public void testUpdatePayment() {
        System.out.println("updatePayment");
        Payment instance = null;
        boolean expResult = false;
        boolean result = instance.updatePayment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePayment method, of class Payment.
     */
    @Test
    public void testDeletePayment() {
        System.out.println("deletePayment");
        Payment instance = null;
        boolean expResult = false;
        boolean result = instance.deletePayment();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
