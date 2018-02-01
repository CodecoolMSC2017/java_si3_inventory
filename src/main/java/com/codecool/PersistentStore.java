package com.codecool;

import java.util.List;

public class PersistentStore extends Store {
    public void storeProduct(Product product){
        products.add(product);
    }

}
