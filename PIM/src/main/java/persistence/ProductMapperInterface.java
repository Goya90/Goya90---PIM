package persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Products;


public interface ProductMapperInterface {
    
    public void jsonInsertOrUpdateToDB(ArrayList<Products> list, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void excelInsertOrUpdateToDB(ArrayList<Products> list, String propertyname) throws ClassNotFoundException, NumberFormatException, SQLException, IOException;
    
    public boolean checkIfProductExists(String productid, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String addProduct(ArrayList<Products> products, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String deleteProduct(int id, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Products> getSearchResults(int productid, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Products> showAllProducts(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Products> showSearchedProduct(String search, String attribute, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Products> dbDownload(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String bulkEditPublished(String attribute, boolean changeValue, ArrayList<Products> products, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String bulkEditProducts(String attribute, String changeValue, ArrayList<Products> products, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void editProduct(int id, Products product, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String getProductCountChart(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public int getProductCount(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String chartPublishedStatusDiagram(String propertyname) throws ClassNotFoundException, SQLException, IOException;
}
