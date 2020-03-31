package persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Categories;
import logic.Products;

public interface Facade { 
    
    public void jsonInsertOrUpdateToDB(ArrayList<Products> products, String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public void excelInsertOrUpdateToDB(ArrayList<Products> product, String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public String addProduct(ArrayList<Products> products, String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public String addMainCategory (String category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String addMinorCategory (String category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void deleteMainCategory (int category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void deleteMinorCategory (int category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void editMainCategory (int categoryInt, String categoryStr, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void editMinorCategory (int categoryInt, String categoryStr, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Categories> getMainCategories (String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Categories> getMinorCategories (String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public int getMainCategoriesID(String mainname, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public int getMinorCategoriesID(String mainname, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Products> searchProduct(int i, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Products> showAllProducts(String propertyname) throws SQLException, ClassNotFoundException, IOException;
        
    public void editProduct(int id, Products product, String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public String deleteProduct(int id, String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public ArrayList<Products> showSearchedProduct(String search, String attribute, String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public ArrayList<Products> dbDownload(String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public String bulkEditPublished(String attribute, boolean changeValue, ArrayList<Products> products, String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public String bulkEditProducts(String attribute, String changeValue, ArrayList<Products> products, String propertyname) throws SQLException, ClassNotFoundException, IOException;
    
    public String chartMinorCategory(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String chartMainCategory(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String chartPublishedStatusDiagram(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String getProductCountChart(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void checkOrCreateLinkminormain(int mainid, int minorid, String propertyname) throws ClassNotFoundException, SQLException, IOException;
}
