package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





public abstract class CsvStore implements StorageCapable {

    private List<List<String>> products = new ArrayList<>();


    protected void loadFromCsv(String csvFile,String splitter)throws FileNotFoundException, IOException{

        BufferedReader br = null;
        String line;


        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                products.add(Arrays.asList(line.split(splitter))) ;


                }
            for (List<String> product : products) {
                for (String s : product){
                    System.out.print(s+" ");
                }
                System.out.println();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void storeProduct(Product product){
        List<String> tmpList = new ArrayList<>();

        if(product instanceof CDProduct){
            CDProduct p = (CDProduct) product;
            tmpList.add(p.getName());
            tmpList.add(Integer.toString(p.getPrice()));
            tmpList.add(Integer.toString(p.getNumOfTracks()));
            tmpList.add("cd");
        }
        else{
            BookProduct p = (BookProduct) product;
            tmpList.add(p.getName());
            tmpList.add(Integer.toString(p.getPrice()));
            tmpList.add(Integer.toString(p.getNumOfPages()));
            tmpList.add("book");
        }

        products.add(tmpList);

    }

    protected Product createProduct(String type, String name, int price, int size){
        Product product;
        if(type.equals("Book")){
            product = new BookProduct(name,price,size);
            storeBookProduct(name,price,size);

        }else{
            product = new CDProduct(size,name,price);
            storeCDProduct(name,price,size);

        }
        return product;
    }

    public void saveToCsv(String filename) {
       try{loadFromCsv(filename,";");}catch (IOException e){e.printStackTrace();}
       try{
           FileWriter fw = new FileWriter(filename);
           for(List<String> ls : products){
               for(int s=0;s<ls.size();s++){
                   fw.append(ls.get(s));
                   if(s!=ls.size()-1){
                       fw.append(";");
                   }
               }
               fw.append("\n");
           }
           fw.flush();
           fw.close();
       }catch(IOException e){
           e.printStackTrace();
       }



    }

    public void store(){



    }

    public List<Product> getAllProduct(){
        return null;
    }

    public void storeCDProduct(String name, int price, int tracks){
        Product cd = new CDProduct(tracks,name,price);
        storeProduct(cd);
    }

    public void storeBookProduct(String name, int price, int pages){
        Product book = new BookProduct(name,price,pages);
        storeProduct(book);
    }
}
