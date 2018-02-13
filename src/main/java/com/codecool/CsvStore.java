package com.codecool;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





public abstract class CsvStore implements StorageCapable {

    private List<List<String>> products = new ArrayList<>();


    protected void loadFromCsv(String csvFile){

        BufferedReader br = null;
        String line;


        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                products.add(Arrays.asList(line.split(";"))) ;


                }


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

    protected void storeProduct(Product product){
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

    public void saveToCsv(String filename) {
       loadFromCsv(filename);
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

    public List<Product> getAllProduct(){
        List<Product> tmpList = new ArrayList<>();
        for(List<String> ls : products){
            if(ls.get(3).equals("book")){
                tmpList.add(new BookProduct(ls.get(0),Integer.parseInt(ls.get(1)),Integer.parseInt(ls.get(2))));
            }else{
                tmpList.add(new CDProduct(Integer.parseInt(ls.get(1)),ls.get(0),Integer.parseInt(ls.get(2))));
            }
        }
        return tmpList;
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
