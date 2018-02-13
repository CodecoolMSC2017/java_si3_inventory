package com.codecool;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CsvStoreTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadFromCsv() {
    PersistentStore pStore = new PersistentStore();
    pStore.loadFromCsv("testCSV.CSV");
    assertTrue(pStore.getAllProduct().size()!=0);

    }

    @Test
    void storeCDProduct(){
        PersistentStore pStore = new PersistentStore();
        pStore.storeCDProduct("Heart shaped box",35,20);
        assertTrue(pStore.getAllProduct().get(pStore.getAllProduct().size()-1) instanceof CDProduct);
    }

    @Test
    void storeBookProduct(){
        PersistentStore pStore = new PersistentStore();
        pStore.storeBookProduct("Daughter of Smoke and Bones",35,320);
        assertTrue(pStore.getAllProduct().get(pStore.getAllProduct().size()-1) instanceof BookProduct);
    }

    @Test
    void getAllProduct() {
        PersistentStore pStore = new PersistentStore();

        int beforeSize=pStore.getAllProduct().size();
        //Adding 1 item to check if it really adds item
        pStore.storeCDProduct("Eminem",54,60);
        //Checks if the size of the ArrayList's size is not the same before addition
        assertTrue(pStore.getAllProduct().size()!=beforeSize);


        beforeSize=pStore.getAllProduct().size();
        pStore.storeCDProduct("In the end",50,21);
        //Checks if after 2 additions the size of the arrayList is 2
        assertTrue(pStore.getAllProduct().size()!=beforeSize);
        assertTrue(pStore.getAllProduct().size()==2);
    }
}