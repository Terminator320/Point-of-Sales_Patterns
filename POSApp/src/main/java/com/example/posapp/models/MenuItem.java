package com.example.posapp.models;

import java.util.Objects;

public class MenuItem {
    private int id, invId;
    private String name;
    private double price;
    private Inventory inventory;


    public MenuItem(int id, int invId, String name, double price) {
        this.id = id;
        this.invId = invId;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    public MenuItem(int invId, String name, double price, Inventory inventory) {
        this.invId = invId;
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

    public int getInvId() {
        return invId;
    }

    public void setInvId(int invId) {
        this.invId = invId;
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

    //


}
