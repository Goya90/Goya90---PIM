package persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.Categories;


public interface CategoryMapperInterface {
    
    public String addMainCategory (String category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String addMinorCategory (String category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
        
    public void deleteMainCategory (int category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void deleteMinorCategory (int category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void editMainCategory (int categoryInt, String categoryStr, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void editMinorCategory (int categoryInt, String categoryStr, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Categories> getMinorValuesFromDB(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public ArrayList<Categories> getMainValuesFromDB(String propertyname) throws ClassNotFoundException, SQLException, IOException;
        
    public int getMinorValuesFromDBFile(String minorname, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public int getMainValuesFromDBFile(String mainname, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public int createMainIDInDB(String mainname, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public int createMinorIDInDB(String minorname, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public void checkOrCreateLinkminormain(int mainid, int minorid, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public Boolean checkMainCategory(String category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public Boolean checkMinorCategory(String category, String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public int getMainAmount(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String chartMainCategory(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public int getMinorAmount(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    public String chartMinorCategory(String propertyname) throws ClassNotFoundException, SQLException, IOException;
    
    
}
