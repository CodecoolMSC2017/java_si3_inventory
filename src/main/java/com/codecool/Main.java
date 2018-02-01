package com.codecool;

public class Main {
    public static void main(String[] args) {

        PersistentStore storage = new PersistentStore();
        StoreManager sManager = new StoreManager();
        sManager.addStorage(storage);

        sManager.addCDProduct("In the end",60,24);
        sManager.addBookProduct("Harry Potter and the sorcerer's stone",55,345);
        storage.store();
        System.out.println(sManager.listProducts());
        System.out.println(sManager.getTotalProductPrice());



    }
}
