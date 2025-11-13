package com.example.posapp.models;

import java.util.Objects;

public class MenuItem {
    private int id, invId,categoryId;
    private String name;
    private double price;

    public MenuItem(int invId, int categoryId, String name, double price) {
        this.invId = invId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
    }

    public MenuItem(int id, int invId, int categoryId, String name, double price) {
        this.id = id;
        this.invId = invId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return getId() == menuItem.getId() && getInvId() == menuItem.getInvId() && getCategoryId() == menuItem.getCategoryId() && Double.compare(getPrice(), menuItem.getPrice()) == 0 && Objects.equals(getName(), menuItem.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInvId(), getCategoryId(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", invId=" + invId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }



}
