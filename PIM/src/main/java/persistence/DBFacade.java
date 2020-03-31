package persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Categories;
import logic.Products;

public class DBFacade implements Facade {
    ProductMapper productMapper = new ProductMapper();
    CategoryMapper categoryMapper = new CategoryMapper();

    @Override
    public void jsonInsertOrUpdateToDB(ArrayList<Products> products, String propertyname) throws SQLException, ClassNotFoundException, IOException {
        productMapper.jsonInsertOrUpdateToDB(products, propertyname);
    }

    @Override
    public void excelInsertOrUpdateToDB(ArrayList<Products> product, String propertyname) throws SQLException, ClassNotFoundException, IOException {
        productMapper.excelInsertOrUpdateToDB(product, propertyname);
    }

    @Override
    public String addProduct(ArrayList<Products> products, String propertyname) throws SQLException, ClassNotFoundException, IOException {
        return productMapper.addProduct(products, propertyname);
    } 

    @Override
    public String addMainCategory(String category, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return categoryMapper.addMainCategory(category, propertyname);
    }

    @Override
    public String addMinorCategory(String category, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return categoryMapper.addMinorCategory(category, propertyname);
    }

    @Override
    public void deleteMainCategory(int category, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        categoryMapper.deleteMainCategory(category, propertyname);
    }

    @Override
    public void deleteMinorCategory(int category, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        categoryMapper.deleteMinorCategory(category, propertyname);
    }

    @Override
    public void editMainCategory(int categoryInt, String categoryStr, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        categoryMapper.editMainCategory(categoryInt, categoryStr, propertyname);
    }

    @Override
    public void editMinorCategory(int categoryInt, String categoryStr, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        categoryMapper.editMinorCategory(categoryInt, categoryStr, propertyname);
    }

    @Override
    public ArrayList<Categories> getMainCategories(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return categoryMapper.getMainValuesFromDB(propertyname);
    }

    @Override
    public ArrayList<Categories> getMinorCategories(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return categoryMapper.getMinorValuesFromDB(propertyname);
    }
    
    @Override
    public int getMainCategoriesID(String mainname, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return categoryMapper.getMainValuesFromDBFile(mainname, propertyname);
    }
    
    @Override
    public int getMinorCategoriesID(String mainname, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return categoryMapper.getMinorValuesFromDBFile(mainname, propertyname);
    }
    
    @Override
    public ArrayList<Products> searchProduct(int i, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return productMapper.getSearchResults(i, propertyname);
    }

    @Override
    public ArrayList<Products> showAllProducts(String propertyname) throws SQLException, ClassNotFoundException, IOException {
        return productMapper.showAllProducts(propertyname);
    }
    
    @Override
    public void editProduct (int id, Products product, String propertyname) throws SQLException, ClassNotFoundException, IOException {
        productMapper.editProduct(id, product, propertyname);
    } 
    
    @Override
    public String deleteProduct (int id, String propertyname) throws SQLException, ClassNotFoundException, IOException {
      return productMapper.deleteProduct(id, propertyname);
    } 
    
    @Override
    public ArrayList<Products> showSearchedProduct(String search, String attribute, String propertyname) throws SQLException, ClassNotFoundException, IOException {
        return productMapper.showSearchedProduct(search, attribute, propertyname);
    }

    @Override
    public ArrayList<Products> dbDownload(String propertyname) throws SQLException, ClassNotFoundException, IOException {
        return productMapper.dbDownload(propertyname);
    }

    @Override
    public String bulkEditPublished(String attribute, boolean changeValue, ArrayList<Products> products, String propertyname) throws SQLException, ClassNotFoundException, IOException {
        return productMapper.bulkEditPublished(attribute, changeValue, products, propertyname);
    }

    @Override
    public String bulkEditProducts(String attribute, String changeValue, ArrayList<Products> products, String propertyname) throws SQLException, ClassNotFoundException, IOException {
        return productMapper.bulkEditProducts(attribute, changeValue, products, propertyname);
    }

    @Override
    public String chartMinorCategory(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return categoryMapper.chartMinorCategory(propertyname);
    }

    @Override
    public String chartMainCategory(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return categoryMapper.chartMainCategory(propertyname);
    }

    @Override
    public String chartPublishedStatusDiagram(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return productMapper.chartPublishedStatusDiagram(propertyname);
    }

    @Override
    public String getProductCountChart(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        return productMapper.getProductCountChart(propertyname);
    }

    @Override
    public void checkOrCreateLinkminormain(int mainid, int minorid, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        categoryMapper.checkOrCreateLinkminormain(mainid, minorid, propertyname);
    }
    
    

        
}