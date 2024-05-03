package com.example.scanbarcode;

public class Item {
    private String itemName;
    private int itemCount;

    public Item() {
        // Default constructor required for Firebase
    }

    public Item(String itemName, int itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
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
}

