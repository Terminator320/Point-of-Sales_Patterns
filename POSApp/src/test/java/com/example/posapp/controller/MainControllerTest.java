/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.controller;

import javafx.event.ActionEvent;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class MainControllerTest {
    
    public MainControllerTest() {
    }

    /**
     * Test of inventory method, of class MainController.
     */
    @Test
    public void testInventory() {
        System.out.println("inventory");
        ActionEvent event = null;
        MainController instance = new MainController();
        instance.inventory(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
