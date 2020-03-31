/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBtest;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.DBConnection;
import static persistence.DBConnection.getConnection;

/**
 *
 * @author Frederik
 */
public class DBFacadeExtra {

    DBConnection DBConnection = new DBConnection();

    public String getCustomDataFromDB(String s) throws ClassNotFoundException, SQLException, IOException {
        String output = "";
        String sql = s;
        ResultSet result = getConnection("/dbtest.properties").prepareStatement(sql).executeQuery();

        try {
            while (result.next()) {
                output = result.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return output;
    }

    public void droptable(String propertylink) throws ClassNotFoundException, SQLException, IOException {

        String sql = "DROP TABLE if exists products;";
        String sql1 = "DROP TABLE if exists linkMinorMain;";
        String sql2 = "DROP TABLE if exists minorCategories;";
        String sql3 = "DROP TABLE if exists mainCategories;";

        try {
            PreparedStatement statement = DBConnection.getConnection(propertylink).prepareStatement(sql);
            PreparedStatement statement1 = DBConnection.getConnection(propertylink).prepareStatement(sql1);
            PreparedStatement statement2 = DBConnection.getConnection(propertylink).prepareStatement(sql2);
            PreparedStatement statement3 = DBConnection.getConnection(propertylink).prepareStatement(sql3);
            statement.executeUpdate();
            statement1.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void createtable(String propertylink) throws ClassNotFoundException, SQLException, IOException {

        String mainCategories = "CREATE TABLE mainCategories (" +
"                categoryid INTEGER not null AUTO_INCREMENT unique," +
"                mainCategoryName VARCHAR(45)," +
"                primary key (categoryid));";

        String minorCategories = "CREATE TABLE minorCategories (" +
"                categoryid INTEGER not null AUTO_INCREMENT unique," +
"                minorCategoryName VARCHAR(45)," +
"                primary key (categoryid));";

        String linkMinorMain = "CREATE TABLE linkMinorMain (" +
"                mainid INT NOT NULL," +
"                minorid INT NOT NULL UNIQUE," +
"                FOREIGN KEY (mainid) REFERENCES mainCategories(categoryid)," +
"                FOREIGN KEY (minorid) REFERENCES minorCategories(categoryid));";

        String products = "CREATE TABLE products (" +
"                productid INTEGER not null unique," +
"                name VARCHAR(45) not null," +
"                nameDescription VARCHAR(2000)," +
"                description VARCHAR(2000)," +
"                companyName VARCHAR(45)," +
"                price DOUBLE," +
"                quantity INTEGER," +
"                pictureName VARCHAR(45)," +
"                publishedStatus tinyint," +
"                minorCategory INTEGER," +
"                mainCategory INTEGER," +
"                FOREIGN KEY (mainCategory) REFERENCES mainCategories(categoryid)," +
"                FOREIGN KEY (minorCategory) REFERENCES minorCategories(categoryid));";

        try {
            PreparedStatement statement = DBConnection.getConnection(propertylink).prepareStatement(mainCategories);
            PreparedStatement statement1 = DBConnection.getConnection(propertylink).prepareStatement(minorCategories);
            PreparedStatement statement2 = DBConnection.getConnection(propertylink).prepareStatement(linkMinorMain);
            PreparedStatement statement3 = DBConnection.getConnection(propertylink).prepareStatement(products);
            statement.executeUpdate();
            statement1.executeUpdate();
            statement2.executeUpdate();
            statement3.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
