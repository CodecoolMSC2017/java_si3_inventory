package com.codecool;

import java.util.List;

public class StoreManager{

    private StorageCapable storage;



    public void addStorage(StorageCapable storage){
        this.storage=storage;
    }

    public void addCDProduct(String name, int price, int tracks){
        storage.storeCDProduct(name,price,tracks);
    }

    public void addBookProduct(String name, int price, int pages){
        storage.storeBookProduct(name,price,pages);
    }

    public String listProducts(){
        String productList = "";
        for(int i=0;i<storage.getAllProduct().size();i++){
            productList += storage.getAllProduct().get(i).getName()+",";
        }

        return productList;
    }

    public int getTotalProductPrice(){
        int totalPrice = 0;
        for(int i=0;i<storage.getAllProduct().size();i++){
            totalPrice+= storage.getAllProduct().get(i).getPrice();
        }

        return totalPrice;
    }

    
}
