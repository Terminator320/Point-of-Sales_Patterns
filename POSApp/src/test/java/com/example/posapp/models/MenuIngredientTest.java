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
public class MenuIngredientTest {
    
    public MenuIngredientTest() {
    }

    /**
     * Test of getMenuItemId method, of class MenuIngredient.
     */
    @Test
    public void testGetMenuItemId() {
        System.out.println("getMenuItemId");
        MenuIngredient instance = null;
        int expResult = 0;
        int result = instance.getMenuItemId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMenuItemId method, of class MenuIngredient.
     */
    @Test
    public void testSetMenuItemId() {
        System.out.println("setMenuItemId");
        int menuItemId = 0;
        MenuIngredient instance = null;
        instance.setMenuItemId(menuItemId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInventory method, of class MenuIngredient.
     */
    @Test
    public void testGetInventory() {
        System.out.println("getInventory");
        MenuIngredient instance = null;
        Inventory expResult = null;
        Inventory result = instance.getInventory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInventory method, of class MenuIngredient.
     */
    @Test
    public void testSetInventory() {
        System.out.println("setInventory");
        Inventory inventory = null;
        MenuIngredient instance = null;
        instance.setInventory(inventory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantityUsed method, of class MenuIngredient.
     */
    @Test
    public void testGetQuantityUsed() {
        System.out.println("getQuantityUsed");
        MenuIngredient instance = null;
        int expResult = 0;
        int result = instance.getQuantityUsed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantityUsed method, of class MenuIngredient.
     */
    @Test
    public void testSetQuantityUsed() {
        System.out.println("setQuantityUsed");
        int quantityUsed = 0;
        MenuIngredient instance = null;
        instance.setQuantityUsed(quantityUsed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByMenuItemId method, of class MenuIngredient.
     */
    @Test
    public void testGetByMenuItemId() {
        System.out.println("getByMenuItemId");
        int menuItemId = 0;
        List<MenuIngredient> expResult = null;
        List<MenuIngredient> result = MenuIngredient.getByMenuItemId(menuItemId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
