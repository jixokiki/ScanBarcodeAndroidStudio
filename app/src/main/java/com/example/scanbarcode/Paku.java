package com.example.scanbarcode;

public class Paku {
    private String itemName;
    private int itemCount;
    private int itemPrice;

    public Paku() {
        // Default constructor required for Firebase
    }

    public Paku(String itemName, int itemCount, int itemPrice) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public int getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
