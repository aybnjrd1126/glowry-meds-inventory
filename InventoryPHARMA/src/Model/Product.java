/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author jawed
 */
public class Product {

    private String code;
    private String prodname;
    private int qty;
    private double price;
    private String category;
    private String expiryDate;

    public Product(String code, String prodname, int qty, double price, String category, String expiryDate) {
        this.code = code;
        this.prodname = prodname;
        this.qty = qty;
        this.price = price;
        this.category = category;
        this.expiryDate = expiryDate;
    }

    public String getCode() { return code; }
    public String getProdname() { return prodname; }
    public int getQty() { return qty; }
    public double getPrice() { return price; }
    public String getCategory() {return category; }
    public String getExpiryDate() {return expiryDate;}
}
