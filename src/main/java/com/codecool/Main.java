package com.codecool;

public class Main {
    public static void main(String[] args) {

        PersistentStore storage = new PersistentStore();
        StoreManager sManager = new StoreManager();
        sManager.addStorage(storage);
        storage.loadProducts("Products.xml");
        sManager.addCDProduct("testAlbumName",60,24);
        sManager.addBookProduct("testBookTitle",55,345);
        storage.store();
        System.out.println(sManager.listProducts());
        System.out.println(sManager.getTotalProductPrice());




    }
}
