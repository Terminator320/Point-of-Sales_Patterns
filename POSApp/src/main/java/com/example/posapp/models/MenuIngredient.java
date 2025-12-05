package com.example.posapp.models;

import com.example.posapp.LogConfig;
import database.ConfigManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuIngredient {
    private int menuItemId;      // PK, FK to menu_item.menuItem_id
    private Inventory inventory; // PK, FK to inventory.invId
    private int quantityUsed;    // how many units per one menu item

    private static final Logger LOGGER = LogConfig.getLogger(MenuIngredient.class.getName());

    public MenuIngredient(int menuItemId, Inventory inventory, int quantityUsed) {
        this.menuItemId = menuItemId;
        this.inventory = inventory;
        this.quantityUsed = quantityUsed;
    }


    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(int quantityUsed) {
        this.quantityUsed = quantityUsed;
    }


    // load all ingredients for one menu item
    public synchronized static List<MenuIngredient> getByMenuItemId(int menuItemId) {
        List<MenuIngredient> list = new ArrayList<>();

        final String sql = "SELECT ing.menu_item_id, ing.inv_id, ing.quantity_used ,inv.invName, inv.qty, inv.lowStockThreshold FROM menu_item_ingredient ing JOIN inventory inv ON inv.invId = ing.inv_id WHERE ing.menu_item_id = ?";

        try (Connection conn = ConfigManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, menuItemId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int invId = rs.getInt("inv_id");
                int qtyUsed = rs.getInt("quantity_used");

                String invName = rs.getString("invName");
                int invQty = rs.getInt("qty");
                int lowStockThreshold = rs.getInt("lowStockThreshold");

                Inventory inv = new Inventory(invId, invName, invQty, lowStockThreshold);

                list.add(new MenuIngredient(menuItemId, inv, qtyUsed));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage() + e.getCause() + " \nDB error while fetching ingredients for menu item " + menuItemId);
        }
        return list;
    }


}
