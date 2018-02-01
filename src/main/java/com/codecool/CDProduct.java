package com.codecool;

public class CDProduct extends Product {
    public int getNumOfTracks() {
        return numOfTracks;
    }

    private int numOfTracks;

    public CDProduct(int numOfTracks,String name,int price) {
        super(name, price);
        this.numOfTracks = numOfTracks;
    }
}
