package com.codecool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;


public abstract class Store implements StorageCapable {

    public List<Product> products = new ArrayList<>();


    private void saveToXml(){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Products.xml"));

            Element rootElement = doc.createElement("ProductsInStore");
            doc.appendChild(rootElement);

            for (int i = 0; i < getAllProduct().size() ; i++) {
                Element product1 = doc.createElement("Product");
                rootElement.appendChild(product1);
                Attr attr1 = doc.createAttribute("name");
                Attr attr2 = doc.createAttribute("price");
                Attr attr3 = doc.createAttribute("size");
                Attr attr4 = doc.createAttribute("type");
                attr1.setValue(getAllProduct().get(i).getName());
                attr2.setValue(Integer.toString(getAllProduct().get(i).getPrice()));
                if (getAllProduct().get(i) instanceof CDProduct) {
                    attr3.setValue(Integer.toString(((CDProduct) getAllProduct().get(i)).getNumOfTracks()));
                    attr4.setValue("cd");
                }else if(getAllProduct().get(i) instanceof BookProduct) {
                    attr3.setValue(Integer.toString(((BookProduct) getAllProduct().get(i)).getNumOfPages()));
                    attr4.setValue("book");
                }
                product1.setAttributeNode(attr1);
                product1.setAttributeNode(attr2);
                product1.setAttributeNode(attr3);
                product1.setAttributeNode(attr4);

                transformer.transform(source, result);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    protected void storeProduct(Product product){
        products.add(product);
        saveToXml();
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

    public List<Product> loadProducts(String filename) {
        try {

            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Product");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    if(eElement.getAttribute("type").equals("book")){
                        products.add(new BookProduct(eElement.getAttribute("name"),Integer.parseInt(eElement.getAttribute("price")),Integer.parseInt(eElement.getAttribute("size"))));

                    }else if (eElement.getAttribute("type").equals("cd")){
                        products.add(new CDProduct(Integer.parseInt(eElement.getAttribute("size")),eElement.getAttribute("name"),Integer.parseInt(eElement.getAttribute("price"))));
                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;

    }

    public void store(){
        saveToXml();


    }

    public List<Product> getAllProduct(){
        return products;
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
