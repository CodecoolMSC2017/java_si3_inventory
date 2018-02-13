package com.codecool;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PersistentStore storage = new PersistentStore();
        StoreManager sManager = new StoreManager();
        sManager.addStorage(storage);
        //testing writing into CSV
        while(true) {
            System.out.println("What would you like to add ? (book/cd)");
            String choice = new Scanner(System.in).next();
            if (choice.equals("book")) {
                System.out.println("What is the title of the book ?");
                String title = new Scanner(System.in).next();
                System.out.println("How much does it cost ?");
                int price = new Scanner(System.in).nextInt();
                System.out.println("How many pages does it have ?");
                int pageNum = new Scanner(System.in).nextInt();
                storage.storeBookProduct(title, price, pageNum);
                break;
            } else if (choice.equals("cd")) {
                System.out.println("What is the title of the CD ?");
                String title = new Scanner(System.in).next();
                System.out.println("How much does it cost ?");
                int price = new Scanner(System.in).nextInt();
                System.out.println("How many tracks does it have ?");
                int trackNum = new Scanner(System.in).nextInt();
                storage.storeCDProduct(title, price, trackNum);
                break;
            } else {
                System.out.println("That's not an option");
            }
        }
        storage.saveToCsv("products.csv");







    }
}
