package com.example.dhwanigrocerystore.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {

    // private static int totalPrice;
    String productName;
    //String productPrice;
    String currentDate;
    String currentTime;
    String totalQuantity;
    int totalPrice;
    String documentId;
    Integer overTotalAmount;
    public MyCartModel(){

    }

    public MyCartModel(String productName, String currentDate, String currentTime, String totalQuantity, int totalPrice,int  overTotalAmount) {
        this.productName = productName;
      //  this.productPrice = productPrice;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this. overTotalAmount= overTotalAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Integer getOverTotalAmount() {
        return overTotalAmount;
    }

    public void setOverTotalAmount(Integer overTotalAmount) {
        this.overTotalAmount = overTotalAmount;
    }
}
