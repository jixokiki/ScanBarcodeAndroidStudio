package com.example.scanbarcode;

public class Semen {
    private String itemName;
    private int itemCount;

    private int itemCountPrice;

    private String itemBarcode; // Tambahkan atribut untuk menyimpan barcode item

    public Semen() {
        // Default constructor required for Firebase
    }

    public Semen(String itemName, String itemBarcode ,int itemCount, int itemCountPrice) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemBarcode = itemBarcode;
        this.itemCountPrice = itemCountPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemBarcode() { // Tambahkan metode untuk mendapatkan barcode item
        return itemBarcode;
    }

    public void setItemBarcode(String itemBarcode) { // Tambahkan metode untuk mengatur barcode item
        this.itemBarcode = itemBarcode;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemCountPrice() {
        return itemCountPrice;
    }

    public void setItemCountPrice(int itemCountPrice) {
        this.itemCountPrice = itemCountPrice;
    }
}
