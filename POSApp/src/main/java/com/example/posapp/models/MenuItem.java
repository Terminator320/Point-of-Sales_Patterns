package com.example.posapp.models;

import java.util.Objects;

public class MenuItem {
    private int id, itemCode;
    private String name;
    private double price;
    private Inventory inventory;

    public MenuItem(int itemCode,String name,double price,Inventory inventory) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    public MenuItem(int id, int itemCode, String name, double price) {
        this.id = id;
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return name;

    }
}
