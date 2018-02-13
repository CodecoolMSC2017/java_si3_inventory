package com.codecool;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        PersistentStore storage = new PersistentStore();
        StoreManager sManager = new StoreManager();
        sManager.addStorage(storage);
        storage.storeBookProduct("Hunger games",65,320);
        storage.storeCDProduct("In the end",30,21);
        storage.saveToCsv("products.csv");
        //sManager.addCDProduct("testAlbumName",60,24);
        //sManager.addBookProduct("testBookTitle",55,345);






    }
}
