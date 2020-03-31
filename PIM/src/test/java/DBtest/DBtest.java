package DBtest;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.Categories;
import logic.ExcelHandler;
import logic.JsonHandler;
import logic.Products;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import persistence.CategoryMapper;
import persistence.DBConnection;
import persistence.DBFacade;
import persistence.ProductMapper;

public class DBtest {

    DBFacadeExtra dbextra = new DBFacadeExtra();
    DBFacade dbfacade = new DBFacade();
    ProductMapper productmapper = new ProductMapper();
    CategoryMapper categorymapper = new CategoryMapper();
    JsonHandler jsonhandler = new JsonHandler();
    ExcelHandler excelhandler = new ExcelHandler();
    String DBPROPERTYTEST = "/dbtest.properties";
    DBConnection connection = new DBConnection();

    @Before
    public void setUp() throws SQLException, ClassNotFoundException, IOException {
        dbextra.droptable(DBPROPERTYTEST);
        dbextra.createtable(DBPROPERTYTEST);

    }

    @Test
    public void uploadOfExcelUploadEmptyRow() throws SQLException, ClassNotFoundException, IOException, IOException {
        String fileName = "src/test/java/files/linewithemptyrow.xlsx";
        ArrayList<Products> product = excelhandler.extractInfo(fileName);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);

        int expected = 1;
        String actually = dbextra.getCustomDataFromDB("select COUNT(name) from products;");

        assertEquals(expected, Integer.parseInt(actually));

        String expectedrequired = "0";
        String actuallyrequired = dbextra.getCustomDataFromDB("select publishedStatus from products;");
        assertEquals(expectedrequired, actuallyrequired);

    }

    @Test
    public void uploadOfExcelUpload() throws SQLException, ClassNotFoundException, IOException, IOException {
        String fileName = "src/test/java/files/linewithoutemptyrow.xlsx";
        ArrayList<Products> product = excelhandler.extractInfo(fileName);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);

        String expected = "1";
        String actually = dbextra.getCustomDataFromDB("select publishedStatus from products;");
        assertEquals(expected, actually);

    }

    @Test
    public void updateOfExcelUpload() throws SQLException, ClassNotFoundException, IOException, IOException {
        String fileName = "src/test/java/files/linewithoutemptyrow.xlsx";
        ArrayList<Products> product = excelhandler.extractInfo(fileName);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);

        String expected = "1";
        String actually = dbextra.getCustomDataFromDB("select publishedStatus from products;");
        assertEquals(expected, actually);

    }

    @Test
    public void creationOfLinkedMMOfExcelUpload() throws SQLException, ClassNotFoundException, IOException, IOException {
        String fileName = "src/test/java/files/linewithoutemptyrow.xlsx";
        ArrayList<Products> product = excelhandler.extractInfo(fileName);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);
        String expectedminor = "1";
        String expectedmain = "1";

        String actuallymain = dbextra.getCustomDataFromDB("select mainid from linkMinorMain;");
        assertEquals(expectedmain, actuallymain);

        String actuallyminor = dbextra.getCustomDataFromDB("select minorid from linkMinorMain;");
        assertEquals(expectedminor, actuallyminor);

    }

    @Test
    public void uploadOfJsonUpload() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException {
        String fileName = "src/test/java/files/singledata.json";
        ArrayList<Products> product = jsonhandler.makeJSonFileIntoArray(fileName);
        dbfacade.jsonInsertOrUpdateToDB(product, DBPROPERTYTEST);

        int expected = 1;
        String dbcall = dbextra.getCustomDataFromDB("select COUNT(name) from products;");

        assertEquals(expected, Integer.parseInt(dbcall));

    }
    
    @Test
    public void uploadOfJsonUploadReuseCategory() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException {
        String fileName = "src/test/java/files/sameminorandmain.json";
        ArrayList<Products> product = jsonhandler.makeJSonFileIntoArray(fileName);
        dbfacade.jsonInsertOrUpdateToDB(product, DBPROPERTYTEST);

        int expected = 2;
        String dbcall = dbextra.getCustomDataFromDB("select COUNT(name) from products;");

        assertEquals(expected, Integer.parseInt(dbcall));

    }
    
    @Test
    public void updateOfJsonUpload() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException {
        String fileName = "src/test/java/files/singledata.json";
        ArrayList<Products> product = jsonhandler.makeJSonFileIntoArray(fileName);
        dbfacade.jsonInsertOrUpdateToDB(product, DBPROPERTYTEST);
        dbfacade.jsonInsertOrUpdateToDB(product, DBPROPERTYTEST);
        dbfacade.jsonInsertOrUpdateToDB(product, DBPROPERTYTEST);
        dbfacade.jsonInsertOrUpdateToDB(product, DBPROPERTYTEST);

        int expected = 1;
        String dbcall = dbextra.getCustomDataFromDB("select COUNT(name) from products;");

        assertEquals(expected, Integer.parseInt(dbcall));

    }

    @Test
    public void emptyRowIsNullExcel() throws SQLException, ClassNotFoundException, IOException, IOException {
        String fileName = "src/test/java/files/linewithemptyrow.xlsx";
        ArrayList<Products> product = excelhandler.extractInfo(fileName);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);

        String description = dbextra.getCustomDataFromDB("select description from products where productid = 8337");

        assertNull(description);

    }
    
    @Test
    public void excelReuseCategoryID() throws SQLException, ClassNotFoundException, IOException, IOException {
        String fileName = "src/test/java/files/categoryidreuse.xlsx";
        ArrayList<Products> product = excelhandler.extractInfo(fileName);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);

        String minorcategory = dbextra.getCustomDataFromDB("select minorCategory from products where productid = 5000;");
        String maincategory = dbextra.getCustomDataFromDB("select mainCategory from products where productid = 5000;");
        
        assertEquals("1", minorcategory);
        assertEquals("1", maincategory);

    }
    
    @Test
    public void excelEmptyCategory() throws SQLException, ClassNotFoundException, IOException, IOException {
        String fileName = "src/test/java/files/nocategory.xlsx";
        ArrayList<Products> product = excelhandler.extractInfo(fileName);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);
        dbfacade.excelInsertOrUpdateToDB(product, DBPROPERTYTEST);

        String minorcategory = dbextra.getCustomDataFromDB("select minorCategory from products where productid = 3280;");
        String maincategory = dbextra.getCustomDataFromDB("select mainCategory from products where productid = 3280;");
        
        assertEquals(null, minorcategory);
        assertEquals(null, maincategory);

    }

    @Test
    public void creationOfMainCategory() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Frost", DBPROPERTYTEST);

        String dbcall = dbextra.getCustomDataFromDB("select mainCategoryName from mainCategories");

        assertEquals("Frost", dbcall);

    }

    @Test
    public void creationOfMinorCategory() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Salat", DBPROPERTYTEST);

        String dbcall = dbextra.getCustomDataFromDB("select minorCategoryName from minorCategories");

        assertEquals("Salat", dbcall);

    }

    @Test
    public void deletionOfMainCategory() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Salat", DBPROPERTYTEST);

        String dbcall = dbextra.getCustomDataFromDB("select COUNT(mainCategoryName) from mainCategories");
        String expected = "1";
        assertEquals(dbcall, expected);

        dbfacade.deleteMainCategory(1, DBPROPERTYTEST);
        String dbcall2 = dbextra.getCustomDataFromDB("select COUNT(mainCategoryName) from mainCategories");
        String expectednow = "0";
        assertEquals(expectednow, dbcall2);
    }

    @Test
    public void deletionOfMinorCategory() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Salat", DBPROPERTYTEST);

        String dbcall = dbextra.getCustomDataFromDB("select COUNT(minorCategoryName) from minorCategories");
        String expected = "1";
        assertEquals(dbcall, expected);

        dbfacade.deleteMinorCategory(1, DBPROPERTYTEST);
        String dbcall2 = dbextra.getCustomDataFromDB("select COUNT(minorCategoryName) from minorCategories");
        String expectednow = "0";
        assertEquals(expectednow, dbcall2);
    }

    @Test
    public void editMainCategory() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Oksekød", DBPROPERTYTEST);

        dbfacade.editMainCategory(1, "Svinekød", DBPROPERTYTEST);
        String dbcall = dbextra.getCustomDataFromDB("select mainCategoryName from mainCategories");
        String expected = "Svinekød";
        assertEquals(expected, dbcall);
    }

    @Test
    public void editMinorCategory() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Salat", DBPROPERTYTEST);
        dbfacade.editMinorCategory(1, "Frugt", DBPROPERTYTEST);
        String dbcall = dbextra.getCustomDataFromDB("select minorCategoryName from minorCategories");
        String expected = "Frugt";
        assertEquals(expected, dbcall);
    }

    @Test
    public void addProductManually() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Prudctname", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        String dbcall = dbextra.getCustomDataFromDB("select COUNT(productid) from products;");
        String expected = "1";
        assertEquals(expected, dbcall);
    }

    @Test
    public void getMainCategories() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Salat", DBPROPERTYTEST);
        dbfacade.addMainCategory("Frugt og Grønt", DBPROPERTYTEST);
        dbfacade.addMainCategory("Is", DBPROPERTYTEST);

        String expected = "3";
        String dbcall = dbextra.getCustomDataFromDB("select COUNT(mainCategoryName) from maincategories;");

        assertEquals(expected, dbcall);
    }

    @Test
    public void getMinorCategories() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Drikkevarer", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Kød", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Slik", DBPROPERTYTEST);

        String expected = "4";
        String dbcall = dbextra.getCustomDataFromDB("select COUNT(minorCategoryName) from minorCategories;");

        assertEquals(expected, dbcall);
    }

    @Test
    public void searchProductByID() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Grønne æbler", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(55, "Test1", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(35, "Test2", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(500, "Test3", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        String expected = "Grønne æbler";
        ArrayList<Products> dbcall = dbfacade.searchProduct(34, DBPROPERTYTEST);
        String actually = "";
        for (Products productsdb : dbcall) {
            actually = productsdb.getName();
        }

        assertEquals(expected, actually);
    }

    @Test
    public void showAllProducts() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Gulerødder øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(35, "Solsikkeskud øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(55, "Radisemix øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(500, "Kaki frugter", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        ArrayList<Products> dbcall = dbfacade.showAllProducts(DBPROPERTYTEST);

        String productnames = "";
        final StringBuilder builder = new StringBuilder();
        String expected = "Gulerødder øko.+Solsikkeskud øko.+Radisemix øko.+Kaki frugter+";
        for (Products productsdb : dbcall) {
            builder.append(productsdb.getName() + "+");
        }
        String concatenatedString = builder.toString();
        assertEquals(expected, concatenatedString);
    }

    @Test
    public void checkOrCreateLinkedMM() throws SQLException, ClassNotFoundException, IOException, IOException {
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMainCategory("Økologisk", DBPROPERTYTEST);

        dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Drikkevarer", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Kød", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Slik", DBPROPERTYTEST);

        categorymapper.checkOrCreateLinkminormain(2, 3, DBPROPERTYTEST);
        categorymapper.checkOrCreateLinkminormain(2, 3, DBPROPERTYTEST);
        categorymapper.checkOrCreateLinkminormain(2, 3, DBPROPERTYTEST);
        categorymapper.checkOrCreateLinkminormain(2, 3, DBPROPERTYTEST);

        String expected = "1";
        String actually = dbextra.getCustomDataFromDB("select COUNT(minorid) from linkMinorMain;");

        assertEquals(expected, actually);
    }

    @Test
    public void checkCreationOfSeveralLinkMM() throws SQLException, ClassNotFoundException, IOException, IOException {
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMainCategory("Økologisk", DBPROPERTYTEST);

        dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Drikkevarer", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Kød", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Slik", DBPROPERTYTEST);

        categorymapper.checkOrCreateLinkminormain(2, 3, DBPROPERTYTEST);
        categorymapper.checkOrCreateLinkminormain(1, 1, DBPROPERTYTEST);
        categorymapper.checkOrCreateLinkminormain(2, 2, DBPROPERTYTEST);
        categorymapper.checkOrCreateLinkminormain(1, 4, DBPROPERTYTEST);

        String expected = "4";
        String actually = dbextra.getCustomDataFromDB("select COUNT(minorid) from linkMinorMain;");

        assertEquals(expected, actually);
    }

    @Test
    public void editProduct() throws SQLException, ClassNotFoundException, IOException {

        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(69, "Agurk", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        dbfacade.editProduct(69, new Products(69, "Squash", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"), DBPROPERTYTEST);
        String dbcall = dbextra.getCustomDataFromDB("SELECT name FROM products WHERE productid = 69");
        String expected = "Squash";
        assertEquals(expected, dbcall);
    }

    @Test
    public void deleteProduct() throws SQLException, ClassNotFoundException, IOException {

        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(69, "Agurk", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        String dbcall = dbextra.getCustomDataFromDB("select COUNT(name) from products");
        String expected = "1";
        assertEquals(expected, dbcall);

        dbfacade.deleteProduct(69, DBPROPERTYTEST);
        String dbcall2 = dbextra.getCustomDataFromDB("select COUNT(name) from products");
        String expected2 = "0";
        assertEquals(expected2, dbcall2);
    }

    @Test
    public void showSearchedProduct() throws SQLException, ClassNotFoundException, IOException {

        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Gulerødder øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(35, "Solsikkeskud øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(55, "Radisemix øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(500, "Kaki frugter", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        ArrayList<Products> dbcall = dbfacade.showSearchedProduct("5", "productid", DBPROPERTYTEST);
        int actual = dbcall.size();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void dbDownload() throws SQLException, ClassNotFoundException, IOException {

        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(69, "Agurk", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        ArrayList<Products> dbcall = dbfacade.dbDownload(DBPROPERTYTEST);
        String expected = "Frugt";
        String actual = "";
        for (Products productsdb : dbcall) {

            actual = productsdb.getMainCategory();
        }

        assertEquals(expected, actual);
    }

    @Test
    public void bulkEditProducts() throws SQLException, ClassNotFoundException, IOException {

        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Gulerødder øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(35, "Solsikkeskud øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(55, "Radisemix øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(500, "Kaki frugter", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        dbfacade.bulkEditProducts("price", "29.99", products, DBPROPERTYTEST);
        String dbcall = dbextra.getCustomDataFromDB("SELECT COUNT(name) FROM products WHERE price = 29.99");
        String expected = "4";
        assertEquals(expected, dbcall);

        String dbcall2 = dbfacade.bulkEditProducts("price", "hejsa", products, DBPROPERTYTEST);
        String expected2 = "error";
        assertEquals(expected2, dbcall2);
    }

    @Test
    public void bulkEditPublished() throws SQLException, ClassNotFoundException, IOException {

        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Gulerødder øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(35, "Solsikkeskud øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(55, "Radisemix øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(500, "Kaki frugter", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        String dbcall = dbextra.getCustomDataFromDB("SELECT COUNT(name) FROM products WHERE publishedStatus = false");
        String expected = "4";
        assertEquals(expected, dbcall);

        dbfacade.bulkEditPublished("publishedStatus", true, products, DBPROPERTYTEST);
        String dbcall2 = dbextra.getCustomDataFromDB("SELECT COUNT(name) FROM products WHERE publishedStatus = true");
        String expected2 = "4";
        assertEquals(expected2, dbcall2);
    }

    @Test
    public void chartMinorCategory() throws SQLException, ClassNotFoundException, IOException {
        Gson gsonObj = new Gson();
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Gulerødder øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);
        String returnvalue = dbfacade.chartMinorCategory(DBPROPERTYTEST);
        
        HashMap<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        map = new HashMap<Object, Object>();
        map.put("label", "Økologisk");
        map.put("y", "100");
        list.add(map);
        
        String expected = gsonObj.toJson(list);


        
        assertEquals(expected, returnvalue);
        assertNotNull(returnvalue);
    }

    @Test
    public void chartMainCategory() throws SQLException, ClassNotFoundException, IOException {
        Gson gsonObj = new Gson();
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMainCategory("Grønt", DBPROPERTYTEST);
        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Gulerødder øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(35, "Solsikkeskud øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(55, "Radisemix øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "2"));
        products.add(new Products(500, "Kaki frugter", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "2"));
        dbfacade.addProduct(products, DBPROPERTYTEST);
        String returnvalue = dbfacade.chartMainCategory(DBPROPERTYTEST);
        
        HashMap<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        map = new HashMap<Object, Object>();
        map.put("label", "Frugt");
        map.put("y", "50");
        list.add(map);
        map = new HashMap<Object, Object>();
        map.put("label", "Grønt");
        map.put("y", "50");
        list.add(map);
        String expected = gsonObj.toJson(list);
        
        assertEquals(expected, returnvalue);
        assertNotNull(returnvalue);
    }
    
    @Test
    public void chartPublishedStatusDiagram() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);

        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Gulerødder øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(35, "Solsikkeskud øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(55, "Radisemix øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(500, "Kaki frugter", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);

        String returnvalue = dbfacade.chartPublishedStatusDiagram(DBPROPERTYTEST);

        assertNotNull(returnvalue);
    }
    
    @Test
    public void getProductCountChart() throws SQLException, ClassNotFoundException, IOException {
        Gson gsonObj = new Gson();
        dbfacade.addMainCategory("Frugt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Økologisk", DBPROPERTYTEST);

        ArrayList<Products> products = new ArrayList();
        products.add(new Products(34, "Gulerødder øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(35, "Solsikkeskud øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", false, "1", "1"));
        products.add(new Products(55, "Radisemix øko.", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        products.add(new Products(500, "Kaki frugter", "Productnamedescription", "productdescription", "companyname", 34, 50, "picturename", true, "1", "1"));
        dbfacade.addProduct(products, DBPROPERTYTEST);
        HashMap<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        map = new HashMap<Object, Object>();
        map.put("x", 10);
        map.put("y", 4);
        map.put("indexLabel", "Total of products");
        list.add(map);
        String expected = gsonObj.toJson(list);
        String returnvalue = dbfacade.getProductCountChart(DBPROPERTYTEST);

        assertNotNull(returnvalue);
        assertEquals(expected, returnvalue);
    }
    
    @Test
    public void creationOfDuplicateMainCategory() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Frost", DBPROPERTYTEST);
        String response = dbfacade.addMainCategory("Frost", DBPROPERTYTEST);
        assertEquals("Category already exists!", response);
    }
    
     @Test
    public void creationOfDuplicateMinorCategory() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        String response = dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        assertEquals("Category already exists!", response);
    }
    
    @Test
    public void getMinorCategoriesFromDB() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        String response = dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        ArrayList<Categories> dbreturn = dbfacade.getMinorCategories(DBPROPERTYTEST);
        String actually = "";
        for (Categories categories : dbreturn) {
            actually = categories.getName();
        }
        
        assertEquals("Frost", actually);
    }
    
    @Test
    public void getMainCategoriesFromDB() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        String response = dbfacade.addMainCategory("Frost", DBPROPERTYTEST);
        ArrayList<Categories> dbreturn = dbfacade.getMainCategories(DBPROPERTYTEST);
        String actually = "";
        for (Categories categories : dbreturn) {
            actually = categories.getName();
        }
                
        assertEquals("Frost", actually);
    }
    
    @Test
    public void getMinorCategoriesID() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Grønt", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Oksekød", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Ris", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Pasta", DBPROPERTYTEST);
        
        int actually = dbfacade.getMinorCategoriesID("Pasta", DBPROPERTYTEST);
        
        assertEquals(5, actually);
        
    }
    
    @Test
    public void getMainCategoriesID() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMainCategory("Frost", DBPROPERTYTEST);
        dbfacade.addMainCategory("Grønt", DBPROPERTYTEST);
        dbfacade.addMainCategory("Oksekød", DBPROPERTYTEST);
        dbfacade.addMainCategory("Ris", DBPROPERTYTEST);
        dbfacade.addMainCategory("Pasta", DBPROPERTYTEST);
        
        int actually = dbfacade.getMainCategoriesID("Ris", DBPROPERTYTEST);
        
        assertEquals(4, actually);
    }
    
    @Test
    public void checkOrCreateLinkminormain() throws SQLException, ClassNotFoundException, IOException {
        dbfacade.addMinorCategory("Frost", DBPROPERTYTEST);
        dbfacade.addMinorCategory("Grønt", DBPROPERTYTEST);
        dbfacade.addMainCategory("Frost", DBPROPERTYTEST);
        dbfacade.addMainCategory("Grønt", DBPROPERTYTEST);
        
        dbfacade.checkOrCreateLinkminormain(1, 1, DBPROPERTYTEST);
        dbfacade.checkOrCreateLinkminormain(1, 1, DBPROPERTYTEST);
        
        String dbcall = dbextra.getCustomDataFromDB("select COUNT(mainid) from linkMinorMain;");
        
        assertEquals("1", dbcall);
    }
           
}



