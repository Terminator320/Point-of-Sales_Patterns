package com.example.posapp.models;

import java.util.Objects;

public class MenuItem {
    private int id;
    private String name;
    private double price,costPrice;
    private Inventory inventory;

    public MenuItem(int id, String name, double price, double costPrice, Inventory inventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.costPrice = costPrice;
        this.inventory = inventory;
    }

    public MenuItem(int id, String name, double price, double costPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.costPrice = costPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return this.name;
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    //crud
}
