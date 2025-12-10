/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.example.posapp.models;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GeorgeVogas
 */
public class PopularItemsTest {
    
    public PopularItemsTest() {
    }

    /**
     * Test of getPopular_items_id method, of class PopularItems.
     */
    @Test
    public void testGetPopular_items_id() {
        System.out.println("getPopular_items_id");
        PopularItems instance = null;
        int expResult = 0;
        int result = instance.getPopular_items_id();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPopular_items_id method, of class PopularItems.
     */
    @Test
    public void testSetPopular_items_id() {
        System.out.println("setPopular_items_id");
        int popular_items_id = 0;
        PopularItems instance = null;
        instance.setPopular_items_id(popular_items_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPopular_items_name method, of class PopularItems.
     */
    @Test
    public void testGetPopular_items_name() {
        System.out.println("getPopular_items_name");
        PopularItems instance = null;
        String expResult = "";
        String result = instance.getPopular_items_name();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPopular_items_name method, of class PopularItems.
     */
    @Test
    public void testSetPopular_items_name() {
        System.out.println("setPopular_items_name");
        String popular_items_name = "";
        PopularItems instance = null;
        instance.setPopular_items_name(popular_items_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPopular_items_quantity method, of class PopularItems.
     */
    @Test
    public void testGetPopular_items_quantity() {
        System.out.println("getPopular_items_quantity");
        PopularItems instance = null;
        int expResult = 0;
        int result = instance.getPopular_items_quantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPopular_items_quantity method, of class PopularItems.
     */
    @Test
    public void testSetPopular_items_quantity() {
        System.out.println("setPopular_items_quantity");
        int popular_items_quantity = 0;
        PopularItems instance = null;
        instance.setPopular_items_quantity(popular_items_quantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of popularItemsFinder method, of class PopularItems.
     */
    @Test
    public void testPopularItemsFinder() {
        System.out.println("popularItemsFinder");
        HashMap<String, Integer> expResult = null;
        HashMap<String, Integer> result = PopularItems.popularItemsFinder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
