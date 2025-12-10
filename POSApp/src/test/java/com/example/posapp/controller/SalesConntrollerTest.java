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
public class SalesConntrollerTest {
    
    public SalesConntrollerTest() {
    }

    /**
     * Test of initialize method, of class SalesConntroller.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        SalesConntroller instance = new SalesConntroller();
        instance.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadProfitLossPie method, of class SalesConntroller.
     */
    @Test
    public void testLoadProfitLossPie() {
        System.out.println("loadProfitLossPie");
        double totalNetProfit = 0.0;
        double totalCost = 0.0;
        double totalRevenue = 0.0;
        SalesConntroller instance = new SalesConntroller();
        instance.loadProfitLossPie(totalNetProfit, totalCost, totalRevenue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
