package logic;

import java.util.ArrayList;

public class Products {

    private final int ProductID;
    private final String ProductName;
    private final String ProductNameDescription;
    private final String ProductDescription;
    private final String CompanyName;
    private final double Price;
    private final int Quantity;
    private String PictureName;
    private final boolean PublishedStatus;
    private final String MinorCategory;
    private final String MainCategory;
    private static ArrayList<Products> productTempholder;

    public Products(int ProductID, String ProductName, String ProductNameDescription, 
             String ProductDescription, String CompanyName, double Price, int Quantity, 
             String PictureName, boolean PublishedStatus, String MinorCategory, String MainCategory) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductNameDescription = ProductNameDescription;
        this.ProductDescription = ProductDescription;
        this.CompanyName = CompanyName;
        this.Price = Price;
        this.Quantity = Quantity;
        this.PictureName = PictureName;
        this.PublishedStatus = PublishedStatus;
        this.MinorCategory = MinorCategory;
        this.MainCategory = MainCategory;
    }

    public String getName() {
        return ProductName;
    }

    public void setPictureName(String PictureName) {
        this.PictureName = PictureName;
    }

    public String getDescription() {
        return ProductDescription;
    }

    public String getNameDescription() {
        return ProductNameDescription;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getPictureName() {
        return PictureName;
    }

    public boolean getPublishedStatus() {
        return PublishedStatus;
    }

    public String getMinorCategory() {
        return MinorCategory;
    }

    public String getMainCategory() {
        return MainCategory;
    }

    public int getId() {
        return ProductID;
    }

    public double getPrice() {
        return Price;
    }

    public int getQty() {
        return Quantity;
    }

    public static ArrayList<Products> getProductTempholder() {
        return productTempholder;
    }

    public static void setProductTempholder(ArrayList<Products> productTempholder) {
        Products.productTempholder = productTempholder;
    }

    @Override
    public String toString() {
        return "\nOrderID: " + getId() + "\nName: " + getName() + "\nDescription: " + 
                getNameDescription() + "\nCompany: " + getCompanyName() + "\nPrice: " 
                + getPrice() + "\nQuantity: " + getQty() + "\nPicture name: " + 
                getPictureName() + "\npublished: " + getPublishedStatus() + 
                "\nMinor Category: " + getMinorCategory() + "\nMajor Category: " +
                getMainCategory();
    }
    
}
