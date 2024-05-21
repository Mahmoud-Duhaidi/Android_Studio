package com.example.birzeit_university_student.classes;

public class Product {

    private int productId;
    private String productName;
    private String productSize;
    private double productPrice;

    private int quantity ;

    public Product(int productId, String productName, String productSize, double productPrice , int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productSize = productSize;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
