package persistence;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logic.Products;
import static persistence.DBConnection.getConnection;

public class ProductMapper implements ProductMapperInterface {

    CategoryMapper category = new CategoryMapper();

    /**
     *
     * Once the json file has been uploaded we need to add the information to
     * the database. This method will either create or update the product
     * information that is in the database. The method will also make calls to
     * other mappers in order to check if a product has already been added, if a
     * category already exists or needs to be created.
     *
     *
     * @author - Bringordie - Frederik Braagaard
     * @param list holds all the values of the products
     * @param propertyname used for checking to execute in production or test.
     */
    @Override
    public void jsonInsertOrUpdateToDB(ArrayList<Products> list, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        int ProductID;
        String ProductName;
        String ProductNameDescription;
        String ProductDescription;
        String CompanyName;
        Double Price;
        int Quantity;
        String PictureName;
        Boolean PublishedStatus;
        int MinorCategory = 0;
        int MainCategory = 0;

        for (Products products : list) {
            ProductID = products.getId();
            //BOOLEAN CHECK
            Boolean booleanIDCheck = checkIfProductExists(String.valueOf(ProductID), propertyname);
            if (booleanIDCheck == false) {

                ProductName = products.getName();
                ProductNameDescription = ifElseString(products.getNameDescription());
                ProductDescription = ifElseString(products.getDescription());
                CompanyName = ifElseString(products.getCompanyName());
                Price = products.getPrice();
                Quantity = products.getQty();
                PictureName = ifElseString(products.getPictureName());

                //Checking minorvalue
                if (products.getMinorCategory() == null) {
                    //do nothing
                } else if (category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname) == 0) {
                    int minorIDCreated = category.createMinorIDInDB(products.getMinorCategory(), propertyname);
                    MinorCategory = minorIDCreated;
                } else if (category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname) > 0) {
                    int reuseIDCreated = category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname);
                    MinorCategory = reuseIDCreated;
                } 
                //Checking mainvalue
                if (products.getMainCategory() == null) {
                    //do nothing
                } else if (category.getMainValuesFromDBFile(products.getMainCategory(), propertyname) == 0) {
                    int mainIDCreated = category.createMainIDInDB(products.getMainCategory(), propertyname);
                    MainCategory = mainIDCreated;
                } else if (category.getMainValuesFromDBFile(products.getMainCategory(), propertyname) > 0) {
                    int reuseIDCreated = category.getMainValuesFromDBFile(products.getMainCategory(), propertyname);
                    MainCategory = reuseIDCreated;
                } 

                //Requirements for publishing
                if (ProductName == null || ProductNameDescription == null || ProductDescription == null || CompanyName == null || MinorCategory == 0 || MainCategory == 0) {
                    PublishedStatus = false;
                } else {
                    PublishedStatus = true;
                }

                try {

                    String sql = "INSERT INTO products(productid, name, nameDescription, "
                            + "description, companyName, price, quantity, pictureName, "
                            + "publishedStatus, minorCategory, mainCategory)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement statement = getConnection(propertyname).prepareStatement(sql);
                    statement.setInt(1, ProductID);
                    statement.setString(2, ProductName);
                    statement.setString(3, ProductNameDescription);
                    statement.setString(4, ProductDescription);
                    statement.setString(5, CompanyName);
                    statement.setDouble(6, Price);
                    statement.setInt(7, Quantity);
                    statement.setString(8, PictureName);
                    statement.setBoolean(9, PublishedStatus);
                    if (MinorCategory == 0){
                        statement.setNull(10, Types.INTEGER);
                    } else {
                    statement.setInt(10, MinorCategory);
                    }
                    if (MainCategory == 0){
                        statement.setNull(11, Types.INTEGER);
                    } else {
                    statement.setInt(11, MainCategory);
                    }
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }
                category.checkOrCreateLinkminormain(MainCategory, MinorCategory, propertyname);
            } else {
                ProductID = products.getId();
                ProductName = products.getName();
                ProductNameDescription = ifElseString(products.getNameDescription());
                ProductDescription = ifElseString(products.getDescription());
                CompanyName = ifElseString(products.getCompanyName());
                Price = products.getPrice();
                Quantity = products.getQty();
                PictureName = ifElseString(products.getPictureName());

                //Checking minorvalue
                if (products.getMinorCategory() == null) {
                    //do nothing
                } else if (category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname) == 0) {
                    int minorIDCreated = category.createMinorIDInDB(products.getMinorCategory(), propertyname);
                    MinorCategory = minorIDCreated;
                } else if (category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname) > 0) {
                    int reuseIDCreated = category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname);
                    MinorCategory = reuseIDCreated;
                } 
                //Checking mainvalue
                if (products.getMainCategory() == null) {
                    //do nothing
                } else if (category.getMainValuesFromDBFile(products.getMainCategory(), propertyname) == 0) {
                    int mainIDCreated = category.createMainIDInDB(products.getMainCategory(), propertyname);
                    MainCategory = mainIDCreated;
                } else if (category.getMainValuesFromDBFile(products.getMainCategory(), propertyname) > 0) {
                    int reuseIDCreated = category.getMainValuesFromDBFile(products.getMainCategory(), propertyname);
                    MainCategory = reuseIDCreated;
                } 
                //Requirements for publishing
                if (ProductName == null || ProductNameDescription == null || ProductDescription == null || CompanyName == null || MinorCategory == 0 || MainCategory == 0) {
                    PublishedStatus = false;
                } else {
                    PublishedStatus = true;
                }

                try {
                    String sql = "update products set productid = ?, name= ?, "
                            + "nameDescription= ?, description= ?, companyName= ?, "
                            + "price= ?, quantity= ?, pictureName = ?, "
                            + "publishedStatus= ?, minorCategory= ?, mainCategory= ? "
                            + "where productid ='" + ProductID + "'";
                    PreparedStatement statement = getConnection(propertyname).prepareStatement(sql);
                    statement.setInt(1, ProductID);
                    statement.setString(2, ProductName);
                    statement.setString(3, ProductNameDescription);
                    statement.setString(4, ProductDescription);
                    statement.setString(5, CompanyName);
                    statement.setDouble(6, Price);
                    statement.setInt(7, Quantity);
                    statement.setString(8, PictureName);
                    statement.setBoolean(9, PublishedStatus);
                    if (MinorCategory == 0){
                        statement.setNull(10, Types.INTEGER);
                    } else {
                    statement.setInt(10, MinorCategory);
                    }
                    if (MainCategory == 0){
                        statement.setNull(11, Types.INTEGER);
                    } else {
                    statement.setInt(11, MainCategory);
                    }
                    statement.executeUpdate();

                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println(e);
                }
                category.checkOrCreateLinkminormain(MainCategory, MinorCategory, propertyname);
            }
        }
    }

    /**
     *
     * @author - Bringordie - Frederik Braagaard
     * @param string - the product logic it takes in.
     * @return Used for when uploading to set null so it doesn't show "" in db.
     */
    public String ifElseString(String string) {
        String returnvalue;
        if (string.isEmpty() || string == null || string.equals("")) {
            returnvalue = null;
        } else {
            returnvalue = string;
        }
        return returnvalue;

    }

    /**
     *
     * Once the excel file has been uploaded we need to add the information to
     * the database. This method will either create or update the product
     * information that is in the database. The method will also make calls to
     * other mappers in order to check if a product has already been added, if a
     * category already exists or needs to be created.
     *
     * @author - Bringordie - Frederik Braagaard
     * @param list holds all the values of the products
     * @param propertyname used for checking to execute in production or test.
     */
    @Override
    public void excelInsertOrUpdateToDB(ArrayList<Products> list, String propertyname) throws ClassNotFoundException, NumberFormatException, SQLException, IOException {
        int ProductID;
        String ProductName;
        String ProductNameDescription;
        String ProductDescription;
        String CompanyName;
        Double Price;
        int Quantity;
        String PictureName;
        Boolean PublishedStatus;
        int MinorCategory = 0;
        int MainCategory = 0;

        for (Products products : list) {
            ProductID = products.getId();
            //BOOLEAN CHECK
            Boolean booleanIDCheck = checkIfProductExists(String.valueOf(ProductID), propertyname);
            if (booleanIDCheck == false) {

                ProductName = products.getName();
                ProductNameDescription = ifElseString(products.getNameDescription());
                ProductDescription = ifElseString(products.getDescription());
                CompanyName = ifElseString(products.getCompanyName());
                Price = products.getPrice();
                Quantity = products.getQty();
                PictureName = products.getPictureName();
              
                //Checking minorvalue
                if (products.getMinorCategory().contains("empty")) {
                    //do nothing
                } else if (category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname) == 0) {
                    int minorIDCreated = category.createMinorIDInDB(products.getMinorCategory(), propertyname);
                    MinorCategory = minorIDCreated;
                } else if (category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname) > 0) {
                    int reuseIDCreated = category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname);
                    MinorCategory = reuseIDCreated;
                } 
                //Checking mainvalue
                if (products.getMainCategory().contains("empty")) {
                    //do nothing
                } else if (category.getMainValuesFromDBFile(products.getMainCategory(), propertyname) == 0) {
                    int mainIDCreated = category.createMainIDInDB(products.getMainCategory(), propertyname);
                    MainCategory = mainIDCreated;
                } else if (category.getMainValuesFromDBFile(products.getMainCategory(), propertyname) > 0) {
                    int reuseIDCreated = category.getMainValuesFromDBFile(products.getMainCategory(), propertyname);
                    MainCategory = reuseIDCreated;
                } 

                //Requirements for publishing
                if (ProductName == null || ProductNameDescription == null || ProductDescription == null || CompanyName == null || MinorCategory == 0 || MainCategory == 0) {
                    PublishedStatus = false;
                } else {
                    PublishedStatus = true;
                }
                try {
                    String sql = "INSERT INTO products(productid, name, nameDescription, "
                            + "description, companyName, price, quantity, pictureName, "
                            + "publishedStatus, minorCategory, mainCategory)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement statement = getConnection(propertyname).prepareStatement(sql);
                    statement.setInt(1, ProductID);
                    statement.setString(2, ProductName);
                    statement.setString(3, ProductNameDescription);
                    statement.setString(4, ProductDescription);
                    statement.setString(5, CompanyName);
                    statement.setDouble(6, Price);
                    statement.setInt(7, Quantity);
                    statement.setString(8, PictureName);
                    statement.setBoolean(9, PublishedStatus);
                    if (products.getMinorCategory().contains("empty")){
                        statement.setNull(10, Types.INTEGER);
                    } else {
                    statement.setInt(10, MinorCategory);
                    }
                    if (products.getMainCategory().contains("empty")){
                        statement.setNull(11, Types.INTEGER);
                    } else {
                    statement.setInt(11, MainCategory);
                    }
                    statement.executeUpdate();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //Check or add categories to linkminormain
                category.checkOrCreateLinkminormain(MainCategory, MinorCategory, propertyname);
            } else {
                ProductID = products.getId();
                ProductName = products.getName();
                ProductNameDescription = ifElseString(products.getNameDescription());
                ProductDescription = ifElseString(products.getDescription());
                CompanyName = ifElseString(products.getCompanyName());
                Price = products.getPrice();
                Quantity = products.getQty();
                PictureName = products.getPictureName();

                //Checking minorvalue
                if (products.getMinorCategory().contains("empty")) {
                    //do nothing
                } else if (category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname) == 0) {
                    int minorIDCreated = category.createMinorIDInDB(products.getMinorCategory(), propertyname);
                    MinorCategory = minorIDCreated;
                } else if (category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname) > 0) {
                    int reuseIDCreated = category.getMinorValuesFromDBFile(products.getMinorCategory(), propertyname);
                    MinorCategory = reuseIDCreated;
                } 
                //Checking mainvalue
                if (products.getMainCategory().contains("empty")) {
                    //do nothing
                } else if (category.getMainValuesFromDBFile(products.getMainCategory(), propertyname) == 0) {
                    int mainIDCreated = category.createMainIDInDB(products.getMainCategory(), propertyname);
                    MainCategory = mainIDCreated;
                } else if (category.getMainValuesFromDBFile(products.getMainCategory(), propertyname) > 0) {
                    int reuseIDCreated = category.getMainValuesFromDBFile(products.getMainCategory(), propertyname);
                    MainCategory = reuseIDCreated;
                } 
                //Requirements for publishing
                if (ProductName == null || ProductNameDescription == null || ProductDescription == null || CompanyName == null || MinorCategory == 0 || MainCategory == 0) {
                    PublishedStatus = false;
                } else {
                    PublishedStatus = true;
                }

                try {
                    String sql = "update products set productid = ?, name= ?, "
                            + "nameDescription= ?, description= ?, companyName= ?, "
                            + "price= ?, quantity= ?, pictureName = ?, "
                            + "publishedStatus= ?, minorCategory= ?, mainCategory= ? "
                            + "where productid ='" + ProductID + "'";
                    PreparedStatement statement = getConnection(propertyname).prepareStatement(sql);
                    statement.setInt(1, ProductID);
                    statement.setString(2, ProductName);
                    statement.setString(3, ProductNameDescription);
                    statement.setString(4, ProductDescription);
                    statement.setString(5, CompanyName);
                    statement.setDouble(6, Price);
                    statement.setInt(7, Quantity);
                    statement.setString(8, PictureName);
                    statement.setBoolean(9, PublishedStatus);
                    if (products.getMinorCategory().contains("empty")){
                        statement.setNull(10, Types.INTEGER);
                    } else {
                    statement.setInt(10, MinorCategory);
                    }
                    if (products.getMainCategory().contains("empty")){
                        statement.setNull(11, Types.INTEGER);
                    } else {
                    statement.setInt(11, MainCategory);
                    }
                    statement.executeUpdate();

                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println(e);
                }

            }
        }
    }

    /**
     *
     * Used to see if a product already exists. If it does the previous 
     * method will either update if it's an upload of a file. Or send an error
     * if it's for creating a product with an already existing productid.
     * 
     * @author - Bringordie - Frederik Braagaard
     * @param productid the id of the product
     * @param propertyname for db connection
     * @return returns true or false depending on if product exists.
     */
    @Override
    public boolean checkIfProductExists(String productid, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        Boolean returnvalue = false;
        int tempholder;
        String sql = "select COUNT(productid) from products where productid = '" + productid + "'";
        ResultSet result = getConnection(propertyname).prepareStatement(sql).executeQuery();

        try {
            while (result.next()) {
                tempholder = result.getInt(1);
                if (tempholder == 0) {
                    returnvalue = false;
                } else {
                    returnvalue = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return returnvalue;
    }

    /**
     * Used for adding a product from enter id, name, namedescription, description, companyname, price, quantity, picturename, 
     * publishstatus and what main- and minorcategories it belongs to.
     * @author ClausFindinge - Claus Mikkelsen Findinge
     * @param products - a list of prdoucts existing in the database
     * @param propertyname - for db connection
     * @return returns different messages whether the product has been added to the database or not
     */
    @Override
    public String addProduct(ArrayList<Products> products, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        String returnvalue = "";
        for (Products product : products) {
            Boolean booleanIDCheck = checkIfProductExists(String.valueOf(product.getId()), propertyname);
            if (booleanIDCheck == false) {
                returnvalue = "productadded";
                try {
                    String sql = "INSERT INTO products(productid, name, nameDescription, "
                            + "description, companyName, price, quantity, pictureName, "
                            + "publishedStatus, minorCategory, mainCategory)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement statement = getConnection((propertyname)).prepareStatement(sql);
                    statement.setInt(1, product.getId());
                    statement.setString(2, product.getName());
                    statement.setString(3, product.getNameDescription());
                    statement.setString(4, product.getDescription());
                    statement.setString(5, product.getCompanyName());
                    statement.setDouble(6, product.getPrice());
                    statement.setInt(7, product.getQty());
                    statement.setString(8, product.getPictureName());
                    statement.setBoolean(9, product.getPublishedStatus());
                    statement.setString(10, product.getMinorCategory());
                    statement.setString(11, product.getMainCategory());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } else {
                returnvalue = "alreadyexists";
            }
        }
        return returnvalue;
    }

    /**
     *
     * Used for searching for a product via it's id.
     * 
     * @author - Bringordie - Frederik Braagaard
     * @param productid the id of the product
     * @param propertyname - for connection to db
     * @return returns an arraylist of the searched product
     */
    @Override
    public ArrayList<Products> getSearchResults(int productid, String propertyname) throws ClassNotFoundException, SQLException, IOException {

        ArrayList<Products> searchResults = new ArrayList();
        String sql = "select products.*, maincategories.mainCategoryName, minorcategories.minorCategoryName "
                + "from products "
                + "inner join maincategories on products.mainCategory = maincategories.categoryid "
                + "inner join minorcategories on products.minorCategory = minorcategories.categoryid "
                + "where productid =" + productid;
        ResultSet result = getConnection(propertyname).prepareStatement(sql).executeQuery();

        try {
            while (result.next()) {
                int ProductID = result.getInt(1);
                String ProductName = result.getString(2);
                String ProductNameDescription = result.getString(3);
                String ProductDescription = result.getString(4);
                String CompanyName = result.getString(5);
                double Price = result.getDouble(6);
                int Quantity = result.getInt(7);
                String PictureName = result.getString(8);
                boolean PublishedStatus = result.getBoolean(9);
                String MainCategory = result.getString(12);
                String MinorCategory = result.getString(13);
                searchResults.add(new Products(ProductID, ProductName, ProductNameDescription, ProductDescription, CompanyName, Price, Quantity, PictureName, PublishedStatus, MinorCategory, MainCategory));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return searchResults;
    }

    /**
     *
     * Shows all the products in the database that exists.
     * It does a join on the categoryIDs in order to get the category names
     * for the user. The result.next skips 10 and 11 because these are the
     * IDs of the categories and we have no need to see these as this is only
     * used for the backend.
     * 
     * @author - Bringordie - Frederik Braagaard
     * @param propertyname - for db connection
     * @return returns an arraylist of all the products from the db
     */
    @Override
    public ArrayList<Products> showAllProducts(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        ArrayList<Products> searchResults = new ArrayList();
        //String sql = "SELECT * FROM products ORDER BY minorCategory ASC";
        String sql = "select products.*, maincategories.mainCategoryName, "
                + "minorcategories.minorCategoryName from products inner join "
                + "maincategories on products.mainCategory = maincategories.categoryid "
                + "inner join minorcategories on products.minorCategory "
                + "= minorcategories.categoryid ORDER BY minorcategories.minorCategoryName ASC";
        ResultSet result = getConnection(propertyname).prepareStatement(sql).executeQuery();

        try {
            while (result.next()) {
                int ProductID = result.getInt(1);
                String ProductName = result.getString(2);
                String ProductNameDescription = result.getString(3);
                String ProductDescription = result.getString(4);
                String CompanyName = result.getString(5);
                double Price = result.getDouble(6);
                int Quantity = result.getInt(7);
                String PictureName = result.getString(8);
                boolean PublishedStatus = result.getBoolean(9);
                //Skipping 2 because of join
                String MainCategory = result.getString(12);
                String MinorCategory = result.getString(13);
                searchResults.add(new Products(ProductID, ProductName, ProductNameDescription, ProductDescription, CompanyName, Price, Quantity, PictureName, PublishedStatus, MinorCategory, MainCategory));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return searchResults;
    }

    /**
     *
     * Used for editing a product from it's id. 
     * 
     * @author - Bringordie - Frederik Braagaard
     * @param id - the product id
     * @param product - the product
     * @param propertyname - for db connection
     */
    @Override
    public void editProduct(int id, Products product, String propertyname) throws ClassNotFoundException, SQLException, IOException {

        try {
            {
                String sql = ("UPDATE products SET productid = (?), name = (?), nameDescription= (?), "
                        + "description= (?), companyName= (?), price= (?), quantity= (?), pictureName= (?), "
                        + "publishedStatus= (?), minorCategory= (?), mainCategory= (?) WHERE (`productid` = '" + id + "');");
                PreparedStatement statement = getConnection((propertyname)).prepareStatement(sql);
                statement.setInt(1, product.getId());
                statement.setString(2, product.getName());
                statement.setString(3, product.getNameDescription());
                statement.setString(4, product.getDescription());
                statement.setString(5, product.getCompanyName());
                statement.setDouble(6, product.getPrice());
                statement.setInt(7, product.getQty());
                statement.setString(8, product.getPictureName());
                statement.setBoolean(9, product.getPublishedStatus());
                statement.setString(10, product.getMinorCategory());
                statement.setString(11, product.getMainCategory());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Used for deleting a product from it's id.
     * @author ClausFindinge - Claus Mikkelsen Findinge
     * @param id - the id of the product you want to delete
     * @param propertyname - for db connection
     * @return returns different messages whether the product has been deleted or not.
     */
    @Override
    public String deleteProduct(int id, String propertyname) throws ClassNotFoundException, SQLException, IOException {

        String returnvalue = "";
        Boolean booleanIDCheck = checkIfProductExists((Integer.toString(id)), propertyname);
        if (booleanIDCheck == true) {

            try {
                String sql = "DELETE FROM products WHERE productid = ?";
                PreparedStatement statement = getConnection((propertyname)).prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
                returnvalue = "deleteproduct";

            } catch (SQLException e) {
                System.out.println(e);
            }
            return returnvalue;
        }
        returnvalue = "deletealreadyexists";
        return returnvalue;
    }

    /**
     *
     * Shows an array of product(s) from a wildcard that has been searched for.
     * It can contain 0-x depending on the ammount of matches.
     * 
     * @author - Bringordie - Frederik Braagaard
     * @param search - search input value
     * @param attribute - search value from dropdown
     * @param propertyname - for db connection
     * @return - shows a wildcard arraylist search of all the products that
     * matches.
     */
    @Override
    public ArrayList<Products> showSearchedProduct(String search, String attribute, String propertyname) throws ClassNotFoundException, SQLException, IOException {
        ArrayList<Products> searchResults = new ArrayList();
        String sql = "select products.*, maincategories.mainCategoryName, "
                + "minorcategories.minorCategoryName from products inner join "
                + "maincategories on products.mainCategory = maincategories.categoryid "
                + "inner join minorcategories on products.minorCategory "
                + "= minorcategories.categoryid where " + attribute + " LIKE '%" + search + "%'";
        ResultSet result = getConnection(propertyname).prepareStatement(sql).executeQuery();

        try {
            while (result.next()) {
                int ProductID = result.getInt(1);
                String ProductName = result.getString(2);
                String ProductNameDescription = result.getString(3);
                String ProductDescription = result.getString(4);
                String CompanyName = result.getString(5);
                double Price = result.getDouble(6);
                int Quantity = result.getInt(7);
                String PictureName = result.getString(8);
                boolean PublishedStatus = result.getBoolean(9);
                //Skipping 2 because of join
                String MinorCategory = result.getString(12);
                String MainCategory = result.getString(13);
                searchResults.add(new Products(ProductID, ProductName, ProductNameDescription, ProductDescription, CompanyName, Price, Quantity, PictureName, PublishedStatus, MinorCategory, MainCategory));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return searchResults;
    }

    /**
     *
     * When we need to download the content of the database this method is used
     * for selecting all with a join again and result.get 10/11 is skipped as 
     * the document we download should consist of readable data so not binary.
     * 
     * @author - Bringordie - Frederik Braagaard
     * @param propertyname - for db connection
     * @return - returns all products as an arraylist used for Json download
     */
    @Override
    public ArrayList<Products> dbDownload(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        ArrayList<Products> searchResults = new ArrayList();
        String sql = "select products.*, maincategories.mainCategoryName, "
                + "minorcategories.minorCategoryName from products inner join "
                + "maincategories on products.mainCategory = maincategories.categoryid "
                + "inner join minorcategories on products.minorCategory "
                + "= minorcategories.categoryid ORDER BY minorcategories.minorCategoryName ASC";
        ResultSet result = getConnection(propertyname).prepareStatement(sql).executeQuery();

        try {
            while (result.next()) {
                int ProductID = result.getInt(1);
                String ProductName = result.getString(2);
                String ProductNameDescription = result.getString(3);
                String ProductDescription = result.getString(4);
                String CompanyName = result.getString(5);
                double Price = result.getDouble(6);
                int Quantity = result.getInt(7);
                String PictureName = result.getString(8);
                //This one will just be ignored.
                boolean PublishedStatus = result.getBoolean(9);
                //Skipping 2 because of join
                String MainCategory = result.getString(12);
                String MinorCategory = result.getString(13);
                searchResults.add(new Products(ProductID, ProductName, ProductNameDescription, ProductDescription, CompanyName, Price, Quantity, PictureName, PublishedStatus, MinorCategory, MainCategory));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return searchResults;
    }

    /**
     *
     * @author - Malthe
     */
    @Override
    public String bulkEditProducts(String attribute, String changeValue, ArrayList<Products> products, String propertyname)
            throws ClassNotFoundException, SQLException, IOException {

        String callback = "";
        try {
            for (Products product : products) {
                String sql = ("UPDATE products SET " + attribute + " = '" + changeValue + "' WHERE productid = " + product.getId());
                PreparedStatement statement = getConnection((propertyname)).prepareStatement(sql);
                statement.executeUpdate();
                callback = "success";
            }
        } catch (SQLException e) {
            System.out.println(e);
            callback = "error";
        }
        return callback;
    }

    /**
     *
     * @author - Malthe
     */
    @Override
    public String bulkEditPublished(String attribute, boolean changeValue, ArrayList<Products> products, String propertyname)
            throws ClassNotFoundException, SQLException, IOException {

        String callback = "";
        try {
            for (Products product : products) {
                String sql = ("UPDATE products SET " + attribute + " = " + changeValue + " WHERE productid = " + product.getId());
                PreparedStatement statement = getConnection((propertyname)).prepareStatement(sql);
                statement.executeUpdate();
                callback = "success";
            }
        } catch (SQLException e) {
            System.out.println(e);
            callback = "error";
        }
        return callback;
    }
        
    /**
     *
     * Used for displaying the count of published and non published products
     * in a chart on the index page.
     * 
     * @author - Bringordie - Frederik Braagaard
     * @param propertyname used for DB access
     * @return returns and gson.Obj.toJson as this is how the charts reads data.
     */
    @Override
    public String chartPublishedStatusDiagram(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        Gson gsonObj = new Gson();
        HashMap<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        int total = getProductCount(propertyname);
        String sqlfalse = "select COUNT(publishedStatus) from products where publishedStatus = false";
        String sqltrue = "select COUNT(publishedStatus) from products where publishedStatus = true";
        ResultSet result = getConnection(propertyname).prepareStatement(sqlfalse).executeQuery();

        try {
            while (result.next()) {
                map = new HashMap<Object, Object>();
                map.put("x", 10);
                map.put("y", (result.getInt(1)));
                map.put("indexLabel", "Not ready for publish");
                list.add(map);
            }
        } catch (SQLException e) {
            throw e;
        }
        
        ResultSet result2 = getConnection(propertyname).prepareStatement(sqltrue).executeQuery();
        try {
            while (result2.next()) {
                map = new HashMap<Object, Object>();
                map.put("x", 20);
                map.put("y", (result2.getInt(1)));
                map.put("indexLabel", "Ready for publish");
                list.add(map);
            }
        } catch (SQLException e) {
            throw e;
        }
        String publishedStatus = gsonObj.toJson(list);
        return publishedStatus;
    }
    
    /**
     *
     * Gets the product count. Used for chartPublishedStatusDiagram.
     * 
     * @author - Bringordie - Frederik Braagaard
     * @param propertyname used for DB Connection.
     * @return retuns the count of all products.
     */
    @Override
    public int getProductCount(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        int total = 0;
        String sql = "select COUNT(productid) from products";
        ResultSet result = getConnection(propertyname).prepareStatement(sql).executeQuery();
        try {
            while (result.next()) {
                total += result.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }
    
    /**
     *
     * Used for displaying the count of products that the firm has in stock.
     * Used for the chart on the index page.
     * 
     * @author - Bringordie - Frederik Braagaard
     */
    @Override
    public String getProductCountChart(String propertyname) throws ClassNotFoundException, SQLException, IOException {
        Gson gsonObj = new Gson();
        HashMap<Object, Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
        String sql = "select COUNT(productid) from products where quantity > 0";
        ResultSet result = getConnection(propertyname).prepareStatement(sql).executeQuery();
        try {
            while (result.next()) {
                map = new HashMap<Object, Object>();
                map.put("x", 10);
                map.put("y", (result.getInt(1)));
                map.put("indexLabel", "Total of products");
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        String allProducts = gsonObj.toJson(list);
        return allProducts;
    }

}
