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
public class MenuItemTest {
    
    public MenuItemTest() {
    }

    /**
     * Test of getMenuItemId method, of class MenuItem.
     */
    @Test
    public void testGetMenuItemId() {
        System.out.println("getMenuItemId");
        MenuItem instance = null;
        int expResult = 0;
        int result = instance.getMenuItemId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMenuItemId method, of class MenuItem.
     */
    @Test
    public void testSetMenuItemId() {
        System.out.println("setMenuItemId");
        int menuItemId = 0;
        MenuItem instance = null;
        instance.setMenuItemId(menuItemId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class MenuItem.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        MenuItem instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class MenuItem.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        MenuItem instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class MenuItem.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        MenuItem instance = null;
        double expResult = 0.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class MenuItem.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 0.0;
        MenuItem instance = null;
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCostPrice method, of class MenuItem.
     */
    @Test
    public void testGetCostPrice() {
        System.out.println("getCostPrice");
        MenuItem instance = null;
        double expResult = 0.0;
        double result = instance.getCostPrice();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCostPrice method, of class MenuItem.
     */
    @Test
    public void testSetCostPrice() {
        System.out.println("setCostPrice");
        double costPrice = 0.0;
        MenuItem instance = null;
        instance.setCostPrice(costPrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addIngredient method, of class MenuItem.
     */
    @Test
    public void testAddIngredient() {
        System.out.println("addIngredient");
        MenuIngredient ingredient = null;
        MenuItem instance = null;
        instance.addIngredient(ingredient);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIngredients method, of class MenuItem.
     */
    @Test
    public void testGetIngredients() {
        System.out.println("getIngredients");
        MenuItem instance = null;
        List<MenuIngredient> expResult = null;
        List<MenuIngredient> result = instance.getIngredients();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MenuItem.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MenuItem instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class MenuItem.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        MenuItem instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class MenuItem.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        MenuItem instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMenuItems method, of class MenuItem.
     */
    @Test
    public void testGetMenuItems() {
        System.out.println("getMenuItems");
        ObservableList<MenuItem> expResult = null;
        ObservableList<MenuItem> result = MenuItem.getMenuItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getById method, of class MenuItem.
     */
    @Test
    public void testGetById() {
        System.out.println("getById");
        int id = 0;
        MenuItem expResult = null;
        MenuItem result = MenuItem.getById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
